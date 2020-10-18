package com.youpd.phototsrv.utils;

import com.youpd.phototsrv.constants.OrderConstants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.Java2DFrameConverter;

public class SmartFileUtils {

  public static String videoImage(String filePath) throws FrameGrabber.Exception {
    File file = new File(filePath);
    return videoImage(filePath, file.getParent() + File.separator + OrderConstants.SUO_LUE_TU, file.getName().split("\\.")[0]);
  }

  public static String videoImage(File file) throws FrameGrabber.Exception {
    return videoImage(file.getAbsolutePath(), file.getParent() + File.separator + OrderConstants.SUO_LUE_TU, file.getName().split("\\.")[0]);
  }

  /**
   * * 截取视频第六帧的图片
   *
   * @param filePath 视频路径
   * @param dir 文件存放的根目录
   * @return 图片的相对路径 例：pic/1.png
   */
  public static String videoImage(String filePath, String dir, String picName) throws FrameGrabber.Exception {
    String pngPath = "";
    FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
    ff.setFormat("mp4");
    ff.start();
    int ffLength = ff.getLengthInFrames();
    Frame f;
    int i = 0;
    while (i < ffLength) {
      f = ff.grabImage();
      //截取第6帧
      if ((i > 5) && (f.image != null)) {
        //生成图片的相对路径 例如：pic/uuid.png
        pngPath = picName + ".png";
        //执行截图并放入指定位置
        doExecuteFrame(f, dir + File.separator + pngPath);
        break;
      }
      i++;
    }
    ff.stop();
    return dir + File.separator + pngPath;
  }

  /**
   * 生成图片的相对路径
   *
   * @return 图片的相对路径 例：pic/1.png
   */
  private static String getPngPath() {
    return getUUID() + ".png";
  }


  /**
   * 生成唯一的uuid
   *
   * @return uuid
   */
  private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }


  /**
   * 截取缩略图
   *
   * @param f Frame
   */
  private static void doExecuteFrame(Frame f, String targerFilePath) throws Exception {
    String imagemat = "png";
    if (null == f || null == f.image) {
      return;
    }
    Java2DFrameConverter converter = new Java2DFrameConverter();
    BufferedImage bi = converter.getBufferedImage(f);
    File output = new File(targerFilePath);
    try {
      output.mkdirs();
      output.createNewFile();
      ImageIO.write(bi, imagemat, output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
