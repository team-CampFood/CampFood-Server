package com.campfood.src.university.service;

import com.campfood.common.error.ErrorCode;
import com.campfood.common.exception.RestApiException;
import com.campfood.src.university.entity.University;
import com.campfood.src.university.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;

    public University findUniversityByName(String name) {
        return universityRepository.findByName(name)
                .orElseThrow(() -> new RestApiException(ErrorCode.UNIVERSITY_NOT_EXIST));
    }
}
