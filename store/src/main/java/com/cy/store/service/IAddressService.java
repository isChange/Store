package com.cy.store.service;

import com.cy.store.entity.Address;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.List;


public interface IAddressService {
    /**
     * 增加收货地址
     * @param address
     * @param username
     * @param uid
     */
    void insertAddress(Address address , String username , Integer uid);

    /**
     * 更具uid查询记录并根据isDefault升序createTime降序排列
     * @param uid
     */
    List<Address> findAddressByUid(Integer uid);

    /**
     * 设置默认地址
     * @param aid
     * @return
     */
    void setIsDefault(Integer aid, Integer uid , String username);

    /**
     * 根据aid删除收货地址
     * @param aid
     */
    void deleteAddressByAid(Integer aid,Integer uid);

    Address findAddressByAid(Integer aid , Integer uid);
}
