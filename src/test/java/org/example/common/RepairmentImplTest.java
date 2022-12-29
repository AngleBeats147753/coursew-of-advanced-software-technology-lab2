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
        worker1.setName("111");
        faultType2.add(faultTypeC);
        faultType2.add(faultTypeD);
        worker2.setTreatableFaults(faultType2);
        worker2.setName("222");
        List<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        List<FaultType> faultTypes1 = new ArrayList<>();
        faultTypes1.add(faultTypeA);
        faultTypes1.add(faultTypeB);
        List<FaultType> faultTypes2 = new ArrayList<>();
        faultTypes2.add(faultTypeC);
        faultTypes2.add(faultTypeD);
        Repairment repairment = new RepairmentImpl();
        TaskScheduling taskScheduling1 = repairment.schedule(workers, faultTypes1);
        List<TaskScheduling> taskSchedulingList = repairment.getTaskSchedulingList();
        TaskScheduling taskScheduling2 = repairment.schedule(workers, faultTypes2);
        taskSchedulingList = repairment.getTaskSchedulingList();

        String name1 = taskScheduling1.getWorker().getName();
        String name2 = taskScheduling2.getWorker().getName();
        String name1_exp = "111";
        String name2_exp = "222";
        assert name1.equals(name1_exp) && name2.equals(name2_exp) && taskSchedulingList.size() == 2: "test failed";
    }

    @Test
    void comment() {
        String message = "响应及时";
        int score = 5;
        Repairment repairment = new RepairmentImpl();
        Comment comment = repairment.comment(message, score);

        String content = comment.getTimeliness();
        int feedback = comment.getAttitudeScore();
        assert content == "响应及时" && feedback == 5 : "test failed";
    }

    @Test
    void complaint() {
        String message = "维修太慢了";
        Repairment repairment = new RepairmentImpl();
        Complaint complaint = repairment.complaint(message);

        String content = complaint.getComplaintContent();
        assert content == "维修太慢了" : "test failed";
    }

    @Test
    void setWorkTime() {

    }

    @Test
    void getWorkTime() {
    }

}