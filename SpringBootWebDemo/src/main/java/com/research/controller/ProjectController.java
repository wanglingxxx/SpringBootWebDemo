package com.research.controller;

import com.alibaba.druid.util.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);
    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertProject(@RequestParam("title") String title,
                                    @RequestParam("summary") String summary,
                                    @RequestParam("applicant") String applicant,
                                    @RequestParam("declareUnits") String declareUnits,
                                    @RequestParam("remark") String remark,
                                    @RequestParam("userId") Integer userId,
            @RequestParam("projectFile") MultipartFile projectFile) {

        Preconditions.checkArgument(!StringUtils.isEmpty(title), "Project title is null");
        Preconditions.checkArgument(!StringUtils.isEmpty(summary), "Project summary is null");
        Preconditions.checkArgument(!StringUtils.isEmpty(applicant), "Project applicant is null");
        Preconditions.checkArgument(!StringUtils.isEmpty(declareUnits), "Project declareUnits is null");
        Preconditions.checkArgument(!StringUtils.isEmpty(remark), "Project remark is null");
        Preconditions.checkArgument(userId != null && userId > 0, "Project userId is null");

        String fileName = projectFile.getOriginalFilename();
        Project project = new Project();
        project.setApplicant(applicant);
        project.setTitle(title);
        project.setSummary(summary);
        project.setDeclareUnits(declareUnits);
        project.setRemark(remark);
        project.setAuditState("未通过");
        project.setUserId(userId);

        project.setDeclareTime(new Date());
        project.setLastUpdated(new Date());
        project.setCreatedDate(new Date());

        saveFile(project, fileName, projectFile);


        int count = projectService.insertProject(project);


        if(count == 0) {
            logger.info("/project/add noContent build!");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/project/add result : {}",project);
            return ResponseEntity.ok(project);
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteProjectById(Integer id) {
        Preconditions.checkArgument(id != 0, "Project id is illegal");

        int count = projectService.deleteProjectById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateProjectById(
                                        @RequestParam("id") Integer id,
                                        @RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "summary", required = false) String summary,
                                        @RequestParam(value = "applicant",required = false) String applicant,
                                        @RequestParam(value = "declareUnits",required = false) String declareUnits,
                                        @RequestParam(value = "remark",required = false) String remark,
                                        @RequestParam("projectFile") MultipartFile projectFile) {
        Preconditions.checkArgument(id != null && id > 0, "Project id is null");

        Project project = new Project();
        project.setId(id);
        project.setTitle(title);
        project.setSummary(summary);
        project.setApplicant(applicant);
        project.setDeclareUnits(declareUnits);
        project.setRemark(remark);
        project.setLastUpdated(new Date());
        String fileName = projectFile.getOriginalFilename();

        if(!StringUtils.isEmpty(fileName)) {

            saveFile(project, fileName, projectFile);
        }

        int count = projectService.updateProjectById(project);

        if(count == 0) {
            logger.info("/project/update updateProjectById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/project/update updateProjectById return :{}",project);
            return ResponseEntity.ok(project);
        }
    }

    @RequestMapping(value = "/audit", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> auditProjectById(@RequestParam("id") Integer id,
                                        @RequestParam("auditState") String auditState) {
        Preconditions.checkArgument(id != null && id > 0, "Project id is null");
        Preconditions.checkArgument(!StringUtils.isEmpty(auditState), "Project auditState is null");

        Project project = new Project();
        project.setId(id);
        project.setAuditState(auditState);
        project.setAuditPassTime(new Date());

        int count = projectService.updateProjectById(project);

        if(count == 0) {
            logger.info("/project/audit auditProjectById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/project/audit auditProjectById return :{}",project);
            return ResponseEntity.ok(project);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
        Preconditions.checkArgument(id != 0, "Project id is illegal");


        Project project = projectService.getProjectById(id);
        logger.info("/project/id/ getProjectById return :{}",project);
        return ResponseEntity.ok(project);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjects(Pagination pagination) {
        Preconditions.checkArgument(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkArgument(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Project> projects = projectService.queryProjects(pagination);

        return ResponseEntity.ok(projects);
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getProjectsAll(@RequestParam(value = "conditions", defaultValue = "all") String conditions,
                                     @RequestParam(value = "userId", required = false) Integer userId,
                                     @RequestParam(value = "pageIndex",required = false)Integer pageIndex,
                                     @RequestParam(value = "pageSize",required = false)Integer pageSize) {

        List<Project> projects = null;
        Pagination pagination = new Pagination();
        pagination.setUserId(userId);

        if(conditions.equals("all")) {
            pagination.setPageSize(100);
            projects = projectService.queryProjects(pagination);
        } else if(conditions.equals("recent")) {
            Date date = new Date();
            date.setMonth(date.getMonth()-1);
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
            String condition = formatter.format(date);
            pagination.setDate(condition);
            projects = projectService.queryProjects(pagination);
        } else if(conditions.equals("pass")) {
            pagination.setState("通过");
            projects = projectService.queryProjects(pagination);
        } else if(conditions.equals("unpass")) {
            pagination.setState("未通过");
            projects = projectService.queryProjects(pagination);
        } else if(conditions.equals("top")) {
            if(pageSize != null && pageSize >0){
                pagination.setPageSize(pageSize);
            }else {
                pagination.setPageSize(7);
            }
            pagination.setPageIndex(pageIndex);
            pagination.setState("通过");
            projects = projectService.queryProjects(pagination);
        } else {
            logger.info("/project/queryAll return :{}","");
            return ResponseEntity.noContent().build();
        }


        logger.info("/project/queryAll return :{}"+projects);
        return ResponseEntity.ok(projects);
    }

    /**
     * 封装了保存文件到本地的代码
     * @param project Project
     * @param fileName filename
     * @param projectFile file
     */
    private void saveFile(Project project, String fileName, MultipartFile projectFile) {
        String saveFileName = fileName.substring(0,fileName.indexOf(".")) + "_"+project.getApplicant()+"_"+
                System.currentTimeMillis()+fileName.substring(fileName.indexOf("."));
        try {
            File directory = new File("");//参数为空
            String filePath = directory.getCanonicalPath() +"\\src\\main\\webapp\\upload\\project\\"+project.getApplicant()+"\\"+saveFileName;

            File targetFile = new File(filePath);

            logger.info("/project/add saveProject filePath, fileName:{} ",targetFile,saveFileName);

            if (!targetFile.exists()) {
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();
            }

            project.setProjectFile(saveFileName);

            projectFile.transferTo(targetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
