package com.research.controller;

import com.google.common.base.Preconditions;
import com.research.model.Politics;
import com.research.model.Pagination;
import com.research.service.PoliticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/politics")
public class PoliticsController {
    @Autowired
    PoliticsService politicsService;

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> insertPolitics(@RequestBody Politics politics) {

        Preconditions.checkNotNull(politics != null, "Politics is null");

        int count = politicsService.insertPolitics(politics);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(politics);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> deletePoliticsById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Politics id is illegal");

        int count = politicsService.deletePoliticsById(id);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(id);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT,consumes = {"application/json"},produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> updatePoliticsById(@RequestBody Politics politics) {
        Preconditions.checkNotNull(politics != null && politics.getId() != 0, "Politics id is illegal");

        int count = politicsService.updatePoliticsById(politics);

        if(count == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(politics);
        }
    }

    @RequestMapping(value = "/{id}/", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getPoliticsById(@PathVariable("id") Integer id) {
        Preconditions.checkNotNull(id != 0, "Politics id is illegal");


        Politics politics = politicsService.getPoliticsById(id);

        return ResponseEntity.ok(politics);
    }
    @RequestMapping(value = "/query", method = RequestMethod.GET,produces = {"application/json;charset=utf-8"})
    ResponseEntity<?> getPoliticss(Pagination pagination) {
        Preconditions.checkNotNull(pagination.getPageIndex() > 0 ,"PageIndex is illegal");
        Preconditions.checkNotNull(pagination.getPageSize() > 0 ,"PageSize is illegal");

        List<Politics> politicss = politicsService.getPolitics(pagination);

        return ResponseEntity.ok(politicss);
    }
}
