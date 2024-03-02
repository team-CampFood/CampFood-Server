package com.campfood.config.data;

import com.campfood.src.store.dto.request.StoreUpdateDTO;
import com.campfood.src.store.entity.*;
import com.campfood.src.store.repository.StoreCategoryRepository;
import com.campfood.src.store.repository.StoreOpenTimeRepository;
import com.campfood.src.store.repository.StoreRepository;
import com.campfood.src.store.repository.StoreUniversityRepository;
import com.campfood.src.university.entity.University;
import com.campfood.src.university.repository.UniversityRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreCategoryRepository storeCategoryRepository;
    @Autowired
    private StoreOpenTimeRepository storeOpenTimeRepository;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private StoreUniversityRepository storeUniversityRepository;

    @Value("${excel.data.path}")
    private String excelPath;

    @PostConstruct
    public void init() {
        scheduledParseExcelFile();;
    }

    @Transactional
    public void parseExcelFile(String filePath) {
        try (FileInputStream excelFile = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(excelFile)) {

            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                Store store = storeRepository.findByIdentificationId(checkType(currentRow.getCell(2)))
                                .orElse(Store.builder()
                                        .identificationId(checkType(currentRow.getCell(2)))
                                        .build());
                System.out.println(store.getName());

                store.updateStore(
                        StoreUpdateDTO.builder()
                                .name(checkType(currentRow.getCell(1)))
                                .identificationId(checkType(currentRow.getCell(2)))
                                .rate(Double.parseDouble(checkType(currentRow.getCell(3))))
                                .visitedReview(Integer.parseInt(checkType(currentRow.getCell(4))))
                                .blogReview(Integer.parseInt(checkType(currentRow.getCell(5))))
                                .address(checkType(currentRow.getCell(6)))
                                .storeNumber(checkType(currentRow.getCell(7)))
                                .latitude(checkType(currentRow.getCell(8)))
                                .longitude(checkType(currentRow.getCell(9)))
                                .build()
                );

                Store savedStore = storeRepository.save(store);

                // 가게 대학교 저장
                String universityName = checkType(currentRow.getCell(10));
                University university = universityRepository.findByName(universityName)
                        .orElseThrow(); //TODO: 대학교명 없는 경우 예외처리

                if (!storeUniversityRepository.existsByUniversityAndStore(university, savedStore)) {
                    // db에 없는 경우
                    storeUniversityRepository.save(
                            StoreUniversity.builder()
                                    .store(savedStore)
                                    .university(university)
                                    .build());
                }

                // 가게 카테고리 저장
                String[] categories = toCategories(checkType(currentRow.getCell(11)));

                List<Category> updatedCategories = new ArrayList<>();

                for (String categoryStr : categories) {
                    // 공백 제거
                    categoryStr = categoryStr.trim();

                    System.out.println(savedStore.getName());

                    // Category 열겨형에서 해당하는 값 찾기
                    String finalCategoryStr = categoryStr;
                    Category category = Arrays.stream(Category.values())
                            .filter(c -> c.getToKorean().equals(finalCategoryStr))
                            .findFirst()
                            .orElseThrow(); //TODO: 카테고리르 찾지 못한 경우 예외처리

                    // 찾은 Category 값으로 StoreCategory 생성 또는 업데이트
                    StoreCategory storeCategory = storeCategoryRepository.findByStoreAndCategory(savedStore, category)
                            .orElseGet(() -> {
                                StoreCategory newStoreCategory = StoreCategory.builder()
                                        .category(category)
                                        .store(savedStore)
                                        .build();
                                return newStoreCategory;
                            });

                    storeCategoryRepository.save(storeCategory);  // StoreCategory 저장
                    updatedCategories.add(category);
                }

                // 삭제된 카테고리 삭제
                List<StoreCategory> existingStoreCategories = storeCategoryRepository.findAllByStore(savedStore);
                for (StoreCategory existingStoreCategory : existingStoreCategories) {
                    if (!updatedCategories.contains(existingStoreCategory.getCategory())) {
                        storeCategoryRepository.delete(existingStoreCategory);
                        existingStoreCategories.remove(existingStoreCategory);
                    }
                }

                // 가게 운영시간 저장
                OpenDay[] openDays = OpenDay.values();

                for (int idx = 12; idx < 19; idx++) {
                    String data = checkType(currentRow.getCell(idx));
                    if (!data.isEmpty()) {

                        // 요일별로 StoreOpenTime 엔티티 생성 또는 수정
                        OpenDay openDay = openDays[idx - 12];
                        StoreOpenTime storeOpenTime = storeOpenTimeRepository.findByStoreAndDay(savedStore, openDay)
                                .orElseGet(() -> {
                                    StoreOpenTime newStoreOpenTime = StoreOpenTime.builder()
                                            .store(savedStore)
                                            .day(openDay)
                                            .build();
                                    return newStoreOpenTime;
                                });

                        storeOpenTime.updateContent(data);
                        storeOpenTimeRepository.save(storeOpenTime);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private String[] toCategories(String excelData) {
        return excelData.split(",");
    }

    private String checkType(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        if (cell.getCellType() == CellType.STRING) {
            return String.valueOf(cell.getStringCellValue());
        }
        else {
            return "";
        }
    }

    @Scheduled(fixedRate = 604800000)  // 1주일마다 실행
    public void scheduledParseExcelFile() {
        String localFilePath = excelPath;

        // 엑셀 파일 파싱
        parseExcelFile(localFilePath);
    }
}
