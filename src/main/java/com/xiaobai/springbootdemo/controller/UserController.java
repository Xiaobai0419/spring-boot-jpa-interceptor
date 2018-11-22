package com.xiaobai.springbootdemo.controller;

import com.xiaobai.springbootdemo.entities.User;
import com.xiaobai.springbootdemo.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        return userJpaRepository.findAll();
    }

    @RequestMapping(method = {RequestMethod.POST,RequestMethod.PUT})//POST:新增，PUT:修改，JPA中以id是否与已有数据重复区分，重复则更新（参数只在url中时生效），不重复或没有则新增，JPA默认都是以id更新，不传id,则新增
    public User save(User user) {//设置了@GeneratedValue(strategy = GenerationType.IDENTITY)的@Id，用户传递id也无效，新增都是数据库自增id
        return userJpaRepository.save(user);//实际开发中最好使用复杂查询写修改逻辑，新增、修改用一个方法会乱
    }//POST,PUT均可把参数写在url后面或Body里，如果都写，比如name,最终值是两者都有，逗号隔开的字符串，且url中参数优先，如果是更新，url、Body中都有id参数，则以url中参数更新
//更新时不传递某字段会造成该字段被更新为空，所以说单表简单JPA操作还是相当局限
    @RequestMapping(method = RequestMethod.DELETE)//DELETE方式，经实验需要将id参数附在url后面！也是以id来进行操作，如果用户传入已有数据中不存在的id,根据打印的sql,是先新增这条数据，再删除，如果是已存在的id,则只删除
    public List<User> delete(User user) {
        userJpaRepository.delete(user);
        return userJpaRepository.findAll();
    }

}
