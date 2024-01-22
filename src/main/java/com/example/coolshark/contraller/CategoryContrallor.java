package com.example.coolshark.contraller;


import com.example.coolshark.entity.Category;
import com.example.coolshark.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryContrallor {
    @Autowired
    CategoryMapper mapper;


    @RequestMapping("/category/select")
    public List<Category> select(){
      return mapper.selectCategory();
    }

    @RequestMapping("/category/delete")
    public void select(int id){
         mapper.delete(id);
    }

    @RequestMapping("/category/insert")
    public void insert(Category category){
        mapper.insert(category);
    }
}
