package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.BackgroundMusic;

public interface BackgroundMusicMapper {
    int deleteByPrimaryKey(String id);

    int insert(BackgroundMusic record);

    BackgroundMusic selectByPrimaryKey(String id);

    List<BackgroundMusic> selectAll();

    int updateByPrimaryKey(BackgroundMusic record);
}