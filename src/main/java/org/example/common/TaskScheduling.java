package org.example.common;

import java.time.LocalDateTime;
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

    RepairmentRecord repair(LocalDateTime startTime, LocalDateTime finishTime, String content);
    void complete();
}
