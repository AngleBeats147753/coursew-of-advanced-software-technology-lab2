package org.example.common;

import java.util.List;

public class WorkerImpl implements Worker {
    List<Complaint> complaintList;
    List<FaultType> treatableFaultList;
    List<Repairment> repairmentList;

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
