package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;//@RequestMapping
import org.springframework.web.servlet.ModelAndView;//ModelAndView : Model������  View jsp������
import org.springframework.web.servlet.view.RedirectView;

import model.MemberBean;
import model.ShoppingDAO;
import model.SuBean;


public class ShoppingController {
	
	//���� : 
	//shopping-servlet.xml�� �������踦 �̿��Ͽ�....<bean>��ü�� ...
	//<bean id="sujak" name="/sujak.do"  class="controller.ShoppingController"
	//	   p:shoppingDao-ref="shoppingDao"></bean> ��� �س��ұ� ������...
	//shoppingDao��ü�� �����ü� �ִ�.
	ShoppingDAO shoppingDao; //DB�Ϳ����� DB�۾��Ұ�ü �����ͼ� ������ ���� ����
	
	
	//������� //DB�Ϳ����� DB�۾��Ұ�ü �����ͼ� ����
	public void setShoppingDao(ShoppingDAO shoppingDao) {
		this.shoppingDao = shoppingDao;
	}
	
	@RequestMapping("/index.do")//index.do��� ��û�� ������ �Ʒ���  �޼ҵ带 �����Ͻÿ�.
	public ModelAndView index(HttpSession session){//ȸ�� ���� ������ ����ϱ����Ͽ� ������ ����

		//�����Ϳ� jsp�� �������ִ� ��ü ����
		ModelAndView mav = new ModelAndView();
		
		//ȸ�� ������ ����ϱ����Ͽ� ���ǰ�ü�� �ҷ���
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//������ �̿��Ͽ� �α���ó��
		if(mbean==null){//top.jsp���� �α��� ������ ó���ϱ����� �ҽ�
			mav.addObject("mbean" , null);  // �����͸� �Ʒ���ShoppingMain.jsp�� �����ֱ�����  ������ ���
			mav.setViewName("ShoppingMain"); //ShoppingMain.jsp �������� ����			
		}else{
			mav.addObject("mbean" , mbean); // �����͸� �Ʒ���ShoppingMain.jsp�� �����ֱ�����  ������ ���
			mav.setViewName("ShoppingMain"); //ShoppingMain.jsp �������� ����		
		}

		return mav;		
	}
	
	
	//���۾� ������ �����ٸ� �ڵ����� ȣ��Ǵ� �޼ҵ�
		@RequestMapping("/sujak.do")
		public ModelAndView suJak(String num){//���� ���۾� ���� ī�װ��� �޴��� �ϳ��� ������ ���� ���޹ޱ�

			//�����Ϳ� jsp�� �������ִ� ��ü ����
			ModelAndView mav = new ModelAndView();
			//��� ��û�� ���Դ����� ���� ������ ���ͼ� �ش� model�� �����Ͽ� �����͸� ����������
			if(num==null){ // ����ǰ�� �����ų� top�� �ִ� �޴��� ���۾� ������ư�� ������			
				
				//��������!!!
				//DB�� �����Ͽ�..�˻��� ��� ���۾�������  �����;� �Ѵ�.
				//�׷��⿡ �ռ�  DtoŬ������ ������!
				
				//DB�� �����Ͽ�..�˻��� ��� ���۾�������(SuBean��)�� List�� ��Ƽ� ��������
				List<SuBean> list = shoppingDao.getAllSutool();
				
				//�����͸� �Ʒ���ShoppingMain.jsp�� �����ֱ�����  �����͸� new ModelAndView()��ü�� ���			
				mav.addObject("list", list);
						
			}else { // �ش�޴����� ���ý� ���Ǵ� �ҽ�		
				//���� ���۾� ���� ī�װ��� �޴��� �ϳ��� ������ ���� �����Ͽ� ....
				// DB���� �˻��� ���۾�������(SuBean��)��  List�� ��Ƽ� ��������
				List<SuBean> list = shoppingDao.getSelectSutool(num);
				
				//�����͸� �Ʒ���ShoppingMain.jsp�� �����ֱ�����  �����͸� new ModelAndView()��ü�� ���
				mav.addObject("list", list);
			}	
			
			// centerȭ������.."SujakCenter.jsp"���ڿ� �����͸�.. 
			// �Ʒ���ShoppingMain.jsp�� �����ֱ����� new ModelAndView()��ü�� ���
			mav.addObject("center" ,"SujakCenter.jsp");
			
			// leftȭ������.."SujakLeft.jsp"���ڿ� �����͸�.. 
			// �Ʒ���ShoppingMain.jsp�� �����ֱ�����  new ModelAndView()��ü�� ���
			mav.addObject("left", "SujakLeft.jsp");
			
			//�̵��� ��������->  ShoppingMain.jsp �������� ����
			mav.setViewName("ShoppingMain");
			
			//�����Ϳ� MVC�� V������(jsp��)�� �������ִ� ��ü ����
			return mav;
			
		}//suJak �޼ҵ� ��
		
		
		

	
	
}//ShoppingController Ŭ���� ��
