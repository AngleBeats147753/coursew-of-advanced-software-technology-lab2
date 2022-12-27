package org.example.common;

import java.time.LocalDate;
import java.util.List;

public class TaskSchedulingImpl implements TaskScheduling{
    @Override
    public Worker getWorker() {
        return null;
    }

    @Override
    public void setWorker(Worker worker) {

    }

    @Override
    public List<RepairmentRecord> getRepairmentRecord() {
        return null;
    }

    @Override
    public void setRepairmentRecord(List<RepairmentRecord> repairmentRecordList) {

    }

    @Override
    public boolean getIfComplete() {
        return false;
    }

    @Override
    public RepairmentRecord repair(LocalDate startTime, LocalDate finishTime, String content) {
        return null;
    }

    @Override
    public void complete() {

    }
}
