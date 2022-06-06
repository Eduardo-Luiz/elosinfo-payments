package com.elosinfo.payments.controller;

import com.elosinfo.payments.dto.CategoryInitDto;
import com.elosinfo.payments.entity.CategoryEntity;
import com.elosinfo.payments.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        CategoryInitDto dto1 = new CategoryInitDto();
        dto1.setName("RECEITAS".toUpperCase(Locale.ROOT));
        dto1.setActive(Boolean.TRUE);
        this.categoryService.save(dto1);

        CategoryInitDto dto2 = new CategoryInitDto();
        dto2.setName("Salário");
        dto2.setActive(Boolean.TRUE);
        dto2.setParentId(dto1.getId());
        this.categoryService.save(dto2);

        CategoryInitDto dto3 = new CategoryInitDto();
        dto3.setName("Aluguel");
        dto3.setActive(Boolean.TRUE);
        dto3.setParentId(dto1.getId());
        this.categoryService.save(dto3);

        CategoryInitDto dto4 = new CategoryInitDto();
        dto4.setName("HABITAÇÃO".toUpperCase(Locale.ROOT));
        dto4.setActive(Boolean.TRUE);
        this.categoryService.save(dto4);

        CategoryInitDto dto5 = new CategoryInitDto();
        dto5.setName("Prestação da casa".toUpperCase(Locale.ROOT));
        dto5.setActive(Boolean.TRUE);
        dto5.setParentId(dto4.getId());
        this.categoryService.save(dto5);

        CategoryInitDto dto6 = new CategoryInitDto();
        dto6.setName("Diarista".toUpperCase(Locale.ROOT));
        dto6.setActive(Boolean.TRUE);
        dto6.setParentId(dto4.getId());
        this.categoryService.save(dto6);
    }
}
