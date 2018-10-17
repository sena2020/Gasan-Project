package service;

public class FoodApplyUpdateService {
	private static FoodApplyUpdateService instance = new FoodApplyUpdateService();
	private FoodApplyUpdateService() {}
	public static FoodApplyUpdateService getInstance() {
		return instance;
	}	
}
