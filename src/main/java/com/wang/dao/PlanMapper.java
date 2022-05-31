package com.wang.dao;

import com.wang.entity.Plan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlanMapper {

    List<Plan> selectAllPlan();

    int addPlan(Plan plan);

    int deletePlanByID(@Param("planID") String planID);

}
