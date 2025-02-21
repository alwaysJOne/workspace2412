package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * http://localhost:8001/servlet/gettest.do
 * Servlet의 요청경로는 contextPath뒤에 작성됨
 */
@WebServlet("/gettest.do")
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
		//Get방식으로 요청시 해당 deGGet메서드가 저절로 호출된다.(톰캣이 서블릿객체를 생성해서 메서드 호출까지 함)
		System.out.println("GET요청 도착");
		
		/*
		 * 첫번째 매개변수인 request에는 요청시 전달된 내용들이 담겨있음(사용자가 입력한 값, 요청방식, 요청자의 url등...)
		 * 두번째 매개변수인 reponse에는 요청 처리 후 응답에 사용되는 객체(어떤타입으로 응답을 할지, 어떤 값을 응답할지등을 넣어주면 됨)
		 * 
		 * 요청처리를 위해서 요청시 전달된 값을 추출
		 * request의 parameter영역안에 존재
		 * request.getparameter("키");
		 */
		
		String name = request.getParameter("name"); //최지원 | ""
		String gender = request.getParameter("gender"); // M | F | null
		int age = Integer.parseInt(request.getParameter("age")); // "23" -> 23
		String city = request.getParameter("city"); //"경기" | "서울" 등등
		double height = Double.parseDouble(request.getParameter("height")); // "180.0" -> 180.0
		
		//체크박스와 같이 여러개의 값을 추출하고자할 때
		String[] foods = request.getParameterValues("food"); // ["한식", "중식"] || null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		System.out.println("foods : " + String.join(", ", foods));
		
		// service > dao > db
		
		/*
		 * int result = new MemberService().insertMember(name,gender...);
		 * if(result > 0){
		 * 		//성공
		 * } else {
		 * 		//실패
		 * }
		 */
		
		//위의 결과에따라 응다페이지(html) 만들어서 전송
		//즉, 여기 java코드내에 사용자가 보게될 응다 html구문을 작성
		
		//response객체를 통해서 사용자에게 응답화면 전달
		
		//1)응답으로 출력할 내용은 html이고 문자셋은 utf-8이다 -> 선언
		response.setContentType("text/html; charset=utf-8");
		
		//2)응답받는 사용자와의 스트림 연결
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<h2>개인정보 응답화면</h2>");
		out.printf("<span>%s</span>님은", name);
		out.printf("<span>%d</span>살이며", age);
		out.printf("<span>%s</span>에 삽니다", city);
		out.println("성별은");
		if(gender == null) {
			out.println(" 미입력 상태입니다.");
		} else if(gender.equals("M")) {
			out.println(" 남자입니다.");
		} else {
			out.println(" 여자입니다");
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
