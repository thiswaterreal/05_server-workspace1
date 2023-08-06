package com.kh.controller;

import java.io.IOException;
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
		
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		String pizza = request.getParameter("pizza");
		String[] toppings = request.getParameterValues("topping");
		String[] sides = request.getParameterValues("side");
		String payment = request.getParameter("payment");
		
		System.out.println("userName : " + userName);
		System.out.println("phone : " + phone);
		System.out.println("address : " + address);
		//System.out.println("message : " + message);
		System.out.println("pizza : " + pizza);
		//System.out.println("toppings : " + String.join("/", toppings));
		//System.out.println("sides : " + String.join("/", sides));
		System.out.println("payment : " + payment);
		
		if(message.equals("")) { // 빈문자열이 넘어오는 경우
			System.out.println("요청사항이 없습니다.");
		}else {
			System.out.println("message : " + message);			
		}
		
		System.out.println("pizza : " + pizza);
		
		if(toppings == null) { // 아무런 선택도 안한 경우
			System.out.println("topping : 없음");
		}else { // 하나라도 선택한 경우
			System.out.println("topping : " + String.join("/", toppings));
		}
		
		if(sides == null) { // 아무런 선택도 안한 경우
			System.out.println("side : 없음");
		}else { // 하나라도 선택한 경우
			System.out.println("side : " + String.join("/", sides));
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
