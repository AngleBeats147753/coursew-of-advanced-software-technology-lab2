package org.example.common;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface TaskScheduling {
    Worker getWorker();
    void setWorker(Worker worker);
    List<RepairmentRecord> getRepairmentRecord();
    boolean getIfComplete();

    RepairmentRecord repair(LocalDate startTime, LocalDate finishTime, String content);
    void complete();
}
