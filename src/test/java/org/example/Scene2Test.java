package org.example;

import org.example.common.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Scene2Test {

    /**
     * 用户遇到突发情况,报修处理
     */
    @Test
    void testScene2() {

        //创建各种人员信息
        Owner owner = new OwnerImpl();
        owner.setName("1102号业主");
        PropertyManager propertyManager = new PropertyManagerImpl();
        propertyManager.setName("物业经理1");
        Dispatcher dispatcher = new DispatcherImpl();
        dispatcher.setName("001号调度员");
        Worker worker1 = new WorkerImpl();
        worker1.setName("001号维修工人");
        List<FaultType> faultTypeList1 = new ArrayList<>();
        faultTypeList1.add(FaultType.C);
        faultTypeList1.add(FaultType.D);
        worker1.setTreatableFaults(faultTypeList1);
        Worker worker2 = new WorkerImpl();
        worker2.setName("002号维修工人");
        List<FaultType> faultTypeList2 = new ArrayList<>();
        faultTypeList2.add(FaultType.A);
        faultTypeList2.add(FaultType.C);
        worker2.setTreatableFaults(faultTypeList2);
        ArrayList<Worker> workerList = new ArrayList<>();
        workerList.add(worker1);
        workerList.add(worker2);

        //用户遇到突发情况，主要为电梯故障
        //业主通过报修电话进行报修
        //系统开始处理
        RepairmentSystem repairmentSystem = new RepairmentSystemImpl(new ArrayList<>(), workerList);
        String source = "报修电话";
        String faultType = FaultType.C.toString();
        //调度员将报修信息录入报修系统
        Repairment repairment = repairmentSystem.applyRepairment(owner, dispatcher, faultType, source);

        //调度员进行调度，首先分配给维修工人1
        System.out.println("调度员开始进行任务调度...");
        TaskScheduling taskScheduling = repairment.schedule(repairmentSystem.getWorkerList(), List.of(FaultType.C));
        repairmentSystem.showRepairments();

        //维修工人1收到任务后去现场进行维修，并留下维修记录1以及维修记录2
        Worker worker = taskScheduling.getWorker();
        System.out.printf("调度员调度完成，被调度的工人：%s ,工人开始工作...\n", worker.getName());
        worker.beginToWork();
        assert worker.getIfWorking() : "被调度的工人1未开始工作";
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime time1 = startTime.plus(1, TimeUnit.HOURS.toChronoUnit());
        LocalDateTime time2 = startTime.plus(3, TimeUnit.HOURS.toChronoUnit());
        taskScheduling.repair(startTime, time1, "维修工人1维修电梯故障维修记录1");
        taskScheduling.repair(time1, time2, "维修工人1维修电梯故障维修记录2,无法维修剩下部分，有断电的故障需要等待维修");
        worker.completeWork();
        repairment.setWorkTime();
        System.out.println("查看当前报修情况：");
        repairmentSystem.getCurrentScheduling(repairment);

        assert !repairment.getIfComplete() : "系统判定报修已完成错误";

        assert !worker.getIfWorking() : "被调度的工人1未结束工作";
        //维修工人1告知调度员无法维修剩下的部分
        //调度员继续进行调度，分配给维修工人2
        System.out.println("调度员开始进行任务调度...");
        TaskScheduling taskScheduling2 = repairment.schedule(repairmentSystem.getWorkerList(), List.of(FaultType.A));
        repairmentSystem.showRepairments();

        //维修工人2收到任务后去现场进行维修，并留下维修记录3
        Worker newWorker = taskScheduling.getWorker();
        System.out.printf("调度员调度完成，被调度的工人：%s ,工人开始工作...\n", newWorker.getName());
        newWorker.beginToWork();
        assert newWorker.getIfWorking() : "被调度的工人2未开始工作";
        LocalDateTime startTime2 =time2.plus(2, TimeUnit.HOURS.toChronoUnit());
        LocalDateTime endTime = startTime2.plus(2, TimeUnit.HOURS.toChronoUnit());
        taskScheduling.repair(startTime2, endTime, "维修工人2维修故障记录3");
        newWorker.completeWork();
        repairment.setWorkTime();
        System.out.println("查看当前报修情况：");
        repairmentSystem.getCurrentScheduling(repairment);

        //维修完成后业主进行投诉
        System.out.println("业主投诉");
        taskScheduling.complete();
        Complaint complaint = repairment.complaint("业主投诉维修时间太长");
        assert !complaint.getProcessingResults() : "未成功进行投诉";
        //工人1，工人2，调度员进行投诉说明
        System.out.println("工人1，工人2，调度员进行投诉说明");
        ComplaintDescription complaintDescription1 = new ComplaintDescriptionImpl();
        ComplaintDescription complaintDescription2 = new ComplaintDescriptionImpl();
        ComplaintDescription complaintDescription3 = new ComplaintDescriptionImpl();
        complaintDescription1.setComplaint(complaint);
        complaintDescription2.setComplaint(complaint);
        complaintDescription3.setComplaint(complaint);
        complaintDescription1.setPerson(worker1);
        complaintDescription1.setDescription("维修情况未详细说明，维修超时");
        complaintDescription2.setPerson(worker2);
        complaintDescription1.setDescription("维修调度有问题");
        complaintDescription3.setPerson(dispatcher);
        complaintDescription3.setDescription("调度存在问题");
        List<ComplaintDescription> complaintDescriptionList = new ArrayList<>();
        complaintDescriptionList.add(complaintDescription1);
        complaintDescriptionList.add(complaintDescription2);
        complaintDescriptionList.add(complaintDescription3);
        complaint.setComplaintDescription(complaintDescriptionList);
        assert complaint.getComplaintDescription().size()==3 : "投诉说明存在问题";

        //物业对该投诉进行处理，关闭该投诉
        System.out.println("物业对该投诉进行处理，关闭该投诉");
        complaint.handle(propertyManager,"处理完成");
        assert complaint.getProcessingResults() : "投诉处理失败";
        repairment.complete();

    }
}
