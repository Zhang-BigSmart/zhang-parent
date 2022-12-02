package com.zhang.practice.thread;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.*;

/**
 * @author : zzh
 * create at:  2022/6/8
 * @description:
 */
public class VodDemo {

    public static final String REGION = "ap-guangzhou";
    public static final String SECRET_ID = "AKID77vWwA9uRlriLjsyvjKitqY1GG1ZuBH8";
    public static final String SECRET_KEY = "g7JI2XnoZ7TedPxFI7j8LJ4arzwf9OO9";
    public static String TASK_ID = "1500013554-ComposeMedia-12588eada9df3403b757c18af95905d0tt0";
    public static String url1 = "http://1500005593.vod2.myqcloud.com/6c9a451evodcq1500005593/bb779cd6387702301852948590/f0.aac";
    public static String url2 = "http://1500005593.vod2.myqcloud.com/6c9a451evodcq1500005593/f371d6a7387702301853045901/f0.aac";


    public static VodClient vodClient;

    public static void main(String[] args) throws TencentCloudSDKException, InterruptedException {
        init();
        //composeMedia();
        describeTaskDetail();
    }

    public static void init() {
        Credential credential = new Credential(SECRET_ID, SECRET_KEY);
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("vod.tencentcloudapi.com");
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        vodClient = new VodClient(credential, REGION, clientProfile);
    }

    public static void composeMedia() throws TencentCloudSDKException {
        ComposeMediaRequest req = new ComposeMediaRequest();

        ComposeMediaOutput composeMediaOutput = new ComposeMediaOutput();
        composeMediaOutput.setFileName("demo");
        composeMediaOutput.setContainer("mp3");

        req.setOutput(composeMediaOutput);

        MediaTrack mediaTrack1 = new MediaTrack();
        mediaTrack1.setType("Audio");
        AudioTrackItem audioTrackItem1 = new AudioTrackItem();
        audioTrackItem1.setSourceMedia(url2);
        MediaTrackItem mediaTrackItem1 = new MediaTrackItem();
        mediaTrackItem1.setType("Audio");
        mediaTrackItem1.setAudioItem(audioTrackItem1);
        mediaTrack1.setTrackItems(new MediaTrackItem[]{mediaTrackItem1});


        MediaTrack mediaTrack2 = new MediaTrack();
        mediaTrack2.setType("Audio");
        AudioTrackItem audioTrackItem2 = new AudioTrackItem();
        audioTrackItem2.setSourceMedia(url1);
        //audioTrackItem2.setSourceMediaStartTime(4f);
        MediaTrackItem mediaTrackItem2 = new MediaTrackItem();
        mediaTrackItem2.setType("Audio");
        mediaTrackItem2.setAudioItem(audioTrackItem2);
        mediaTrack2.setTrackItems(new MediaTrackItem[]{mediaTrackItem2});

        req.setTracks(new MediaTrack[]{mediaTrack1, mediaTrack2});
        ComposeMediaResponse composeMediaResponse = vodClient.ComposeMedia(req);
        System.out.println(composeMediaResponse.toString());

        System.out.println("requestId: " + composeMediaResponse.getRequestId());
        System.out.println("taskId: " + composeMediaResponse.getTaskId());
        TASK_ID = composeMediaResponse.getTaskId();
    }

    public static void describeTaskDetail() throws TencentCloudSDKException, InterruptedException {
        System.out.println("=== task detail query ===");
        Thread.sleep(10000);
        DescribeTaskDetailRequest req = new DescribeTaskDetailRequest();
        req.setSubAppId(1500013554L);
        req.setTaskId(TASK_ID);
        DescribeTaskDetailResponse res = vodClient.DescribeTaskDetail(req);
        System.out.println(JSON.toJSONString(res));
        System.out.println("=== task detail result ===");
    }


}
