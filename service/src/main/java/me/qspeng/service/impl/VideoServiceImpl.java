package me.qspeng.service.impl;

import me.qspeng.dao.VideoMapper;
import me.qspeng.model.Video;
import me.qspeng.service.VideoService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private Sid sid;

    @Override
    public String saveVideo(Video video) {
        String id = sid.nextShort();
        video.setId(id);
        videoMapper.insertSelective(video);
        return id;
    }
}
