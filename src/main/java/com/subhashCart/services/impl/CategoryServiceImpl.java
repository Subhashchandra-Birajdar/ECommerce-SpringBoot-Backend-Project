package com.subhashCart.services.impl;

import com.subhashCart.models.Category;
import com.subhashCart.repositories.CategoryDao;
import com.subhashCart.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao catDao;

    @Override
    public Category addCategory(Category category) {

        Category category1 = catDao.save(category);

        return category1;
    }


}
