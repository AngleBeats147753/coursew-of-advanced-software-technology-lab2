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
        taskSchedulingList.add(createATaskScheduling(worker));
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
    void showRepairments(RepairmentSystem repairmentSystem) {
//        for(Repairment repairment : repairmentList){
//            System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
//            System.out.printf("调度员：%s\n", repairment.getDispatcher().getName());
//            System.out.printf("维修内容：%s\n", repairment.getFaultContent());
//            System.out.printf("来源：%s\n", repairment.getSource());
//            System.out.printf("报修时间：%s\n", repairment.getRepairTime());
//            System.out.println("*************************");
//        }
    }

    @Test
    void getWorkerInfo(Worker worker){
        System.out.printf("工人姓名： %s", worker.getName());
        System.out.print("可处理的维修类型：");
        for (FaultType faultType: worker.getTreatableFaults()){
            System.out.print(faultType + ", ");
        }
        System.out.println();
        if (worker.getIfWorking())
            System.out.println("工作ing...");
        else
            System.out.println("目前未被分配工作");
    }

    @Test
    void getCurrentScheduling(Repairment repairment) {
        System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
        System.out.printf("维修内容：%s\n", repairment.getFaultContent());
        if(repairment.getTaskSchedulingList() == null){
            System.out.println("未调度");
        } else {
            int size = repairment.getTaskSchedulingList().size();
            TaskScheduling scheduling = repairment.getTaskSchedulingList().get(size-1);
            System.out.printf("维修人员：%s", scheduling.getWorker());
            for (RepairmentRecord record: scheduling.getRepairmentRecord()){
                System.out.print("开始时间：" + record.getStartTime() + "    ");
                String endTime;
                if (record.getFinishTime() != null)
                    endTime = record.getFinishTime().toString();
                else
                    endTime = "未结束";
                System.out.print("结束时间：" + endTime + "    ");
                System.out.println("维修内容：" + record.getRepairContent());
            }
            if (scheduling.getIfComplete())
                System.out.println("已完成");
            else
                System.out.println("未完成");
        }
    }

    @Test
    boolean workerAvailable(Worker worker){
        return worker.getIfWorking();
    }


    @Test
    void getWorkTime(Repairment repairment){

        System.out.println("维修时间：" + repairment.getRepairTime());
    }
}