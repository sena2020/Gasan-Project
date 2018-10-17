package jdbc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

//디비 커넥션 풀을 준비하기 위한 컨텍스트 리스너 클래스.
public class DBCPInitListener implements ServletContextListener {

	// 시작할 때 우리의 디비 커넥션 풀을 셋팅하도록 해보자.
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

		// 서블릿 컨텍스트를 받고
		ServletContext sc = sce.getServletContext();
		// 서블릿 컨텍스트를 통해서 init파라미터(디비 정보가 있느 properties)를 받음.
		// String poolConfigFile = st.getInitParameter("poolConfigFile");

		// 파일 주소로 파일을 읽어야 함. 시스템상 주소.
		String poolConfigFile = sc.getRealPath(sc.getInitParameter("poolConfigFile"));

		// 파일주소로 properties객체에 파일에 있는 데이터를 넣을 것임.
		Properties prop = new Properties();

		try {
			prop.load(new FileReader(poolConfigFile));
		} catch (FileNotFoundException e) {// 파일을 읽는 것에 대한 익셉션

			// 직접 처리하는 로직

			throw new RuntimeException("not found poolConfigFile", e);
		} catch (IOException e) {// 파일을 읽는 것에 대한 익셉션

			throw new RuntimeException("fail to read poolconfigFile", e);
		}

		// jdbc드라이버 로드
		loadJDBCDriver(prop);

		// 커넥션 풀 초기화
		initConnectionPool(prop);
	}

	private void initConnectionPool(Properties prop) {
		
		
		String connectionUri = prop.getProperty("jdbcUri");
		String userName=prop.getProperty("dbUser");
        String userPassword=prop.getProperty("dbPwd");
		try {


			
			//디비 접속 정보를 인자로 넣고 커넥션을 만들어 주는 팩토리 객체를 생성
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(connectionUri, userName, userPassword);

			//풀에서 쓸 수 있는 커넥션을 만들어주는 팩토리에 커넥션 팩토리를 넣고 생성
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connFactory, null);

			// 커넥션이 유효한지 확인하기 위한 쿼리를 지정
			//getProperty의 첫번째 인자는 파일에 정의 되어 있는 값이고, 두 번째 인자는 없을시에 기본값으로 해줄 값.
			poolableConnectionFactory.setValidationQuery(prop.getProperty("validationQuery", "select1"));

			// 커넥션 풀의 설정 정보를 다루는 객체 생성하고 설정 정보 셋팅 . GenericObjectPoolConfig가 설정정보를 다루는 객체임을 알고 있으면 됨.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<>();

			// 커넥션 풀의 설정정보를 가지고 검사주기 셋팅
			// 이걸 여유있게 주는 이유는 다 사라져버리면 커넥션을 할 수 없기 때문이다.
			poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 60L * 5L); //어느정도 여유있게 줄 것인지.

			// 풀에 보관중인 커넥션이 유효한지 검사여부
			poolConfig.setTestWhileIdle(true);

			// 커넥션 최소 갯수 . 커넥션 풀 안에서 존재할 수 있는 총 갯수
			poolConfig.setMinIdle((Integer.parseInt(prop.getProperty("minIdle", "5"))));
			//스트링으로 받을 수 밖에 없어서 먼저 문자로 표현을 해주고 따로 인티저로 바꿔줌.

			// 커넥션 최대 갯수. 풀 안에 있든 사용하고 있든 그 모든 것을 합한 총 갯수
			poolConfig.setMaxTotal(Integer.parseInt(prop.getProperty("maxTotal","50")));

			// 커넥션 풀 생성 시 팩토리와 커넥션 설정을 받음
			GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<>(poolableConnectionFactory,poolConfig);

			// poolableConnectionFactory에 생성한 커넥션 풀을 연결
			poolableConnectionFactory.setPool(pool);

			// 커넥션 풀을 제공하는 jdbc드라이버를 등록
			//풀링 드라이버를 로드

			Class.forName(prop.getProperty("poolingDriver"));

			// 커넥션 풀 드라이버에 생성한 커넥션 풀을 등록
			// driver.getConnection("jdbc:apache:commons:dbcp:jdbcPool")
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName=prop.getProperty("poolName");
			
			//생성한 커넥션 풀을 커넥션 풀 드라이버에 등록
			driver.registerPool(poolName, pool);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Not found poolingDriver File");
		}catch(SQLException e) {
			throw new RuntimeException("can not get Driver PoolingDriver");
		}
	}

	
	// jdbc 드라이버를 로드하는 메소드를 정의 ... 간단하다니 ^^.,.??
	private void loadJDBCDriver(Properties prop) {
		// 프로퍼티에서 설정한 드라이버 주소
		String driverClass = prop.getProperty("jdbcDriver");

		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBX Driver", e);
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
