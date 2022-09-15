package com.cy.store.service.serviceImpl;

import com.cy.store.entity.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getDistrictByParent(String parent) {
        List<District> districtList = districtMapper.getDistrictByParent(parent);

        for (District district :districtList) {
            district.setId(null);
            district.setParent(null);
        }
        return districtList;
    }

    @Override
    public String getNameByCode(String code) {
        String nameByCode = districtMapper.getNameByCode(code);
        return nameByCode;
    }
}
