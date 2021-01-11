package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.bean.UserVO;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonUtils;
import com.example.demo.util.PageRequest;
import com.example.demo.util.Upload;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//Controler层：
//Controler负责请求转发，接受页面过来的参数，传给Service处理，接到返回值，再传给页面。
@Api(value = "UserController 测试",tags = "这是一个测试")
//controller层使用@controller注解
/*@Controller 用于标记在一个类上，
使用它标记的类就是一个SpringMVC Controller 对象。分发处理器将会扫描使用了该注解的类的方法。
通俗来说，被Controller标记的类就是一个控制器，这个类中的方法，就是相应的动作。*/
@RestController  //相当于@Controller + @ResponseBody
/*@RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。
 用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。*/
@RequestMapping(value = "/user")
//@CrossOrigin
public class UserController {

    @Autowired
    public UserService userService;

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
    @ApiOperation(value="获取用户列表", notes="")
    @GetMapping(value = "/getUser")
    //@ResponseBody
    public List<User> getUser(@RequestBody Map map) {
        List<User> users =  userService.getUser(map);
        return users;
    }

    @GetMapping(value = "/getUserByName")
    public List<User> getUserByName(@RequestParam String name){
        return userService.getUserByName(name);
    }

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
    @ApiOperation(value="批量删除用户", notes="根据url的id来指定删除对象，可同时传入多个id")
    @ApiImplicitParam(name = "ids", value = "用户id数组", required = true, dataType = "String")
    @DeleteMapping("/deleteByBatch")
    //@ResponseBody
    public String deleteByBatch(@RequestParam String ids) {
        userService.deleteByBatch (Arrays.asList (ids.split (",")));
        return "delete success";
    }

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
    @ApiOperation(value="新增前对phone查重", notes="在数据库中对url的phone查重")
    @ApiImplicitParam(name = "phone", value = "用户电话号码", required = true, dataType = "String")
    @GetMapping("/insertDuplicateByPhone")
    //@ResponseBody
    public Integer insertDuplicateByPhone(@RequestParam String phone){
        return userService.insertDuplicateByPhone(phone);
    }

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
    @ApiOperation(value="新增前对email查重", notes="在数据库中对url的email查重")
    @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String")
    @GetMapping("/insertDuplicateByEmail")
    //@ResponseBody
    public Integer insertDuplicateByEmail(@RequestParam String email){
        return userService.insertDuplicateByEmail(email);
    }

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
    @ApiOperation(value="创建用户", notes="根据UserVO对象创建用户")
    @ApiImplicitParam(name = "userVO", value = "用户详细实体userVO", required = true, dataType = "UserVO")
    @PostMapping("/insert")
    //@ResponseBody
    public String insert(UserVO userVO){
        // 上传文件
        MultipartFile file = userVO.getFile();
        Upload upload = new Upload();
        String path = null;
        try {
            path = upload.uploadFile(file);
        } catch (Exception e) {
            path = "文件为空";
        }
        //path = upload.uploadFile(file);
        //将JSON字符串转换为JavaBean
        User user = JsonUtils.jsonString2Bean(userVO.getUser(),User.class);
        user.setIntroduction(path);
        userService.insert(user);
        return "insert success";
    }

    @PostMapping("/insertUser")
    public String insertUser(@RequestBody User user){
        userService.insertUser(user);
        return "insertUser success";
    }

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
    @ApiOperation(value="通过姓名模糊查询", notes="根据url传入的name进行模糊查询")
    @ApiImplicitParam(name = "name", value = "用户的name属性", required = true, dataType = "String")
    @GetMapping("/queryByName")
    public List<String> queryByName(@RequestParam String name){
        return userService.queryByName(name);
    }

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
    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParam(name = "user", value = "用户实体类user", required = true, dataType = "user")
    @PostMapping("/updateUserById")
    //@ResponseBody
    public String updateUserById(@RequestBody User user){
        userService.updateUserById(user);
        return "update success";
    }

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
    @ApiOperation(value="对email查重", notes="根据url的id选中除该用户之外的用户，对其邮箱号进行查重")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @GetMapping("/queryDuplicateByEmail")
    //@ResponseBody
    public Integer queryDuplicateByEmail(@RequestParam String email,@RequestParam Integer id){
        return userService.queryDuplicateByEmail(email,id);
    }

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
    @ApiOperation(value="对电话号查重", notes="根据url的id选中除该用户之外的用户，对其电话号进行查重")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户电话号码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer")
    })
    @GetMapping("/queryDuplicateByPhone")
    //@ResponseBody
    public Integer queryDuplicateByPhone(@RequestParam String phone,@RequestParam Integer id){
        String s = Integer.toString(id);
        return userService.queryDuplicateByPhone(phone,id);
    }

    /**
     * 分页查询
     * Title: findPage
     * @Description： 分页查询
     * @Param [pageQuery]
     * @return： java.lang.Object
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 10:44
     */
    @ApiOperation(value="分页查询", notes="根据url的pageNum,pageSize,params进行分页查询")
    @ApiImplicitParam(name = "pageQuery", value = "查询请求", required = true, dataType = "PageRequest")
    @PostMapping(value="/findPage")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return userService.findPage(pageQuery);
    }
}
