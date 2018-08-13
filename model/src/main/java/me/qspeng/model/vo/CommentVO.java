package me.qspeng.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CommentVO {
    private String id;
    private String videoId;
    private String fromUserId;
    private Date createTime;
    private String comment;
    private String faceImage;
    private String nickname;
    private String toNickname;
    private String timeAgoStr;
}