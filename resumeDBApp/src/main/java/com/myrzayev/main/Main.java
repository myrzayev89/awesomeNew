/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.main;

import com.myrzayev.daoInter.UserDaoInter;
import com.myrzayev.entity.User;

/**
 *
 * @author MyRzayev
 */
public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.instanceUserDao();
        User u = new User(0, "Rufet", "Rzayev", null, null, null, null, null, null, null);
        userDao.addUser(u);
        System.out.println("Istifadeci elave edildi");
    }
}
