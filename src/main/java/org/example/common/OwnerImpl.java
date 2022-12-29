package org.example.common;

import java.util.List;

public class OwnerImpl implements Owner{
    List<Complaint> complaintList;
    String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
