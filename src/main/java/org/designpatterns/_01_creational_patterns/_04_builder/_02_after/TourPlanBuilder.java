package org.designpatterns._01_creational_patterns._04_builder._02_after;

import org.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;

public interface TourPlanBuilder {

    TourPlanBuilder title(String title); // 메소드 체이닝을 위해 return type을 TourPlanBuilder

    TourPlanBuilder nightsAndDays(int nights, int days);

    TourPlanBuilder startDate(LocalDate localDate);

    TourPlanBuilder whereToStay(String whereToStay);

    TourPlanBuilder addPlan(int day, String plan);


    TourPlan getPlan(); // getPlan 구현할때 인스턴스가 정상적인지 검증하는 로직을 넣기 좋다!
}
