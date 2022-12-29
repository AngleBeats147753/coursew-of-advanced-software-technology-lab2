package org.example.common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RepairmentImplTest {

    FaultType faultTypeA = FaultType.A;
    FaultType faultTypeB = FaultType.B;
    FaultType faultTypeC = FaultType.C;
    FaultType faultTypeD = FaultType.D;
    Worker worker1 = new WorkerImpl();
    List<FaultType> faultType1 = new ArrayList<>();
    Worker worker2 = new WorkerImpl();
    List<FaultType> faultType2 = new ArrayList<>();

    @Test
    void schedule() {
        faultType1.add(faultTypeA);
        faultType1.add(faultTypeB);
        worker1.setTreatableFaults(faultType1);
        faultType2.add(faultTypeC);
        faultType2.add(faultTypeD);
        worker2.setTreatableFaults(faultType2);
        List<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        List<FaultType> faultTypes1 = new ArrayList<>();
        faultTypes1.add(faultTypeA);
        faultTypes1.add(faultTypeB);
        List<FaultType> faultTypes2 = new ArrayList<>();
        faultTypes1.add(faultTypeC);
        faultTypes1.add(faultTypeD);
        Repairment repairment = new RepairmentImpl();
        TaskScheduling taskScheduling1 = repairment.schedule(workers, faultTypes1);
        List<TaskScheduling> taskSchedulingList = repairment.getTaskSchedulingList();
        TaskScheduling taskScheduling2 = repairment.schedule(workers, faultTypes2);
        taskSchedulingList = repairment.getTaskSchedulingList();

    }

    @Test
    void comment() {
    }

    @Test
    void complaint() {
    }

    @Test
    void setWorkTime() {
    }

    @Test
    void getWorkTime() {
    }

    @Test
    void complete() {
    }
}