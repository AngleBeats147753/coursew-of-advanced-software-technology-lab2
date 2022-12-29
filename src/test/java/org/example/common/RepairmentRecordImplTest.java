package org.example.common;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;


class RepairmentRecordImplTest {

    @Test
    void getStartTime() {
    }

    @Test
    void setStartTime() {
    }

    @Test
    void getFinishTime() {
    }

    @Test
    void setFinishTime() {
    }

    @Test
    void getWorkingHours() {
        long time;
        LocalDateTime finish = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(2022, 12, 29, 10, 0, 0);
        Duration duration = Duration.between(start, finish);
        time = duration.toHours();
        System.out.println(time);
    }

    @Test
    void getRepairContent() {
    }

    @Test
    void setRepairContent() {
    }
}