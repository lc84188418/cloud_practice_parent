package com.lc.homepage.service.impl;

import com.lc.homepage.dao.UserMapper;
import com.lc.homepage.entity.User;
import com.lc.homepage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:UserServiceImpl
 * Package:com.cdrj.homepage.service.impl
 * Description: hello world！
 *
 * @Date:2020/3/5 0005 15:33
 * @Author:lc
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryall() {
        return userMapper.queryall();
    }

//    @Override
//    public PageResult selectAllPage(PageRequest pageRequest) {
//        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
//    }
//
//    @Override
//    public PageResult selectByentityPage(PageRequest pageRequest, User user) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> sysMenus = userMapper.selectByentityPage(user);
//        return PageUtils.getPageResult(pageRequest,new PageInfo<User>(sysMenus));
//    }

//    @Override
//    public User queryByUserid(int user_id) {
//        return userMapper.selectByPrimaryKey(user_id);
//    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

//    @Override
//    public int delete(int user_id) {
//        return userMapper.deleteByPrimaryKey(user_id);
//    }

    @Override
    public User userGetByUserName(String username) {
        return userMapper.userGetByUserName(username);
    }

//    @Override
//    public int update(User user) {
//        return userMapper.updateByPrimaryKey(user);
//    }

//    @Override
//    public Role queryRoleNameByUserid(int userId) {
//        return userMapper.queryRoleNameByUserid(userId);
//    }
//    /**
//     * 调用分页插件完成分页
//     * @return
//     */
//    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> sysMenus = userMapper.selectAllPage();
//        return new PageInfo<User>(sysMenus);
//    }
}
