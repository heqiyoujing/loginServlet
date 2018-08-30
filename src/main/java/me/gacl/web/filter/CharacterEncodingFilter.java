package me.gacl.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 过滤器处理表单传到servlet的乱码问题
 * @author gacl
 * 过滤器常用的几个场景：
 * 一、统一全站字符编码
 * 二、禁止浏览器缓存所有动态页面
 * 三、控制浏览器缓存页面中的静态资源
 * 四、实现用户自动登陆
 * 监听器常用的几个场景：
 * 一、统计当前在线人数
 * 二、自定义Session扫描器
 */
public class CharacterEncodingFilter implements Filter {

	//存储系统使用的字符编码
	private String encoding=null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//encoding在web.xml中指定
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//解决表单提交时的中文乱码问题
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}


