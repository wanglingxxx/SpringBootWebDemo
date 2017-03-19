package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Notice;
import com.research.model.Pagination;
import com.research.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertNotice(@RequestBody Notice notice) {

        Preconditions.checkArgument(notice != null, "Notice is null");

        int count = noticeService.insertNotice(notice);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notice);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteNoticeById(@PathVariable("id") Integer id) {
        Preconditions.checkArgument(id != 0, "Notice id is illegal");

        int count = noticeService.deleteNoticeById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateNoticeById(@RequestBody Notice notice) {
        Preconditions.checkArgument(notice != null && notice.getId() != 0, "Notice id is illegal");

        int count = noticeService.updateNoticeById(notice);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notice);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getNoticeById(@PathVariable("id") Integer id) {
        Preconditions.checkArgument(id != 0, "Notice id is illegal");


        Notice notice = noticeService.getNoticeById(id);

        return ResponseEntity.ok(notice);
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getNotices(Pagination pagination) {
        Preconditions.checkArgument(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkArgument(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Notice> notices = noticeService.getNotices(pagination);

        return ResponseEntity.ok(notices);
    }
}
