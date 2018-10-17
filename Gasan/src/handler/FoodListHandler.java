package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ArticlePage;
import service.FoodApplyListService;
import service.FoodListService;
import service.FoodPage;

public class FoodListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		FoodListService foodListService = FoodListService.getInstance();
		FoodApplyListService listService = FoodApplyListService.getInstance();

		String pageStr = req.getParameter("page");
		String category = req.getParameter("category");
		String order = req.getParameter("order");
		String restName = req.getParameter("restName");

		int currentPage = 1;
		if (pageStr != null && !pageStr.isEmpty()) {
			currentPage = Integer.parseInt(pageStr);
		}

		FoodPage foodPage = null;
		if (req.getMethod().equals("GET")) {
			if (order == null || order.isEmpty()) {
				if (category == null || category.isEmpty()) {
					foodPage = foodListService.getFoodPage(currentPage, order);
				} else {
					foodPage = foodListService.getFoodPageByCategory(currentPage, category, order);
				}
			} else if (order.equals("star")) {
				foodPage = foodListService.getFoodPageByScore(currentPage, category, order);
			} else if (order.equals("views")) {
				foodPage = foodListService.getFoodPageByViews(currentPage, category, order);
			} else if (order.equals("reviews")) {
				foodPage = foodListService.getFoodPageByReviews(currentPage, category, order);
			} else if (order.equals("id")) {
				foodPage = foodListService.getFoodPageById(currentPage, category, order);
			}
		} else if (req.getMethod().equals("POST")) {
			foodPage = foodListService.getFoodPageByName(currentPage, restName, order);
		}

		ArticlePage articlePage = listService.getArticlePageForMain();

		req.setAttribute("articlePage", articlePage);
		req.setAttribute("foodPage", foodPage);

		return "/index.jsp";

	}

}
