package com.myrzayev.resume.controller;

import com.myrzayev.daoInter.UserDaoInter;
import com.myrzayev.entity.User;
import com.myrzayev.main.Context;
import com.myrzayev.resume.util.ControllerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            User user = userDao.findByEmailAndPassword(email, pass);
            if (user == null) {
                throw new IllegalArgumentException("Email or Password incorrect!");
            }
            req.getSession().setAttribute("loggedInUser", user);
            resp.sendRedirect("index");
        } catch (Exception ex) {
            ControllerUtil.errorPage(resp,ex);
        }
    }
}
