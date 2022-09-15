package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.entity.JsonResult;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    //允许最大传输数据的大小
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    //允许传输的数据类型
    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }
    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public JsonResult<Void> reg(User user){
        //如果这里发生异常会被父类的异常处理机制给捕获并返回一个信息实体类
        userService.insert(user);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping("/avatar")
    public JsonResult<User> getAvatar(HttpSession session){
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());

        User user = userService.findUserByUid(uid);

        return new JsonResult<User>(OK,user);
    }
    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password ,HttpSession session ){
        User login = userService.login(username, password);
        //将用户信息到session
        session.setAttribute("uid",login.getUid());
        session.setAttribute("username",login.getUsername());
        return new JsonResult<User>(OK,login);
    }
    @RequestMapping("/updatePassword")
    public JsonResult<User> updateForPassword(String oldPassword, String newPassword,HttpSession session){
        //获得用户uid和username
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString()) ;
        String username = (String) session.getAttribute("username");
        userService.updateForPassword(uid,username,oldPassword,newPassword);

        return new JsonResult<User>(OK);
    }

    @RequestMapping("/updateForPersonList")
    public JsonResult<User> updateForPersonList(HttpSession session ){

        User result = userService.findUserByUid(Integer.parseInt(session.getAttribute("uid").toString()));

        return new JsonResult<User>(OK,result);
    }

    @RequestMapping("/updateForPerson")
    public JsonResult<User> updateForPerson(User user ,HttpSession session){
        //获取uid
        Object uid = session.getAttribute("uid");
        Object username = session.getAttribute("username");
        //进行修改
        userService.updateForUserResource(Integer.parseInt(uid.toString()),username.toString(),user);

        return new JsonResult<User>(OK);
    }

    @RequestMapping("/uploadFile")
    public JsonResult<String> uploadFile(HttpSession session, @RequestPart("uploadFile") MultipartFile file ){

        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();

        //判断文件是否为空
        // 是：抛出异常
        if (file == null){
            throw new FileEmptyException("文件为空");
        }

        // 判断上传的文件大小是否超出限制值
        // 是：抛出异常
        if (file.getSize() > AVATAR_MAX_SIZE ){
            throw new FileSizeException("传输内容过大");
        }
        // 判断上传的文件类型是否超出限制
        // 是：抛出异常
        if (!AVATAR_TYPES.contains(file.getContentType())){
            throw new FileTypeException("文件类型不对");
        }
        //获取要上传到服务器的真实地址
        String realPath = session.getServletContext().getRealPath("upload");
        log.info(realPath);
        //判断该路径下是否存在该目录没有就创建
        File uploadFile = new File(realPath);
        if (!uploadFile.exists()){
            uploadFile.mkdir();
        }
        //获取上传的文件名字
        String uploadFileName = file.getOriginalFilename();
        //保留文件名后缀
        int lastIndex = uploadFileName.lastIndexOf(".");
        String fileNameSuffix = "";
        if (lastIndex > 0){
            fileNameSuffix = uploadFileName.substring(lastIndex);
        }
        //将文件名前缀改为通用uuid随机串
        uploadFileName = UUID.randomUUID().toString() + fileNameSuffix;

        //创建上传到服务器的目标文件
        File dest = new File(uploadFile,uploadFileName);
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }
        String avatar =  "/upload/" + uploadFileName;
        userService.updateUserAvatar(uid,avatar,username);
        return new JsonResult<String>(OK,null,avatar);
    }

}
