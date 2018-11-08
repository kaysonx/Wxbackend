package me.qspeng.service;

import me.qspeng.model.Comment;
import me.qspeng.model.Video;
import me.qspeng.utils.PagedResult;

import java.util.List;

public interface VideoService {
    String saveVideo(Video video);

    void updateVideoCover(String videoId, String coverPath);

    PagedResult getAllVideos(Video video, Integer isSaveRecord,
                                    Integer page, Integer pageSize);

    PagedResult getLikedVideos(String userId, Integer page, Integer pageSize);

    PagedResult getFollowedUserVideos(String userId, Integer page, Integer pageSize);

    List<String> getHotWords();

    void likeVideo(String userId, String videoId, String videoCreatorId);

    void unLikeVideo(String userId, String videoId, String videoCreatorId);

    void addComment(Comment comment);

    PagedResult getAllComments(String videoId, Integer page, Integer pageSize);
}
