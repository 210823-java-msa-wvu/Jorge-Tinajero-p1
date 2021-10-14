package dev.tinajero.models;

import java.math.BigDecimal;

public class Users {
    private int id;
    private String userName;
    private String password;
    private BigDecimal money;
    private boolean employee,
    directS, departH, benCo;

    public Users(){}

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Users(int id, String userName, String password, BigDecimal money, boolean employee, boolean directS, boolean departH, boolean benCo) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.money = money;
        this.employee = employee;
        this.directS = directS;
        this.departH = departH;
        this.benCo = benCo;
    }

    public Users(String userName, String password, BigDecimal money, boolean employee, boolean directS, boolean departH, boolean benCo) {
        this.userName = userName;
        this.password = password;
        this.money = money;
        this.employee = employee;
        this.directS = directS;
        this.departH = departH;
        this.benCo = benCo;
    }

//    public boolean getPosition(){
//
//
//        if(benCo){
//            return isBenCo();
//        }
//        else if( employee)
//            return isEmployee();
//        else if(departH)
//            return isDepartH();
//        else if(directS)
//            return isDirectS();
//
//
//        return false;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public boolean isDirectS() {
        return directS;
    }

    public void setDirectS(boolean directS) {
        this.directS = directS;
    }

    public boolean isDepartH() {
        return departH;
    }

    public void setDepartH(boolean departH) {
        this.departH = departH;
    }

    public boolean isBenCo() {
        return benCo;
    }

    public void setBenCo(boolean benCo) {
        this.benCo = benCo;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", employee=" + employee +
                ", directS=" + directS +
                ", departH=" + departH +
                ", benCo=" + benCo +
                '}';
    }
}
