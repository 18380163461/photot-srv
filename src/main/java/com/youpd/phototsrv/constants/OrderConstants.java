package com.youpd.phototsrv.constants;


public class OrderConstants {

  private OrderConstants() {
  }

  public static int INSERTBATCH_COUNT = 1000;

  /**
   * 公共状态类型
   */
  public enum CommonStatus {
    STATUS0(0, "NO"),
    STATUS1(1, "YES"),
    STATUS2(2, "DEL"),
    ;
    private final Integer key;
    private final String name;

    CommonStatus(Integer key, String name) {
      this.key = key;
      this.name = name;
    }

    public static String getNameByKey(Integer key) {
      for (CommonStatus type : CommonStatus.values()) {
        if (type.getKey().equals(key)) {
          return type.getName();
        }
      }
      return null;
    }

    public Integer getKey() {
      return key;
    }

    public String getName() {
      return name;
    }
  }

}


