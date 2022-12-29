package org.example.common;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;


class RepairmentRecordImplTest {

    @Test
    void startTimeTest() {
        RepairmentRecord repairmentRecord = new RepairmentRecordImpl();
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 29, 10, 0, 0);
        repairmentRecord.setStartTime(startTime);
        LocalDateTime testTime = repairmentRecord.getStartTime();

        assert testTime == startTime;
    }

    @Test
    void finishTimeTest() {
        RepairmentRecord repairmentRecord = new RepairmentRecordImpl();
        LocalDateTime finishTime = LocalDateTime.of(2022, 12, 29, 10, 0, 0);
        repairmentRecord.setFinishTime(finishTime);
        LocalDateTime testTime = repairmentRecord.getFinishTime();

        assert testTime == finishTime;
    }

    @Test
    void workingHoursTest() {
        RepairmentRecord repairmentRecord = new RepairmentRecordImpl();
        LocalDateTime finishTime = LocalDateTime.of(2022, 12, 29, 18, 30, 30);
        LocalDateTime startTime = LocalDateTime.of(2022, 12, 29, 10, 20, 20);

        repairmentRecord.setStartTime(startTime);
        repairmentRecord.setFinishTime(finishTime);
        long testTime = repairmentRecord.getWorkingHours();

        assert testTime == 8;
    }

    @Test
    void repairContentTest() {
        RepairmentRecord repairmentRecord = new RepairmentRecordImpl();
        String content = "老王家的水管爆了，已经维修完毕。";
        repairmentRecord.setRepairContent(content);
        String testContent = repairmentRecord.getRepairContent();

        assert testContent.equals(content);
    }
}