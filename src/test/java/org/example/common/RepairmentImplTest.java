package org.example.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
    LocalDateTime localDateTime1 = LocalDateTime.of(2022, 12, 29, 15, 16);
    LocalDateTime localDateTime2 = LocalDateTime.of(2022, 12, 29, 19, 16);
    LocalDateTime localDateTime3 = LocalDateTime.of(2022, 12, 29, 21, 16);
    LocalDateTime localDateTime4 = LocalDateTime.of(2022, 12, 29, 22, 16);

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
        assert content.equals("响应及时") && feedback == 5 : "test failed";
    }

    @Test
    void complaint() {
        String message = "维修太慢了";
        Repairment repairment = new RepairmentImpl();
        Complaint complaint = repairment.complaint(message);

        String content = complaint.getComplaintContent();
        assert content.equals("维修太慢了") : "test failed";
    }

    @Test
    void setWorkTime() {
        /*RepairmentRecord repairmentRecord1 = new RepairmentRecordImpl();
        repairmentRecord1.setStartTime(localDateTime1);
        repairmentRecord1.setFinishTime(localDateTime2);
        RepairmentRecord repairmentRecord2 = new RepairmentRecordImpl();
        repairmentRecord2.setStartTime(localDateTime1);
        repairmentRecord2.setFinishTime(localDateTime3);
        RepairmentRecord repairmentRecord3 = new RepairmentRecordImpl();
        repairmentRecord3.setStartTime(localDateTime2);
        repairmentRecord3.setFinishTime(localDateTime3);
        RepairmentRecord repairmentRecord4 = new RepairmentRecordImpl();
        repairmentRecord4.setStartTime(localDateTime2);
        repairmentRecord4.setFinishTime(localDateTime4);
        List<RepairmentRecord> repairmentRecordList1 = new ArrayList<>();
        repairmentRecordList1.add(repairmentRecord1);
        repairmentRecordList1.add(repairmentRecord2);
        List<RepairmentRecord> repairmentRecordList2 = new ArrayList<>();
        repairmentRecordList2.add(repairmentRecord3);
        repairmentRecordList2.add(repairmentRecord4);
        TaskScheduling taskScheduling1 = new TaskSchedulingImpl();*/

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
        TaskScheduling taskScheduling2 = repairment.schedule(workers, faultTypes2);


    }

    @Test
    void getWorkTime() {
    }

}