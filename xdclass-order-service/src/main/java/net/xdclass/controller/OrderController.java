package net.xdclass.controller;

import net.xdclass.domain.Video;
import net.xdclass.domain.VideoOrder;
import net.xdclass.service.VideoServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description 旭瑶&小滴课堂 xdclass.net
 * @Author 二当家小D  代码、笔记和技术指导联系我即可
 * @Version 1.0
 **/

@RestController
@RequestMapping("api/v1/video_order")
public class OrderController {



    @Autowired
    private VideoServiceFeign videoServiceFeign;


    @RequestMapping("/save")
    public Object save(int videoId) {

        Video video = videoServiceFeign.saveVideo(new Video());
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoId(video.getId());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());
        videoOrder.setServerInfo(video.getServerInfo());
        return videoOrder;

    }

    @PostMapping("/insert")
    public Video save(@RequestBody Video video) {
        return videoServiceFeign.saveVideo(video);
    }


    private int temp = 0;

    @RequestMapping("/list")
    public Object list() {

        temp++;

        if (temp % 3 == 0) {
            throw new RuntimeException();
        }

        Map<String, String> map = new HashMap<>();
        map.put("title1", "Alibaba微服务专题");
        map.put("title2", "小滴课堂面试专题第一季");
        return map;
    }

}
