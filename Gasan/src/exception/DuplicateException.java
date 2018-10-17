package exception;

//중복 발생시 처리할 예외
public class DuplicateException extends RuntimeException {
	
	public DuplicateException(String message) {
		super(message);
	}
	

}