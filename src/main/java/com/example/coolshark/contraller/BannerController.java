package com.example.coolshark.contraller;

import com.example.coolshark.entity.Banner;
import com.example.coolshark.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
public class BannerController {

    //静态资源不能调用非静态资源
    private static File staticDir;

    //static代码块会在类加载的时候,被执行,并且其中的内容,只会被执行一次
    static {
        try {
            staticDir = new File(
                    BannerController.class.getClassLoader().getResource(
                            "./static"
                    ).toURI()
            );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    BannerMapper mapper;

    @RequestMapping("/banner/select")
    public List<Banner> select(){
     return mapper.select();
    }

    @RequestMapping("/banner/upload")
    public String upload(MultipartFile picFile){
        String filename =  picFile.getOriginalFilename();
        String dir = "D:/files/";
        System.out.println(dir);
        File dirfile = new File(dir);
        if (! dirfile.exists()){
            dirfile.mkdirs();
        }
        String suffix = filename.substring(filename.lastIndexOf("."));

        filename = UUID.randomUUID()+suffix;
        dir = dir +filename;
        try {
            picFile.transferTo(new File(dir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;

    }

    @RequestMapping("/banner/delete")
    public void delete(int id){
        //先查询轮播图的地址url
        String url = mapper.selectUrlById(id);
        //得到磁盘的完整路径

        //String dir = staticDir+"\\imgs\\";
        String path = "d:/files/"+url;
        new File(path).delete();
        mapper.deleteById(id);
    }
    @RequestMapping("/banner/insert")
    public void insert(@RequestBody Banner banner){
        System.out.println(banner.getUrl());
        mapper.insert(banner);
    }

}
