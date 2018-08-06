package me.qspeng.api.controller;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import me.qspeng.api.constant.Constant;
import me.qspeng.api.constant.VideoState;
import me.qspeng.api.tool.FFMpegTool;
import me.qspeng.api.vo.UploadVideoVO;
import me.qspeng.model.BackgroundMusic;
import me.qspeng.model.Video;
import me.qspeng.service.BgmService;
import me.qspeng.service.VideoService;
import me.qspeng.utils.JSONResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static me.qspeng.api.constant.Constant.FILE_SPACE;

@RestController
@RequestMapping("/video")
@Api(value = "Controller for video", tags = {"video"})
@Log4j2
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private BgmService bgmService;

    @PostMapping(headers="content-type=multipart/form-data")
    public JSONResult upload(UploadVideoVO uploadVideoVO, MultipartFile file) throws Exception {
        final String userId = uploadVideoVO.getUserId();
        String videoPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";
        String finalVideoPath;
        try {
            if (file == null) {
                return JSONResult.errorMsg("上传出错...");
            }

            String fileName = file.getOriginalFilename();

            if(Strings.isNullOrEmpty(fileName)) {
                throw new Exception();
            }
            StringBuilder fileNamePrefix = new StringBuilder();
            String[] fileNameSplit = fileName.split("\\.");
            for (int i = 0; i < fileNameSplit.length - 1; i++) {
                fileNamePrefix.append(fileNameSplit[i]);
            }

            finalVideoPath = FILE_SPACE + videoPathDB + "/" + fileName;
            videoPathDB += ("/" + fileName);
            coverPathDB += ("/" + fileNamePrefix + ".jpg");

            File outputFile = new File(finalVideoPath);
            if(outputFile.getParentFile() == null || !outputFile.getParentFile().isDirectory()) {
                Objects.requireNonNull(outputFile.getParentFile()).mkdirs();
            }
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(outputFile);;
            @Cleanup InputStream inputStream = file.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
        }
        FFMpegTool FFMpegTool = new FFMpegTool(Constant.FFMPEG_EXECUTE_PATH);

        if(!Strings.isNullOrEmpty(uploadVideoVO.getBgmId())) {
            BackgroundMusic bgm = bgmService.getBgmById(uploadVideoVO.getBgmId());
            String mp3InputPath = FILE_SPACE + bgm.getPath();
            String videoInputPath = FILE_SPACE + videoPathDB;
            String mergedVideoName = UUID.randomUUID().toString() + ".mp4";
            videoPathDB = "/" + userId + "/video/" + mergedVideoName;
            finalVideoPath = FILE_SPACE + videoPathDB;
            FFMpegTool.mergeVideoWithMusic(videoInputPath, mp3InputPath, uploadVideoVO.getVideoDuration(), finalVideoPath);
        }

        log.info("videoPathDB:" + videoPathDB);
        log.info("finalVideoPath:" + finalVideoPath);

        FFMpegTool.getVideoCover(finalVideoPath, FILE_SPACE + coverPathDB);

        Video video = new Video();
        video.setAudioId(uploadVideoVO.getBgmId());
        video.setUserId(uploadVideoVO.getUserId());
        video.setVideoSeconds(uploadVideoVO.getVideoDuration());
        video.setVideoHeight(uploadVideoVO.getVideoHeight());
        video.setVideoWidth(uploadVideoVO.getVideoWidth());
        video.setVideoDesc(uploadVideoVO.getDesc());
        video.setCoverPath(coverPathDB);
        video.setVideoPath(videoPathDB);
        video.setStatus(VideoState.SUCCESS.value);
        video.setCreateTime(new Date());

        String videoId = videoService.saveVideo(video);
        return JSONResult.ok(videoId);
    }
}
