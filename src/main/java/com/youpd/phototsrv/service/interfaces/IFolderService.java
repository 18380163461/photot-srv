package com.youpd.phototsrv.service.interfaces;

import com.youpd.phototsrv.dao.FolderAndChild;
import com.youpd.phototsrv.model.AddFolderRequset;
import com.youpd.phototsrv.model.BaseResponse;
import com.youpd.phototsrv.model.FloderTree;
import java.util.List;

public interface IFolderService {


  BaseResponse<Long> addFolder(AddFolderRequset request);

  BaseResponse<FolderAndChild> folders();

  BaseResponse<List<FloderTree>> folders2(String path);

  BaseResponse<List<FolderAndChild>> files(String path);
}
