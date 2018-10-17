package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.CommandHandler;
import handler.NullHandler;

public class MainController extends HttpServlet {

	private Map<String, CommandHandler> hMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		// 프로퍼티파일에 있는 값을 꺼내 올 프로퍼티 객체를 생성.

		// 핸들러와 커맨드 명령어 정보가 있는 프로퍼티 파일을 프로퍼티 객체에 담기.
		Properties prop = new Properties();
		// getInitParameter("handlerConfigFile")를 통해 프로퍼티 파일의 위치값을 가져오고
		// getRealPath를 통해 실제 시스템 경로를 가져왐
		String confPath = getServletContext().getRealPath(getInitParameter("handlerConfigFile")); // xml에 기재 할 내용
		// 파일에서 프로퍼티로 내용를 가져옴!

		try (FileReader fr = new FileReader(confPath)) {
			prop.load(fr);// <String, String> -> <명령어, 핸들러클래스풀네임>
			prop.list(System.out);

			// 프로퍼티에 담긴 아이를 hMap에 객체로 만들어서 담음.
			for (Object key : prop.keySet()) {
				String c = prop.getProperty((String) key);
				System.out.println(c);
				Class<?> cl = Class.forName(c);
				hMap.put((String) key, (CommandHandler)cl.getDeclaredConstructor().newInstance());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getRequestURL());
		String command = req.getRequestURI();
		// 핸들러 객체를 담을 핸들러 변수
		CommandHandler handler = null;

		System.out.println("contextPath" + req.getContextPath());

		// 프로젝트 주소를 잘라낼 것!
		if (command.indexOf(req.getContextPath()) == 0) {
			command = command.substring(req.getContextPath().length());
			// substring(시작 인덱스) -> 시작인데스부터~끝까지를 반환.
		}
		System.out.println("수정후: " + command);

		// 앞을 잘라내고 주소 뒤 명령부분만 남김.
		if (command == null) {
			// 명령어가 없을 때 NullHandler를 통해 처리!
			//handler = new NullHandler();
			command = "/foodList";
		} else {
			// 명령어가 들어오면 해당 명령어를 키로하는 맵에서 핸들러 객체를 받아옴!
			handler = hMap.get(command);
		}

		// 핸들러로부터 결과페이지 정보를 보여줄 화면주소
		String view = null;

		// 핸들러를 통해 요청에 해당되는 작업를 하고 결과 화면 주소를 받아옴.
		try {
			view = handler.process(req, resp);
			System.out.println(view);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 요청을 viewPage로 전달해 줌.
		if (view != null) {// RequestDispatcher
			RequestDispatcher dispatcher = req.getRequestDispatcher(view);
			dispatcher.forward(req, resp);
		}
	}
}
