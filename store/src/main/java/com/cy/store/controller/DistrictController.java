package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.entity.JsonResult;
import com.cy.store.service.serviceImpl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController{
    @Autowired
    private DistrictServiceImpl districtService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getDistrictList(String parent){
        List<District> districtList = districtService.getDistrictByParent(parent);
        return new JsonResult<>(OK,districtList);
    }
}
