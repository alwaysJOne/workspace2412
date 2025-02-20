package z.practice.polymorphism;

import java.util.Scanner;

public class LibraryMenu {
	LibraryController lc;
	Scanner sc;

	public LibraryMenu() {
		super();
		this.lc = new LibraryController();
		this.sc = new Scanner(System.in); 
	}
	
	public void mainMenu() {
		String name;
		int age;
		char gender;
		
		System.out.print("이름 : ");
		name = sc.next();
		System.out.print("나이 : ");
		age = sc.nextInt();
		System.out.print("성별 : ");
		gender = sc.next().charAt(0);
		
		Member newMember = new Member(name, age, gender);
		lc.insertMember(newMember);
		
		while(true) {
			System.out.println();
			System.out.println("==== 메뉴 ====");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 전체 조회");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 대여하기");
			System.out.println("5. 도서 추가하기");
			System.out.println("6. 도서 삭제하기");
			System.out.println("9. 프로그램 종료하기");
			System.out.print("메뉴 번호 :");
			
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				Member m = lc.myInfo();
				System.out.println(m);
				break;
			case 2:
				selectAll();
				break;
			case 3:
				searchBook();
				break;
			case 4:
				rentBook();
				break;
			case 5:
				if(lc.isInsertBook()) {
					insertBook();	
				} else {
					System.out.println("추가가능한 도서의 수를 초과하였습니다.");
				}
				break;
			case 6:
				deleteBook();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못입력하셨습니다.");
			}
		}
	}
	
	public void selectAll() {
		Book[] bList = lc.selectAll();
		for(int i=0; i < bList.length && bList[i] != null; i++) {
			System.out.println(i + "번도서 : " + bList[i]);
		}
	}
	
	public void searchBook() {
		System.out.print("검색할 제목 키워드 : ");
		String keyword = sc.next();
		Book[] searchList = lc.searchBook(keyword);
		for(int i=0; i < searchList.length && searchList[i] != null; i++) {
			System.out.println(searchList[i]);
		}
	}
	
	public void printBookList(Book[] bList) {
		
	}
	
	public void rentBook() {
		selectAll();
		
		System.out.print("대여할 도서 번호 선택 : ");
		int index = sc.nextInt();
		int result = lc.rentBook(index);
		switch(result) {
		case 0:
			System.out.println("성공적으로 대여되었습니다.");
			break;
		case 1:
			System.out.println("나이 제한으로 대여 불가능입니다");
			break;
		case 2:
			System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요");
			break;
		}
	}
	
	public void insertBook() {
		
		while(true) {
			System.out.println("================== 도서 추가 ==================");
			System.out.println("어떤 도서를 추가하시겠습니까?");
			System.out.println("1. 만화책  2. 요리책");
			System.out.print("번호 입력 : ");
			int type = sc.nextInt();
			sc.nextLine();
			
			if(!(type == 1 || type == 2)) {
				System.out.println("잘못입력하셨습니다.");
				continue;
			}
			
			System.out.print("제목을 입력하세요 : ");
			String title = sc.nextLine();
			
			System.out.print("저자를 입력하세요 : ");
			String author = sc.nextLine();
			
			System.out.print("출판사를 입력하세요 : ");
			String publisher = sc.nextLine();
			
			int index = 0;
			switch(type) {
			case 1:
				System.out.print("연령제한 나이를 입력하세요 : ");
				int accessAge = sc.nextInt();
				index = lc.insertBook(new AniBook(title, author, publisher,accessAge));
			case 2:
				System.out.print("쿠폰 여부(y/n) : ");
				boolean isCoupon = (sc.next().toLowerCase().charAt(0) == 'y');
				index = lc.insertBook(new CookBook(title, author, publisher, isCoupon));
			}
			
			System.out.println(index + "번째에 성공적으로 추가하였습니다.");
			break;
		}
	}
	
	public void deleteBook() {
		selectAll();
		
		System.out.print("삭제할 도서 번호 선택 : ");
		int index = sc.nextInt();
		lc.deleteBook(index);
	}
}
