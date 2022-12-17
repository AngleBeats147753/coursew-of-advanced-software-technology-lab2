package org.example.common;

import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Worker extends Person{

    List<FaultType> getTreatableFaults();
    void setTreatableFaults(List<FaultType> treatableFaults);

    List<Repairment> getRepairmentList();
    void setRepairmentList(List<Repairment> repairmentList);
}
