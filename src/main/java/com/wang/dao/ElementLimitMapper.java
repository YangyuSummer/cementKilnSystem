package com.wang.dao;

import com.wang.entity.ElementLimit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElementLimitMapper {

    List<ElementLimit> selectAllLimit();

    int addLimit(ElementLimit elementLimit);

    ElementLimit queryLimitByElement(@Param("element") String element);

}
