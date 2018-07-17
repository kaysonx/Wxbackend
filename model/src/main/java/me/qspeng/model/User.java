package me.qspeng.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
public class User {
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像，如果没有默认给一张
     */
    @Column(name = "face_image")
    private String faceImage;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 粉丝数量
     */
    @Column(name = "fans_counts")
    private Integer fansCounts;

    /**
     * 关注的人总数
     */
    @Column(name = "follow_counts")
    private Integer followCounts;

    /**
     * 接受到的赞美/收藏的数量
     */
    @Column(name = "receive_like_counts")
    private Integer receiveLikeCounts;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取头像，如果没有默认给一张
     *
     * @return face_image - 头像，如果没有默认给一张
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * 设置头像，如果没有默认给一张
     *
     * @param faceImage 头像，如果没有默认给一张
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取粉丝数量
     *
     * @return fans_counts - 粉丝数量
     */
    public Integer getFansCounts() {
        return fansCounts;
    }

    /**
     * 设置粉丝数量
     *
     * @param fansCounts 粉丝数量
     */
    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    /**
     * 获取关注的人总数
     *
     * @return follow_counts - 关注的人总数
     */
    public Integer getFollowCounts() {
        return followCounts;
    }

    /**
     * 设置关注的人总数
     *
     * @param followCounts 关注的人总数
     */
    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    /**
     * 获取接受到的赞美/收藏的数量
     *
     * @return receive_like_counts - 接受到的赞美/收藏的数量
     */
    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    /**
     * 设置接受到的赞美/收藏的数量
     *
     * @param receiveLikeCounts 接受到的赞美/收藏的数量
     */
    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }
}