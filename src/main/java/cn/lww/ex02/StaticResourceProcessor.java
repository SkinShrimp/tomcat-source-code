package cn.lww.ex02;

import java.io.IOException;

//用来提供静态资源请求
public class StaticResourceProcessor {

  public void process(Request request, Response response) {
    try {
      response.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}