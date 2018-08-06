package me.qspeng.service.impl;

import me.qspeng.dao.BackgroundMusicMapper;
import me.qspeng.model.BackgroundMusic;
import me.qspeng.service.BgmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    private BackgroundMusicMapper backgroundMusicMapper;

    @Override
    public List<BackgroundMusic> getBgmList() {
        return backgroundMusicMapper.selectAll();
    }

    @Override
    public BackgroundMusic getBgmById(String bgmId) {
        return backgroundMusicMapper.selectByPrimaryKey(bgmId);
    }
}
