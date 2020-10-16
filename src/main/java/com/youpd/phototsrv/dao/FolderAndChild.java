package com.youpd.phototsrv.dao;

import java.util.List;

public class FolderAndChild {

  private String absolutePath;
  private String name;
  List<FolderAndChild> child;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbsolutePath() {
    return absolutePath;
  }

  public void setAbsolutePath(String absolutePath) {
    this.absolutePath = absolutePath;
  }

  public List<FolderAndChild> getChild() {
    return child;
  }

  public void setChild(List<FolderAndChild> child) {
    this.child = child;
  }
}
