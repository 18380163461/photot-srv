package com.youpd.phototsrv.model;

public class PageArg {

  public PageArg() {
  }

  public PageArg(int pageNum, int pageSize) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
  }

  /**
   * 请求查询的页码
   */
  private int pageNum = 1;

  /**
   * 每页显示条数
   */
  private int pageSize = 10;

  public int getPageNum() {
    return pageNum;
  }

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
}
