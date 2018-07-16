package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.LikedFan;

public interface LikedFanMapper {
    int deleteByPrimaryKey(String id);

    int insert(LikedFan record);

    LikedFan selectByPrimaryKey(String id);

    List<LikedFan> selectAll();

    int updateByPrimaryKey(LikedFan record);
}