package handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Food;
import service.CommentListService;
import service.CommentPage;
import service.FoodViewService;

public class FoodViewHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String noStr = req.getParameter("no");
		int currentPage = 1;
		String pageStr = req.getParameter("page");
		if(noStr == null) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter pw = resp.getWriter();
			pw.print("<script>alert('음식점을 찾을 수 음슴 :('); location.href='" + req.getContextPath() + "/foodList'</script>");
			throw new RuntimeException();
		}
		
		if(pageStr != null) {
			currentPage = Integer.parseInt(pageStr);
		}
		
		int foodId = Integer.parseInt(noStr);
		
		
		FoodViewService foodViewService = FoodViewService.getInstance();
		
		Food food = foodViewService.getFood(foodId);
		req.setAttribute("food", food);
		
		CommentListService commentListService = CommentListService.getInstance();
		
		CommentPage commentPage = commentListService.getCommentPage(foodId, currentPage);
		req.setAttribute("commentPage", commentPage);		
		
		System.out.println(commentPage);
		return "/foodEnroll.jsp";
	}

}
