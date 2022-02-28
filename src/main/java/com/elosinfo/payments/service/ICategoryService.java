package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.CategoryDto;
import com.elosinfo.payments.entity.CategoryEntity;
import com.elosinfo.payments.entity.PaymentEntity;

import java.util.List;

public interface ICategoryService {
    public List<CategoryEntity> getAll();
    public void save(CategoryDto categoryDto);
}
