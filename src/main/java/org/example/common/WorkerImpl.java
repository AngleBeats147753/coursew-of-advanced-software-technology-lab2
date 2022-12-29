package org.example.common;

import java.util.List;

public class WorkerImpl implements Worker {
    List<Complaint> complaintList;
    List<FaultType> treatableFaultList;
    List<Repairment> repairmentList;
    String name;
    boolean ifWorking = false;   //是否在工作的字段
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean getIfWorking() {
        return this.ifWorking;
    }

    @Override
    public void beginToWork() {
        this.ifWorking = true;
    }

    @Override
    public void completeWork() {
        this.ifWorking = false;
    }

    @Override
    public List<FaultType> getTreatableFaults() {
        return treatableFaultList;
    }

    @Override
    public void setTreatableFaults(List<FaultType> treatableFaults) {
        this.treatableFaultList = treatableFaults;
    }

    @Override
    public List<Repairment> getRepairmentList() {
        return repairmentList;
    }

    @Override
    public void setRepairmentList(List<Repairment> repairmentList) {
        this.repairmentList = repairmentList;
    }

    @Override
    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    @Override
    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }
}
