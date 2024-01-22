package com.example.coolshark.mapper;

import com.example.coolshark.entity.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into product values(null,#{title},#{url}," +
            "#{price},#{oldPrice},0,#{saleCount},#{created},#{categoryId})")
    void insert(Product product);

    @Select("select * from product")
    List<Product> select();

    @Delete("delete from product where id = #{id}")
    void delete(int id);

    @Select("select url from product where id=#{id}")
    String getUrlById(int id);

    @Select("Select id ,title,url,price,old_price,sale_count from product")
    List<Product> selectIndex();

    @Select("select id,title,sale_count from product order by sale_count desc limit 0,6")
    @Result(property = "saleCount",column = "sale_count")
    List<Product> selectTop();

    @Select("select id,title,url,price,old_price,sale_count from product where category_id=#{cid}")
    @Result(property = "oldPrice",column = "old_price")
    @Result(property = "saleCount",column = "sale_count")
    List<Product> selectByCid(int cid);
    //concat sql中拼接的函数
    @Select("select id,title,url,price,old_price,sale_count from product where title like concat('%',#{cid},'%')")
    @Result(property = "oldPrice",column = "old_price")
    @Result(property = "saleCount",column = "sale_count")
    List<Product> selectByWd(String wd);


    @Select("select id,title,url,price,old_price,sale_count from product where id=#{id}")
    @Result(property = "oldPrice",column = "old_price")
    @Result(property = "saleCount",column = "sale_count")
    Product selectById(int id);


    @Update("update product set view_count=view_count+1 where id=#{id}")
    void updateViewCount(int id);
}
