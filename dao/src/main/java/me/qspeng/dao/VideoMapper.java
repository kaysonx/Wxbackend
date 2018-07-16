package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.Video;

public interface VideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(Video record);

    Video selectByPrimaryKey(String id);

    List<Video> selectAll();

    int updateByPrimaryKey(Video record);
}