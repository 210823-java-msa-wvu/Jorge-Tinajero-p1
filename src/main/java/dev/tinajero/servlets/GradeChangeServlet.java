package dev.tinajero.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javaws.IconUtil;
import dev.tinajero.models.Reimbursement;
import dev.tinajero.models.Users;
import dev.tinajero.repos.ApprovalRepo;
import dev.tinajero.repos.ReimbursementRepo;
import dev.tinajero.repos.UserRepo;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GradeChangeServlet extends HttpServlet {
    Reimbursement re = new Reimbursement();
    ReimbursementRepo reimbursementRepo = new ReimbursementRepo();
    UserRepo userRepo = new UserRepo();
    ApprovalRepo approvalRepo = new ApprovalRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        //re = om.readValue(req.getReader(), Reimbursement.class);
        String letter = req.getParameter("gradingType");
        int reimbursementId = Integer.parseInt(req.getParameter("reimbursementId"));
        String additional = req.getParameter("additionalInfo");
        String amountChange = req.getParameter("amountChange");
        Cookie[] cookies = req.getCookies();
        Cookie userCookie = cookies[1];
        Users u = userRepo.getByUsername(userCookie.getValue());
        Reimbursement re = reimbursementRepo.getWithId(reimbursementId);
        System.out.println(re);
        if("allow".equals(amountChange)){
            if(u.isDirectS() && u.isDepartH())
                response.sendRedirect("welcomeDS.html");
            else if(u.isDirectS()) {
                response.sendRedirect("welcomeDS.html");
            }
            else if(u.isDepartH()) {
                response.sendRedirect("welcomeDH.html");
            }
            else if(u.isBenCo()) {
                response.sendRedirect("welcomeBenco.html");
            }
            else {
                response.sendRedirect("welcomeEmp.html");
            }
        }
        else if("decline".equals(amountChange)){
            u.setMoney(u.getMoney().add(re.getReimburse()));
            System.out.println(u.getMoney());
            reimbursementRepo.updateReimbursement(reimbursementId, u.getMoney());
            reimbursementRepo.deleteReimbursement(reimbursementId);
            if(u.isDirectS() && u.isDepartH())
                response.sendRedirect("welcomeDS.html");
            else if(u.isDirectS()) {
                response.sendRedirect("welcomeDS.html");
            }
            else if(u.isDepartH()) {
                response.sendRedirect("welcomeDH.html");
            }
            else if(u.isBenCo()) {
                response.sendRedirect("welcomeBenco.html");
            }
            else {
                response.sendRedirect("welcomeEmp.html");
            }
        }

        if(re.isAdditionalInfoSb()){
            approvalRepo.updateAdditionalInfo(additional, reimbursementId, "directSuper");
        }
        if(re.isAdditionalInfoHb()){
            approvalRepo.updateAdditionalInfo(additional, reimbursementId, "departmentH");
        }
        if(re.isAdditionalInfoBenb()){
            approvalRepo.updateAdditionalInfo(additional, reimbursementId, "benCo");
        }
        if(reimbursementRepo.updateGrade(u.getId(), reimbursementId, letter)) {
            if(u.isDirectS() && u.isDepartH())
                response.sendRedirect("welcomeDS.html");
            else if(u.isDirectS()) {
                response.sendRedirect("welcomeDS.html");
            }
            else if(u.isDepartH()) {
                response.sendRedirect("welcomeDH.html");
            }
            else if(u.isBenCo()) {
                response.sendRedirect("welcomeBenco.html");
            }
            else {
                response.sendRedirect("welcomeEmp.html");
            }
        }
        else
            System.out.println("could not process the request - from grade servlet");
    }
}
