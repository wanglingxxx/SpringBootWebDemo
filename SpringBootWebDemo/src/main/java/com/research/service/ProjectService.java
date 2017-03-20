package com.research.service;

import com.research.mapper.ProjectMapper;
import com.research.model.Pagination;
import com.research.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    public int insertProject(Project project) {
        return projectMapper.insertProject(project);
    }

    public int deleteProjectById(Integer id) {
        return projectMapper.deleteProjectById(id);
    }

    public int updateProjectById(Project project) {
        return projectMapper.updateProjectById(project);
    }

    public Project getProjectById(Integer id) {
        return projectMapper.getProjectById(id);
    }

    public List<Project> getProjects(Pagination pagination) {
        if(pagination.getPageIndex() == null) {
            pagination.setPageIndex(0);
        }
        if(pagination.getPageSize() == null) {
            pagination.setPageSize(10);
        }
        pagination.setPageIndex(pagination.getPageIndex() * pagination.getPageSize());
        return projectMapper.getProjects(pagination);
    }

    public List<Project> queryProjects(String date, String state) {

        return projectMapper.queryProjects(date, state);
    }

    public Integer getProjectCounts() {
        return projectMapper.getProjectCounts();
    }
}
