package m.api;
import java.text.SimpleDateFormat;
import java.util.Date;     
 
public class D_Date {
	public void method01(){
		Date date1 = new Date();
		System.out.println(date1);
		
		//내가 원하는 날짜(2025년 07월 16일)로 세팅
		//1)생성자를 통해서 생성
		Date date2 = new Date(2025 - 1900, 7 - 1, 16);
		System.out.println(date2);
		
		//2) 기본생성자로 생성한 후에 setter메서드를 이용
		date1.setYear(2025 - 1900);
		date1.setMonth(7 - 1);
		date1.setDate(16);
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		System.out.println(date1);
		
		System.out.println(1900 + date1.getYear() + "년");
		
		//SimpledateFormat  
		//Date타입을 형식에 맞춰 보여주기 위한 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd hh시 mm분 ss초");
		String date5 = sdf.format(date1);
		System.out.println(date5);
	} 
}
