package org.example.common;

import java.time.Duration;
import java.time.LocalDateTime;

public class RepairmentRecordImpl implements RepairmentRecord{
    LocalDateTime startTime;
    LocalDateTime finishTime;
    String repairContent;

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    @Override
    public void setStartTime(LocalDateTime time) {
        this.startTime = time;
    }

    @Override
    public LocalDateTime getFinishTime() {
        return this.finishTime;
    }

    @Override
    public void setFinishTime(LocalDateTime time) {
        this.finishTime = time;
    }

    @Override
    public long getWorkingHours() {
        Duration duration = Duration.between(this.startTime, this.finishTime);
        return duration.toHours();
    }

    @Override
    public String getRepairContent() {
        return this.repairContent;
    }

    @Override
    public void setRepairContent(String content) {
        this.repairContent = content;
    }
}
