package dev.tinajero.services;

import dev.tinajero.models.Reimbursement;
import dev.tinajero.repos.ReimbursementRepo;

import java.math.BigDecimal;

public class ReimbursementService {

    ReimbursementRepo reimbursementRepo = new ReimbursementRepo();

    public boolean reimbursementInfo(String courseStart, String time, String place, String description, BigDecimal cost, BigDecimal coveredCost, String grading, BigDecimal eventType, boolean important, int empId, String username, String justification){
    //sets a reimbursement object to the fields so that it can pass the object to the reimbursement repo
    //where it will be handled by the update method in the repo.
        Reimbursement re = new Reimbursement();
        re.setCourseStartdate(courseStart);
        re.setTime(time);
        re.setDescription(description);
        re.setPlace(place);
        re.setMoney(cost);
        re.setReimburse(coveredCost);
        re.setGrading(grading);
        re.setEventType(eventType);
        re.setUrgent(important);
        re.setEmployeeId(empId);
        re.setEmployeeUser(username);
        re.setJustification(justification);
        if(re != null) {//if the object is not empty it will execute the update and return the response
            boolean response = reimbursementRepo.update(re);
            return response;
        }
        System.out.println("could not update the database with the reimbursement info");
        return false;
    }


}
