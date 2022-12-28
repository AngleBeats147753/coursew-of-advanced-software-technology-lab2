package org.example.common;

import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Person {
    List<Complaint> getComplaintList();
    void setComplaintList(List<Complaint> complaintList);
}
