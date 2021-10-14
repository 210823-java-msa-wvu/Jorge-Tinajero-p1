package dev.tinajero.services;

import dev.tinajero.repos.CourseRepo;

import java.math.BigDecimal;

public class CourseService {

    CourseRepo courseRepo = new CourseRepo();

    public BigDecimal percentReturn(int eventType){

        return courseRepo.getPercent(eventType); //call function to get the percentage worth of the event for reimbursement

    }
}
