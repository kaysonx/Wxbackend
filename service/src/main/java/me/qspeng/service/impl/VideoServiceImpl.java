package me.qspeng.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.val;
import lombok.var;
import me.qspeng.dao.*;
import me.qspeng.model.Comment;
import me.qspeng.model.LikedVideo;
import me.qspeng.model.SearchRecord;
import me.qspeng.model.Video;
import me.qspeng.model.vo.CommentVO;
import me.qspeng.model.vo.VideoVO;
import me.qspeng.service.VideoService;
import me.qspeng.utils.PagedResult;
import me.qspeng.utils.TimeAgoUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SearchRecordMapper searchRecordMapper;

    @Autowired
    private LikedVideoMapper likedVideoMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private Sid sid;

    @Override
    public String saveVideo(Video video) {
        String id = sid.nextShort();
        video.setId(id);
        videoMapper.insertSelective(video);
        return id;
    }

    @Override
    public void updateVideoCover(String videoId, String coverPath) {
        Video video = new Video();
        video.setId(videoId);
        video.setCoverPath(coverPath);
        videoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public PagedResult getAllVideos(Video video, Integer isSaveRecord, Integer page, Integer pageSize) {
        if (isSaveRecord != null && isSaveRecord == 1) {
           SearchRecord record = new SearchRecord();
           record.setId(sid.nextShort());
           record.setContent(video.getVideoDesc());
           searchRecordMapper.insert(record);
        }

        PageHelper.startPage(page, pageSize);
        var list = videoMapper.getAllVideos(video.getVideoDesc(), video.getUserId());
        var pageList = new PageInfo<VideoVO>(list);

        var pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageList.getTotal());

        return pagedResult;
    }

    @Override
    public PagedResult getLikedVideos(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        var list = videoMapper.getLikedVideos(userId);
        return getPagedResult(page, list);
    }

    private <T> PagedResult getPagedResult(Integer page, List<T> list) {
        var pageList = new PageInfo<T>(list);
        var pagedResult = new PagedResult();
        pagedResult.setTotal(pageList.getPages());
        pagedResult.setRows(list);
        pagedResult.setPage(page);
        pagedResult.setRecords(pageList.getTotal());
        return pagedResult;
    }

    @Override
    public PagedResult getFollowedUserVideos(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        var list = videoMapper.getFollowedUserVideos(userId);
        return getPagedResult(page, list);
    }

    @Override
    public List<String> getHotWords() {
        return searchRecordMapper.getHotWords();
    }

    @Override
    public void likeVideo(String userId, String videoId, String videoCreatorId) {
        LikedVideo likedVideo = new LikedVideo();
        likedVideo.setId(sid.nextShort());
        likedVideo.setUserId(userId);
        likedVideo.setVideoId(videoId);
        likedVideoMapper.insert(likedVideo);

        videoMapper.addVideoFavorCount(videoId);
        userMapper.addReceivedLikeCount(videoCreatorId);
    }

    @Override
    public void unLikeVideo(String userId, String videoId, String videoCreatorId) {
        var example = new Example(LikedVideo.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("videoId", videoId);

        likedVideoMapper.deleteByExample(example);
        videoMapper.reduceVideoFavorCount(videoId);
        userMapper.reduceReceivedLikeCount(videoCreatorId);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setId(sid.nextShort());
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);
    }

    @Override
    public PagedResult getAllComments(String videoId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        var list = commentMapper.getComments(videoId);
        var timeFormattedList = list.stream().peek(commentVO -> commentVO.setTimeAgoStr(TimeAgoUtils.format(commentVO.getCreateTime()))).collect(Collectors.toList());
        return getPagedResult(page, timeFormattedList);
    }
}
