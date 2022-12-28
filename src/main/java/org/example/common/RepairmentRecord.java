package org.example.common;

import java.time.LocalDate;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface RepairmentRecord {
    LocalDate getStartTime();
    void setStartTime(LocalDate time);
    LocalDate getFinishTime();
    void setFinishTime(LocalDate time);
    long getWorkingHours();

    String getRepairContent();
    void setRepairContent(String content);
}
