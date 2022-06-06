package com.elosinfo.payments.service;

import com.elosinfo.payments.dto.CategoriesDto;
import com.elosinfo.payments.dto.CategoryDto;
import com.elosinfo.payments.dto.CategoryInitDto;
import com.elosinfo.payments.entity.CategoryEntity;

import java.util.List;

public interface ICategoryService {
    public List<CategoryEntity> getAll();
    public CategoriesDto getTree();
    public void save(CategoryInitDto categoryInitDto);
}
