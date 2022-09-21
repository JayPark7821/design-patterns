package org.designpatterns._01_creational_patterns._04_builder._02_after;

import org.designpatterns._01_creational_patterns._04_builder._01_before.TourPlan;

import java.time.LocalDate;

public class InstanceTourBuilder implements TourPlanBuilder {

    private TourPlan tourPlan;

    public TourPlanBuilder newInstnace() {
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.tourPlan.setTitle(title);
        return this;
    }

}
