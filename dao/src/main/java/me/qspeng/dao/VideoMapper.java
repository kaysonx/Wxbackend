package me.qspeng.dao;

import me.qspeng.model.Video;
import me.qspeng.model.vo.VideoVO;
import me.qspeng.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper extends MyMapper<Video> {
    /**
     * @Description: 条件查询所有视频列表
     */
    List<VideoVO> getAllVideos(@Param("videoDesc") String videoDesc,
                               @Param("userId") String userId);

    /**
     * @Description: 查询关注的视频
     */
    List<VideoVO> getMyFollowersVideos(String userId);

    /**
     * @Description: 查询点赞视频
     */
    List<VideoVO> getMyLikedVideos(@Param("userId") String userId);

    /**
     * @Description: 对视频喜欢的数量进行累加
     */
    void addVideoLikeCount(String videoId);

    /**
     * @Description: 对视频喜欢的数量进行累减
     */
    void reduceVideoLikeCount(String videoId);
}