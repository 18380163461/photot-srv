package com.youpd.phototsrv.utils;

import java.net.FileNameMap;
import java.net.URLConnection;
import org.springframework.util.ObjectUtils;

public class FileTypeUtils {

  private final static String PREFIX_VIDEO = "video/";

  private static final String PIC = "bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp,avif";
  private static final String VIDEO = "wmv,asf,asx,mp4,rm,rmvb,mov,m4v,3gp";

  public static boolean isPic(String name) {
    for (String temp : PIC.split(",")) {
      if (name.endsWith(temp)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the Mime Type from a File
   *
   * @param fileName 文件名
   * @return 返回MIME类型 thx https://www.oschina.net/question/571282_223549 add by fengwenhua 2017年5月3日09:55:01
   */
  private static String getMimeType(String fileName) {
    FileNameMap fileNameMap = URLConnection.getFileNameMap();
    String type = fileNameMap.getContentTypeFor(fileName);
    return type;
  }

  /**
   * 根据文件后缀名判断 文件是否是视频文件
   *
   * @param fileName 文件名
   * @return 是否是视频文件
   */
  public static boolean isVedioFile(String fileName) {
    String mimeType = getMimeType(fileName);
    if (!ObjectUtils.isEmpty(fileName) && mimeType.contains(PREFIX_VIDEO)) {
      return true;
    }
    return false;
  }
}
