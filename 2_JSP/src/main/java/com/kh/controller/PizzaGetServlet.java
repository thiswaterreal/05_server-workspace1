package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaGetServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// System.out.println("잘되나??");
		
		// 1) 전달값 중에 한글이 있을 경우 인코딩 처리 (post 방식일때만)
		// request.setCharacterEncoding("URF-8");
		
		
		// 2) 요청시 전달값 뽑기 및 데이터 가공처리(파싱 같은거) => 변수 및 객체기록
		// request.getParameter("키값") 			: "벨류값" 반환 (String 형으로)
		// request.getParameterValues("키값")		: ["벨류값", "벨류값", ..] 반환 (String 형으로)
		// => 만일 키 값이 존재하지 않을 경우 null 반환		
		String userName = request.getParameter("userName");	// "차은우"			 req속성
		String phone = request.getParameter("phone");		// "01011112222"
		String address = request.getParameter("address");	// "서울시 강남구"		 req속성
		String message = request.getParameter("message");	// "빨리 가져다 주세요" | ""
		String pizza = request.getParameter("pizza");		// "콤비네이션피자"
		String[] toppings = request.getParameterValues("topping");	// ["고구마무스", "치즈크러스트", ..]	| null
		String[] sides = request.getParameterValues("side");		// ["핫소스", "피클", ..]			| null
		String payment = request.getParameter("payment");	// "card"
		
		// 잠깐) 콘솔에 확인
		System.out.println("userName : " + userName);
		System.out.println("phone : " + phone);
		System.out.println("address : " + address);
		System.out.println("message : " + message);
		System.out.println("pizza : " + pizza);
		//System.out.println("toppings : " + String.join("/", toppings));
		//System.out.println("sides : " + String.join("/", sides));
		System.out.println("payment : " + payment);
		
		
		// 3) 요청처리 (db에 sql문 실행하러 > Service > Dao)
		// 				   Controller > Service > Dao >> DB
		int price = 0;
		
		// >> 피자
		switch(pizza) {
		case "콤비네이션피자" : price += 5000; break;
		case "치즈피자" : price += 6000; break;
		case "포테이토피자" : 							// 여기도 += 7000 으로 같으니까..
		case "고구마피자" : price += 7000; break;
		case "불고기피자" : price += 8000; break;
		}
		
		// >> 토핑
		if(toppings != null) {	// 토핑이 null이 아닐때만 돌리기
			
			for(int i=0; i<toppings.length; i++) {	// 0번인덱스 쭉 비교하고, 1번인덱스 쭉 비교..
				switch(toppings[i]) {
				case "고구마무스" : 
				case "콘크림무스" : price += 1500; break;
				case "파인애플토핑" : 
				case "치즈토핑" : price += 2000; break;
				case "치즈바이트" : 
				case "치즈크러스트" : price += 3000; break;
				}
			}
		}
		
		// >> 사이드
		if(sides != null) {	// 사이드가 null이 아닐때만 돌리기
			
			for(int i=0; i<sides.length; i++) {
				switch(sides[i]) {
				case "콜라" :
				case "사이다" : price += 2000; break;
				case "핫소스" :
				case "갈릭소스" : price += 300; break;
				case "피클" :
				case "파마산치즈가루" : price += 500; break;
				}
			}
		}
		
		System.out.println("price : " + price);
		
		
		// 4) 요청처리 후 사용자가 보게 될 응답페이지 (결제페이지) 만들기
		//	  응답페이지(jsp)를 선택해서 포워딩
		//	  단, 응답페이지에서 필요한 데이터가 있다면 담아서 포워딩 할 것!
		//	  request attribute 영역에 담기
		//	  어떤 데이터가 필요한지 모르겠다면? => 먼저 jsp를 만들어보기!
		request.setAttribute("userName", userName);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		request.setAttribute("pizza", pizza);
		request.setAttribute("toppings", toppings);
		request.setAttribute("sides", sides);
		request.setAttribute("payment", payment);
		request.setAttribute("price", price);
		
		// 응답할 뷰 (jsp) 선택
		RequestDispatcher view = request.getRequestDispatcher("views/pizza/pizzaPayment.jsp");
		// 선택된 뷰가 사용자에게 보여지도록 포워딩
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
