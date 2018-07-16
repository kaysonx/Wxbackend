package me.qspeng.dao;

import java.util.List;
import me.qspeng.model.Report;

public interface ReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(Report record);

    Report selectByPrimaryKey(String id);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);
}