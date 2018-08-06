package me.qspeng.api.tool;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTool {
    private String ffmpegCommand;

    public FFMpegTool(String ffmpegCommand) {
        this.ffmpegCommand = ffmpegCommand;
    }

    public void mergeVideoWithMusic(String videoInputPath, String mp3InputPath,
                                    double seconds, String videoOutputPath) throws Exception {
        //ffmpeg -i old.mp4 -i bgm.mp3 -t 7 -y new.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegCommand);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(videoOutputPath);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        @Cleanup InputStream errorStream = process.getErrorStream();
        @Cleanup InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        @Cleanup BufferedReader br = new BufferedReader(inputStreamReader);

        while ( br.readLine() != null ) { }
    }

    public void getVideoCover(String videoInputPath, String coverOutputPath) throws IOException, InterruptedException {
        //ffmpeg -ss 00:00:01 -i input.mp4 -vframes 1 output.jpg
        List<String> command = new java.util.ArrayList<String>();
        command.add(ffmpegCommand);

        // 指定截取第1秒
        command.add("-ss");
        command.add("00:00:01");

        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);

        command.add("-vframes");
        command.add("1");

        command.add(coverOutputPath);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        @Cleanup InputStream errorStream = process.getErrorStream();
        @Cleanup InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        @Cleanup BufferedReader br = new BufferedReader(inputStreamReader);

        while ( br.readLine() != null ) { }
    }
}
