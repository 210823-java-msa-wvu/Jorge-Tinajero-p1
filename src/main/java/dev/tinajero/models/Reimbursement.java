package dev.tinajero.models;

import java.math.BigDecimal;
import java.util.Date;

public class Reimbursement {
    private int id;
    private int employeeId;
    private String employeeUser;
    private String courseStartdate;
//    private String reqDate;
    private String time;
    private String place;
    private String description;
    private BigDecimal money;
    private BigDecimal reimburse;
    private String grading, justification;
    private BigDecimal eventType;
    private String pass = "N/A";
    private boolean urgent, approved;
    private BigDecimal amountApproved = BigDecimal.valueOf(0);
    private boolean directSuper,departmentHead,benCo, amountIncrease;
    private boolean additionalInfoSb, additionalInfoHb, additionalInfoBenb;
    private String additionalInfoS = "", additionalInfoH="", additionalInfoBen="";
    private String explanationDS="", explanationDH="", explanation="";

    public Reimbursement(){}

    public Reimbursement(int id, int employeeId, String employeeUser, String courseStartdate, String time, String place, String description, BigDecimal money, BigDecimal reimburse, String grading, String justification, BigDecimal eventType, String pass, boolean urgent, boolean approved, BigDecimal amountApproved, boolean directSuper, boolean departmentHead, boolean benCo, boolean additionalInfoSb, boolean additionalInfoHb, boolean additionalInfoBenb, String additionalInfoS, String additionalInfoH, String additionalInfoBen, String explanationDS, String explanationDH, String explanation, boolean amountIncrease) {
        this.id = id;
        this.employeeId = employeeId;
        this.employeeUser = employeeUser;
        this.courseStartdate = courseStartdate;
        this.time = time;
        this.place = place;
        this.description = description;
        this.money = money;
        this.reimburse = reimburse;
        this.grading = grading;
        this.justification = justification;
        this.eventType = eventType;
        this.pass = pass;
        this.urgent = urgent;
        this.approved = approved;
        this.amountApproved = amountApproved;
        this.directSuper = directSuper;
        this.departmentHead = departmentHead;
        this.benCo = benCo;
        this.additionalInfoSb = additionalInfoSb;
        this.additionalInfoHb = additionalInfoHb;
        this.additionalInfoBenb = additionalInfoBenb;
        this.additionalInfoS = additionalInfoS;
        this.additionalInfoH = additionalInfoH;
        this.additionalInfoBen = additionalInfoBen;
        this.explanationDS = explanationDS;
        this.explanationDH = explanationDH;
        this.explanation = explanation;
        this.amountIncrease = amountIncrease;
    }

    public boolean isAmountIncrease() {
        return amountIncrease;
    }

    public void setAmountIncrease(boolean amountIncrease) {
        this.amountIncrease = amountIncrease;
    }

    public String getAdditionalInfoH() {
        return additionalInfoH;
    }

    public void setAdditionalInfoH(String additionalInfoH) {
        this.additionalInfoH = additionalInfoH;
    }

    public String getAdditionalInfoBen() {
        return additionalInfoBen;
    }

    public void setAdditionalInfoBen(String additionalInfoBen) {
        this.additionalInfoBen = additionalInfoBen;
    }

    public String getExplanationDS() {
        return explanationDS;
    }

    public void setExplanationDS(String explanationDS) {
        this.explanationDS = explanationDS;
    }

    public String getExplanationDH() {
        return explanationDH;
    }

    public void setExplanationDH(String explanationDH) {
        this.explanationDH = explanationDH;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeUser() {
        return employeeUser;
    }

    public void setEmployeeUser(String employeeUser) {
        this.employeeUser = employeeUser;
    }

    public String getCourseStartdate() {
        return courseStartdate;
    }

    public void setCourseStartdate(String courseStartdate) {
        this.courseStartdate = courseStartdate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getReimburse() {
        return reimburse;
    }

    public void setReimburse(BigDecimal reimburse) {
        this.reimburse = reimburse;
    }

    public String getGrading() {
        return grading;
    }

    public void setGrading(String grading) {
        this.grading = grading;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public BigDecimal getEventType() {
        return eventType;
    }

    public void setEventType(BigDecimal eventType) {
        this.eventType = eventType;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getAmountApproved() {
        return amountApproved;
    }

    public void setAmountApproved(BigDecimal amountApproved) {
        this.amountApproved = amountApproved;
    }

    public boolean isDirectSuper() {
        return directSuper;
    }

    public void setDirectSuper(boolean directSuper) {
        this.directSuper = directSuper;
    }

    public boolean isDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(boolean departmentHead) {
        this.departmentHead = departmentHead;
    }

    public boolean isBenCo() {
        return benCo;
    }

    public void setBenCo(boolean benCo) {
        this.benCo = benCo;
    }

    public boolean isAdditionalInfoSb() {
        return additionalInfoSb;
    }

    public void setAdditionalInfoSb(boolean additionalInfoSb) {
        this.additionalInfoSb = additionalInfoSb;
    }

    public boolean isAdditionalInfoHb() {
        return additionalInfoHb;
    }

    public void setAdditionalInfoHb(boolean additionalInfoHb) {
        this.additionalInfoHb = additionalInfoHb;
    }

    public boolean isAdditionalInfoBenb() {
        return additionalInfoBenb;
    }

    public void setAdditionalInfoBenb(boolean additionalInfoBenb) {
        this.additionalInfoBenb = additionalInfoBenb;
    }

    public String getAdditionalInfoS() {
        return additionalInfoS;
    }

    public void setAdditionalInfoS(String additionalInfoS) {
        this.additionalInfoS = additionalInfoS;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", employeeUser='" + employeeUser + '\'' +
                ", courseStartdate='" + courseStartdate + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", money=" + money +
                ", reimburse=" + reimburse +
                ", grading='" + grading + '\'' +
                ", justification='" + justification + '\'' +
                ", eventType=" + eventType +
                ", pass='" + pass + '\'' +
                ", urgent=" + urgent +
                ", approved=" + approved +
                ", amountApproved=" + amountApproved +
                ", directSuper=" + directSuper +
                ", departmentHead=" + departmentHead +
                ", benCo=" + benCo +
                ", amountIncrease=" + amountIncrease +
                ", additionalInfoSb=" + additionalInfoSb +
                ", additionalInfoHb=" + additionalInfoHb +
                ", additionalInfoBenb=" + additionalInfoBenb +
                ", additionalInfoS='" + additionalInfoS + '\'' +
                ", additionalInfoH='" + additionalInfoH + '\'' +
                ", additionalInfoBen='" + additionalInfoBen + '\'' +
                ", explanationDS='" + explanationDS + '\'' +
                ", explanationDH='" + explanationDH + '\'' +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
