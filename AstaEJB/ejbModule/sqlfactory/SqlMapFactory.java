package sqlfactory;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class SqlMapFactory {
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ThreadLocal<SqlMapFactory> THREAD_LOCAL = new ThreadLocal() {
	     @Override
	     protected SqlMapFactory initialValue() {
	             return new SqlMapFactory();
	     }
	};


	private final String resource = "datasource.xml";
	

	public static SqlMapFactory instance(){
		return THREAD_LOCAL.get();
	}
	
	
	private SqlMapFactory(){}
	
	
	private SqlSessionFactory getSqlSessionFactory() {
		
	   if(sqlSessionFactory==null){
		   try {		
				Reader reader = Resources.getResourceAsReader(resource);
				SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
				sqlSessionFactory = builder.build(reader);
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
		
	   return sqlSessionFactory;
	}
	

	public SqlSession openSession(){
		if (sqlSession == null){
			sqlSession = THREAD_LOCAL.get().getSqlSessionFactory().openSession();
		}
		return sqlSession;
	}
	
	
	public void commitSession(){
		if (sqlSession!=null){
			THREAD_LOCAL.get().sqlSession.commit();	
		}
	}
	
	public  void closeSession(){
		if (THREAD_LOCAL.get().sqlSession!=null){		
			THREAD_LOCAL.get().sqlSession.close();
			THREAD_LOCAL.get().sqlSession = null;
		}
	}
	  	
	public <T> T getMapper(Class<T> type){
		return (T) THREAD_LOCAL.get().sqlSession.getMapper(type);
	}

	public void rollbackSession() {
		if (instance().sqlSession!=null){		
			instance().sqlSession.rollback();
		}		
	}
}