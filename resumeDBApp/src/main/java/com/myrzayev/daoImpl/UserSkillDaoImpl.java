/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.daoImpl;

import com.myrzayev.entity.Skill;
import com.myrzayev.entity.User;
import com.myrzayev.entity.UserSkill;
import com.myrzayev.daoInter.AbstractDao;
import com.myrzayev.daoInter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyRzayev
 */
public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet res) throws Exception {
        int userId = res.getInt("id");
        int skillId = res.getInt("skill_id");
        String skillName = res.getString("skill_name");
        int power = res.getInt("power");

        return new UserSkill(null, new User(userId), new Skill(skillId, skillName), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> list = new ArrayList<>();
        try (Connection c = connect()) {//Bu auto close demekdir connection-i
            PreparedStatement stmt = c.prepareStatement("select "
                    + "u.*, "
                    + "us.skill_id, "
                    + "s.name as skill_name, "
                    + "us.power "
                    + "from "
                    + "user_skill us "
                    + "left join user u on us.user_id = u.id "
                    + "left join skill s on us.skill_id = s.id "
                    + "where user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet res = stmt.getResultSet();
            while (res.next()) {
                UserSkill u = getUserSkill(res);
                list.add(u);
            }
        } catch (Exception ex) {
        }
        return list;
    }
}
