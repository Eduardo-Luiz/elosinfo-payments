package com.elosinfo.payments.controller;

import com.elosinfo.payments.dto.CategoryDto;
import com.elosinfo.payments.entity.CategoryEntity;
import com.elosinfo.payments.entity.PaymentEntity;
import com.elosinfo.payments.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getAll());
    }

    @PostMapping("/initialization")
    public void initialization(){

        CategoryDto dto1 = new CategoryDto();
        dto1.setDescription("RECEITAS".toUpperCase(Locale.ROOT));
        dto1.setMainCategory(Boolean.TRUE);
        dto1.setActive(Boolean.TRUE);
        this.categoryService.save(dto1);

        CategoryDto dto2 = new CategoryDto();
        dto2.setDescription("Salário");
        dto2.setMainCategory(Boolean.FALSE);
        dto2.setActive(Boolean.TRUE);
        dto2.setParentId(dto1.getId());
        this.categoryService.save(dto2);

        CategoryDto dto3 = new CategoryDto();
        dto3.setDescription("Aluguel");
        dto3.setMainCategory(Boolean.FALSE);
        dto3.setActive(Boolean.TRUE);
        dto3.setParentId(dto1.getId());
        this.categoryService.save(dto3);

        CategoryDto dto4 = new CategoryDto();
        dto4.setDescription("HABITAÇÃO".toUpperCase(Locale.ROOT));
        dto4.setMainCategory(Boolean.TRUE);
        dto4.setActive(Boolean.TRUE);
        this.categoryService.save(dto4);

        CategoryDto dto5 = new CategoryDto();
        dto5.setDescription("Prestação da casa".toUpperCase(Locale.ROOT));
        dto5.setMainCategory(Boolean.FALSE);
        dto5.setActive(Boolean.TRUE);
        dto5.setParentId(dto4.getId());
        this.categoryService.save(dto5);

        CategoryDto dto6 = new CategoryDto();
        dto6.setDescription("Diarista".toUpperCase(Locale.ROOT));
        dto6.setMainCategory(Boolean.FALSE);
        dto6.setActive(Boolean.TRUE);
        dto6.setParentId(dto4.getId());
        this.categoryService.save(dto6);
    }
}
