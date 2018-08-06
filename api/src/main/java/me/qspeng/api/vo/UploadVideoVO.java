package me.qspeng.api.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Data
public class UploadVideoVO {
    private String userId;
    private String bgmId;
    private String desc;
    private float videoDuration;
    private int videoWidth;
    private int videoHeight;
}
