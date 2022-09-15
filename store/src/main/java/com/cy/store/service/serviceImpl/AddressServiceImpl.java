package com.cy.store.service.serviceImpl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Transactional
@Service
public class AddressServiceImpl implements IAddressService {
    @Value("${user.address.max-count}")
    private int maxCount;
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public void insertAddress(Address address, String username, Integer uid) {
        //获取地址总记录数看是否超过规定的数量
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount){
            throw new AddressCountLimitException("地址数量过多");
        }
        Integer isDefault = count == 0 ? 0 : 1;
        address.setUid(uid);
        Date now = new Date();
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);

        Integer insert = addressMapper.insert(address);
        if (insert != 1){
            throw new InsertException("添加时发现未知异常");
        }
    }

    @Override
    public List<Address> findAddressByUid(Integer uid) {
        //获取记录
        List<Address> addressList = addressMapper.findAddressByUid(uid);
        //将没有展示的数据给剔除节省空间
        for (Address address :addressList) {
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }

        return addressList;

    }

    @Override
    public void setIsDefault(Integer aid, Integer uid, String username) {
        //根据aid得到记录并判断是否为空
        Address result = addressMapper.findAddressByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("该记录不存在");
        }
        //判断该记录的uid是否为操作这的uid 防止操作不是自己的数据
        if (result.getUid() != uid){
            throw new AccessDeniedException("你操作的数据不是你本人的");
        }
        Integer integerUid = addressMapper.updateIsDefaultByUid(uid);
        if (integerUid < 1){
            throw new UpdateException("更新数据时发生异常");
        }
        Integer integerAid = addressMapper.updateIsDefaultByAid(aid, username, new Date());
        if (integerAid != 1){
            throw new UpdateException("更新数据时发生异常");
        }
    }

    @Override
    public void deleteAddressByAid(Integer aid, Integer uid) {
        //通过aid获取记录存不存在 如果不存在无需删除
        Address result = addressMapper.findAddressByAid(aid);
        if (result == null){
            return;
        }
        //检查是否是操作别人的数据
        if (result.getUid() != uid){
            throw new AccessDeniedException("非法操作数据");
        }

        Integer integer = addressMapper.deleteAddressByAid(aid);
        if (integer != 1){
            throw new DeleteException("删除发生未知异常");
        }
        if (result.getIsDefault() == 0){
            Address address = addressMapper.deleteAddressForDef(uid);
            Integer integerAid =
                    addressMapper.updateIsDefaultByAid(address.getAid(), result.getModifiedUser(), new Date());
            if (integerAid != 1){
                throw new UpdateException("更新数据时发生未知异常");
            }
        }

    }

    @Override
    public Address findAddressByAid(Integer aid, Integer uid) {
        Address result = addressMapper.findAddressByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("该地址不存在");
        }
        if (result.getUid() != uid){
            throw new AccessDeniedException("非法操作");
        }
        result.setProvinceCode(null);
        result.setCityCode(null);
        result.setAreaCode(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);
        return result;
    }
}
