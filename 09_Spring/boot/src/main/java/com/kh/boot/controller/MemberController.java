package com.kh.boot.controller;

import com.kh.boot.domain.vo.Member;
import com.kh.boot.service.GoogleAPiService;
import com.kh.boot.service.MemberService;
import com.kh.boot.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {

    /*
          @Autowired
          의존성주입을 사용할 때 사용하는 어노테이션
          클래스내에서 필요한 개체를 직접 생성하지않고 spring컨테이너가 관리하는 객체(Bean에 등록)를 주입받아서 사용할 수 있게 해줌
          필드주입방식/생성자주입방식

          private MemberService memberService = new MemberServiceImpl();
          기존 객체 생성방식
          객체간의 결합도가 높아짐(소스코드 수정이 일어날 경우 하나하나 전부 바꿔줘야한다.)
          서비스가 동시에 매우 많은 요청이 될 경우 그만큼 객체가 생성된다.

          Di(Dependency Injection) - 의존성 주입
          코드 결합도가 낮아지고 코드를 분리할 수 있음

          필드주입방식
          스프링 컨테이너가 객체를 생성 후, @Autowired붙은 필드에 의존성을 주입해주는 방식
          장점 : 간결하다. 따로 생성자나 setter를 작성하지 않아도 된다.
          단점 : 테스트가 어려움(핃드주입방식은 객체생성시 의존성이 주입되지 않고 been에서 생성 후 주입받는 방식이기때문에
                              테스트 진행시 임의에 객체를 생성하기가 어렵다)
                불변성을 보장할 수 없음. 객체생성시 의존성이 주입되어 고정되지 않기때문에 클래스 생성 이후에 의존성이 변경될 수 있음

          생성자주입방식
          스프링 컨테이너가 객체를 생성할 때 @Autowired어노테이션이 붙은 생성자를 통해 필요한 의존성을 주입하는 방식
          장점 : 불변셩 보장, 테스트가 편리하다. 순환참조방지
     */

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final GoogleAPiService googleAPiService;

    @Autowired
    public MemberController(MemberService memberService, BCryptPasswordEncoder bCryptPasswordEncoder, GoogleAPiService googleAPiService) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.googleAPiService = googleAPiService;
    }

    /*
    spring에서 클라이언트가 보낸 정보를 받는 방법
    1. HttpServletRequest를 활용해서 전달값을 가져옴
    메소드에 매게변수로 HttpServletRequest를 작성해주면
    스프링컨테이너가 해당 메서드를 호출할 때 자동으로 매게변수로 주입
     */
    /*
    @PostMapping("login.me")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("userId");
        String password = request.getParameter("userPwd");
        System.out.println(id);
        System.out.println(password);

        return null;
    }
    */

    /*
    2. @RequestParam 어노테이션을 이용하는 방법
    request.getParameter(키)로 벨류를 추출하는 역할을 대신 해주는 어노테이션
    요청 parameter의 key값과 동일하게 매게변수명을 설정해주면 @RequestParam어노테이션을 생략해도 됨
     */
    /*
    @PostMapping("login.me")
    public String login(@RequestParam(value = "userId", defaultValue = "user01") String id, String userPwd) {

        System.out.println(id);
        System.out.println(userPwd);

        return null;
    }
     */

    /*
    3. 객체를 이용하는 방법(@ModelAttribute 생략가능)
    HTML 폼 데이터를 객체로 받을 때 사용할 수 있음
    요청시 전달값들을 담고자하는 클래스 타입을 만들어준 뒤
    전달되는 key값과 매게변수 클래스의 필드명을 동일하게 작성해주면
    객체를 매게변수로해서 값을 담아 전달해준다.
     */
//    @PostMapping("login.me")
//    public String login(@ModelAttribute Member member) {
//
//        System.out.println(member.getUserId());
//        System.out.println(member.getUserPwd());
//
//
//        return null;
//    }

    /*
    요청처리후에 데이터를 담아서 응답하는 방법(포워딩 or url재요청)
    1. 스프링에서 제공하는 Model객체를 이용하는 방법
    포워딩할 응답뷰로 전다라고자하는 데이터를 맵형식(k-v)으로 담을 수 있는 영역
    Model객체에 requestScope값 전달
     */
