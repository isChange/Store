package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    Integer countByUid(@Param("uid") Integer uid);

    Integer insert(Address address);

    List<Address> findAddressByUid(@Param("uid") Integer uid);

    Integer updateIsDefaultByAid(@Param("aid") Integer aid , @Param("modifiedUser") String modifiedUser,
                                 @Param("modifiedTime")Date modifiedTime);

    Integer updateIsDefaultByUid(@Param("uid")Integer uid);

    Address findAddressByAid(@Param("aid")Integer aid);

    Integer deleteAddressByAid(@Param("aid")Integer aid);
    //删除默认地址后要返回一个下一个地址为默认的  该功能为返回一下地址
    Address deleteAddressForDef(@Param("uid") Integer uid);


}
