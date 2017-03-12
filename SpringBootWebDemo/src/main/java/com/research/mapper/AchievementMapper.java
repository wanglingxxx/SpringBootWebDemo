package com.research.mapper;

import java.util.List;

import com.research.model.Pagination;
import org.apache.ibatis.annotations.Mapper;
import com.research.model.Achievement;

@Mapper
public interface AchievementMapper {

	int insertAchievement(Achievement achievement);
	
	int deleteAchievementById(Integer id);
	
	int updateAchievementById(Achievement achievement);
	
	Achievement getAchievementById(Integer id);
	
	List<Achievement> getAchievements(Pagination pagination);

	Integer getAchievementCounts();
}
