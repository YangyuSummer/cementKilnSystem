package com.wang.service.impl;


import com.wang.dao.ElementLimitMapper;
import com.wang.entity.ElementLimit;
import com.wang.service.ElementLimitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ElementLimitServiceImpl implements ElementLimitService {

    @Resource
    private ElementLimitMapper elementLimitMapper;


    @Override
    public List<ElementLimit> selectAllLimit() {
        return elementLimitMapper.selectAllLimit();
    }

    @Override
    public int addLimit(ElementLimit elementLimit) {
        return elementLimitMapper.addLimit(elementLimit);
    }

    @Override
    public ElementLimit queryLimitByElement(String element) {
        return elementLimitMapper.queryLimitByElement(element);
    }
}
