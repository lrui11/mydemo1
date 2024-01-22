package com.example.coolshark.contraller;


import com.example.coolshark.entity.User;
import com.example.coolshark.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserContraller {
    @Autowired
    Mapper mapper;

    @RequestMapping("/login")
    public int login(@RequestBody User user, HttpSession session){
        User u =  mapper.selectByUsername(user.getUsername());
        if(u!=null){
            if(u.getPassword().equals(user.getPassword())){
                session.setAttribute("user",u);
                return 1;
            }else {
                return 3;//密码错误
            }
        }

        return 2;//用户不存在
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session){
           session.removeAttribute("user");
    }


    @RequestMapping("/currentUser")
    public User currentUser(HttpSession session){
        User u = (User)session.getAttribute("user");
        return  u;
    }

}
