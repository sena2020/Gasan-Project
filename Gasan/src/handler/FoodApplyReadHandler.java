package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ArticleFoodApply;
import service.FoodApplyReadService;

public class FoodApplyReadHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String noStr = req.getParameter("no");
		
		if(noStr == null) {
			throw new RuntimeException();
		}
		
		int applyId = Integer.parseInt(noStr);
		
		FoodApplyReadService applyReadService = FoodApplyReadService.getInstance();
		
		ArticleFoodApply foodApply = applyReadService.getFoodApply(applyId);
		
		req.setAttribute("foodApply", foodApply);
		
		return "/readApply.jsp";
	}

}
