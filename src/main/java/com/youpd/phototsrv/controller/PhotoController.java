package com.youpd.phototsrv.controller;

import com.youpd.phototsrv.constants.BusinessConstants;
import com.youpd.phototsrv.dao.FolderAndChild;
import com.youpd.phototsrv.model.AddFolderRequset;
import com.youpd.phototsrv.model.BaseResponse;
import com.youpd.phototsrv.model.FloderTree;
import com.youpd.phototsrv.service.interfaces.IFolderService;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("photo")
public class PhotoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PhotoController.class);
  @Autowired
  IFolderService iFolderService;

  @GetMapping("test")
  public String test() {
    return BusinessConstants.BUSI_SUCCESS_MESSAGE;
  }


  /**
   * @description: <p> 添加一个文件主目录 <p/>
   * @date: 2020-10-17 0:29
   */
  @PostMapping("addFolder")
  public BaseResponse addFolder(@RequestBody AddFolderRequset request) {
    return iFolderService.addFolder(request);
  }

  /**
   * @description: <p> 获取所有目录 <p/>
   * @date: 2020-10-17 0:29
   */
  @GetMapping("folders")
  public BaseResponse<FolderAndChild> folders() {
    return iFolderService.folders();
  }
  /**
   * @description: <p> 获取固定文件夹下的最多两级目录<p/>
   * @date: 2020-10-22 11:47
   */
  @GetMapping("folders2")
  public BaseResponse<List<FloderTree>> folders2(String path) {
    return iFolderService.folders2(path);
  }
  /**
   * @description: <p>获取所有文件 <p/>
   * @date: 2020-10-17 1:04
   */
  @GetMapping("files")
  public BaseResponse<List<FolderAndChild>> files(String path) {
    return iFolderService.files(path);
  }

  /**
   * @description: <p> 获取缩略图<p/>
   * @date: 2020-10-17 1:04
   */
  @RequestMapping("download")
  public void download(HttpServletResponse response, String path) throws Exception {
    // 设置下载的响应头信息
    File file = new File(path);
    response.setHeader("Content-Disposition",
        "attachment;fileName=" + file.getName());
    InputStream is = new FileInputStream(file);
    OutputStream os = response.getOutputStream();
    byte[] buffer = new byte[1024]; // 图片文件流缓存池
    while (is.read(buffer) != -1) {
      os.write(buffer);
    }
    os.flush();
  }

  /**
   * @description: <p> 下载缩略图<p/>
   * @date: 2020-10-17 1:09
   */
  @RequestMapping("downloadThumbnail")
  public void downloadThumbnail(HttpServletResponse response, String path) throws Exception {
    // 设置下载的响应头信息
    File file = new File(path);
    response.setHeader("Content-Disposition",
        "attachment;fileName=" + file.getName());
    OutputStream os = response.getOutputStream();
    Thumbnails.of(path)
        .size(800, 800).toOutputStream(os);
  }
}
