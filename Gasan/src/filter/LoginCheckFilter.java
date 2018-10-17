package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//로그인이 되어있는지 체크하는 필터
public class LoginCheckFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// 세션을 받아서, 세션이 살아있는지 확인을 한다.
		// 세션이 있으면?? ->요청한 기능이 있는 곳으로 보내버림.
		// 요청은 이미 리퀘스트 객체와 체인에 담겨있으므로 나중에 chain에 doFilter를 이용해서 넘겨줌.

		// 세션은 어떻게 사용? ServletRequest 에는 http가 없네? 좀 더 포괄적임
		// 다운 캐스팅을 해야 사용가능함.
		HttpServletRequest request = (HttpServletRequest) req; // 다운캐스팅
		HttpSession session = request.getSession(false);// 디폴트가 true이므로 false로 써줘야 세션을 새로 생성하지 않음. 근데 새로 생성 왜? 질문!

		// 요청이 실패(세션이 없을때)했을 때는 로그인 페이지로 이동해야함.
		if (session == null || session.getAttribute("authUser") == null) {
			HttpServletResponse response = (HttpServletResponse) resp; // 다운 캐스팅?
			response.sendRedirect(request.getContextPath() + "/login");
		} else {

			// 세션이 있으면?요청한 기능이 있는 곳으로 보내버림.
			chain.doFilter(req, resp);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
