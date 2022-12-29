package org.example.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepairmentSystemImplTest {
    public static LocalDateTime createRandomDate(){
        //将两个时间转为时间戳
        LocalDateTime startTime = LocalDateTime.now().minusDays(100);
        LocalDateTime endTime = startTime.plusDays(50);
        long start = startTime.toEpochSecond(ZoneOffset.of("+8"));
        long end = endTime.toEpochSecond(ZoneOffset.of("+8"));
        //获取两个时间的随机数
        long difference = (long)(Math.random() * (end - start));
        //生成时间
        return LocalDateTime.ofEpochSecond(start+difference,0, ZoneOffset.ofHours(8));
    }

    @Test
    void test1(){
        LocalDateTime dateTime = createRandomDate();
        System.out.println(dateTime);
    }

    Dispatcher createADispatcher(int difference){
        Dispatcher dispatcher = new DispatcherImpl();
        dispatcher.setName("调度员"+difference);
        return dispatcher;
    }
    Owner createAnOwner(int difference){
        Owner owner = new OwnerImpl();
        owner.setName("业主"+difference);
        return owner;
    }
    Worker createAWorker(int difference){
        Worker worker = new WorkerImpl();
        worker.setName("维修工"+difference);
        List<FaultType> faults = new ArrayList<>();
        faults.add(FaultType.A);
        faults.add(FaultType.C);
        faults.add(FaultType.E);
        worker.setTreatableFaults(faults);
        return worker;
    }

    TaskScheduling createATaskScheduling(Worker worker){
        TaskScheduling taskScheduling = new TaskSchedulingImpl();
        taskScheduling.setWorker(worker);
        LocalDateTime start;
        int difference;
        start = createRandomDate();
        difference = (int)(Math.random() * (10));
        taskScheduling.repair(start, start.plusHours(difference), "某维修内容");
        start = createRandomDate();
        difference = (int)(Math.random() * (10));
        taskScheduling.repair(start, start.plusHours(difference), "某维修内容");
        return taskScheduling;
    }

    Repairment createRepairment(Dispatcher dispatcher, Owner owner, Worker worker){
        Repairment repairment = new RepairmentImpl();
        repairment.setDispatcher(dispatcher);
        repairment.setApplicant(owner);
        repairment.setFaultContent("修水管");
        List<TaskScheduling> taskSchedulingList = new ArrayList<TaskScheduling>();
        TaskScheduling taskScheduling1 = createATaskScheduling(worker);
        taskScheduling1.complete();
        taskSchedulingList.add(taskScheduling1);
        taskSchedulingList.add(createATaskScheduling(worker));
        repairment.setTaskScheduling(taskSchedulingList);
        repairment.setWorkTime();
        repairment.setRepairTime(LocalDateTime.now());
        repairment.setSource("小区");
        return repairment;
    }

    RepairmentSystem createSysytem(){
        List<Worker> workerList = new ArrayList<>();
        workerList.add(createAWorker(1));
        workerList.add(createAWorker(2));
        workerList.add(createAWorker(3));
        Dispatcher dispatcher1 = createADispatcher(1);
        Dispatcher dispatcher2 = createADispatcher(2);
        Owner owner1 = createAnOwner(1);
        Owner owner2 = createAnOwner(2);
        Owner owner3 = createAnOwner(3);

        List<Repairment> repairmentList = new ArrayList<>();
        repairmentList.add(createRepairment(dispatcher1, owner1, workerList.get(0)));
        repairmentList.add(createRepairment(dispatcher2, owner2, workerList.get(2)));

        return new RepairmentSystemImpl(repairmentList, workerList);
    }

    @Test
    void showRepairmentTest() {
        Worker worker1 = createAWorker(1);
        Dispatcher dispatcher1 = createADispatcher(1);
        Owner owner1 = createAnOwner(1);
        Repairment repairment = createRepairment(dispatcher1, owner1, worker1);

        RepairmentSystem system = createSysytem();
        List<TaskScheduling> systemRes = system.showRepairment(repairment);
        assert systemRes.equals(repairment.getTaskSchedulingList());
    }

    @Test
    void getWorkerInfoTest(){
        Worker testWorker = createAWorker(111);
        Dispatcher dispatcher1 = createADispatcher(1);
        Dispatcher dispatcher2 = createADispatcher(2);
        Owner owner1 = createAnOwner(1);
        Owner owner2 = createAnOwner(2);
        RepairmentSystem system = createSysytem();
        system.getWorkerList().add(testWorker);

        //创建repairment和调度
        List<TaskScheduling> taskSchedulingList = new ArrayList<>();
        Repairment repairment1 = createRepairment(dispatcher1, owner1, testWorker);
        Repairment repairment2 = createRepairment(dispatcher2, owner1, testWorker);
        List<TaskScheduling> taskSchedulings1 = new ArrayList<>();
        List<TaskScheduling> taskSchedulings2 = new ArrayList<>();
        TaskScheduling taskScheduling1 = new TaskSchedulingImpl();
        TaskScheduling taskScheduling2 = new TaskSchedulingImpl();
        taskScheduling1.setWorker(testWorker);
        taskScheduling2.setWorker(testWorker);
        taskSchedulings1.add(taskScheduling1);
        taskSchedulings2.add(taskScheduling2);
        repairment1.setTaskScheduling(taskSchedulings1);
        repairment2.setTaskScheduling(taskSchedulings2);
        //创建投诉
        List<Complaint> complaintList = new ArrayList<>();
        List<Complaint> complaints = new ArrayList<>();
        Complaint complaint = new ComplaintImpl();
        complaint.setComplaintContent("服务态度不好");
        complaint.setProcessingResults(false);
        List<ComplaintDescription> descriptionList = new ArrayList<>();
        ComplaintDescription description = new ComplaintDescriptionImpl();
        description.setDescription("维修工态度不好");
        description.setPerson(testWorker);
        descriptionList.add(description);
        complaint.setComplaintDescription(descriptionList);
        complaints.add(complaint);
        repairment1.setCompliantList(complaints);
        system.getRepairmentList().add(repairment1);
        system.getRepairmentList().add(repairment2);

        taskSchedulingList.add(taskScheduling1);
        taskSchedulingList.add(taskScheduling2);
        complaintList.add(complaint);
        WorkerInfo trueRes = new WorkerInfo(taskSchedulingList, complaintList);
        WorkerInfo systemRes = system.getWorkerInfo(testWorker);

        assert systemRes.equals(trueRes);
    }

    @Test
    void getCurrentSchedulingTest() {
        Worker worker1 = createAWorker(1);
        Dispatcher dispatcher1 = createADispatcher(1);
        Owner owner1 = createAnOwner(1);
        Repairment repairment = createRepairment(dispatcher1, owner1, worker1);
        List<TaskScheduling> list = repairment.getTaskSchedulingList();
        TaskScheduling trueRes = list.get(list.size()-1);

        RepairmentSystem system = createSysytem();
        TaskScheduling systemRes = system.getCurrentScheduling(repairment);
        assert systemRes.equals(trueRes);
    }

    @Test
    void workerAvailableTest(){
        Worker worker1 = createAWorker(1);
        RepairmentSystem system = createSysytem();

        boolean systemRes = system.workerAvailable(worker1);
        assert  systemRes == worker1.getIfWorking();
    }


    @Test
    void getWorkTimeTest(){
        Worker worker1 = createAWorker(1);
        Dispatcher dispatcher1 = createADispatcher(1);
        Owner owner1 = createAnOwner(1);
        Repairment repairment = createRepairment(dispatcher1, owner1, worker1);
        RepairmentSystem system = createSysytem();
        system.showRepairment(repairment);
        long SystemWorkHours = system.getWorkTime(repairment);

        long trueWorkHours = 0;
        for (TaskScheduling taskScheduling: repairment.getTaskSchedulingList()){
            for (RepairmentRecord record: taskScheduling.getRepairmentRecord()){
                trueWorkHours+=record.getWorkingHours();
            }
        }
        assert trueWorkHours == SystemWorkHours;
    }
}