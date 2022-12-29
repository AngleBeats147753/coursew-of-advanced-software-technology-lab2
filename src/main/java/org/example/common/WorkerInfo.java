package org.example.common;

import java.util.List;

public class WorkerInfo {
    public List<TaskScheduling> taskSchedulings;
    public List<Complaint> complaintList;


    public WorkerInfo(List<TaskScheduling> taskSchedulings, List<Complaint> complaintList) {
        this.taskSchedulings = taskSchedulings;
        this.complaintList = complaintList;
    }

    public List<TaskScheduling> getTaskSchedulings() {
        return taskSchedulings;
    }

    public void setTaskSchedulings(List<TaskScheduling> taskSchedulings) {
        this.taskSchedulings = taskSchedulings;
    }

    public List<Complaint> getComplaintList() {
        return complaintList;
    }

    public void setComplaintList(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    @Override
    public boolean equals(Object otherObject){       //测试两个对象是否是同一个对象，是的话返回true
        if(this == otherObject) {  //测试检测的对象是否为空，是就返回false
            return true;
        }
        if(otherObject == null) {  //测试两个对象所属的类是否相同，否则返回false
            return false;
        }
        if(getClass() != otherObject.getClass()) {  //对otherObject进行类型转换以便和类A的对象进行比较
            return false;
        }
        WorkerInfo other=(WorkerInfo) otherObject;
        return complaintList.equals(other.complaintList) && taskSchedulings.equals(other.taskSchedulings);
    }
}
