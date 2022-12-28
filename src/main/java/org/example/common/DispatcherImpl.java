package org.example.common;


import java.util.List;

public class DispatcherImpl implements Dispatcher{
    List<Complaint> complaintList;

    @Override
    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    @Override
    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }
}
