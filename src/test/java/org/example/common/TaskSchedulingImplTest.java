package org.example.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskSchedulingImplTest {

    @Test
    void workerTest() {
        Worker worker = new WorkerImpl();
        String name = "JoJo";
        worker.setName(name);

        TaskScheduling taskScheduling = new TaskSchedulingImpl();
        taskScheduling.setWorker(worker);
        Worker testWorker = taskScheduling.getWorker();
        String testName = testWorker.getName();

        assert testName.equals(name);
    }

    @Test
    void repairmentRecordTest() {
        TaskScheduling taskScheduling = new TaskSchedulingImpl();
        LocalDateTime finishTime1 = LocalDateTime.of(2022, 12, 27, 18, 30, 30);
        LocalDateTime finishTime2 = LocalDateTime.of(2022, 12, 28, 18, 30, 30);
        LocalDateTime finishTime3 = LocalDateTime.of(2022, 12, 29, 18, 30, 30);
        LocalDateTime startTime1 = LocalDateTime.of(2022, 12, 27, 10, 20, 20);
        LocalDateTime startTime2 = LocalDateTime.of(2022, 12, 28, 10, 20, 20);
        LocalDateTime startTime3 = LocalDateTime.of(2022, 12, 29, 10, 20, 20);
        String content1 = "老王家的水管爆了，修了三分之一。";
        String content2 = "老王家的水管爆了，修了三分之二。";
        String content3 = "老王家的水管爆了，已经维修完毕。";
        taskScheduling.repair(startTime1, finishTime1, content1);
        taskScheduling.repair(startTime2, finishTime2, content2);
        taskScheduling.repair(startTime3, finishTime3, content3);

        List<RepairmentRecord> repairmentRecords = taskScheduling.getRepairmentRecord();
        RepairmentRecord repairmentRecord = repairmentRecords.get(1);
        String testContent = repairmentRecord.getRepairContent();

        assert testContent.equals(content2);
    }

    @Test
    void getIfComplete() {
        TaskScheduling taskScheduling = new TaskSchedulingImpl();
        boolean initIfComplete = taskScheduling.getIfComplete();
        taskScheduling.complete();
        boolean testIfComplete = taskScheduling.getIfComplete();

        assert testIfComplete && !initIfComplete;
    }
}