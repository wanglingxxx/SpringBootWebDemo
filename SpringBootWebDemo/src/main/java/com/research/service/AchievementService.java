package com.research.service;

import com.research.model.Achievement;
import com.research.model.Pagination;
import com.research.repository.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {
    @Autowired
    AchievementRepository achievementRepository;

    public int insertAchievement(Achievement achievement) {
        return achievementRepository.insertAchievement(achievement);
    }

    public int deleteAchievementById(Integer id) {
        return achievementRepository.deleteAchievementById(id);
    }

    public int updateAchievementById(Achievement achievement) {
        return achievementRepository.updateAchievementById(achievement);
    }

    public Achievement getAchievementById(Integer id) {
        return achievementRepository.getAchievementById(id);
    }

    public List<Achievement> getAchievements(Pagination pagination) {
        return achievementRepository.getAchievements(pagination);
    }

    public Integer getAchievementCounts() {
        return achievementRepository.getAchievementCounts();
    }
}
