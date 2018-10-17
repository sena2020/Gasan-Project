package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ArticlePage;
import service.FoodApplyListService;


public class FoodApplyListHandler implements CommandHandler {

	// 페이지 번호를 파라미터로 받고 서비스를 이용해서 페이지 목록을 받아온다.
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub

		FoodApplyListService listService = FoodApplyListService.getInstance();
		String pageNostr = req.getParameter("pageNo");

		int pageNo = 1; // pageNo : 페이지 넘버
		if (pageNostr != null) {
			pageNo = Integer.parseInt(pageNostr);
		}
		// 아티클 페이지 객체를 만들어서 아티클서비스를 통해서 아티클페이지를 받아온다.
		ArticlePage articlePage = listService.getArticlePage(pageNo);
		req.setAttribute("articlePage", articlePage);//얘를 통해 모든 정보를 꺼내오면 됨.
		return "/applylist.jsp";
	}

}
