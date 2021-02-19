package com.lc.homepage.dao;

import com.lc.homepage.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
//    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

//    User selectByPrimaryKey(Integer userId);

//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);

    List<User> queryall();
//    /**
//     * 分页查询用户
//     * @return
//     */
//    List<User> selectAllPage();

    User userGetByUserName(String username);

//    Role queryRoleNameByUserid(int userId);

//    List<User> selectByentityPage(User user);
}