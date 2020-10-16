package com.youpd.phototsrv.service.interfaces;

import com.youpd.phototsrv.dao.FolderAndChild;
import com.youpd.phototsrv.model.AddFolderRequset;
import com.youpd.phototsrv.model.BaseResponse;
import java.util.List;

public interface IFolderService {


  BaseResponse<Long> addFolder(AddFolderRequset request);

  BaseResponse<FolderAndChild> folders();

  BaseResponse<List<FolderAndChild>> files(String path);
}
