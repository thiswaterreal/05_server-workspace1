package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do")	/* 여기를 확인!! 잘 연결됐는지 확인!! */
							/* get 방식이면 => doGet으로 */
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get방식으로 요청시 해당 이 doGet 메소드가 자동으로 호출됨
		//System.out.println("잘 실행되나?");
		
		/*
		 * 첫번째 매개변수인 request 에는 요청시 전달된 내용들이 담겨 있음 (사용자가 입력한 값, 요청 전송 방식(get|post), ip주소 등)
		 * 두번째 매개변수인 response 요청처리 후 응답할 때 사용되는 객체
		 * 
		 * 요청처리를 위해서 요청시 전달된 값들 뽑기
		 * request의 parameter 영역 안에 존재
		 * 
		 * 따라서 request의 parameter 영역으로부터 전달된 데이터 뽑는 메소드
		 * 
		 * 1) 값이 하나(단수)일 때
		 * > request.getParameter("키값");		: String (그에 해당하는 value값)
		 * 
		 * 2) 값이 여러개(복수)일 때 == 배열
		 * > request.getParameterValues("키값");	: String[] (그에 해당하는 value값들이 배열에 담겨서 반환)
		 * 
		 * */
		
		// 1) 단수
		String name = request.getParameter("name");							// "차은우" | ""
		String gender = request.getParameter("gender");						// "M" | "F" | null
		int age = Integer.parseInt(request.getParameter("age"));			// "20" => 20 | "" => NumberFormatException 예외 발생! : 빈문자열, 숫자가 아닌걸 형변환? 불가!!
		String city = request.getParameter("city");							// "서울" | "전라도" | ..
		double height = Double.parseDouble(request.getParameter("height")); // "160" => 160.0
		// 2) 체크박스와 같이 복수 개의 벨류값들을 뽑고자 할 때
		String[] foods = request.getParameterValues("food");				// ["한식", "일식] | null  반환
		
		
		
		// 이렇게 웹에서 입력받은 값(사용자가 요청한 값)가져올 수 있음
		// 1) 단수
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		// 2) 복수
		// System.out.println("foods : " + foods);		// 땡! 주소값 나옴..[Ljava.lang.String;@433e14e4
		// System.out.println("foods : " + foods[0]);	// 땡! 이건 오류날 수도 있음 null이면 / 첫번째 선택한 체크박스 값. 일식 
		if(foods == null) {
			System.out.println("foods : 없음");
		}else {
			// for(타입 변수명 : 배열명){}
			/*
			for(String f : foods) {
				System.out.println("foods : " + f);
			}
			*/
			
			// for문 외의 방법 : String.join("구분자",배열)
			System.out.println("foods : " + String.join("/", foods)); // foods에 있는 값들을 '/' 기준으로 출력
		}
		
		
		//-----------------------------------------------------------------------------------
		// 뽑아낸 값들을 가지고 요청처리 해야됨 (db와 상호작용 : 조회, 삽입, 수정, 삭제..)
		// > Service 메소드 호출 > Dao 메소드 호출 > DB에 sql문 실행

		/*
		int result = new MemberService().insertMember(name, gender, age, city, height, foods);
		if(result > 0) {
			// 성공 => 성공페이지로..
		}else {
			// 실패 => 실패페이지로..
		}
		*/
		// 위의 요청 처리 후 성공했다는 가정하에 사용자가 보게 될 응답페이지(html) 만들어서 전송
		// 즉, 여기 'Java 코드 내에' 사용자가 보게 될 '응답 html 구문' 작성할거!!
		
		// 장점 : Java 코드 내에 작성하기 때문에 반복문, 조건문, 유용한 메소드 같은 걸 활용할 수 있음
		// 단점 : 불편함, 복잡함, 혹시라도 나중에 html 수정한다면 Java 코드를 수정하는거라서
		//		 다시 반영하고자 한다면 서버를 재실행 해야됨
		
		
		// * response 객체를 통해 사용자에게 'html(응답화면)' 전달
		
		// 1) 이제부터 내가 출력할 내용은 문서형태가 html이고 문자인코딩 문자셋 utf-8이라는 걸 선언
		response.setContentType("text/html; charset=UTF-8");
		
		// 2) 응답하고자 하는 사용자 (요청했던 사용자)와의 스트림(클라이언트와의 통로) 생성
		PrintWriter out = response.getWriter();
		
		// 3) 저 스트림(out)을 통해 응답 html 구문을 한줄씩 출력
		out.println("<html>");
		
		out.println("<head>");
		
		out.println("<style>");
		out.println("h2{color:red}");		
		out.println("#name{color:orange}");		
		out.println("#age{color:orangered}");		
		out.println("#city{color:green}");		
		out.println("#height{color:blue}");		
		out.println("#gender{color:pink}");			
		out.println("</style>");
		
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h2>개인정보 응답 화면</h2>");
		//out.println("<span>" + name + "님은</span>");
		out.printf("<span id='name'>%s</span> 님은 ", name);
		out.printf("<span id='age'>%d</span> 살이며, ", age);
		out.printf("<span id='city'>%s</span>에 사는 ", city);
		out.printf("키는 <span id='height'>%.1f</span> cm이고, ", height);
		
		out.printf("성별은");
		if(gender == null) {
			out.println(" 선택을 안했습니다. <br>");
		}else {
			if(gender.equals("M")) {	// String String 비교니까 .equels()
				out.println("<span id='gender'>남자</span>입니다.");
			}else {
				out.println("<span id='gender'>여자</span>입니다.");			
			}			
		}
		
		out.print("좋아하는 음식은 ");
		if(foods == null) {
			out.println("없습니다.");
		}else {	// ["한식", "중식"]
			out.print("<ul>");
				for(int i=0; i<foods.length; i++) {
					out.print("<li>" + foods[i] + "</li>");
				}
			out.print("</ul>");
		}
		
		out.println("</body>");
		
		out.println("</html>");
		
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
