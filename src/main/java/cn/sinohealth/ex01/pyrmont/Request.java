package cn.sinohealth.ex01.pyrmont;

import java.io.InputStream;
import java.io.IOException;

/**
 * 代表一个HTTP请求,从负责与客户端通信的 Socket 中传递过来InputStream 对象来构造这个类的一个实例。调用 InputStream 对象其中一个 read 方法来获
 * 取 HTTP 请求的原始数据。
 */
public class Request {

  private InputStream input;
  //统一资源标识符
  private String uri;

  //接受Socket的InputStream
  public Request(InputStream input) {
    this.input = input;
  }


  //解析HTTP请求
  //parse 方法解析 HTTP 请求里边的原始数据,
  public void parse() {
    // Read a set of characters from the socket
    StringBuffer request = new StringBuffer(2048);
    int i;
    byte[] buffer = new byte[2048];
    try {
      i = input.read(buffer);
    }
    catch (IOException e) {
      e.printStackTrace();
      i = -1;
    }
    for (int j=0; j<i; j++) {
      request.append((char) buffer[j]);
    }
    //从Socket接收到的请求
    System.out.print(request.toString());
    uri = parseUri(request.toString());
  }


  /**
   * 是通过调用 HTTP请求的私有方法 parseUri 获得的 URI
   * @param requestString
   * @return
   */
  private String parseUri(String requestString) {
    int index1, index2;
    index1 = requestString.indexOf(' ');
    if (index1 != -1) {
      index2 = requestString.indexOf(' ', index1 + 1);
      if (index2 > index1)
        return requestString.substring(index1 + 1, index2);
    }
    return null;
  }

  public String getUri() {
    return uri;
  }

}