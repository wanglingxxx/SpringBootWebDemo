package com.research.repository;

import com.research.model.Pagination;
import com.research.model.Project;

import java.util.List;

public interface ProjectRepository {
    int insertProject(Project project);

    int deleteProjectById(Integer id);

    int updateProjectById(Project project);

    Project getProjectById(Integer id);

    List<Project> getProjects(Pagination pagination);

    Integer getProjectCounts();
}
