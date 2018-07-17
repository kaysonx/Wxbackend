package me.qspeng.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "users_report")
public class Report {
    @Id
    private String id;

    /**
     * 被举报用户id
     */
    @Column(name = "deal_user_id")
    private String dealUserId;

    /**
     * 被举报视屏id
     */
    @Column(name = "deal_video_id")
    private String dealVideoId;

    /**
     * 举报类型, 详情见 枚举
     */
    private String title;

    /**
     * 详细秒速
     */
    private String content;

    /**
     * 举报人id
     */
    private String userid;

    /**
     * 举报时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取被举报用户id
     *
     * @return deal_user_id - 被举报用户id
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * 设置被举报用户id
     *
     * @param dealUserId 被举报用户id
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId == null ? null : dealUserId.trim();
    }

    /**
     * 获取被举报视屏id
     *
     * @return deal_video_id - 被举报视屏id
     */
    public String getDealVideoId() {
        return dealVideoId;
    }

    /**
     * 设置被举报视屏id
     *
     * @param dealVideoId 被举报视屏id
     */
    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId == null ? null : dealVideoId.trim();
    }

    /**
     * 获取举报类型, 详情见 枚举
     *
     * @return title - 举报类型, 详情见 枚举
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置举报类型, 详情见 枚举
     *
     * @param title 举报类型, 详情见 枚举
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取详细秒速
     *
     * @return content - 详细秒速
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置详细秒速
     *
     * @param content 详细秒速
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取举报人id
     *
     * @return userid - 举报人id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置举报人id
     *
     * @param userid 举报人id
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * 获取举报时间
     *
     * @return create_date - 举报时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置举报时间
     *
     * @param createDate 举报时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}