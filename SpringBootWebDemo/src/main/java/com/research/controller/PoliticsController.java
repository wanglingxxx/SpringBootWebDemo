package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Politics;
import com.research.model.Pagination;
import com.research.service.PoliticsService;
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
@RequestMapping("/politics")
public class PoliticsController {

    Logger logger = LoggerFactory.getLogger(PoliticsController.class);

    @Autowired
    PoliticsService politicsService;

    @RequestMapping(value = "/add", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertPolitics(String title, String author, String origin,
                                     @RequestParam("contentFile") MultipartFile contentFile) {

        Preconditions.checkNotNull(contentFile != null && contentFile.getOriginalFilename().length() > 0, "Politics contentFile is null");
        Preconditions.checkNotNull(title != null, "Politics title is null");
        Preconditions.checkNotNull(author != null, "Politics author is null");
        Preconditions.checkNotNull(origin != null, "Politics origin is null");

        String fileName = contentFile.getOriginalFilename();
        Politics politics = new Politics();
        politics.setTitle(title);
        politics.setAuthor(author);
        politics.setOrigin(origin);
        politics.setCreatedDate(new Date());
        politics.setLastUpdated(new Date());
        politics.setDate(new Date());

        saveFile(politics, fileName, contentFile);

        int count = politicsService.insertPolitics(politics);

        if(count == 0) {
            logger.info("/politics/add result : {}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/politics/add result : {}",politics);
            return ResponseEntity.ok(politics);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deletePoliticsById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Politics id is illegal");

        int count = politicsService.deletePoliticsById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updatePoliticsById(@RequestBody Politics politics) {
        Preconditions.checkNotNull(politics != null && politics.getId() != 0, "Politics id is illegal");

        int count = politicsService.updatePoliticsById(politics);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(politics);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getPoliticsById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Politics id is illegal");


        Politics politics = politicsService.getPoliticsById(id);

        return ResponseEntity.ok(politics);
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getPoliticss(Pagination pagination) {
        Preconditions.checkNotNull(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkNotNull(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Politics> politicss = politicsService.getPolitics(pagination);

        return ResponseEntity.ok(politicss);
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getPoliticsAll(@RequestParam(value = "conditions", defaultValue = "all") String conditions) {

        List<Politics> projects = null;
        Pagination pagination = new Pagination();

        if(conditions.equals("all")) {
            pagination.setPageSize(100);
            projects = politicsService.getPolitics(pagination);
        } else if(conditions.equals("recent")) {
            Politics project = new Politics();
            Date date = new Date();
            date.setMonth(date.getMonth()-1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String condition = formatter.format(date);
            projects = politicsService.queryProjects(condition ,"");
        }


        logger.info("/politics/queryAll return :{}"+projects);
        return ResponseEntity.ok(projects);
    }

    /**
     * 封装了保存文件到本地的代码
     * @param politics politics
     * @param fileName filename
     * @param projectFile file
     */
    private void saveFile(Politics politics, String fileName, MultipartFile projectFile) {
        String saveFileName = fileName.substring(0,fileName.indexOf(".")) + "_"+politics.getAuthor()+"_"+
                System.currentTimeMillis()+fileName.substring(fileName.indexOf("."));
        try {
            File directory = new File("");//参数为空
            String filePath = directory.getCanonicalPath() +"\\src\\main\\webapp\\upload\\politics\\"+politics.getAuthor()+"\\"+saveFileName;

            File targetFile = new File(filePath);

            logger.info("/politics/add Politics filePath, fileName:{} ",targetFile,saveFileName);

            if (!targetFile.exists()) {
                targetFile.getParentFile().mkdirs();
                targetFile.createNewFile();
            }

            politics.setContentFile(saveFileName);

            projectFile.transferTo(targetFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
