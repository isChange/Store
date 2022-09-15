package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {

    List<District> getDistrictByParent(@Param("parent") String parent);

    String getNameByCode(@Param("code") String code);

}
