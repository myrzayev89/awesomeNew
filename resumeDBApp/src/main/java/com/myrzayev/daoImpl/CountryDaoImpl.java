/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.daoImpl;

import com.myrzayev.entity.Country;
import com.myrzayev.daoInter.AbstractDao;
import com.myrzayev.daoInter.CountryDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyRzayev
 */
public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {

    private Country getCountry(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");
        String nationality = res.getString("nationality");

        Country country = new Country(id, name, nationality);
        return country;
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select * from country");
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                Country country = getCountry(res);
                list.add(country);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateCountry(Country con) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update country set name=?, nationality=? where id=?");
            stmt.setString(1, con.getName());
            stmt.setString(2, con.getNationality());
            stmt.setInt(3, con.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeCountry(int id) {
        try (Connection c = connect()) {
            Statement stat = c.createStatement();
            return stat.execute("delete from country where id = " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
