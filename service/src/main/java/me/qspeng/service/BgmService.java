package me.qspeng.service;

import me.qspeng.model.BackgroundMusic;

import java.util.List;

public interface BgmService {
    List<BackgroundMusic> getBgmList();
    BackgroundMusic getBgmById(String bgmId);
}
