package org.example.common;

import java.time.LocalDateTime;
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
        repairment.setRepairTime(LocalDateTime.now());
        repairmentList.add(repairment);

        return repairment;
    }

    @Override
    public void showRepairments() {
        for(Repairment repairment : repairmentList){
            System.out.printf("报修人：%s\n", repairment.getApplicant()); //todo 姓名
            System.out.printf("调度员：%s\n", repairment.getDispatcher()); //todo 姓名
            System.out.printf("维修内容：%s\n", repairment.getFaultContent());
            System.out.printf("来源：%s\n", repairment.getSource());
            System.out.printf("报修时间：%s\n", repairment.getRepairTime());
            System.out.println("*************************");
        }
    }

    @Override
    public List<FaultType> getTreatableFaults() {
        return null;
    }

    @Override
    public void getCurrentScheduling(Repairment repairment) {
        System.out.printf("报修人：%s\n", repairment.getApplicant()); //todo 姓名
        System.out.printf("维修内容：%s\n", repairment.getFaultContent());
        if(repairment.getTaskSchedulingList() == null){
            System.out.println("未调度");
        } else {
            for (TaskScheduling taskScheduling: repairment.getTaskSchedulingList()){
                System.out.printf("维修人员：%s", taskScheduling.getWorker()); // todo 姓名
                if (taskScheduling.getIfComplete())
                    System.out.println("已完成");
                else
                    System.out.println("未完成");
            }
        }
    }

    @Override
    public void getWorkerInfo(Worker worker){
        System.out.printf("工人姓名： %s", worker); // todo 姓名
        System.out.println();
    }


    @Override
    public boolean workerAvailable(Worker worker){
        return Boolean.FALSE;   // todo 打印是否有空
    }

    @Override
    public void getWorkTime(Repairment repairment){
        System.out.println("维修时间：");
        System.out.println(repairment.getRepairTime());
    }
}
