package com.research.mapper;

import com.research.model.Pagination;
import com.research.model.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int insertProject(Project project);

    int deleteProjectById(Integer id);

    int updateProjectById(Project project);

    Project getProjectById(Integer id);

    List<Project> getProjects(Pagination pagination);

    Integer getProjectCounts();

    List<Project> queryProjects(Pagination pagination);
}
