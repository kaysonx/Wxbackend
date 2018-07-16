package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.LikedVideo;

public interface LikedVideoMapper {
    int deleteByPrimaryKey(String id);

    int insert(LikedVideo record);

    LikedVideo selectByPrimaryKey(String id);

    List<LikedVideo> selectAll();

    int updateByPrimaryKey(LikedVideo record);
}