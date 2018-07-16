package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.SearchRecord;

public interface SearchRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(SearchRecord record);

    SearchRecord selectByPrimaryKey(String id);

    List<SearchRecord> selectAll();

    int updateByPrimaryKey(SearchRecord record);
}