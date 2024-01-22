package com.example.coolshark.contraller;

import com.example.coolshark.entity.Product;
import com.example.coolshark.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${dirPath}")
    private String dirPath;

    @Autowired
    ProductMapper mapper;

    @RequestMapping("/insert")
    public void insert(@RequestBody Product product){
        //
        product.setCreated(new Date());
        mapper.insert(product);
    }


    @RequestMapping("/select")
    public List<Product> select(){


        return mapper.select();
    }

    @RequestMapping("/delete")
    public void delete(int id){
        File dirFile = new File(dirPath);
        String url = mapper.getUrlById(id);
        new File(dirFile+"/"+url).delete();
        mapper.delete(id);
    }

    @RequestMapping("/select/index")
    public List<Product> selectIndex(){
        return mapper.selectIndex();
    }

    @RequestMapping("/select/top")
    public  List<Product> selectTop(){
        List<Product> list = mapper.selectTop();
        //遍历list集合
        for (Product p: list) {
            if (p.getTitle().length()>3){
                String title = p.getTitle().substring(0,3)+"...";
                p.setTitle(title);
            }
        }
        return list;
    }

    @RequestMapping("/select/category")
    public List<Product> selectByCid(int cid){
        return mapper.selectByCid(cid);
    }

    @RequestMapping("/search")
    public List<Product> selectByWd(String wd){
        return mapper.selectByWd(wd);
    }

    @RequestMapping("/selectById")
    public Product selectByWd(int id){
        //让浏览量+1
        mapper.updateViewCount(id);
        System.out.println(id);
        return mapper.selectById(id);
    }




}
