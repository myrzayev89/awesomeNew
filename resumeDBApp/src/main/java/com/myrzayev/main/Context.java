/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myrzayev.main;

import com.myrzayev.daoImpl.CountryDaoImpl;
import com.myrzayev.daoImpl.EmpHistoryDaoImpl;
import com.myrzayev.daoImpl.UserDaoImpl;
import com.myrzayev.daoImpl.UserSkillDaoImpl;
import com.myrzayev.daoInter.CountryDaoInter;
import com.myrzayev.daoInter.EmpHistoryDaoInter;
import com.myrzayev.daoInter.UserDaoInter;
import com.myrzayev.daoInter.UserSkillDaoInter;

/**
 *
 * @author MyRzayev
 */
public class Context {

    public static UserDaoInter instanceUserDao() {
        return new UserDaoImpl();
    }
    
    public static UserSkillDaoInter instanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }
    
    public static EmpHistoryDaoInter instanceEmpHistoryDao() {
        return new EmpHistoryDaoImpl();
    }
    
    public static CountryDaoInter instanceCountryDao() {
        return new CountryDaoImpl();
    }
    
}
