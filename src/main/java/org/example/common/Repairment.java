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
    boolean getIfComplete(TaskScheduling taskScheduling);
    Dispatcher getDispatcher();
    void setDispatcher(Dispatcher dispatcher);
    List<TaskScheduling> getTaskSchedulingList();
    void setTaskSchedulingList(List<TaskScheduling> taskSchedulingList);
    Comment getComment();
    List<Complaint> getCompliantList();
    void setCompliantList(List<Complaint> compliantList);

    TaskScheduling schedule(List<Worker> workers, FaultType faults);
    Comment comment(String message, int score);
    Complaint complaint(String complaint);
    void setWorkTime(RepairmentRecord repairmentRecord);
    long getWorkTime();
    void complete();
}
