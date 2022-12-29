package org.example.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class RepairmentSystemImpl implements RepairmentSystem {
    private List<Repairment> repairmentList;
    private List<Worker> workerList;

    public RepairmentSystemImpl(List<Repairment> repairmentList, List<Worker> workerList) {
        this.repairmentList = repairmentList;
        this.workerList = workerList;
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
    public List<Worker> getWorkerList() {
        return workerList;
    }

    @Override
    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    @Override
    public Repairment applyRepairment(Person applicant, Dispatcher dispatcher,
                                      String content, String source) {
        Repairment repairment = new RepairmentImpl();
        repairment.setApplicant(applicant);
        repairment.setDispatcher(dispatcher);
        repairment.setSource(source);
        repairment.setFaultContent(content);
        repairment.setRepairTime(LocalDateTime.now());
        repairmentList.add(repairment);
        return repairment;
    }
}
