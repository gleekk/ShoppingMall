package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;//@RequestMapping
import org.springframework.web.servlet.ModelAndView;//ModelAndView : Model데이터  View jsp페이지
import org.springframework.web.servlet.view.RedirectView;

import model.MemberBean;
import model.ShoppingDAO;
import model.SuBean;


public class ShoppingController {
	
	//설명 : 
	//shopping-servlet.xml에 의존관계를 이용하여....<bean>객체에 ...
	//<bean id="sujak" name="/sujak.do"  class="controller.ShoppingController"
	//	   p:shoppingDao-ref="shoppingDao"></bean> 라고 해놓았기 떄문에...
	//shoppingDao객체를 가져올수 있다.
	ShoppingDAO shoppingDao; //DB와연동된 DB작업할객체 가져와서 저장할 변수 선언
	
	
	//설정취득 //DB와연동된 DB작업할객체 가져와서 저장
	public void setShoppingDao(ShoppingDAO shoppingDao) {
		this.shoppingDao = shoppingDao;
	}
	
	@RequestMapping("/index.do")//index.do라는 요청이 들어오면 아래의  메소드를 실행하시오.
	public ModelAndView index(HttpSession session){//회원 가입 정보를 사용하기위하여 세션을 설정

		//데이터와 jsp를 리턴해주는 객체 생성
		ModelAndView mav = new ModelAndView();
		
		//회원 정보를 사용하기위하여 세션객체를 불러옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//세션을 이용하여 로그인처리
		if(mbean==null){//top.jsp에서 로그인 정보를 처리하기위한 소스
			mav.addObject("mbean" , null);  // 데이터를 아래의ShoppingMain.jsp로 보내주기위해  데이터 담기
			mav.setViewName("ShoppingMain"); //ShoppingMain.jsp 페이지명 셋팅			
		}else{
			mav.addObject("mbean" , mbean); // 데이터를 아래의ShoppingMain.jsp로 보내주기위해  데이터 담기
			mav.setViewName("ShoppingMain"); //ShoppingMain.jsp 페이지명 셋팅		
		}

		return mav;		
	}
	
	
	//수작업 공구를 눌렀다면 자동으로 호출되는 메소드
		@RequestMapping("/sujak.do")
		public ModelAndView suJak(String num){//왼쪽 수작업 공구 카테고리별 메뉴중 하나를 선택한 값을 전달받기

			//데이터와 jsp를 리턴해주는 객체 생성
			ModelAndView mav = new ModelAndView();
			//어느 요청이 들어왔는지에 대한 정보를 얻어와서 해당 model에 접근하여 데이터를 가져오도록
			if(num==null){ // 신제품을 누르거나 top에 있는 메뉴중 수작업 공구버튼을 누르면			
				
				//먼저할일!!!
				//DB에 접근하여..검색한 모든 수작업공구를  가져와야 한다.
				//그러기에 앞서  Dto클래스를 만들자!
				
				//DB에 접근하여..검색한 모든 수작업공구들(SuBean들)을 List에 담아서 가져오기
				List<SuBean> list = shoppingDao.getAllSutool();
				
				//데이터를 아래의ShoppingMain.jsp로 보내주기위해  데이터를 new ModelAndView()객체에 담기			
				mav.addObject("list", list);
						
			}else { // 해당메뉴들을 선택시 사용되는 소스		
				//왼쪽 수작업 공구 카테고리별 메뉴중 하나를 선택한 값을 전달하여 ....
				// DB에서 검색한 수작업공구글(SuBean들)을  List에 담아서 가져오기
				List<SuBean> list = shoppingDao.getSelectSutool(num);
				
				//데이터를 아래의ShoppingMain.jsp로 보내주기위해  데이터를 new ModelAndView()객체에 담기
				mav.addObject("list", list);
			}	
			
			// center화면으로.."SujakCenter.jsp"문자열 데이터를.. 
			// 아래의ShoppingMain.jsp로 보내주기위해 new ModelAndView()객체에 담기
			mav.addObject("center" ,"SujakCenter.jsp");
			
			// left화면으로.."SujakLeft.jsp"문자열 데이터를.. 
			// 아래의ShoppingMain.jsp로 보내주기위해  new ModelAndView()객체에 담기
			mav.addObject("left", "SujakLeft.jsp");
			
			//이동할 뷰페이지->  ShoppingMain.jsp 페이지명 셋팅
			mav.setViewName("ShoppingMain");
			
			//데이터와 MVC중 V페이지(jsp명)를 리턴해주는 객체 리턴
			return mav;
			
		}//suJak 메소드 끝
		
		
		

	
	
}//ShoppingController 클래스 끝
