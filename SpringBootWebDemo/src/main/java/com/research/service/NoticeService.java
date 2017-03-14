package com.research.service;

import com.research.mapper.NoticeMapper;
import com.research.model.Notice;
import com.research.model.Pagination;
import com.research.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    public int insertNotice(Notice notice) {
        return noticeMapper.insertNotice(notice);
    }

    public int deleteNoticeById(Integer id) {
        return noticeMapper.deleteNoticeById(id);
    }

    public int updateNoticeById(Notice notice) {
        return noticeMapper.updateNoticeById(notice);
    }

    public Notice getNoticeById(Integer id) {
        return noticeMapper.getNoticeById(id);
    }

    public List<Notice> getNotices(Pagination pagination) {
        return noticeMapper.getNotices(pagination);
    }

    public Integer getNoticeCounts() {
        return noticeMapper.getNoticeCounts();
    }
}
