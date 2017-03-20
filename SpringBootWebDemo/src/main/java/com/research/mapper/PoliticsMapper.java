package com.research.mapper;

import com.research.model.Pagination;
import com.research.model.Politics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PoliticsMapper {
    int insertPolitics(Politics politics);

    int deletePoliticsById(Integer id);

    int updatePoliticsById(Politics politics);

    Politics getPoliticsById(Integer id);

    List<Politics> getPolitics(Pagination pagination);

    Integer getPoliticsCounts();

    List<Politics> queryProjects(@Param("date") String date, @Param("state") String state);
}
