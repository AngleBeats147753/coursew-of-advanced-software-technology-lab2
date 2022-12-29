package org.example.common;

import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Worker extends Person{

    boolean getIfWorking(); //获取是否在工作
    void beginToWork();    //设置开始工作
    void completeWork();    //设置结束工作

    List<FaultType> getTreatableFaults();
    void setTreatableFaults(List<FaultType> treatableFaults);

    List<Repairment> getRepairmentList();
    void setRepairmentList(List<Repairment> repairmentList);
}
