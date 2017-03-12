package com.research.repository;

import com.research.mapper.PoliticsMapper;
import com.research.model.Pagination;
import com.research.model.Politics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoliticsRepositoryImpl implements PoliticsRepository{

    @Autowired
    PoliticsMapper politicsMapper;


    public int insertPolitics(Politics politics) {
        return politicsMapper.insertPolitics(politics);
    }

    public int deletePoliticsById(Integer id) {
        return politicsMapper.deletePoliticsById(id);
    }

    public int updatePoliticsById(Politics politics) {
        return politicsMapper.updatePoliticsById(politics);
    }

    public Politics getPoliticsById(Integer id) {
        return politicsMapper.getPoliticsById(id);
    }

    public List<Politics> getPolitics(Pagination pagination) {
        return politicsMapper.getPolitics(pagination);
    }

    public Integer getPoliticsCounts() {
        return politicsMapper.getPoliticsCounts();
    }
}
