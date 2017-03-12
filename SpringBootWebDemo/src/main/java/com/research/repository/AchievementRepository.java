package com.research.repository;

import com.research.model.Achievement;
import com.research.model.Pagination;

import java.util.List;

public interface AchievementRepository {
    int insertAchievement(Achievement achievement);

    int deleteAchievementById(Integer id);

    int updateAchievementById(Achievement achievement);

    Achievement getAchievementById(Integer id);

    List<Achievement> getAchievements(Pagination pagination);

    Integer getAchievementCounts();
}
