package dev.tinajero.models;

import java.math.BigDecimal;

public class Approvals {
    private int approvalId, employeeId;
    private BigDecimal approvedMoney;
    private boolean appDS, appDH, appBC;
    private boolean requireDs, requireDH, requireBC;
    private String additionalDs, additionalDh, additionalBc;
    private String expDS, expDH, expBC;

    public Approvals(){}

    public Approvals(int approvalId, int employeeId, BigDecimal approvedMoney, boolean appDS, boolean appDH, boolean appBC, boolean requireDs, boolean requireDH, boolean requireBC, String additionalDs, String additionalDh, String additionalBc, String expDS, String expDH, String expBC) {
        this.approvalId = approvalId;
        this.employeeId = employeeId;
        this.approvedMoney = approvedMoney;
        this.appDS = appDS;
        this.appDH = appDH;
        this.appBC = appBC;
        this.requireDs = requireDs;
        this.requireDH = requireDH;
        this.requireBC = requireBC;
        this.additionalDs = additionalDs;
        this.additionalDh = additionalDh;
        this.additionalBc = additionalBc;
        this.expDS = expDS;
        this.expDH = expDH;
        this.expBC = expBC;
    }

    public boolean isRequireDs() {
        return requireDs;
    }

    public void setRequireDs(boolean requireDs) {
        this.requireDs = requireDs;
    }

    public boolean isRequireDH() {
        return requireDH;
    }

    public void setRequireDH(boolean requireDH) {
        this.requireDH = requireDH;
    }

    public boolean isRequireBC() {
        return requireBC;
    }

    public void setRequireBC(boolean requireBC) {
        this.requireBC = requireBC;
    }

    public String getAdditionalDs() {
        return additionalDs;
    }

    public void setAdditionalDs(String additionalDs) {
        this.additionalDs = additionalDs;
    }

    public String getAdditionalDh() {
        return additionalDh;
    }

    public void setAdditionalDh(String additionalDh) {
        this.additionalDh = additionalDh;
    }

    public String getAdditionalBc() {
        return additionalBc;
    }

    public void setAdditionalBc(String additionalBc) {
        this.additionalBc = additionalBc;
    }

    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public BigDecimal getApprovedMoney() {
        return approvedMoney;
    }

    public void setApprovedMoney(BigDecimal approvedMoney) {
        this.approvedMoney = approvedMoney;
    }

    public boolean isAppDS() {
        return appDS;
    }

    public void setAppDS(boolean appDS) {
        this.appDS = appDS;
    }

    public boolean isAppDH() {
        return appDH;
    }

    public void setAppDH(boolean appDH) {
        this.appDH = appDH;
    }

    public boolean isAppBC() {
        return appBC;
    }

    public void setAppBC(boolean appBC) {
        this.appBC = appBC;
    }

    public String getExpDS() {
        return expDS;
    }

    public void setExpDS(String expDS) {
        this.expDS = expDS;
    }

    public String getExpDH() {
        return expDH;
    }

    public void setExpDH(String expDH) {
        this.expDH = expDH;
    }

    public String getExpBC() {
        return expBC;
    }

    public void setExpBC(String expBC) {
        this.expBC = expBC;
    }
}
