package org.example.common;

import javax.print.attribute.standard.RequestingUserName;
import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface RepairmentSystem {

    List<Repairment> getRepairmentList();
    void setRepairmentList(List<Repairment> repairmentList);

    List<Worker> getWorkerList();
    void setWorkerList(List<Worker> workerList);

    Repairment applyRepairment(Person applicant, Dispatcher dispatcher, String content, String source);
    void showRepairments();

    void getCurrentScheduling(Repairment repairment);
    boolean workerAvailable(Worker worker);
    void getWorkTime(Repairment repairment);
    void getWorkerInfo(Worker worker);
}
