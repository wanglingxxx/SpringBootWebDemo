package com.research.repository;

import com.research.model.Notice;
import com.research.model.Pagination;

import java.util.List;

public interface NoticeRepository {
    int insertNotice(Notice notice);

    int deleteNoticeById(Integer id);

    int updateNoticeById(Notice notice);

    Notice getNoticeById(Integer id);

    List<Notice> getNotices(Pagination pagination);

    Integer getNoticeCounts();
}
