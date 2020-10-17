package com.youpd.phototsrv.service.impl;

import com.youpd.phototsrv.constants.BusinessConstants;
import com.youpd.phototsrv.dao.Folder;
import com.youpd.phototsrv.dao.FolderAndChild;
import com.youpd.phototsrv.dao.IFolderDao;
import com.youpd.phototsrv.model.AddFolderRequset;
import com.youpd.phototsrv.model.BaseResponse;
import com.youpd.phototsrv.service.interfaces.IFolderService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class FolderServiceImpl implements IFolderService {

  @Autowired
  IFolderDao iFolderDao;

  @Override
  public BaseResponse<Long> addFolder(AddFolderRequset request) {
    BaseResponse<Long> baseResponse = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    Folder folder = new Folder();
    BeanUtils.copyProperties(request, folder);
    Folder insert = iFolderDao.save(folder);
    baseResponse.setResult(insert.getId());
    return baseResponse;
  }

  @Override
  public BaseResponse<FolderAndChild> folders() {
    BaseResponse<FolderAndChild> baseResponse = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    List<Folder> list = iFolderDao.findAll();
    FolderAndChild folderAndChildAll = new FolderAndChild();
    folderAndChildAll.setName("全部");
    List<FolderAndChild> folderAndChildList = new ArrayList<>();
    for (Folder temp : list) {
      File file = new File(temp.getAbsolutePath());
      FolderAndChild folderAndChild = new FolderAndChild();
      folderAndChild.setAbsolutePath(temp.getAbsolutePath());
      folderAndChild.setName(temp.getAbsolutePath());
      folderAndChildList.add(folderAndChild);
      folderAndChildAll.setChild(folderAndChildList);
      if (file.isDirectory()) {
        folderAndChild.setChild(getFileTree(file, false));
        ;
      }
    }
    baseResponse.setResult(folderAndChildAll);
    return baseResponse;
  }

  @Override
  public BaseResponse<List<FolderAndChild>> files(String path) {
    BaseResponse<List<FolderAndChild>> baseResponse = new BaseResponse<>(true, BusinessConstants.BUSI_SUCCESS_CODE, BusinessConstants.BUSI_SUCCESS_MESSAGE);
    File file = new File(path);
    List<FolderAndChild> list = getFileTree(file, true);
    baseResponse.setResult(list);
    return baseResponse;
  }

  public static List<FolderAndChild> getFileTree(File file, boolean isFile) {
    List<FolderAndChild> baseTreeNodes = null;
    File[] childFiles = file.listFiles();
    if (childFiles != null) {
      for (File listFile : childFiles) {
        if (isFile == listFile.isFile()) {
          if (ObjectUtils.isEmpty(baseTreeNodes)) {
            baseTreeNodes = new ArrayList<>();
          }
          FolderAndChild baseTreeNode = new FolderAndChild();
          baseTreeNode.setName(listFile.getName());
          baseTreeNode.setAbsolutePath(listFile.getAbsolutePath());
          baseTreeNode.setChild(getFileTree(listFile, isFile));
          baseTreeNodes.add(baseTreeNode);
        }
      }
    }
    return baseTreeNodes;
  }
}
