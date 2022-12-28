package org.example.common;

import java.util.List;

/**
 * 投诉
 *
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Complaint {
    String getComplaintContent();

    void setComplaintContent(String content);

    List<ComplaintDescription> getComplaintDescription();

    void setComplaintDescription(List<ComplaintDescription> complaintDescriptionList);

    boolean getProcessingResults();

    void setProcessingResults(boolean results);

    /**
     * 提交说明
     */
    ComplaintDescription describe(Person person, String content);

    /**
     * 投诉处理
     */
    void handle(Person person, String message);
}
