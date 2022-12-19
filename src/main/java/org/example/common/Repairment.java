package org.example.common;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Repairment {
    LocalDate getRepairTime();
    void setRepairTime(LocalDate repairTime);
    String getFaultContent();
    void setFaultContent(String faultContent);
    Person getApplicant();
    void setApplicant(Person applicant);
    String getSource();
    void setSource(String source);
    boolean getIfComplete();
    Dispatcher getDispatcher();
    void setDispatcher(Dispatcher dispatcher);
    List<TaskScheduling> getTaskSchedulingList();
    void setTaskSchedulingList(List<TaskScheduling> taskSchedulingList);
    Comment getComment();
    void setComment(Comment comment);
    List<Complaint> getCompliantList();
    void setCompliantList(List<Complaint> compliantList);

    TaskScheduling schedule(List<Worker> workers, List<FaultType> faults);
    Comment comment(String comment);
    Complaint complaint(String complaint);
    long getWorkTime();
    void complete();
}
