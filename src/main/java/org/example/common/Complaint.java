package org.example.common;

import java.util.List;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Complaint {
    
    String getComplaintContent();
    void setComplaintContent(String content);
    List<ComplaintDescription> getComplaintDescription();
    void setComplaintDescription(List<ComplaintDescription> complaintDescriptionList);
    String getProcessingResults();
    void setProcessingResults(String results);


    ComplaintDescription descripte(Person person);
    void handle(Person person, String message);
}
