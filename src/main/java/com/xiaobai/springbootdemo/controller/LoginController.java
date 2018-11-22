package com.xiaobai.springbootdemo.controller;


import com.xiaobai.springbootdemo.entities.User;
import com.xiaobai.springbootdemo.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：恒宇少年
 * Date：2017/4/7
 * Time：21:17
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserJpaRepository userJPA;

    /**
     * 初始化登录页面
     * @return
     */
    @RequestMapping(value = "/login_view",method = RequestMethod.GET)
    public String login_view(){
        return "login";
    }

    @RequestMapping(value = "/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //登录成功
        boolean flag = true;
        String result = "登录成功";
        //根据用户名查询用户是否存在
        Optional<User> op = userJPA.findOne(new Specification<User>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.where(criteriaBuilder.equal(root.get("name"), user.getName()));
                return null;
            }
        });
        User userEntity = null;
        try {
            userEntity = op.get();
        }catch (Exception e) {

        }
        //用户不存在
        if(userEntity == null){
            flag = false;
            result = "用户不存在，登录失败";}
        //密码错误
        else if(!userEntity.getPwd().equals(user.getPwd())){
            flag = false;
            result = "用户密码不相符，登录失败";
        }
        //登录成功
        if(flag){
            //将用户写入session
            request.getSession().setAttribute("_session_user",userEntity);
//            return "redirect:" + request.getSession().getAttribute("raw_url") != null ? request.getSession().getAttribute("raw_url").toString() : "/index";
            response.sendRedirect(request.getSession().getAttribute("raw_url") != null ? request.getSession().getAttribute("raw_url").toString() : "/index");
            return null;
        }else {
            return "login";
        }
//        return result;

    }
}
