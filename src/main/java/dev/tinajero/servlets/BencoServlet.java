package dev.tinajero.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tinajero.models.Reimbursement;
import dev.tinajero.models.Users;
import dev.tinajero.repos.ApprovalRepo;
import dev.tinajero.repos.ReimbursementRepo;
import dev.tinajero.repos.UserRepo;
import dev.tinajero.services.DisplayReimService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BencoServlet extends HttpServlet {
    UserRepo userRepo = new UserRepo();
    ReimbursementRepo reimbursementRepo = new ReimbursementRepo();
    DisplayReimService displayReimService = new DisplayReimService();
    ApprovalRepo approvalRepo = new ApprovalRepo();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();

        Cookie[] cookies = request.getCookies();//get cookies into array
        Cookie userName = cookies[1];//select cookie 2 that has username stored
        List<Reimbursement> reimbursements;
        Users u = userRepo.getByUsername(userName.getValue());
        reimbursements = displayReimService.getAll(u.isDirectS(), u.isDepartH(), u.isBenCo() );
        String allReimbursements = om.writeValueAsString(reimbursements);
        if(allReimbursements != null) {
            response.setStatus(200);
            response.setContentType("text/plain");
            response.getWriter().write(allReimbursements);
        }
        else
            System.out.println("did not set status");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String approval = req.getParameter("approval");
        int appId = Integer.parseInt(req.getParameter("approvalId"));
        String reason = req.getParameter("reason");
        BigDecimal changeAmount = BigDecimal.valueOf(Long.parseLong(req.getParameter("changeAmount")));
        Cookie[] cookies = req.getCookies();
        Cookie userCookie = cookies[1];

        Users u = userRepo.getByUsername(userCookie.getValue());
        Reimbursement reimbursement = reimbursementRepo.getWithId(appId);
        BigDecimal returnedAmount = reimbursement.getReimburse();
        Users emp = userRepo.getUserById(reimbursement.getEmployeeId());
        String additional = req.getParameter("additional");
        Boolean moreInf = false;

        if(changeAmount != reimbursement.getReimburse()) {
            reimbursementRepo.updateReimbursement(appId, changeAmount);
            reimbursementRepo.updateAmountIncrease(appId, true);
        }
        if("true".equals(additional))
            moreInf = true;
        if("deny".equals(approval)){
            approvalRepo.updateReason(appId,reason, "explanation");
            BigDecimal newf = emp.getMoney().add(returnedAmount);
            userRepo.updateUser(emp.getUserName(), newf);
        }
        if(displayReimService.setApproval(u, appId, approval)) {
            //
            reimbursementRepo.updateAdditional(u.getUserName(), appId, moreInf);
            resp.sendRedirect("/p1/welcomeBenco.html");
        }
        else
            System.out.println("could not process the request - from direct super servlet");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
