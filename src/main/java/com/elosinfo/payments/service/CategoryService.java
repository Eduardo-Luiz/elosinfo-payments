package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.CategoryDto;
import com.elosinfo.payments.entity.CategoryEntity;
import com.elosinfo.payments.entity.PaymentEntity;
import com.elosinfo.payments.exception.PaymentException;
import com.elosinfo.payments.repository.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    private final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAll() {
        List<CategoryEntity> result;
        try {
            result = this.categoryRepository.findAll();
        } catch (Exception e) {
            throw new PaymentException(UNKNOWN_ERROR, "Error searching category with message: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Override
    public void save(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new ModelMapper().map(categoryDto, CategoryEntity.class);
        categoryEntity.setCreatedAt(LocalDateTime.now());
        this.categoryRepository.save(categoryEntity);
        categoryDto.setId(categoryEntity.getId());
    }
}
