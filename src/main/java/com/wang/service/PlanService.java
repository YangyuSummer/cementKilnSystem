package com.wang.service;

import com.wang.entity.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> selectAllPlan();

    int addPlan(Plan plan);

    int deletePlanByID(String planID);

}
