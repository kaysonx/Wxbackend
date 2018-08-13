package me.qspeng.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VideoVO {
    private String id;
    private String userId;
    private String audioId;
    private String videoDesc;
    private String videoPath;
    private Float videoSeconds;
    private Integer videoWidth;
    private Integer videoHeight;
    private String coverPath;
    private Long likeCounts;
    private Integer status;
    private Date createTime;
    private String faceImage;
    private String nickname;
}