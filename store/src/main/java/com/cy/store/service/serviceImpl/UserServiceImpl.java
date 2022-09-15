package com.cy.store.service.serviceImpl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public void insert(User user) {
        //查询是否存在该用户
        User userByUsername = userMapper.findUserByUsername(user.getUsername());
        if (userByUsername != null){
            throw new UsernameDuplicateException("该用户已经存在");
        }
        //将密码加密在注入
        String salt = UUID.randomUUID().toString();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        //将要注入的信息注入
        Date date = new Date();
        user.setSalt(salt);
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(date);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(date);
        user.setIsDelete(0);
        Integer integer = userMapper.insertUser(user);
        if (integer != 1){
            throw new InsertException("注册时遇到未知异常");
        }

    }

    @Override
    public User login(String username, String password) {
        //根据username获得记录
        User result = userMapper.findUserByUsername(username);
        if (result == null){
            throw new UserNoFoundException("用户名错误！");
        }
        if (result.getIsDelete() == 1){
            throw new UserNoFoundException("用户名错误！");
        }
        //获得密码并跟输入的密码比较（因为密码经过加密所以用户输入的密码也要经过加密才能作比较）
        //获得数据库中的密码
        String oldPassword = result.getPassword();
        //获得盐值
        String salt = result.getSalt();
        String newPassword = getMd5Password(password, salt);
        if (!newPassword.equals(oldPassword)){
            throw new PasswordNotMatchException("密码错误!");
        }
        // 创建新的User对象
        // 将查询结果中的uid、username、avatar封装到新的user对象中  之装载需要用到的数据节省空间
        // 返回新的user对象
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void updateForPassword(Integer uid, String username, String oldPassword, String newPassword) {
        //通过uid得到需要修改用户的信息
        User user = userMapper.findUserByUid(uid);

        //判断是否存在该用户和该用户是否没注销了
        if (user == null){
            throw new UserNoFoundException("用户不存在");
        }

        if (user.getIsDelete() == 1){
            throw new UserNoFoundException("该用户已被注销");
        }
        //判断输入的密码和旧密码是否符合 符合才允许被更改
        //将输入的密码加密
        String salt = user.getSalt();
        String md5Password = getMd5Password(oldPassword, salt);
        if (!md5Password.equals(user.getPassword())){
            throw new PasswordNotMatchException("与原密码不符合");
        }

        //获取更新方法所需要的数据
        Date date = new Date();
        String md5NewPassword = getMd5Password(newPassword, salt);
        Integer result = userMapper.updateUserForPassword(uid, md5NewPassword, username, date);
        if (result != 1){
            throw new UpdateException("更新数据时发生未知异常");
        }

    }

    @Override
    public User findUserByUid(Integer uid) {
        User result = userMapper.findUserByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNoFoundException("用户不存在或已注销");
        }
        //将要回显的信息封装到新user返回中节省空间
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }

    @Override
    public void updateForUserResource(Integer uid, String modifiedUser, User user) {
        //获取用户记录信息
        User result = userMapper.findUserByUid(uid);
        if (result == null || result.getIsDelete() == 1){
            throw new UserNoFoundException("用户不存在或已经注销");
        }

        Integer integer = userMapper.updateUserForPersonResource(uid, user.getPhone(), user.getEmail(),
                user.getGender(), modifiedUser, new Date());
        if (integer != 1){
            throw new UpdateException("修改时发生未知异常");
        }
    }

    @Override
    public void updateUserAvatar(Integer uid, String avatar, String username) {
        //获取用户信息判断是否存在
        User result = userMapper.findUserByUid(uid);
        if (result == null){
            throw new UserNoFoundException("用户不存在");
        }
        if (result.getIsDelete() == 1){
            throw new UserNoFoundException("用户已注销");
        }

        Integer integer = userMapper.updateUserAvatar(avatar, username, new Date(), uid);

        if (integer != 1){
            throw new UpdateException("修改数据时发生未知异常");
        }
    }


    private String getMd5Password(String password , String salt){
        for (int i = 0; i < 3; i++) {
            //md5加密
            password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes());
        }
        return password;
    }
}
