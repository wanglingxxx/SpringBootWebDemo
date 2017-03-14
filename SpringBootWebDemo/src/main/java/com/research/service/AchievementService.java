package com.research.service;

import com.research.mapper.AchievementMapper;
import com.research.model.Achievement;
import com.research.model.Pagination;
import com.research.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {
    @Autowired
    AchievementMapper achievementMapper;

    public int insertAchievement(Achievement achievement) {
        return achievementMapper.insertAchievement(achievement);
    }

    public int deleteAchievementById(Integer id) {
        return achievementMapper.deleteAchievementById(id);
    }

    public int updateAchievementById(Achievement achievement) {
        return achievementMapper.updateAchievementById(achievement);
    }

    public Achievement getAchievementById(Integer id) {
        return achievementMapper.getAchievementById(id);
    }

    public List<Achievement> getAchievements(Pagination pagination) {
        return achievementMapper.getAchievements(pagination);
    }

    public Integer getAchievementCounts() {
        return achievementMapper.getAchievementCounts();
    }
}
