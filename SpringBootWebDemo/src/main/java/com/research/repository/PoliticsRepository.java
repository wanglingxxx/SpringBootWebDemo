package com.research.repository;

import com.research.model.Pagination;
import com.research.model.Politics;

import java.util.List;

public interface PoliticsRepository {
    int insertPolitics(Politics politics);

    int deletePoliticsById(Integer id);

    int updatePoliticsById(Politics politics);

    Politics getPoliticsById(Integer id);

    List<Politics> getPolitics(Pagination pagination);

    Integer getPoliticsCounts();
}
