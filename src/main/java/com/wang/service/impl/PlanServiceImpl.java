package com.wang.service.impl;

import com.wang.dao.PlanMapper;
import com.wang.entity.Plan;
import com.wang.service.PlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Resource
    private PlanMapper planMapper;

    @Override
    public List<Plan> selectAllPlan() {
        return planMapper.selectAllPlan();
    }

    @Override
    public int addPlan(Plan plan) {
        return planMapper.addPlan(plan);
    }

    @Override
    public int deletePlanByID(String planID) {
        return planMapper.deletePlanByID(planID);
    }
}
