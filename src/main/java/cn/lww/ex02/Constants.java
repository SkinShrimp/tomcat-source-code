package cn.lww.ex02;

import java.io.File;

/**
 * 1、当第一次调用 servlet 的时候，加载该 servlet 类并调用 servlet 的 init 方法(仅仅一次)
 * 2、对每次请求，构造一个 javax.servlet.ServletRequest 实例和一个 javax.servlet.ServletResponse 实例。
 * 3、调用 servlet 的 service 方法，同时传递 ServletRequest 和 ServletResponse 对象。
 * 4、当 servlet 类被关闭的时候，调用 servlet 的 destroy 方法并卸载 servlet 类。
 *
 *
 *
 * 这个例子实现的功能：
 * 1、等待 HTTP 请求
 * 2、构造一个 ServletRequest 对象和一个 ServletResponse 对象。
 * 3、假如该请求需要一个静态资源的话，调用 StaticResourceProcessor 实例的 process 方法，同时传递 ServletRequest 和 ServletResponse 对象。
 * 4、假如该请求需要一个 servlet 的话，加载 servlet 类并调用 servlet 的 service 方法，同时传递 ServletRequest 和 ServletResponse 对象。
 *
 * 在这个 servlet 容器中，每一次 servlet 被请求的时候，servlet 类都会被加载。
 *
 */

public class Constants {
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "webroot";
}