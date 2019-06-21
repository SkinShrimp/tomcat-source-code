package cn.lww.ex01;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

/**
 * 一个简单的Web服务器
 */
public class HttpServer {

  /** WEB_ROOT is the directory where our HTML and other files reside.
   *  For this package, WEB_ROOT is the "webroot" directory under the working
   *  directory.
   *  The working directory is the location in the file system
   *  from where the java command was invoked.
   */
  //公共静态资源
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "webroot";

  // shutdown command 停止服务的语句
  private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

  // the shutdown command received
  private boolean shutdown = false;

  public static void main(String[] args) {
    System.out.println(WEB_ROOT);
    HttpServer server = new HttpServer();
    server.await();
  }

  public void await() {
    //服务器套接字对的
    ServerSocket serverSocket = null;
    int port = 8080;
    try {
      serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
    }
    catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }

    // Loop waiting for a request
    while (!shutdown) {
      Socket socket = null;
      InputStream input = null;
      OutputStream output = null;
      try {
        //只有在8080端口接受到http请求的时候才会返回
        socket = serverSocket.accept();
        //将字节流输入到内存，需要用到的对象
        input = socket.getInputStream();
        //从内存中输出字节流，需要用到的对象 可能借助PrintWriter
        output = socket.getOutputStream();

        // create Request object and parse
        //创建一个Request方法,并且使用parse方法去解析它
        Request request = new Request(input);
        request.parse();

        // create Response object
        //创建一个Response对象并把Request对象设置给他
        Response response = new Response(output);
        response.setRequest(request);
        response.sendStaticResource();

        // Close the socket
        //关闭套接字
        socket.close();

        //check if the previous URI is a shutdown command
        //检查是否是关闭应用命令是的话关闭应用
        shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
      }
      catch (Exception e) {
        e.printStackTrace();
        continue;
      }
    }
  }
}