//    @PostMapping("login.me")
//    public String login(@ModelAttribute Member member, Model model) {
//
//        System.out.println(member.getUserId());
//        System.out.println(member.getUserPwd());
//
//        model.addAttribute("id", member.getUserId());
//        model.addAttribute("pwd", member.getUserPwd());
//
//        return "index";
//    }

    //2. HttpSession이용한 값저장 후 url재요청
//    @PostMapping("login.me")
//    public String login(@ModelAttribute Member member, HttpSession session) {
//
//        System.out.println(member.getUserId());
//        System.out.println(member.getUserPwd());
//
//        session.setAttribute("id", member.getUserId());
//        session.setAttribute("pwd", member.getUserPwd());
//
//        //url재요청을 원할시 return내용을 redirect:재요청url로 해주면 됨
//        return "redirect:/";
//    }

    /*
    3. ModelAndView객체를 이용한 방법 -> 데이터를 담고 리턴형식까지 지정할 수 있음
     */
    @PostMapping("login.me")
    public ModelAndView login(Member member, ModelAndView mv, HttpSession session) {


        //url재요청을 원할시 return내용을 redirect:재요청url로 해주면 됨
        Member loginMember = memberService.loginMember(member.getUserId());

        //loginMember의 userPwd --> 암호된 userPwd
        //member의 userPwd --> 암호화 전의 userPwd(평문)
        //bCryptPasswordEncoder.matches(평문, 암호문) -> 해당 비밀번호가 암호화된 비밀번호와 일치하면 true 아니면 false반환


        if(loginMember == null){
            mv.addObject("errorMsg", "아이디를 찾을 수 없습니다.");
            mv.setViewName("common/errorPage");
        } else if(!bCryptPasswordEncoder.matches(member.getUserPwd(), loginMember.getUserPwd())){
            mv.addObject("errorMsg", "비밀번호가 일치하지 않습니다.");
            mv.setViewName("common/errorPage");
        }else {
            session.setAttribute("loginUser", loginMember);
            mv.setViewName("redirect:/");
        }

        return mv;
    }

    @GetMapping("login.go")
    public ModelAndView loginGoogle(String code, ModelAndView mv, HttpSession session) {
        System.out.println("code : " + code);
        Map<String, String> userInfo = googleAPiService.requestGoogleUserInfo(code);
        String memberId = userInfo.get("email");

        Member loginMember = memberService.loginMember(memberId);

        if(loginMember == null){
            session.setAttribute("alertMsg", "회원가입 후 이용이 가능합니다.");
            mv.setViewName("redirect:/enrollForm.me?memberId=" + memberId);
        } else {
            session.setAttribute("loginUser", loginMember);
            session.setAttribute("access_token", userInfo.get("access_token"));
            mv.setViewName("redirect:/");
        }
        return mv;
    }

    @GetMapping("logout.me")
    public String logout(HttpSession session) {
        session.setAttribute("alertMsg", "로그아웃 완료");
        session.removeAttribute("loginUser");

        return "redirect:/";
    }

    @GetMapping("enrollForm.me")
    public String enrollForm() {
        return "member/memberEnrollForm";
    }

    @PostMapping("insert.me")
    public String insertMember(Member member, HttpSession session, Model model) {
        /*
            age값은 int로 필드를 구성할 경우 빈문자열이 전달되면 형변환 과정에서 400에러가 발생
            보통 400에러는 보내는 데이터와 받는 데이터의 타입이 일치하지 않을 때 발생한다.

            비밀번호를 사용자 입력 그대로 저장한다.(평문)
            Bcrypt방식을 이용해서 암호화작업 후 저장함
            -> 스프링 시큐리티에서 제공하는 모듈을 이용 (pom.xml에 라이브러리 추가 후 빈에 객체등록)
         */
        String pwd = bCryptPasswordEncoder.encode(member.getUserPwd());
        member.setUserPwd(pwd);

        int result = memberService.insertMember(member);
        if (result > 0){
           session.setAttribute("alertMsg", "성공적으로 회원가입을 완료하였습니다.");
            return "redirect:/";
        } else {
            model.addAttribute("errorMsg", "회원가입 실패");
            return "common/errorPage";
        }
    }

    @GetMapping("myPage.me")
    public String myPage() {return "member/myPage";}
}
