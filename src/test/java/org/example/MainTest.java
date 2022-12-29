package org.example;

import org.example.common.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    void scene1() {

        /*
          前期准备
          初始化系统
         */

        //创建业主、调度员、工人
        Owner owner = new OwnerImpl();
        owner.setName("1203号业主");

        Dispatcher dispatcher = new DispatcherImpl();
        dispatcher.setName("001号调度员");

        //初始化工人列表 2名
        ArrayList<Worker> workerList = new ArrayList<>();
        //001号工人 工种：A B
        Worker worker1 = new WorkerImpl();
        worker1.setName("001号工人");
        List<FaultType> faultTypeList = new ArrayList<>();
        faultTypeList.add(FaultType.A);
        faultTypeList.add(FaultType.B);
        worker1.setTreatableFaults(faultTypeList);
        workerList.add(worker1);

        //002号工人 工种：B C
        Worker worker2 = new WorkerImpl();
        worker2.setName("002号工人");
        List<FaultType> faultTypeList2 = new ArrayList<>();
        faultTypeList2.add(FaultType.A);
        faultTypeList2.add(FaultType.C);
        worker2.setTreatableFaults(faultTypeList2);
        workerList.add(worker2);


        //初始化报修系统
        ArrayList<Repairment> repairmentList = new ArrayList<>();
        RepairmentSystem repairmentSystem = new RepairmentSystemImpl(repairmentList, workerList);


        /*
         * 开始流程
         */

        // 业主遇到突发情况，遭遇故障：A
        // 业主通过微信群/保修电话报修,描述故障信息（故障类别：A）
        String source = "微信群";
        String faultType = FaultType.A.toString();

        //调度员将报修信息录入报修系统
        Repairment curRepairment = repairmentSystem.applyRepairment(owner, dispatcher, faultType, source);

        //调度员开始进行任务调度，将任务分配给合适的维修工人
        System.out.println("调度员开始进行任务调度...");
        TaskScheduling taskScheduling = curRepairment.schedule(repairmentSystem.getWorkerList(), List.of(FaultType.A));
        curRepairment.setTaskScheduling(List.of(taskScheduling));
        repairmentSystem.showRepairments();

        //维修工人收到任务后去现场进行维修，并记录工作活动
        Worker worker = taskScheduling.getWorker();
        System.out.printf("调度员调度完成，被调度的工人：%s ,工人开始工作...\n", worker.getName());
        worker.beginToWork();
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime finishTime = startTime.plus(1, TimeUnit.HOURS.toChronoUnit());
        taskScheduling.repair(startTime, finishTime, curRepairment.getFaultContent());
        worker.completeWork();
        curRepairment.setWorkTime();
        System.out.println("查看当前报修情况：");
        repairmentSystem.getCurrentScheduling(curRepairment);

        //工人通知业主完成维修，业主对维修进行评价
        taskScheduling.complete();
        System.out.println("查看当前报修情况：");
        repairmentSystem.getCurrentScheduling(curRepairment);
        curRepairment.complete();
        System.out.printf("本次报修工作时间：%d 小时 \n", curRepairment.getWorkTime());


        System.out.println("请用户对本次维修进行评价：");
        Comment comment = curRepairment.comment("响应非常及时", 4);
        System.out.printf("[用户评价结果] 响应度：%s, 满意度(满分5分)：%d分", comment.getTimeliness(), comment.getAttitudeScore());


    }
}