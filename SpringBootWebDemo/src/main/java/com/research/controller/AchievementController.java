package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Achievement;
import com.research.model.Pagination;
import com.research.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    @Autowired
    AchievementService achievementService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertAchievement(@RequestBody Achievement achievement) {

        Preconditions.checkNotNull(achievement != null, "Achievement is null");

        int count = achievementService.insertAchievement(achievement);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(achievement);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deleteAchievementById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Achievement id is illegal");

        int count = achievementService.deleteAchievementById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updateAchievementById(@RequestBody Achievement achievement) {
        Preconditions.checkNotNull(achievement != null && achievement.getId() != 0, "Achievement id is illegal");

        int count = achievementService.updateAchievementById(achievement);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(achievement);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getAchievementById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Achievement id is illegal");


        Achievement achievement = achievementService.getAchievementById(id);

        return ResponseEntity.ok(achievement);
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getAchievements(Pagination pagination) {
        Preconditions.checkNotNull(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkNotNull(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Achievement> achievements = achievementService.getAchievements(pagination);

        return ResponseEntity.ok(achievements);
    }
}
