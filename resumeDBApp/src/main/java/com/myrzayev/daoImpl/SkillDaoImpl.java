/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.daoImpl;

import com.myrzayev.daoInter.AbstractDao;
import com.myrzayev.daoInter.SkillDaoInter;
import com.myrzayev.entity.Skill;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyRzayev
 */
public class SkillDaoImpl extends AbstractDao implements SkillDaoInter {

    private Skill getSkill(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");

        Skill sk = new Skill(id, name);
        return sk;
    }
    
    @Override
    public List<Skill> getAllSkill() {
        List<Skill> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            Statement stat = c.createStatement();
            stat.execute("select * from skill");
            ResultSet res = stat.getResultSet();
            while (res.next()) {
                Skill sk = getSkill(res);
                list.add(sk);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    
}
