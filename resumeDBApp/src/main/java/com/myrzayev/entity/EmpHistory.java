/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myrzayev.entity;

import java.sql.Date;

/**
 *
 * @author MyRzayev
 */
public class EmpHistory {
    private Integer id;
    private String header;
    private Date begin_date;
    private Date end_date;
    private String job_desc;
    private User user;

    public EmpHistory() {
    }

    public EmpHistory(Integer id, String header, Date start_date, Date end_date, String job_desc, User user) {
        this.id = id;
        this.header = header;
        this.begin_date = start_date;
        this.end_date = end_date;
        this.job_desc = job_desc;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getStart_date() {
        return begin_date;
    }

    public void setStart_date(Date start_date) {
        this.begin_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    @Override
    public String toString() {
        return "EmploymentHistory{" + "id=" + id + ", header=" + header + ", begin_date=" + begin_date + ", end_date=" + end_date + ", job_desc=" + job_desc + ", user=" + user + '}';
    }
}
