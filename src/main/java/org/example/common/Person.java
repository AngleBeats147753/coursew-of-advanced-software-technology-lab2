package org.example.common;

import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Person {
    String getName();
    void setName(String name);

    List<Complaint> getComplaintList();
    void setComplaintList(List<Complaint> complaintList);
}
