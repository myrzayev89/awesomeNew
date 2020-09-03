/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.daoInter;

import com.myrzayev.entity.Country;
import java.util.List;

/**
 *
 * @author MyRzayev
 */
public interface CountryDaoInter {

    public List<Country> getAllCountry();
    
    public boolean updateCountry(Country c);
    
    public boolean removeCountry(int id);
}
