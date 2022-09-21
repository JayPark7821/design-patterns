package org.designpatterns._01_creational_patterns._04_builder._02_after;

import org.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        TourPlanBuilder builder = new DefaultTourBuilder();
        TourPlan plan = builder.title("칸쿤 여행")
                .nightsAndDays(2, 3)
                .startDate(LocalDate.of(2020, 2, 2))
                .whereToStay("리조트")
                .addPlan(0, "체크인하고 짐풀기")
                .addPlan(0, "저녁 식사")
                .getPlan();

        TourPlan longBeachTrip = builder.title("롱비치")
                .startDate(LocalDate.of(2020, 1, 2))
                .getPlan();
    }
}
