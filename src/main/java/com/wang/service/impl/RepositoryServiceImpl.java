package com.wang.service.impl;

import com.wang.dao.RepositoryMapper;
import com.wang.entity.Repository;
import com.wang.service.RepositoryService;
import com.wang.utils.PageResult;
import com.wang.utils.PageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

    @Override
    public List<Repository> selectAll() {
        return repositoryMapper.selectAll();
    }

    @Override
    public int addWaste(Repository repository) {
        return repositoryMapper.addWaste(repository);
    }

    @Override
    public int deleteWasteByID(String wasteID) {
        return repositoryMapper.deleteWasteByID(wasteID);
    }

    @Override
    public int updateWasteByID(Repository repository) {
        return repositoryMapper.updateWasteByID(repository);
    }

    @Override
    public Repository queryWasteByID(String wasteID) {
        return repositoryMapper.queryWasteByID(wasteID);
    }

    @Override
    public List<Repository> queryWasteByName(String wasteName) {
        return repositoryMapper.queryWasteByName(wasteName);
    }

    @Override
    public List<Repository> queryWasteByEquipment(String solveEquipment) {
        return repositoryMapper.queryWasteByEquipment(solveEquipment);
    }

    @Override
    public PageResult getAdminUserPage(PageUtil pageUtil) {
        List<Repository> repositoryList = repositoryMapper.selectPage(pageUtil);

        int total = repositoryMapper.getTotalRepo(pageUtil);

        PageResult pageResult = new PageResult(repositoryList, total, pageUtil.getLimit(), pageUtil.getPage());

        return pageResult;

    }

    @Override
    public PageResult getQueryUserPage(PageUtil pageUtil) {
        return null;
    }

    @Override
    public List<Repository> getRepoByGroup(List<String> wasteIDs) {

        List<Repository> repoByGroup = repositoryMapper.getRepoByGroup(wasteIDs);
        return repoByGroup;

    }
}
