package com.wang.service;

import com.wang.entity.Repository;
import com.wang.utils.PageResult;
import com.wang.utils.PageUtil;

import java.util.List;

public interface RepositoryService {

    List<Repository> selectAll();

    int addWaste(Repository repository);

    int deleteWasteByID(String wasteID);

    int updateWasteByID(Repository repository);

    Repository queryWasteByID(String wasteID);

    List<Repository> queryWasteByName(String wasteName);

    List<Repository> queryWasteByEquipment(String solveEquipment);

    PageResult getAdminUserPage(PageUtil pageUtil);

    PageResult getQueryUserPage(PageUtil pageUtil);

    List<Repository> getRepoByGroup(List<String> wasteIDs);

}
