package me.qspeng.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import me.qspeng.service.BgmService;
import me.qspeng.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Controller for bgm", tags = {"bgm"})
@RequestMapping("/bgm")
public class BgmController {

    @Autowired
    private BgmService bgmService;

    @GetMapping
    @ApiOperation(value = "bgmList", notes = "get bgm list")
    public JSONResult list() {
        return JSONResult.ok(bgmService.getBgmList());
    }

    @GetMapping("/{bgmId}")
    @ApiOperation(value = "bgmItem", notes = "get bgm")
    public JSONResult bgm(@PathVariable("bgmId") @NonNull String bgmId) {
        return JSONResult.ok(bgmService.getBgmById(bgmId));
    }
}
