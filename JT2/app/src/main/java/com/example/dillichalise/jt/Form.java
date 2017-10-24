package com.example.dillichalise.jt;

/**
 * Created by dillichalise on 7/25/17.
 */

public class Form {

    String jobtitle, skills, company, email, mn, location;
    private String id;

    public String getJobtitle() {
        return this.jobtitle;
    }


    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getSkills() {
        return this.skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getMn() {
        return this.mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId(){
        return id;
    }
}
