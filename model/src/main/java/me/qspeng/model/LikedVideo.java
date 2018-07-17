package me.qspeng.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users_like_videos")
public class LikedVideo {
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 视频id
     */
    @Column(name = "video_id")
    private String videoId;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取视频id
     *
     * @return video_id - 视频id
     */
    public String getVideoId() {
        return videoId;
    }

    /**
     * 设置视频id
     *
     * @param videoId 视频id
     */
    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }
}