package me.qspeng.dao;

import me.qspeng.model.Comment;
import me.qspeng.model.vo.CommentVO;
import me.qspeng.utils.MyMapper;

import java.util.List;

public interface CommentMapper extends MyMapper<Comment> {
    List<CommentVO> getComments(String videoId);
}