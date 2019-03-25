package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

//참고! :
//데이터베이스 연결은 이미 shopping-servlet.xml에서 DriverManagerDataSouece클래스가 연결 해 놓았다.

//클래스 역할 : 미리 연결된 데이터 베이스에 쿼리를 실행하여 결과를 리턴 받아주는 클래스
public class ShoppingDAO {
	
	//쿼리를 실행시켜서 데이터를 가져올수 있도록해주는 객체선언 스프링용 (preparedstatement객체와 사용법유사)
	SimpleJdbcTemplate template;  //pstmt와 유사
	
	//설명 : 
	//shopping-servlet.xml에 의존관계를 이용하여....
	// <bean id="shoppingDao" class="model.ShoppingDAO"  p:dataSource-ref="dataSource"  />라고..
	// 등록 해 놨기 떄문에...
	//데이터 베이스에 접근하여 데이터를 읽어가져올수 있는 커넥션풀 객체를 담을 참조 변수 선언
	DataSource dataSource;
	//참고! 변수선언시...  p:dataSource-ref 중!! dataSource이름과 같이 변수선언을 해줘야 한다.
	

	//setter메소드 
	//설정취득 객체를 생성시키지않고 dataSource사용
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		 
		//커넥션에 있는 DB연결객체의 힘을빌려... 
		//쿼리를 실행시켜서 데이터를 가져올수 있도록해주는 객체생성 
		template = new SimpleJdbcTemplate(dataSource); //pstmt와 유사
	}
	
	
	
	//모든 수작업 공구를 리턴해주는 메소드
		public List<SuBean> getAllSutool() {
			
			/*기존방식 모델1방식,모델2방식에서라면?.......*/
			
			//해야 했던일1. DB에서 검색한 결과레코드를  각각의 SuBean객체에 담고....ArrAyList객체에 SuBean객체를 각각 담아..
			//List형태로 리턴하기위해 ArrayList객체생성 해야 한다.
			
			//해야 했던일2. DB에서 검색한 결과레코드를 담을 SuBean객체 생성해야 함.	
			//해야 했던일 3.  커넥션 풀에서 DB연결 객체를 빌려와서 준비 ( DB연결)	
			//해야 했던일 4.  DB에서  모든 수작업공구 검색  select쿼리  만들어야 함.		
			//해야 했던일 5.  PreparedStatement객체를 이용하여 select실행 해야함.		
			//해야 했던일 6.   select한 결과를  테이블형식으로  ResultSet에 저장 해야함.		
			//해야 했던일 7.  while문을이용하여  rs.next()이용하여  하나씩 컬럼에 있는 데이터를 SuBean객체에 맵핑하여 저장 해야함.		
			//해야 했던일8.   select하여 검색한 결과를 저장한 SuBean객체를 다시.. ArrayList컬렉션객체에  add시키고....		
			//해야 했던일9.   모든 while문을 돌고난후  return list; 해야함.
			//---------------------------------------------------------------------------------------
			/*스프링 방식*/		
			
			//모든 수작업공구 검색 쿼리 준비
			String sql ="select * from sutool";
			
			//RowMapper : 쿼리 결과를 객체로 변환

			//BeanPropertRowMapper클래스 역할 : 
			//Subean빈클래스의 변수명과 DB의 SUTOOL테이블의 컬럼명을 메칭한 객체 생성 
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			
			//template객체를 이용하여 쿼리를 실행시킬때..
			//query()메소드에 실행할 select문과, DB의 SUTOOL테이블의 컬럼명과 매칭한 객체를 전달하여..
			//select한 결과 레코드 갯수만큼 list에 담아  list자체를 리턴 해준다.
			// 또한! 리턴받은 list를 또다시 getAllSutool()메소드를 호출한 곳으로 리턴 하기
			return template.query(sql, rm); 
		}

		
		
		//왼쪽 카테고리 메뉴중 선택된 수작업 공구를 리턴해주는 메소드
		public List<SuBean> getSelectSutool(String num) {//왼쪽 카테고리 메뉴중 선택된 메뉴 번호
			//쿼리 준비
			String sql ="select * from sutool where sucate=?";
			//빈클래스(컬럼명과 똑같은 클래스를  선언한 객체)와 컬럼명을 메칭하는 객체 생성
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			return template.query(sql, rm, num);
		}



	
		
		
		
}//클래스 끝








