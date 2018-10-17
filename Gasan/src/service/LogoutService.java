package service;

public class LogoutService {
	private static LogoutService instance = new LogoutService();
	private LogoutService() {}
	public static LogoutService getInstance() {
		return instance;
	}	
}
