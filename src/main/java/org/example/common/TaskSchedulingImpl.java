package org.example.common;

import java.time.LocalDate;
import java.util.List;

public class TaskSchedulingImpl implements TaskScheduling{
    Worker worker;
    List<RepairmentRecord> repairmentRecordList;
    boolean ifComplete = false;

    @Override
    public Worker getWorker() {
        return worker;
    }

    @Override
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public List<RepairmentRecord> getRepairmentRecord() {
        return repairmentRecordList;
    }

    @Override
    public boolean getIfComplete() {
        return ifComplete;
    }

    @Override
    public RepairmentRecord repair(LocalDate startTime, LocalDate finishTime, String content) {
        return null;
    }

    @Override
    public void complete() {
        this.ifComplete = true;
    }
}
