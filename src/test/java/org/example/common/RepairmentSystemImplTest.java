package org.example.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RepairmentSystemImplTest {

    Dispatcher createDispatcher(){
        Dispatcher dispatcher = new DispatcherImpl();
        dispatcher.setName("调度员1");
    }

    Repairment createRepairment(){
        Repairment repairment = new RepairmentImpl();
        repairment.setDispatcher();
        repairment.setApplicant();
        repairment.setFaultContent("修水管");
        repairment.setWorkTime();
        repairment.setRepairTime(LocalDate.now());
        repairment.setSource("小区");
        return repairment;
    }

    @Test
    void showRepairments() {
        for(Repairment repairment : repairmentList){
            System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
            System.out.printf("调度员：%s\n", repairment.getDispatcher().getName());
            System.out.printf("维修内容：%s\n", repairment.getFaultContent());
            System.out.printf("来源：%s\n", repairment.getSource());
            System.out.printf("报修时间：%s\n", repairment.getRepairTime());
            System.out.println("*************************");
        }
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