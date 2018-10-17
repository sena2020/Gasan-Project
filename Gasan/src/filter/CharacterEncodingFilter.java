package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


//인코딩을 해주는 필터
public class CharacterEncodingFilter implements Filter {

private String encoding;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("encoding filter start");
		
		req.setCharacterEncoding(encoding); //설정해서 갖고오고싶다면?
		chain.doFilter(req, resp);


	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		//web.xml의 설정 값으로 인코딩을 설정하는데, 없다면 기본으로 utf-8을 하겠다.
		
		encoding=config.getInitParameter("encoding");
		
		if(encoding==null) {// == 주의
			encoding="utf-8";
		}
		

	}
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
