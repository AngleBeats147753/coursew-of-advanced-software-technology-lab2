package org.example.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoutianlan
 * @since 2022/12/25
 */
public class ComplaintImpl implements Complaint {

    /**
     * 投诉内容
     */
    private String content;
    /**
     * 投诉说明列表
     */
    private List<ComplaintDescription> descriptionList;
    /**
     * 投诉处理结果
     * true - 已完成
     * false - 未完成
     */
    private boolean results;

    @Override
    public String getComplaintContent() {
        return content;
    }

    @Override
    public void setComplaintContent(String content) {
        this.content = content;
    }

    @Override
    public List<ComplaintDescription> getComplaintDescription() {
        return descriptionList;
    }

    @Override
    public void setComplaintDescription(List<ComplaintDescription> complaintDescriptionList) {
        this.descriptionList = complaintDescriptionList;
    }

    @Override
    public boolean getProcessingResults() {
        return results;
    }

    @Override
    public void setProcessingResults(boolean results) {
        this.results = results;
    }

    @Override
    public ComplaintDescription describe(Person person, String content) {
        ComplaintDescription complaintDescription = new ComplaintDescriptionImpl();
        complaintDescription.setPerson(person);
        complaintDescription.setDescription(content);
        complaintDescription.setComplaint(this);
        if (this.getComplaintDescription() != null)
            this.getComplaintDescription().add(complaintDescription);
        else {
            this.setComplaintDescription(new ArrayList<>());
            this.getComplaintDescription().add(complaintDescription);
        }
        return complaintDescription;
    }

    @Override
    public void handle(Person person, String message) {
        //设置投诉已完成
        setProcessingResults(true);
    }
}
