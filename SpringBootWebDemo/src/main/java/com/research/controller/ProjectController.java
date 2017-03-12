package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Project;
import com.research.model.Pagination;
import com.research.service.ProjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    ResponseEntity<?> insertProject(@RequestParam("title") String title,
                                    @RequestParam("summary") String summary,
                                    @RequestParam("applicant") String applicant,
                                    @RequestParam("declareUnits") String declareUnits,
                                    @RequestParam("remark") String remark,
            @RequestParam("projectFile") MultipartFile projectFile) {

        Preconditions.checkNotNull(projectFile != null, "Project file is null");

        String fileName = projectFile.getOriginalFilename();
        System.out.println(fileName);
        Project project = new Project();
        project.setApplicant(applicant);
        project.setTitle(title);


        String saveFileName = fileName.substring(0,fileName.indexOf(".")) + "_"+project.getApplicant()+"_"+
                System.currentTimeMillis()+fileName.substring(fileName.indexOf("."));

        System.out.println("filename " + saveFileName + "     title : " + project.getTitle());


        try {
            File directory = new File("");//参数为空
            String filePath = directory.getCanonicalPath() +"\\src\\main\\webapp\\upload\\project\\"+project.getApplicant()+"\\"+saveFileName;
           // String path=new File(getClass().getClassLoader().getResource("/main/webapp/upload").toURI()).getPath();
            logger.info("pasth "  + filePath);

            File targetFile = new File(filePath);


            if (!targetFile.exists()) {
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();
            }

            projectFile.transferTo(targetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = projectService.insertProject(project);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(projectFile);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteProjectById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Project id is illegal");

        int count = projectService.deleteProjectById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateProjectById(@RequestBody Project project) {
        Preconditions.checkNotNull(project != null && project.getId() != 0, "Project id is illegal");

        int count = projectService.updateProjectById(project);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(project);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Project id is illegal");


        Project project = projectService.getProjectById(id);

        return ResponseEntity.ok(project);
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjects(Pagination pagination) {
        Preconditions.checkNotNull(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkNotNull(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Project> projects = projectService.getProjects(pagination);

        return ResponseEntity.ok(projects);
    }
}
