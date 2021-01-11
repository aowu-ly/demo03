package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//DAO层：
//DAO层叫数据访问层，全称为data access object，属于一种比较底层，比较基础的操作，具体到对于某个表的增删改查，
//        也就是说某个DAO一定是和数据库的某一张表一一对应的，其中封装了增删改查基本操作，建议DAO只做原子操作，增删改查。
//dao层使用@repository注解
/*@Repository(value="userDao")注解是告诉Spring，让Spring创建一个名字叫“userDao”的UserDaoImpl实例。
        当Service需要使用Spring创建的名字叫“userDao”的UserDaoImpl实例时，
        就可以使用@Resource(name = "userDao")注解告诉Spring，Spring把创建好的userDao注入给Service即可。

@Autowired可以对成员变量、方法和构造函数进行标注，来完成自动装配的工作，
我们也要清楚，@Autowired是根据类型进行自动装配的。*/
@Repository
public interface UserMapper {

    /**
     * 查询全部用户信息
     * Title: getUser
     * @Description：
     * @Param [map]
     * @return： java.util.List<com.example.demo.bean.User>
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:45
     */
    public List<User> getUser(Map map);

    /**
     * 根据主键集合批量删除
     * Title: deleteByBatch
     * @Description： 根据主键集合批量删除
     * @Param [ids]
     * @return： void
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:47
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
    public void updateUserById(User user);

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
    public Integer queryDuplicateByPhone(@Param("phone") String phone, @Param("id") Integer id);

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
     * 分页查询用户
     * @return
     */
    public List<User> queryPage(Map<String,Object> map);

    public List<User> getUserByName(String name);
    public void insertUser(User user);
}
