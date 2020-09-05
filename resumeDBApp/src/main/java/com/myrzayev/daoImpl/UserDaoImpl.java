/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.daoImpl;

import com.myrzayev.entity.Country;
import com.myrzayev.entity.User;
import com.myrzayev.daoInter.AbstractDao;
import com.myrzayev.daoInter.UserDaoInter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MyRzayev
 */
public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    private User getUser(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");
        String surname = res.getString("surname");
        String phone = res.getString("phone");
        String email = res.getString("email");
        String profilDesc = res.getString("profile_desc");
        String adress = res.getString("address");
        int nationalityId = res.getInt("nationality_id");
        int birthplaceId = res.getInt("birthplace_id");
        String nationalityStr = res.getString("nationality");
        String birthplaceStr = res.getString("birthplace");
        Date birthdate = res.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, phone, email, profilDesc, adress, birthdate, nationality, birthplace);
    }

    private User getUserForLogin(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");
        String surname = res.getString("surname");
        String phone = res.getString("phone");
        String email = res.getString("email");
        String profilDesc = res.getString("profile_desc");
        String adress = res.getString("address");
        int nationalityId = res.getInt("nationality_id");
        int birthplaceId = res.getInt("birthplace_id");
        Date birthdate = res.getDate("birthdate");

        return new User(id, name, surname, phone, email, profilDesc, adress, birthdate, null, null);
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationality_id) {
        List<User> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i

            String sql = "select "
                    + "u.*, "
                    + "n.nationality, "
                    + "c.name as birthplace "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where 1=1 ";
            if (name != null && !name.trim().isEmpty()) {
                sql += "and u.name=? ";
            }
            if (surname != null && !surname.trim().isEmpty()) {
                sql += "and u.surname=? ";
            }
            if (nationality_id != null) {
                sql += "and nationality_id=? ";
            }

            PreparedStatement stat = c.prepareStatement(sql);
            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stat.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                stat.setString(i, surname);
                i++;
            }
            if (nationality_id != null) {
                stat.setInt(i, nationality_id);
            }
            
            stat.execute();
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                User u = getUser(res);
                list.add(u);
            }
        } catch (Exception ex) {
        }
        return list;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1,email);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getUserForLogin(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?, surname=?, phone=?, email=?, profile_desc=?, address=?, birthdate=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfilDesc());
            stmt.setString(6, u.getAdress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stat = c.createStatement();
            return stat.execute("delete from user where id = " + id);
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public User getById(int id) {
        User result = null;
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select "
                    + "u.*, "
                    + "n.nationality, "
                    + "c.name as birthplace "
                    + "from user u "
                    + "left join country n on u.nationality_id = n.id "
                    + "left join country c on u.birthplace_id = c.id where u.id = " + id);
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                result = getUser(res);
            }
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email,profile_desc,address,birthdate) values(?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getProfilDesc());
            stmt.setString(6, u.getAdress());
            stmt.setDate(7, u.getBirthDate());
            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }
}
