package com.youpd.phototsrv.model;

import java.util.List;

public class FloderTree {
  private String absolutePath;
  private String text;
  List<FloderTree> children;

  public String getAbsolutePath() {
    return absolutePath;
  }

  public void setAbsolutePath(String absolutePath) {
    this.absolutePath = absolutePath;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public List<FloderTree> getChildren() {
    return children;
  }

  public void setChildren(List<FloderTree> children) {
    this.children = children;
  }
}
