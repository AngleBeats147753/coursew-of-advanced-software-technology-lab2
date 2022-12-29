package org.example.common;

import java.time.LocalDateTime;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface RepairmentRecord {
    LocalDateTime getStartTime();
    void setStartTime(LocalDateTime time);
    LocalDateTime getFinishTime();
    void setFinishTime(LocalDateTime time);
    long getWorkingHours();

    String getRepairContent();
    void setRepairContent(String content);
}
