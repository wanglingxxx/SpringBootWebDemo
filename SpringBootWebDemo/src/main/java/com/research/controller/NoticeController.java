package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Notice;
import com.research.model.Pagination;
import com.research.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST,  produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertNotice(String title, String content, String publisher, String type) {

        Preconditions.checkArgument(title != null && title.length() > 0, "Notice title is illegal");
        Preconditions.checkArgument(content != null && content.length() > 0, "Notice content is illegal");
        Preconditions.checkArgument(publisher != null && publisher.length() > 0, "Notice publisher is illegal");
        Preconditions.checkArgument(type != null && type.length() > 0, "Notice type is illegal");

        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setPublisher(publisher);
        notice.setType(type);
        notice.setPublishDate(new Date());
        notice.setCreatedDate(new Date());
        notice.setLastUpdated(new Date());

        int count = noticeService.insertNotice(notice);

        if(count == 0) {
            logger.info("/notice/add insertNotice return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/notice/add insertNotice return :{}",notice);
            return ResponseEntity.ok(notice);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteNoticeById(Integer id) {
        Preconditions.checkArgument(id != 0, "Notice id is illegal");

        int count = noticeService.deleteNoticeById(id);

        if(count == 0) {
            logger.info("/notice/delete deleteNoticeById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/notice/delete deleteNoticeById return :{}",count);
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateNoticeById(Integer id, String title, String content, String publisher, String type) {
        Preconditions.checkArgument(id != null && id> 0, "Notice id is illegal");

        Notice notice = new Notice();
        notice.setId(id);
        notice.setTitle(title);
        notice.setContent(content);
        notice.setPublisher(publisher);
        notice.setType(type);
        notice.setLastUpdated(new Date());

        int count = noticeService.updateNoticeById(notice);

        if(count == 0) {
            logger.info("/notice/update updateNoticeById return :{}","");
            return ResponseEntity.noContent().build();
        } else {
            logger.info("/notice/update updateNoticeById return :{}",notice);
            return ResponseEntity.ok(notice);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getNoticeById(@PathVariable("id") Integer id) {
        Preconditions.checkArgument(id != 0, "Notice id is illegal");


        Notice notice = noticeService.getNoticeById(id);
        logger.info("/notice/{id}/ getNoticeById return :{}",notice);
        return ResponseEntity.ok(notice);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getNotices(Pagination pagination) {
        Preconditions.checkArgument(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkArgument(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Notice> notices = noticeService.getNotices(pagination);

        return ResponseEntity.ok(notices);
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getNoticesAll(@RequestParam(value = "conditions", defaultValue = "all") String conditions,
                  @RequestParam(value = "pageIndex",required = false)Integer pageIndex,
                  @RequestParam(value = "type", required =false)String type) {

        List<Notice> notices = null;
        Pagination pagination = new Pagination();

        if(conditions.equals("all")) {
            pagination.setPageSize(100);
            notices = noticeService.getNotices(pagination);
        } else if(conditions.equals("recent")) {
            Date date = new Date();
            date.setMonth(date.getMonth()-1);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String condition = formatter.format(date);
            notices = noticeService.queryNotices(condition ,"");
        } else if(conditions.equals("top")) {
            pagination.setPageIndex(pageIndex);
            pagination.setPageSize(7);
            if(type != null && type.length() > 0) {
                pagination.setState(type);
            }
            notices = noticeService.getNotices(pagination);
        }



        logger.info("/notice/queryAll return :{}"+notices);
        return ResponseEntity.ok(notices);
    }
}
