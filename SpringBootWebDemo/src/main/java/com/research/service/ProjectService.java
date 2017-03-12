package com.research.service;

import com.research.model.Pagination;
import com.research.model.Project;
import com.research.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public int insertProject(Project project) {
        return projectRepository.insertProject(project);
    }

    public int deleteProjectById(Integer id) {
        return projectRepository.deleteProjectById(id);
    }

    public int updateProjectById(Project project) {
        return projectRepository.updateProjectById(project);
    }

    public Project getProjectById(Integer id) {
        return projectRepository.getProjectById(id);
    }

    public List<Project> getProjects(Pagination pagination) {
        return projectRepository.getProjects(pagination);
    }

    public Integer getProjectCounts() {
        return projectRepository.getProjectCounts();
    }
}
