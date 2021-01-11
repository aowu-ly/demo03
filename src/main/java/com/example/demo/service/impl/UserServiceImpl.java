package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.PageRequest;
import com.example.demo.util.PageResult;
import com.example.demo.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//service采用@service注解
/*@Service("userService")注解是告诉spring，
当Spring要创建UserServiceImpl的的实例时，bean的名字必须叫做"userService"，
这样当Action需要使用UserServiceImpl的的实例时,就可以由Spring创建好的"userService"，然后注入给Action。*/
@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImpl implements UserService {

    @Resource
    public UserMapper userMapper;

    /*@Autowired
    private RedisTemplate<String,Object> redisTemplate;*/

    //@Cacheable(key="#map")
    @Override
    public List<User> getUser(Map map) {
        /*String key = "product:" + map;
        //先从redis中获取数据
        if(redisTemplate.hasKey(key)){
            System.out.println("执行缓存");
            redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
            List<User> user = (List<User>) redisTemplate.opsForValue().get(key);
        }
        System.out.println("执行MySQL");

        List<User> user = userMapper.getUser(map);
        redisTemplate.opsForValue().set(key,map);
        return user;*/
        return userMapper.getUser(map);
    }

    //@CacheEvict(key="#p0",allEntries = true)
    @Override
    public void deleteByBatch(List ids) {
        userMapper.deleteByBatch(ids);
    }

    @Override
    public Integer insertDuplicateByPhone(String phone){
        return userMapper.insertDuplicateByPhone(phone);
    }

    @Override
    public Integer insertDuplicateByEmail(String email){
        return userMapper.insertDuplicateByEmail(email);
    }

    //@Cacheable(cacheNames="users", key="#user")
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

   // @Cacheable(cacheNames="users", key="#name")
    @Override
    public List<String> queryByName(String name) {
        return userMapper.queryByName(name);
    }

    //@CachePut(cacheNames="users",key="#user")
    @Override
    public void updateUserById(User user) {
        userMapper.updateUserById(user);
    }

    @Override
    public Integer queryDuplicateByPhone(String phone,Integer id) {
        return userMapper.queryDuplicateByPhone(phone,id);
    }

    @Override
    public Integer queryDuplicateByEmail(String email,Integer id) {
        return userMapper.queryDuplicateByEmail(email,id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * Title: getPageInfo
     * @Description： 调用分页插件完成分页
     * @Param [pageRequest]
     * @return： com.github.pagehelper.PageInfo<com.example.demo.bean.User>
     * @throws
     * @Author： liuyang
     * @Date 2020/12/18 11:00
     */
    //@Cacheable(key="#pageRequest")
    public PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> sysMenus = userMapper.queryPage(pageRequest.getParams ());

        return new PageInfo<User>(sysMenus);
    }

    @Override
    public List<User> getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void insertUser(User user){
        userMapper.insertUser(user);
    }
}
