package com.youpd.phototsrv;

import com.youpd.phototsrv.utils.SmartFileUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhototSrvApplicationTests {

  @Test
  void contextLoads() throws Exception {
  }

  @Test
  void Thumbnails() throws Exception {
    Thumbnails.of("D:\\1资料\\壁纸\\ui_loading\\loading_01.png")
        .size(800, 800)
        .toFile("D:\\1资料\\壁纸\\ui_loading\\a380_200x300.jpg");
  }

  @Test
  void Thumbnails2() throws Exception {
    String ss = SmartFileUtils.videoImage("D:\\1资料\\111111\\VID_20200217_211104.mp4");
    System.out.println("");
  }
}
