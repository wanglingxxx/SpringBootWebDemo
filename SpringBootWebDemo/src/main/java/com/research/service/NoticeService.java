package com.research.service;

import com.research.model.Notice;
import com.research.model.Pagination;
import com.research.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeRepository noticeRepository;

    public int insertNotice(Notice notice) {
        return noticeRepository.insertNotice(notice);
    }

    public int deleteNoticeById(Integer id) {
        return noticeRepository.deleteNoticeById(id);
    }

    public int updateNoticeById(Notice notice) {
        return noticeRepository.updateNoticeById(notice);
    }

    public Notice getNoticeById(Integer id) {
        return noticeRepository.getNoticeById(id);
    }

    public List<Notice> getNotices(Pagination pagination) {
        return noticeRepository.getNotices(pagination);
    }

    public Integer getNoticeCounts() {
        return noticeRepository.getNoticeCounts();
    }
}
