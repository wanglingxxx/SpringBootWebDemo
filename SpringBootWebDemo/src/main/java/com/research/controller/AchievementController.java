package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Achievement;
import com.research.model.Pagination;
import com.research.service.AchievementService;
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
@RequestMapping("/achievement")
public class AchievementController {

    Logger logger = LoggerFactory.getLogger(AchievementController.class);
    @Autowired
    AchievementService achievementService;

    @RequestMapping(value = "/add", method = RequestMethod.POST,  produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertAchievement(@RequestParam("projectName") String projectName, @RequestParam("projectMan") String projectMan,
            @RequestParam("awardSituation") String awardSituation,@RequestParam("content") String content,
            @RequestParam(value="contentFile", required = false) MultipartFile contentFile) {

        Preconditions.checkArgument(projectName != null && projectName.length() > 0, "Achievement projectName is null");
        Preconditions.checkArgument(projectMan != null && projectMan.length() > 0, "Achievement projectMan is null");
        Preconditions.checkArgument(awardSituation != null && awardSituation.length() > 0, "Achievement awardSituation is null");
        Preconditions.checkArgument(content != null && content.length() > 0, "Achievement content is null");
        //Preconditions.checkArgument(contentFile != null && contentFile.getOriginalFilename().length() > 0, "Achievement contentFile is null");

        Achievement achievement = new Achievement();
        achievement.setProjectName(projectName);
        achievement.setProjectMan(projectMan);
        achievement.setAwardSituation(awardSituation);
        achievement.setContent(content);
        achievement.setPublishDate(new Date());
        achievement.setCreatedDate(new Date());
        achievement.setLastUpdated(new Date());

        String contentFileName = contentFile.getOriginalFilename();

        saveContentFile(achievement, contentFileName, contentFile);

        int count = achievementService.insertAchievement(achievement);

        if(count == 0) {
            logger.info("/achievement/add insertAchievement return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/achievement/add insertAchievement return :{}",achievement);
            achievement.setId(count);
            return ResponseEntity.ok(achievement);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteAchievementById(Integer id) {
        Preconditions.checkArgument(id != 0, "Achievement id is illegal");

        int count = achievementService.deleteAchievementById(id);

        if(count == 0) {
            logger.info("/achievement/delete deleteAchievementById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/achievement/delete deleteAchievementById return :{}",count);
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateAchievementById(Integer id, @RequestParam(value = "projectName", required = false)String projectName,
                String projectMan, @RequestParam(value = "awardSituation", required = false) String awardSituation,
                @RequestParam(value = "content", required = false) String content,@RequestParam(value = "images",required = false) MultipartFile images) {

        Preconditions.checkArgument(id != null && id > 0, "Achievement id is null");
        Preconditions.checkArgument(projectMan != null && projectMan.length() > 0, "Achievement projectMan is null");

        Achievement achievement = new Achievement();
        achievement.setId(id);
        achievement.setProjectName(projectName);
        achievement.setProjectMan(projectMan);
        achievement.setAwardSituation(awardSituation);
        achievement.setContent(content);
        achievement.setLastUpdated(new Date());

        if(images != null) {
            String imagesFileName = images.getOriginalFilename();
            saveImagesFile(achievement, imagesFileName, images);
        }
        logger.info("acceive achievement : " +achievement);
        int count = achievementService.updateAchievementById(achievement);

        if(count == 0) {
            logger.info("/achievement/update updateAchievementById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/achievement/update updateAchievementById return :{}",achievement);
            return ResponseEntity.ok(achievement);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getAchievementById(@PathVariable("id") Integer id) {
        Preconditions.checkArgument(id != 0, "Achievement id is illegal");


        Achievement achievement = achievementService.getAchievementById(id);
        logger.info("/achievement/{id}/ getAchievementById return :{}",achievement);
        return ResponseEntity.ok(achievement);
    }


    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getAchievements(String projectName, String projectMan) {
        Preconditions.checkArgument(projectName != null && projectName.length() > 0 ,"projectName is illegal");
        Preconditions.checkArgument(projectMan != null && projectMan.length() > 0 ,"projectMan is illegal");

        Achievement achievement = new Achievement();
        achievement.setProjectName(projectName);
        achievement.setProjectMan(projectMan);

        achievement = achievementService.getAchievementByObject(achievement);
        logger.info("/achievement/query getAchievements return :{}",achievement);
        return ResponseEntity.ok(achievement);
    }


    @RequestMapping(value = "/queryAll", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getAchievementsAll(@RequestParam(value = "conditions", defaultValue = "all") String conditions,
                                         @RequestParam(value = "pageIndex",required = false)Integer pageIndex,
                                         @RequestParam(value = "pageSize",required = false)Integer pageSize) {

        List<Achievement> achievements = null;
        Pagination pagination = new Pagination();

        if(conditions.equals("all")) {
            if(pageSize != null && pageSize >0){
                pagination.setPageSize(pageSize);
            }else {
                pagination.setPageSize(100);
            }
            achievements = achievementService.getAchievements(pagination);
        } else if(conditions.equals("recent")) {
            Date date = new Date();
            date.setMonth(date.getMonth()-1);
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
            String condition = formatter.format(date);
            achievements = achievementService.queryAchievements(condition ,"");
        }  else if(conditions.equals("top")) {
            pagination.setPageIndex(pageIndex);
            if(pageSize != null && pageSize >0){
                pagination.setPageSize(pageSize);
            }else {
                pagination.setPageSize(7);
            }
            achievements = achievementService.getAchievements(pagination);
        } else {
            logger.info("/achievement/queryAll return :{}","");
            return ResponseEntity.noContent().build();
        }


        logger.info("/achievement/queryAll return :{}"+achievements);
        return ResponseEntity.ok(achievements);
    }
    /**
     * 封装了保存文件到本地的代码
     * @param achievement achievement
     * @param contentFileName contentFileName
     * @param projectFile projectFile
     */
    private void saveContentFile(Achievement achievement, String contentFileName,MultipartFile projectFile) {
        String contentFile = contentFileName.substring(0,contentFileName.indexOf(".")) + "_"+achievement.getProjectMan()+"_"+
                System.currentTimeMillis()+contentFileName.substring(contentFileName.indexOf("."));

        try {
            File directory = new File("");//参数为空
            String contentfilePath = directory.getCanonicalPath() +"\\src\\main\\webapp\\upload\\achievement\\"+achievement.getProjectMan()+"\\content\\"+contentFile;

            File TargetFile = new File(contentfilePath);

            logger.info("/achievement/add saveAchievement contentFilePath, fileName:{} ",TargetFile,contentFile);

            if (!TargetFile.exists()) {
                TargetFile.getParentFile().mkdirs();
                TargetFile.createNewFile();
            }

            achievement.setContentFile(contentFile);

            projectFile.transferTo(TargetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存图片
     * @param achievement achievement
     * @param imagesFileName imagesFileName
     * @param projectFile projectFile
     */
    private void saveImagesFile(Achievement achievement, String imagesFileName,MultipartFile projectFile) {
        String imagesFile = imagesFileName.substring(0,imagesFileName.indexOf(".")) + "_"+achievement.getProjectMan()+"_"+
                System.currentTimeMillis()+imagesFileName.substring(imagesFileName.indexOf("."));

        try {
            File directory = new File("");//参数为空
            String contentfilePath = directory.getCanonicalPath() +"\\src\\main\\webapp\\upload\\achievement\\"+achievement.getProjectMan()+"\\images\\"+imagesFile;

            File TargetFile = new File(contentfilePath);

            logger.info("/achievement/add saveAchievement contentFilePath, fileName:{} ",TargetFile,imagesFile);

            if (!TargetFile.exists()) {
                TargetFile.getParentFile().mkdirs();
                TargetFile.createNewFile();
            }

            achievement.setImages(imagesFile);

            projectFile.transferTo(TargetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
