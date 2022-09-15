package com.cy.store.controller;


import com.cy.store.entity.Address;
import com.cy.store.entity.JsonResult;
import com.cy.store.service.serviceImpl.AddressServiceImpl;
import com.cy.store.service.serviceImpl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private AddressServiceImpl addressService ;
    @Autowired
    private DistrictServiceImpl districtService;

    @RequestMapping("/insert")
    public JsonResult<Void> insert(Address address , HttpSession session){
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();
        //补全信息
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));
        addressService.insertAddress(address,username,uid);
        return new JsonResult<Void>(OK);
    }

    @RequestMapping("/list")
    public JsonResult<List<Address>> addressList(HttpSession session){
        List<Address> addressList =
                addressService.findAddressByUid(Integer.parseInt(session.getAttribute("uid").toString()));

        return new JsonResult<>(OK,addressList);

    }

    @RequestMapping("/setDefault")
    public JsonResult<Void> setDefault(HttpSession session , Integer aid){

        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        String username = session.getAttribute("username").toString();

        addressService.setIsDefault(aid,uid,username);

        List<Address> addressList = addressService.findAddressByUid(uid);

        return new JsonResult<>(OK);


    }
    @RequestMapping("/delete")
    public JsonResult<Void> deleteByAid(HttpSession session , Integer aid){
        Integer uid = Integer.parseInt(session.getAttribute("uid").toString());
        addressService.deleteAddressByAid(aid,uid);
        return new JsonResult<>(OK);
    }




}
