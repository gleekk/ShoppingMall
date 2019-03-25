package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

//����! :
//�����ͺ��̽� ������ �̹� shopping-servlet.xml���� DriverManagerDataSoueceŬ������ ���� �� ���Ҵ�.

//Ŭ���� ���� : �̸� ����� ������ ���̽��� ������ �����Ͽ� ����� ���� �޾��ִ� Ŭ����
public class ShoppingDAO {
	
	//������ ������Ѽ� �����͸� �����ü� �ֵ������ִ� ��ü���� �������� (preparedstatement��ü�� ��������)
	SimpleJdbcTemplate template;  //pstmt�� ����
	
	//���� : 
	//shopping-servlet.xml�� �������踦 �̿��Ͽ�....
	// <bean id="shoppingDao" class="model.ShoppingDAO"  p:dataSource-ref="dataSource"  />���..
	// ��� �� ���� ������...
	//������ ���̽��� �����Ͽ� �����͸� �о���ü� �ִ� Ŀ�ؼ�Ǯ ��ü�� ���� ���� ���� ����
	DataSource dataSource;
	//����! ���������...  p:dataSource-ref ��!! dataSource�̸��� ���� ���������� ����� �Ѵ�.
	

	//setter�޼ҵ� 
	//������� ��ü�� ������Ű���ʰ� dataSource���
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		 
		//Ŀ�ؼǿ� �ִ� DB���ᰴü�� ��������... 
		//������ ������Ѽ� �����͸� �����ü� �ֵ������ִ� ��ü���� 
		template = new SimpleJdbcTemplate(dataSource); //pstmt�� ����
	}
	
	
	
	//��� ���۾� ������ �������ִ� �޼ҵ�
		public List<SuBean> getAllSutool() {
			
			/*������� ��1���,��2��Ŀ������?.......*/
			
			//�ؾ� �ߴ���1. DB���� �˻��� ������ڵ带  ������ SuBean��ü�� ���....ArrAyList��ü�� SuBean��ü�� ���� ���..
			//List���·� �����ϱ����� ArrayList��ü���� �ؾ� �Ѵ�.
			
			//�ؾ� �ߴ���2. DB���� �˻��� ������ڵ带 ���� SuBean��ü �����ؾ� ��.	
			//�ؾ� �ߴ��� 3.  Ŀ�ؼ� Ǯ���� DB���� ��ü�� �����ͼ� �غ� ( DB����)	
			//�ؾ� �ߴ��� 4.  DB����  ��� ���۾����� �˻�  select����  ������ ��.		
			//�ؾ� �ߴ��� 5.  PreparedStatement��ü�� �̿��Ͽ� select���� �ؾ���.		
			//�ؾ� �ߴ��� 6.   select�� �����  ���̺���������  ResultSet�� ���� �ؾ���.		
			//�ؾ� �ߴ��� 7.  while�����̿��Ͽ�  rs.next()�̿��Ͽ�  �ϳ��� �÷��� �ִ� �����͸� SuBean��ü�� �����Ͽ� ���� �ؾ���.		
			//�ؾ� �ߴ���8.   select�Ͽ� �˻��� ����� ������ SuBean��ü�� �ٽ�.. ArrayList�÷��ǰ�ü��  add��Ű��....		
			//�ؾ� �ߴ���9.   ��� while���� ������  return list; �ؾ���.
			//---------------------------------------------------------------------------------------
			/*������ ���*/		
			
			//��� ���۾����� �˻� ���� �غ�
			String sql ="select * from sutool";
			
			//RowMapper : ���� ����� ��ü�� ��ȯ

			//BeanPropertRowMapperŬ���� ���� : 
			//Subean��Ŭ������ ������� DB�� SUTOOL���̺��� �÷����� ��Ī�� ��ü ���� 
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			
			//template��ü�� �̿��Ͽ� ������ �����ų��..
			//query()�޼ҵ忡 ������ select����, DB�� SUTOOL���̺��� �÷���� ��Ī�� ��ü�� �����Ͽ�..
			//select�� ��� ���ڵ� ������ŭ list�� ���  list��ü�� ���� ���ش�.
			// ����! ���Ϲ��� list�� �Ǵٽ� getAllSutool()�޼ҵ带 ȣ���� ������ ���� �ϱ�
			return template.query(sql, rm); 
		}

		
		
		//���� ī�װ� �޴��� ���õ� ���۾� ������ �������ִ� �޼ҵ�
		public List<SuBean> getSelectSutool(String num) {//���� ī�װ� �޴��� ���õ� �޴� ��ȣ
			//���� �غ�
			String sql ="select * from sutool where sucate=?";
			//��Ŭ����(�÷���� �Ȱ��� Ŭ������  ������ ��ü)�� �÷����� ��Ī�ϴ� ��ü ����
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			return template.query(sql, rm, num);
		}



	
		
		
		
}//Ŭ���� ��








