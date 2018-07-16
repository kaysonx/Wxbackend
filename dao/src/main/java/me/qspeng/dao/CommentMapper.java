package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.Comment;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    Comment selectByPrimaryKey(String id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}