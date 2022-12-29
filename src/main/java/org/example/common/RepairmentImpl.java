package org.example.common;


import java.time.LocalDate;
import java.util.List;

public class RepairmentImpl implements Repairment {
    public LocalDate repairTime;
    public String faultContent;
    public Person applicant;
    public String source;
    public Dispatcher dispatcher;
    public List<TaskScheduling> taskSchedulingList;
    public Comment comment;
    public List<Complaint> complaintList;
    private long workTime;

    @Override
    public LocalDate getRepairTime() {
        return this.repairTime;
    }

    @Override
    public void setRepairTime(LocalDate repairTime) {
        this.repairTime = repairTime;
    }

    @Override
    public String getFaultContent() {
        return this.faultContent;
    }

    @Override
    public void setFaultContent(String faultContent) {
        this.faultContent = faultContent;
    }

    @Override
    public Person getApplicant() {
        return this.applicant;
    }

    @Override
    public void setApplicant(Person applicant) {
        this.applicant = applicant;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public boolean getIfComplete(TaskScheduling taskScheduling) {
        return taskScheduling.getIfComplete();
    }

    @Override
    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    @Override
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public List<TaskScheduling> getTaskSchedulingList() {
        return this.taskSchedulingList;
    }

    @Override
    public void setTaskSchedulingList(List<TaskScheduling> taskSchedulingList) {
        this.taskSchedulingList = taskSchedulingList;
    }

    @Override
    public Comment getComment() {
        return this.comment;
    }

    @Override
    public List<Complaint> getCompliantList() {
        return this.complaintList;
    }

    @Override
    public void setCompliantList(List<Complaint> compliantList) {
        this.complaintList = compliantList;
    }

    @Override
    public TaskScheduling schedule(List<Worker> workers, List<FaultType> faults) {
        TaskScheduling taskScheduling = new TaskSchedulingImpl();
        for(Worker work : workers){
            List<FaultType> cap = work.getTreatableFaults();
            if(cap.containsAll(faults)){
                taskScheduling.setWorker(work);
                break;
            }

        }
        return taskScheduling;
    }

    @Override
    public Comment comment(String message, int score) {
        Comment commentLog = new CommentImpl();
        commentLog.setAttitudeScore(score);
        commentLog.setTimeliness(message);
        return commentLog;
    }

    @Override
    public Complaint complaint(String complaint) {
        Complaint complaintLog = new ComplaintImpl();
        complaintLog.setComplaintContent(complaint);
        return complaintLog;
    }

    @Override
    public void setWorkTime(RepairmentRecord repairmentRecord) {
        this.workTime = this.workTime + repairmentRecord.getWorkingHours();
    }

    @Override
    public long getWorkTime(){
        return this.workTime;
    }

    @Override
    public void complete() {
        System.out.print("本次报修结束！");
    }
}
