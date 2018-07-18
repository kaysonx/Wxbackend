package me.qspeng.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "users")
@Data
@NoArgsConstructor
@ApiModel(value = "User", description = "User model for ORM")
public class User {
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(value = "Username", name = "username", example = "user", required = true)
    private String username;

    @ApiModelProperty(value = "Password", name = "password", example = "pass123456", required = true)
    private String password;

    @Column(name = "face_image")
    @ApiModelProperty(hidden = true)
    private String faceImage;

    @ApiModelProperty(hidden = true)
    private String nickname;

    @Column(name = "fans_counts")
    @ApiModelProperty(hidden = true)
    private Integer fansCounts;

    @Column(name = "follow_counts")
    @ApiModelProperty(hidden = true)
    private Integer followCounts;

    @Column(name = "receive_like_counts")
    @ApiModelProperty(hidden = true)
    private Integer receiveLikeCounts;

    public User(String username, String password) {

        this.username = username;
        this.password = password;
        this.faceImage = "";
        this.fansCounts = 0;
        this.followCounts = 0;
        this.nickname = username;
        this.receiveLikeCounts = 0;

    }

    public void setDefault() {
        this.faceImage = "";
        this.fansCounts = 0;
        this.followCounts = 0;
        this.nickname = this.username;
        this.receiveLikeCounts = 0;
    }
}