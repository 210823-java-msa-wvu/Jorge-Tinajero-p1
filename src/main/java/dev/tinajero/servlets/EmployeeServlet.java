package dev.tinajero.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tinajero.models.Reimbursement;
import dev.tinajero.models.Users;
import dev.tinajero.repos.ReimbursementRepo;
import dev.tinajero.repos.UserRepo;
import dev.tinajero.services.CourseService;
import dev.tinajero.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class EmployeeServlet extends HttpServlet {
    UserRepo userRepo = new UserRepo();
    ReimbursementRepo reimbursementRepo = new ReimbursementRepo();
    private String pattern = "yyyy-MM-dd";//format in which the date will be displayed
    String date =new SimpleDateFormat(pattern).format(new Date());
    ReimbursementService reimbursementService = new ReimbursementService();
    CourseService courseService = new CourseService();
    ObjectMapper om = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();//get cookies into array
        Cookie userName = cookies[1];//select cookie 2 that has username stored
        List<Reimbursement> arr;
        Users u = userRepo.getByUsername(userName.getValue());
        arr = reimbursementRepo.getAllReimbursements(u.getId());
        String reimbursementString = om.writeValueAsString(arr);
//        Cookie reimburse = new Cookie("reimbursement", reimbursementString);
//        reimburse.setSecure(true);
//        System.out.println(reimbursementString);
//        reimburse.setMaxAge(60*60*24);
//        response.addCookie(reimburse);
//        System.out.println(reimbursementString);
        if(reimbursementString != null) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.getWriter().write(reimbursementString);
        }
        else
            System.out.println("did not set status");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Cookie cookie = null;
        Cookie[] cookies = req.getCookies(); //get an array of cookies

        Cookie userCookie = cookies[1];
        Cookie updatedCookie = cookies[0];

        Users u = userRepo.getByUsername(userCookie.getValue());
//        System.out.println(u);
        try {

            String time = req.getParameter("time");
            String todayDate = date.replaceAll("-","");
            String courseDate = req.getParameter("courseStartdate");
            String courseNospace = courseDate.replaceAll("-","");
            String place = req.getParameter("place");
            Integer dateDifference = Integer.parseInt(courseNospace) - Integer.parseInt(todayDate);
            String description = req.getParameter("description");
            BigDecimal cost = BigDecimal.valueOf(Long.parseLong(req.getParameter("cost")));
            String grading = req.getParameter("gradingType");
            String justification = req.getParameter("justification");
            BigDecimal eventType = courseService.percentReturn(Integer.parseInt(req.getParameter("eventType")));
            BigDecimal coveredCost = cost.multiply(eventType);
            int empId = u.getId();
            boolean urgent = false;

            if(coveredCost.intValue() >  u.getMoney().intValue()){
//                System.out.println("user doesnt have enough to cover it");
                coveredCost = u.getMoney();
                u.setMoney(u.getMoney().subtract(coveredCost));
                if(u.getMoney().intValue() <= 0) {
                    u.setMoney(BigDecimal.valueOf(0));
                    userRepo.updateUser(u.getUserName(), u.getMoney());
                    if (updatedCookie != null) {
                        updatedCookie.setValue(om.writeValueAsString(u));
                        resp.addCookie(updatedCookie);
                    }
                        if (dateDifference < 14)
                            urgent = true;
                        else if (dateDifference > 31) { //solves the problem of having a few days difference between months
                            dateDifference = dateDifference - 70;
                            if (dateDifference <= 14)
                                urgent = true;
                        }
                    //if the information was correctly input and is successfully uploaded to the database the user is redirected
                    //to the welcome page
                    if (reimbursementService.reimbursementInfo(courseDate, time, place, description, cost, coveredCost, grading, eventType, urgent, empId, u.getUserName(), justification)) {
                        System.out.println("inputted into data base - servlet");
                        if(u.isDirectS())
                            resp.sendRedirect("welcomeDS.html");
                        else if(u.isDepartH())
                            resp.sendRedirect("welcomeDH.html");
                        else if(u.isBenCo())
                            resp.sendRedirect("welcomeBenco.html");
                        else
                        resp.sendRedirect("welcomeEmp.html");
                    } else
                        System.out.println("failed to put variables into db - from reimbursement servlet");

                }
            }
            else {
            //this makes sure that if the test is in less than 2 weeks it will mark it as urgent
                u.setMoney(u.getMoney().subtract(coveredCost));
                userRepo.updateUser(u.getUserName(), u.getMoney());
                if (updatedCookie != null) {
                    updatedCookie.setValue(om.writeValueAsString(u));
                    resp.addCookie(updatedCookie);
                }
                    if (dateDifference < 14)
                        urgent = true;
                    else if (dateDifference > 31) { //solves the problem of having a few days difference between months
                        dateDifference = dateDifference - 70;
                        if (dateDifference <= 14)
                            urgent = true;
                    }
                //if the information was correctly input and is successfully uploaded to the database the user is redirected
                //to the welcome page
                if (reimbursementService.reimbursementInfo(courseDate, time, place, description, cost, coveredCost, grading, eventType, urgent, empId, u.getUserName(), justification)) {
                    System.out.println("inputted into data base - servlet");
                    if(u.isDirectS()) {
                        resp.sendRedirect("welcomeDS.html");
                    }
                    else if(u.isDepartH()) {
                        resp.sendRedirect("welcomeDH.html");
                    }
                    else if(u.isBenCo()) {
                        resp.sendRedirect("welcomeBenco.html");
                    }
                    else {
                        resp.sendRedirect("welcomeEmp.html");
                    }
                } else
                    System.out.println("failed to put variables into db - from reimbursement servlet");
            }
        }catch(NullPointerException | NumberFormatException e){ //catches if the field is blank or if there was a formatting error
            System.out.println("one of the fields was blank - from employee servlet ");
            resp.sendRedirect("Reimbursement.html");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
