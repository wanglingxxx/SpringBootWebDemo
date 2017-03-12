package com.research.service;

import com.research.model.Pagination;
import com.research.model.Politics;
import com.research.repository.PoliticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsService {
    @Autowired
    PoliticsRepository politicsRepository;

    public int insertPolitics(Politics politics) {
        return politicsRepository.insertPolitics(politics);
    }

    public int deletePoliticsById(Integer id) {
        return politicsRepository.deletePoliticsById(id);
    }

    public int updatePoliticsById(Politics politics) {
        return politicsRepository.updatePoliticsById(politics);
    }

    public Politics getPoliticsById(Integer id) {
        return politicsRepository.getPoliticsById(id);
    }

    public List<Politics> getPolitics(Pagination pagination) {
        return politicsRepository.getPolitics(pagination);
    }

    public Integer getPoliticsCounts() {
        return politicsRepository.getPoliticsCounts();
    }
}
