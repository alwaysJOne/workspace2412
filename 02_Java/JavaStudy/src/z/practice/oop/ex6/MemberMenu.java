package z.practice.oop.ex6;

import java.util.Scanner;

public class MemberMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();

	public MemberMenu() {}

	public void mainMenu() {
		while(true) {
			System.out.println("최대 등록 가능한 회원 수는 " + mc.SIZE + "명입니다.");
			System.out.println("현재 등록된 회원 수는 " + mc.existMemberNum() + "명입니다.");
		
			if(mc.existMemberNum() != 10) {
				System.out.println("1. 새 회원 등록");
			} else {
				System.out.println("회원 수가 모두 꽉 찼기 때문에 일부 메뉴만 오픈됩니다.");
			}
			
			System.out.println("2. 회원 검색");
			System.out.println("3. 회원 정보 수정");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 모두 출력");
			System.out.println("9. 끝내기");
			System.out.print("메뉴 번호 :");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				this.insertMember();
				break;
			case 2:
				this.searchMember();
				break;
			case 3:
				this.updateMember();
				break;
			case 4:
				this.deleteMember();
				break;
			case 5:
				this.printAll();
				break;
			case 9:
				System.out.println("프로그램을 종료합니다");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public void insertMember() {
		String id, name, pwd, email;
		char gender;
		int age;
		
		System.out.println("새 회원을 등록합니다.");
		
		while(true) {
			System.out.print("아이디 : ");
			id = sc.next();
			if(mc.checkId(id)) { //사용가능
				break;
			} else { //사용불가
				System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
			}
		}
		
		System.out.print("이름 : ");
		name = sc.next();
		
		System.out.print("비밀번호 : ");
		pwd = sc.next();
		
		System.out.print("이메일 : ");
		email = sc.next();
		
		while(true) {
			System.out.print("성별 : ");
			gender = sc.next().charAt(0);
			
			if(gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F') {
				break;
			} else {
				System.out.println("성별을 다시 입력하세요.");
			}
		}
		
		System.out.print("나이 : ");
		age = sc.nextInt();
		sc.nextLine(); // 개행문자비움
		
		mc.insertMember(id, name, pwd, email, gender, age);
	}

	public void searchMember() {		
		
		System.out.println("1. 아이디로 검색하기");
		System.out.println("2. 이름으로 검색하기");
		System.out.println("3. 이메일로 검색하기");
		System.out.println("9. 메인으로 돌아기기");
		System.out.print("메뉴 번호 :");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			this.searchId();
			break;
		case 2:
			this.searchName();
			break;
		case 3:
			this.searchEmail();
			break;
		case 9:
			System.out.println("메인으로 돌아갑니다.");
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
	}

	public void searchId() {
		String id;
		
		System.out.println("검색할 아이디 : ");
		id = sc.next();
		
		String memberInfo = mc.searchId(id);
		if(memberInfo == null) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			System.out.println("찾으신 회원 조회 결과입니다.");
			System.out.println(memberInfo);
		}
	}

	public void searchName() {
		String name;
		
		System.out.println("검색할 이름 : ");
		name = sc.next();
		
		Member[] mArr = mc.searchName(name);
		if(mArr[0] == null) {
			System.out.println("검색 결과가 없습니다.");
		} else {
			System.out.println("찾으신 회원 조회 결과입니다.");
			for(Member mem : mArr) {
				if(mem == null)
					break;
				System.out.println(mem.inform());
			}
		}
	}

	public void searchEmail() {
		
	}

	public void updateMember() {
		System.out.println("1. 비밀번호 수정하기");
		System.out.println("2. 이름 수정하기");
		System.out.println("3. 이메일 수정하기");
		System.out.println("9. 메인으로 돌아기기");
		System.out.print("메뉴 번호 :");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			this.updatePassword();
			break;
		case 2:
			this.updateName();
			break;
		case 3:
			this.updateEmail();
			break;
		case 9:
			System.out.println("메인으로 돌아갑니다.");
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}	
	}

	public void updatePassword() {
		String id, pwd;
		
		System.out.print("수정할 회원의 아이디 : ");
		id = sc.next();
		
		System.out.print("수정할 비밀번호 : ");
		pwd = sc.next();
		sc.nextLine();
		
		boolean isUpdate = mc.updatePassword(id, pwd);
		
		if(isUpdate) {
			System.out.println("수정이 성공적으로 되었습니다.");
		} else {
			System.out.println("존재하지 않는 아이디입니다.");
		}
	}

	public void updateName() {
		
	}

	public void updateEmail() {
	
	}

	public void deleteMember() {	
		System.out.println("1. 특정 회원 삭제하기");
		System.out.println("2. 모든 회원 삭제하기");
		System.out.println("9. 메인으로 돌아기기");
		System.out.print("메뉴 번호 :");
		
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			this.deleteOne();
			break;
		case 2:
			this.deleteAll();
			break;
		case 9:
			System.out.println("메인으로 돌아갑니다.");
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}	
	}

	public void deleteOne() {
		// 삭제할 회원 id를 사용자에게 입력 받고 정말 삭제할 것인지 사용자에게
		// 물어본 뒤, Y나 y를 사용자가 입력할 경우 입력 받은 id를
		// mc의 delete() 메소드의 매개변수로 넘김, 반환 값에 따라
		// 검색결과 없으면 “존재하지 않는 아이디입니다.” 출력, mainMenu()로 감
		// 검색 결과가 있으면 “성공적으로 삭제하였습니다.” 출력 후
		// mainMenu()로 돌아감
		System.out.println("삭제할 ID : ");
		String id = sc.next();
		
		System.out.print("정말 삭제하시겠습니까?(y/n) : ");
		char result = sc.next().toLowerCase().charAt(0);
		
		if(result == 'y') {
			boolean isDelete = mc.delete(id);
			if (isDelete) {
				System.out.println("성공적으로 삭제하였습니다.");
			} else {
				System.out.println("존재하지 않는 아이디입니다.");
			}
		}
	
	}

	public void deleteAll() {
		System.out.print("정말 삭제하시겠습니까?(y/n) : ");
		char result = sc.next().toLowerCase().charAt(0);
		if(result == 'y') {
			mc.delete();
			System.out.println("성공적으로 삭제하였습니다.");
		}
	}

	public void printAll() {
		// mc의 printAll() 메소드의 반환 값을 가지고 저장된 회원을 출력하는데
		// 저장된 회원의 수가 0명이면 “저장된 회원이 없습니다.” 출력,
		// 0명이 아니면 저장된 모든 회원의 정보 출력
		Member[] mArr = mc.printAll();
		for(Member mem : mArr) {
			if(mem == null)
				break;
			System.out.println(mem.inform());
		}
	}
}
