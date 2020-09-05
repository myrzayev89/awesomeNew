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
import java.util.List;

@WebServlet(name = "AddUserController", urlPatterns = {"/adduser"})
public class AddUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDaoInter userDao = Context.instanceUserDao();
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String nationalityIdStr = req.getParameter("nid");
            Integer nationalityId = null;
            if (nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
                nationalityId = Integer.parseInt(nationalityIdStr);
            }
            List<User> list = userDao.getAll(name, surname, nationalityId);
            req.setAttribute("usersList", list);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (Exception ex) {
            ControllerUtil.errorPage(resp,ex);
        }
    }

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        User u = new User(0,name,surname,null,null,null,null,null,null,null);
        userDao.addUser(u);
        resp.sendRedirect("index");
    }
}
