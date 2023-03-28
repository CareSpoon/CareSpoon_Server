package com.carespoon.controller;

import com.carespoon.Repository.OneMealRepositoryCustom;
import com.carespoon.domain.User;
import com.carespoon.dto.OneMealResponseDto;
import com.carespoon.dto.OneMealSaveRequestDto;
import com.carespoon.service.OneMealService;
import com.carespoon.service.UserService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class OneMealController {
    private final OneMealService oneMealService;

    @Autowired
    @Qualifier("oneMealRepositoryImpl")
    private OneMealRepositoryCustom oneMealRepositoryCustom;

    private UserService userService;

    @GetMapping("/dailynurition/{userId}")
    public List<Tuple> getDaily(@PathVariable UUID userId, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        User user = userService.findByUuid(userId);
        List<Tuple> dailyNutrition = oneMealRepositoryCustom.findOneMealByCreatedTime(user, localDate);
        return dailyNutrition;
    }

    @GetMapping("/monthlynutrition/{userId}")
    public List<Tuple> getMonthly(@PathVariable UUID userId, @RequestParam String month) {
        YearMonth yearMonth = YearMonth.parse(month);
        User user = userService.findByUuid(userId);
        List<Tuple> monthlyNutrition = oneMealRepositoryCustom.findOneMealByCreatedMonth(user, yearMonth);
        return monthlyNutrition;
    }

    @PostMapping("/onemeal")
    public Long save(@RequestBody OneMealSaveRequestDto requestDto) {
        return oneMealService.save(requestDto);
    }

    public void save(@RequestBody List<String> menus) {
        OneMealSaveRequestDto requestDto;
        int kcal, protein, carbon, fat;
        for (int i = 0; i < menus.size(); i++) {
            
        }
    }


    @GetMapping("/onemeal/{userId}")
    public List<OneMealResponseDto> findById(@PathVariable UUID userId) {
        return oneMealService.findByUser(userId);
    }


}
