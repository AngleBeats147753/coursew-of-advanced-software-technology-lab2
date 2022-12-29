package org.example.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepairmentSystemImpl implements RepairmentSystem {
    private List<Repairment> repairmentList;
    private List<Worker> workerList;

    public RepairmentSystemImpl(ArrayList<Repairment> repairmentList, ArrayList<Worker> workerList) {
        this.repairmentList = repairmentList;
        this.workerList = workerList;
    }

    @Override
    public List<Repairment> getRepairmentList() {
        return repairmentList;
    }

    @Override
    public void setRepairmentList(List<Repairment> repairmentList) {
        this.repairmentList = repairmentList;
    }

    @Override
    public List<Worker> getWorkerList() {
        return workerList;
    }

    @Override
    public void setWorkerList(List<Worker> workerList) {
        this.workerList = workerList;
    }

    @Override
    public Repairment applyRepairment(Person applicant, Dispatcher dispatcher,
                                      String content, String source) {
        Repairment repairment = new RepairmentImpl();
        repairment.setApplicant(applicant);
        repairment.setDispatcher(dispatcher);
        repairment.setSource(source);
        repairment.setFaultContent(content);
        repairment.setRepairTime(LocalDate.now());
        repairmentList.add(repairment);
        return repairment;
    }

    @Override
    public void showRepairments() {
        for(Repairment repairment : repairmentList){
            System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
            System.out.printf("调度员：%s\n", repairment.getDispatcher().getName());
            System.out.printf("维修内容：%s\n", repairment.getFaultContent());
            System.out.printf("来源：%s\n", repairment.getSource());
            System.out.printf("报修时间：%s\n", repairment.getRepairTime());
            System.out.println("*************************");
        }
    }

    @Override
    public void getWorkerInfo(Worker worker){
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

    @Override
    public void getCurrentScheduling(Repairment repairment) {
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


    @Override
    public boolean workerAvailable(Worker worker){
        return worker.getIfWorking();
    }

    @Override
    public void getWorkTime(Repairment repairment){
        System.out.println("维修时间：" + repairment.getRepairTime());
    }
}