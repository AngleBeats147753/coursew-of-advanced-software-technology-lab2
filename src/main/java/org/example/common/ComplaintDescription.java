package org.example.common;

/**
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface ComplaintDescription {

    Person getPerson();
    void setPerson(Person person);

    String getDescription();
    void setDescription(String description);

    Complaint getComplaint();
    void setComplaint(Complaint complaint);

}
