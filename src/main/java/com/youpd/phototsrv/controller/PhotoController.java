package com.youpd.phototsrv.controller;

import com.youpd.phototsrv.constants.BusinessConstants;
import com.youpd.phototsrv.dao.FolderAndChild;
import com.youpd.phototsrv.model.AddFolderRequset;
import com.youpd.phototsrv.model.BaseResponse;
import com.youpd.phototsrv.service.interfaces.IFolderService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

  @GetMapping("files")
  public BaseResponse<List<FolderAndChild>> files(String path) {
    return iFolderService.files(path);
  }


}
