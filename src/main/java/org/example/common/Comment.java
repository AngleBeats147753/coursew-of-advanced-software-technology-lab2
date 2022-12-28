package org.example.common;

/**
 * 评价
 *
 * @author 黄磊
 * @since 2022/12/16
 **/
public interface Comment {
    String getTimeliness();

    void setTimeliness(String timeliness);

    int getAttitudeScore();

    void setAttitudeScore(int attitudeScore);
}
