package org.example.common;

/**
 * @author zhoutianlan
 * @since 2022/12/25
 */
public class CommentImpl implements Comment {
    /**
     * 响应及时度
     */
    private String timeliness;
    /**
     * 服务态度评分1-5
     */
    private int attitudeScore;

    @Override
    public String getTimeliness() {
        return timeliness;
    }

    @Override
    public void setTimeliness(String timeliness) {
        this.timeliness = timeliness;
    }

    @Override
    public int getAttitudeScore() {
        return attitudeScore;
    }

    @Override
    public void setAttitudeScore(int attitudeScore) {
        this.attitudeScore = attitudeScore;
    }
}
