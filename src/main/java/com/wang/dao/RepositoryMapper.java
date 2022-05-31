package com.wang.dao;

import com.wang.entity.Repository;
import com.wang.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface RepositoryMapper {

    List<Repository> selectAll();

    int addWaste(Repository repository);

    int deleteWasteByID(@Param("wasteID") String wasteID);

    int updateWasteByID(Repository repository);

    Repository queryWasteByID(@Param("wasteID") String wasteID);

    List<Repository> queryWasteByName(@Param("wasteName") String wasteName);

    List<Repository> queryWasteByEquipment(@Param("solveEquipment") String solveEquipment);

    List<Repository> selectPage(PageUtil pageUtil);

    int getTotalRepo(PageUtil pageUtil);

    List<Repository> selectQueryPage(PageUtil pageUtil,@Param("equipment") String equipment);

    int getQueryTotalRepo(PageUtil pageUtil,@Param("equipment") String equipment);

    List<Repository> getRepoByGroup(@RequestParam("wasteIDs") List<String> wasteIDs);
}
