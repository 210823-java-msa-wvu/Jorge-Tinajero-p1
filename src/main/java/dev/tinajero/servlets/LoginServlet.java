package dev.tinajero.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tinajero.models.Reimbursement;
import dev.tinajero.models.Users;
import dev.tinajero.repos.ReimbursementRepo;
import dev.tinajero.repos.UserRepo;
import dev.tinajero.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class LoginServlet extends HttpServlet {
    UserRepo userRepo = new UserRepo();
    LoginService loginService = new LoginService();
    ObjectMapper om = new ObjectMapper();
    ReimbursementRepo reimbursementRepo = new ReimbursementRepo();
    Reimbursement reimbursement = new Reimbursement();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = (request.getParameter("username"));//getting the username and password from the html page
        String password = (request.getParameter("password"));//using the request feature.

        Users u = userRepo.getByUsername(username);
        String empInf = om.writeValueAsString(u);
        String user = om.writeValueAsString(request.getParameter("username"));
        Cookie userInfo = new Cookie("username", empInf);
        Cookie userName = new Cookie("userCookie", user);
        // Set expiry date after 24 Hrs for both the cookies.
        userInfo.setMaxAge(60*60*24);
        userName.setMaxAge(60*60*24);
        //pass.setMaxAge(60*60*24);

        // Add both the cookies in the response header.
        response.addCookie(userInfo);
        response.addCookie(userName);

        //response.addCookie( pass );

        //the following boolean variables are set to either true or false depenging on the result of the login service
        boolean signInBenco = loginService.loginBenco(username,password);
        boolean signInDH = loginService.loginDH(username,password);
        boolean signInDS = loginService.loginDS(username,password);
        boolean signInEmp = loginService.login(username,password);

        if(signInDH && signInDS)
            response.sendRedirect("/p1/welcomeDS.html");
        else if (signInBenco){ //if else block checks what the user is and redirects them to their corresponding page
            response.sendRedirect("/p1/welcomeBenco.html");
        }
        else if(signInDH){
            response.sendRedirect("/p1/welcomeDH.html");
        }
        else if(signInDS){
            response.sendRedirect("/p1/welcomeDS.html");
        }
        else if(signInEmp){
            response.sendRedirect("/p1/welcomeEmp.html");
            log.println("success login");
        }
        else {
            response.setStatus(200);
            response.sendRedirect("/p1/login.html");
            System.out.println("Error occurred while obtaining username and password in loginServlet");
        }



    }


}
