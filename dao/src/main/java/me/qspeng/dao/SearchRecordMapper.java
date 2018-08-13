package me.qspeng.dao;

import me.qspeng.model.SearchRecord;
import me.qspeng.utils.MyMapper;

import java.util.List;

public interface SearchRecordMapper extends MyMapper<SearchRecord> {
    List<String> getHotWords();
}