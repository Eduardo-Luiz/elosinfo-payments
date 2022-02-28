package com.elosinfo.payments.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank(message = "Description is required")
    private String description;
    private Boolean mainCategory;
    private Long parentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean active;
}
