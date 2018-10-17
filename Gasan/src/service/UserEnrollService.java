package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDao;
import exception.DuplicateException;
import jdbc.connector.ConnectionProvider;
import model.User;



//회원 가입을 처리하는 서비스
public class UserEnrollService {
	private static UserEnrollService instance = new UserEnrollService();
	private UserEnrollService() {}
	public static UserEnrollService getInstance() {
		return instance;
	}
	
	//join 회원가입하는 로직을 구현.
		//입력되는 폼에 있는 데이터가 잘 들어왔는지 무결성 체크도 해야 함.
		//그래서 폼에서 입력받은 데이터는 user가 아니라 joinRequest라는 객체로 받아서 처리할 예정.
		public void enroll(UserEnrollRequest enrollReq) {
			//회원 가입을 하려면 중복체크를 먼저 해야함.
			
			UserDao userDao = UserDao.getInstance();
			
			try(Connection conn = ConnectionProvider.getConnection()){
				
				//트랜젝션 처리로 로그인 아이디가 중복이 있는지 없는지 확인하여
				//없으면 삽입, 있으면 예외를 발생시킴.
				//select를 한 것을 rollback하는 것은 큰 의미가 없지만 하나의 단위로 업무처리를 하는 것에 의미를 둠??
				
				conn.setAutoCommit(false);//자동으로 commit을 수행하지 않도록 false로 지정 (디폴트 값이 true)
				
				try {
					User user = userDao.selectByLoginId(conn, enrollReq.getLoginId()); //객체를 넘겨받아서
					if(user !=null) {//null 이면 중복이 아니지만 null이 아니라면 중복이므로
						conn.rollback();//rollback을 시켜주고 (<-> commit)
						throw new DuplicateException("아이디 중복");//메시지를 날림.
					}
					//아이디가 중복되지 않았다면 입력 할 수 있다는 것이므로 새로운 user객체를 만들어서 insert함..
					userDao.insert(conn, new User(enrollReq.getLoginId(),enrollReq.getNickname(),enrollReq.getPassword()));
					conn.commit(); // 문제가 발생하지 않고  트랜젝션 작업한 내용이 모두 성공적으로 끝나면 commit을 수행.
				}catch(SQLException e) {
					throw new RuntimeException(e);
				}
				
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
	}

