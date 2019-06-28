package cn.lww.ex02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {

  public void init(ServletConfig config) throws ServletException {
    System.out.println("init");
  }

  public void service(ServletRequest request, ServletResponse response)
    throws ServletException, IOException {
    System.out.println("from service");

    //在 getWriter 方法中，PrintWriter 类的构造方法的第二个参数是一个布尔值表明是否允许
    //自动刷新。传递 true 作为第二个参数将会使任何 println 方法的调用都会刷新输出(output)。
    //不过，print 方法不会刷新输出。
    //因此，任何 print 方法的调用都会发生在 servlet 的 service 方法的最后一行，输出将不会
    //被发送到浏览器。这个缺点将会在下一个应用程序中修复。
    PrintWriter out = response.getWriter();
    out.println("Hello. Roses are red.");
    out.print("Violets are blue.");
  }

  public void destroy() {
    System.out.println("destroy");
  }

  public String getServletInfo() {
    return null;
  }
  public ServletConfig getServletConfig() {
    return null;
  }

}
