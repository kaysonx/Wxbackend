package me.qspeng.dao;

import me.qspeng.model.Video;
import me.qspeng.model.vo.VideoVO;
import me.qspeng.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends MyMapper<Video> {

    List<VideoVO> getAllVideos(@Param("videoDesc") String videoDesc,
                               @Param("userId") String userId);

    List<VideoVO> getFollowedUserVideos(String userId);

    List<VideoVO> getLikedVideos(@Param("userId") String userId);

    void addVideoFavorCount(String videoId);

    void reduceVideoFavorCount(String videoId);
}