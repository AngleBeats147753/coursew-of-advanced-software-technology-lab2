package org.example.common;

/**
 * @author zhoutianlan
 * @since 2022/12/25
 */
public class ComplaintDescriptionImpl implements ComplaintDescription {
    /**
     * 说明人
     */
    private Person person;
    /**
     * 说明内容
     */
    private String description;
    /**
     * 投诉
     */
    private Complaint complaint;

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Complaint getComplaint() {
        return complaint;
    }

    @Override
    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }
}
