package com.lc.homepage.service;

import com.lc.homepage.entity.User;

import java.util.List;

/**
 * ClassName:UserService
 * Package:com.cdrj.homepage.service
 * Description: hello world！
 *
 * @Date:2020/3/5 0005 15:32
 * @Author:lc
 */
public interface UserService {

    int insert(User user);

    List<User> queryall();

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
//    PageResult selectAllPage(PageRequest pageRequest);
//    //分页筛选
//    PageResult selectByentityPage(PageRequest pageQuery, User user);

//    User queryByUserid(int user_id);
//    int update(User user);
//    int delete(int user_id);

    User userGetByUserName(String username);

//    Role queryRoleNameByUserid(int userId);
}
