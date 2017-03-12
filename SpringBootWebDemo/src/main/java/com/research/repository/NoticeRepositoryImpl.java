package com.research.repository;

import com.research.mapper.NoticeMapper;
import com.research.model.Notice;
import com.research.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeRepositoryImpl implements NoticeRepository{
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
