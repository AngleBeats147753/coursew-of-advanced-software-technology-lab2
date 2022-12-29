package org.example.common;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class RepairmentSystemImpl implements RepairmentSystem {
    private List<Repairment> repairmentList;
    private List<Worker> workerList;

    public RepairmentSystemImpl(List<Repairment> repairmentList, List<Worker> workerList) {
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
            System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
            System.out.printf("调度员：%s\n", repairment.getDispatcher().getName());
            System.out.printf("维修内容：%s\n", repairment.getFaultContent());
            System.out.printf("来源：%s\n", repairment.getSource());
            System.out.printf("报修时间：%s\n", repairment.getRepairTime());
            System.out.println("*************************");
        }
    }

    @Override
    public List<TaskScheduling> showRepairment(Repairment repairment) {
        System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
        System.out.printf("调度员：%s\n", repairment.getDispatcher().getName());
        System.out.printf("维修内容：%s\n", repairment.getFaultContent());
        System.out.printf("来源：%s\n", repairment.getSource());
        System.out.printf("报修时间：%s\n", repairment.getRepairTime());
        List<TaskScheduling> taskSchedulings = repairment.getTaskSchedulingList();
        int schedulingNums = taskSchedulings.size();
        int i = 1;
        for (TaskScheduling taskScheduling: taskSchedulings){
            System.out.printf("第%d次调度:", i++);
            if (taskScheduling.getIfComplete())
                System.out.println("（已结束）");
            else
                System.out.println("（未结束）");
            int j = 1;
            for (RepairmentRecord record: taskScheduling.getRepairmentRecord()){
                System.out.printf("第%d次活动； 开始时间: %s；结束时间：%s\n",
                        j++, record.getStartTime(), record.getFinishTime());
            }
        }
        return taskSchedulings;
    }

    @Override
    public WorkerInfo getWorkerInfo(Worker worker){
        List<TaskScheduling> schedulingList = new ArrayList<>();
        List<Complaint> complaintList = new ArrayList<>();
        for (Repairment repairment: repairmentList){
            List<TaskScheduling> list = repairment.getTaskSchedulingList();
            TaskScheduling currentTask = list.get(list.size()-1);
            if(currentTask.getWorker().getName().equals(worker.getName()) && !currentTask.getIfComplete()){
                schedulingList.add(currentTask);
            }
            if (repairment.getCompliantList() == null){
                continue;
            }
            for (Complaint complaint: repairment.getCompliantList()){
                if (complaint.getProcessingResults())
                    break;

                for (ComplaintDescription description: complaint.getComplaintDescription()){
                    if(description.getPerson().getName().equals(worker.getName()))
                        complaintList.add(complaint);
                }
            }
        }

        return new WorkerInfo(schedulingList, complaintList);
    }

    @Override
    public TaskScheduling getCurrentScheduling(Repairment repairment) {
        System.out.printf("报修人：%s\n", repairment.getApplicant().getName());
        System.out.printf("维修内容：%s\n", repairment.getFaultContent());
        TaskScheduling scheduling;
        if(repairment.getTaskSchedulingList() == null){
            System.out.println("未调度");
            return null;
        } else {
            int size = repairment.getTaskSchedulingList().size();
            scheduling = repairment.getTaskSchedulingList().get(size-1);
            System.out.printf("维修人员：%s\n", scheduling.getWorker().getName());
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
            return scheduling;
        }
    }


    @Override
    public boolean workerAvailable(Worker worker){
        return worker.getIfWorking();
    }

    @Override
    public long getWorkTime(Repairment repairment){
        System.out.println("耗费的维修工时：" + repairment.getWorkTime());
        return repairment.getWorkTime();
    }
}
