package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")	/* 여기를 확인!! 잘 연결됐는지 확인!! */
							/* post 방식이면 => doPost으로 => doGet으로 */
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("doGet 메소드 실행");
		
		// 요청시 전달된 값들은 request의 parameter 영역에 담겨 있음
		
		// *************** POST 방식 요청 같은 경우 *****************
		// 뽑기 !!!!!!!!! 전 !!!!!!!!!! 에 인코딩 설정해야됨 => UTF-8로!! (안그럼 글자 깨짐)
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");							// "차은우" | ""
		String gender = request.getParameter("gender"); 					// "M" | "F" | null
		int age = Integer.parseInt(request.getParameter("age")); 			// "20" => 20
		String city = request.getParameter("city");							// "서울" | "전라도" | ..
		double height = Double.parseDouble(request.getParameter("height")); // "160" => 160.0
		
		String[] foods = request.getParameterValues("food");				// ["한식", "일식"] | null
	
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods == null) {
			System.out.println("foods : 없음");
		}else {
			System.out.println("foods : " + String.join("-", foods));
		}
	
		// url body 영역 방식의 인코딩이 UTF-8이 아님.. 그래서 깨지게 나옴.. 따라서 뽑기 전!!에 인코딩 설정해줘야함
		
		
		//---------------------------------------------------------------------------------
		// 요쳥처리(db의 sql문 실행) : > Service > Dao > sql 문 실행
		
		// 요청 처리 다 했다는 가정하에 사용자가 보게 될 응답 html
		
		// 1. 순수 Servlet 방식으로 작성하는 방법	: Java 코드 내에 html을 기술
		// 2. JSP(Java Sever Page)방식		: html 내에 Java 코드를 쓸 수 있음
		
		// 응답페이지를 만드는 과정을 JSP에게 위임! (떠넘기기)
		
		// 단, 그 응답화면(jsp)에서 필요로 하는 데이터들을 주섬주섬 담아서 전달해줘야함
		// 주섬주섬 담기 위한 공간 == request attribute 영역 (키-벨류 세트로)
		// request.setAttribute("키", 벨류);	//(String, Object형) 따라서, 담긴건 Object형
		request.setAttribute("name", name);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("gender", gender);
		request.setAttribute("foods", foods);
		// 파랑 == (키)내맘대로 지음
		// 갈색 == (벨류)사용자가 입력한 값들 담은 변수
		
		// 응답하고자 하는 뷰(jsp) 선택하면서 RequestDispatcher 객체 생성 <= 규칙
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		view.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("doPost 메소드 실행");
		doGet(request, response);
	}

}
