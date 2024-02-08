package com.campfood.src.review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDTO {
    @NotBlank
    private String content;
    private Double tasteRate;
    private Double costEffectivenessRate;
    private Double serviceRate;
    private Double cleanRate;
}
