package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.util.PageRequest;
import com.example.demo.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

//Service层：
//Service层叫服务层，被称为服务，
// 粗略的理解就是对一个或多个DAO进行的再次封装，封装成一个服务，所以这里也就不会是一个原子操作了，需要事物控制。
public interface UserService {

    /**
     * 查询全部
     * Title:getAllUser
     * @Description 查询全部
     * @Param [map]
     * @return java.util.List<com.example.demo.bean.User>
     * @throws
     * @Author liuyang
     * @Date 2020/12/18 10:03
     */
    public List<User> getUser(Map map);

    /**
     * 根据主键集合批量删除
     * Title: deleteByBatch
     * @Description： 根据主键集合批量删除
     * @Param [ids]
     * @return： java.lang.String
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:05
     */
    public void deleteByBatch(List ids);

    /**
     * 新增前查询电话号是否已存在
     * Title: insertDuplicateByPhone
     * @Description： 新增前查询电话号是否已存在
     * @Param [phone]
     * @return： java.lang.Integer
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:16
     */
    public Integer insertDuplicateByPhone(String phone);

    /**
     * 新增前查询邮箱号是否已存在
     * Title: insertDuplicateByEmail
     * @Description： 新增前查询邮箱号是否已存在
     * @Param [email]
     * @return： java.lang.Integer
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:18
     */
    public Integer insertDuplicateByEmail(String email);

    /**
     * 添加个人信息
     * Title:insert
     * @Description： 添加个人信息
     * @Param [userVO]
     * @return： java.lang.String
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:22
     */
    public void insert(User user);

    /**
     * 通过id进行修改
     * Title: updateUserById
     * @Description： 通过id进行修改
     * @Param [user]
     * @return： java.lang.String
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:37
     */
    public void updateUserById(@RequestBody User user);

    /**
     * 通过姓名模糊查询
     * Title: queryByName
     * @Description： 通过姓名模糊查询
     * @Param [name]
     * @return： java.util.List<java.lang.String>
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:36
     */
    public List<String> queryByName(String name);

    /**
     * 修改前对电话号查重
     * Title: queryDuplicateByPhone
     * @Description： 修改前对电话号查重
     * @Param [phone, id]
     * @return： java.lang.Integer
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:42
     */
    public Integer queryDuplicateByPhone(String phone,Integer id);

    /**
     * 修改前对邮箱号查重
     * Title: queryDuplicateByEmail
     * @Description： 修改前对邮箱号查重
     * @Param [email, id]
     * @return： java.lang.Integer
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:43
     */
    public Integer queryDuplicateByEmail(String email,Integer id);

    /**
     * 分页查询
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * Title: findPage
     * @Description： 分页查询
     * @Param [pageRequest]
     * @return： com.example.demo.util.PageResult
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:57
     */
    public PageResult findPage(PageRequest pageRequest);

    public List<User> getUserByName(String name);
    public void insertUser(User user);
}
