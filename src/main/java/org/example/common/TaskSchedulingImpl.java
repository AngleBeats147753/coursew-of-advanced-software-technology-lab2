package org.example.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskSchedulingImpl implements TaskScheduling{
    Worker worker;
    List<RepairmentRecord> repairmentRecordList = new ArrayList<>();
    boolean ifComplete = false;

    @Override
    public Worker getWorker() {
        return this.worker;
    }

    @Override
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public List<RepairmentRecord> getRepairmentRecord() {
        return this.repairmentRecordList;
    }

    @Override
    public boolean getIfComplete() {
        return this.ifComplete;
    }

    @Override
    public RepairmentRecord repair(LocalDateTime startTime, LocalDateTime finishTime, String content) {
        RepairmentRecord repairmentRecord = new RepairmentRecordImpl();
        repairmentRecord.setStartTime(startTime);
        repairmentRecord.setFinishTime(finishTime);
        repairmentRecord.setRepairContent(content);
        this.repairmentRecordList.add(repairmentRecord);

        return repairmentRecord;
    }

    @Override
    public void complete() {
        this.ifComplete = true;
    }
}
