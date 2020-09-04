package com.myrzayev.resume.controller;

import com.myrzayev.daoInter.UserDaoInter;
import com.myrzayev.entity.User;
import com.myrzayev.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserController", urlPatterns = {"/userdetail"})
public class UserDetailController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userIdStr = req.getParameter("id");
            if (userIdStr == null && userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Id is not specified");
            }
            Integer userId = Integer.parseInt(req.getParameter("id"));
            UserDaoInter userDao = Context.instanceUserDao();
            User user = userDao.getById(userId);
            if (user == null) {
                throw new IllegalArgumentException("Idisi " + userId + " olan Istifadeci tapilmadi!");
            }
            req.setAttribute("user", user);
            req.getRequestDispatcher("userdetail.jsp").forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.sendRedirect("error?msg=" + ex.getMessage());
        }
    }

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        String action = req.getParameter("action");
        if(action.equals("update")) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            User u = userDao.getById(id);
            u.setName(name);
            u.setSurname(surname);
            userDao.updateUser(u);
        } else if(action.equals("delete")) {
            userDao.removeUser(id);
        }
        resp.sendRedirect("index");
    }

}
