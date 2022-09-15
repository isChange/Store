package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    /**
     * 添加用户
     * @param user
     * @return 返回受影响的行数
     */
    Integer insertUser(User user);

    /**
     * 通过用户名查找是否已经存在该用户
     * @param username
     * @return
     */
    User findUserByUsername(@Param("username") String username);

    /**
     * 通过uid查找用户
     * @param uid
     * @return
     */
    User findUserByUid(@Param("uid") Integer uid);

    /**
     * 修改密码和其他信息
     * @param uid
     * @param password
     * @param ModifiedUser 修改人
     * @param ModifiedTime 修改时间
     * @return
     */
    Integer updateUserForPassword(@Param("uid") Integer uid,@Param("password")String password ,
                                  @Param("modifiedUser")String ModifiedUser , @Param("modifiedTime") Date ModifiedTime);

    /**
     * 修改个人信息
     * @param uid
     * @param phone
     * @param email
     * @param gender
     * @param ModifiedUser
     * @param ModifiedTime
     * @return
     */

    Integer updateUserForPersonResource(@Param("uid") Integer uid,@Param("phone") String phone ,
                                        @Param("email") String email ,@Param("gender") Integer gender,
                                        @Param("modifiedUser")String ModifiedUser , @Param("modifiedTime") Date ModifiedTime);

    Integer updateUserAvatar(@Param("avatar") String avatar , @Param("modifiedUser") String modifiedUser,
                             @Param("modifiedTime") Date modifiedTime, @Param("uid") Integer uid);

}
