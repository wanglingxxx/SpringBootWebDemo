package com.research.mapper;

import com.research.model.Notice;
import com.research.model.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {
    int insertNotice(Notice notice);

    int deleteNoticeById(Integer id);

    int updateNoticeById(Notice notice);

    Notice getNoticeById(Integer id);

    List<Notice> getNotices(Pagination pagination);

    Integer getNoticeCounts();

    List<Notice> queryNotices(@Param("date")String date,@Param("state") String state);
}
