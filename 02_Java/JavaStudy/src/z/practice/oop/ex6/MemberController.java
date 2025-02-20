package z.practice.oop.ex6;

public class MemberController {
	private Member[] m = new Member[SIZE];
	public static final int SIZE = 10;
	
	

	public MemberController() {
		super();
		
		m[0] = new Member("user01", "김지원", "pass01", "jw1@naver.com", 'm', 35);
		m[1] = new Member("user02", "최지원", "pass02", "jw1@naver.com", 'm', 12);
		m[2] = new Member("user03", "이지원", "pass03", "jw1@naver.com", 'f', 56);
		m[3] = new Member("user04", "박지원", "pass04", "jw1@naver.com", 'f', 32);
	}

	public int existMemberNum() {
		int count = 0;
		for(int i=0; i<m.length; i++) {
			if(m[i] == null) {
				break;
			}
			count++;
		}
		
		return count;
	}

	//check하고자하는 id를 넘겨받아 배열 m에 해당id를 사용하는 Member가 있는지 검사
	//중복된 id가 있을 경우 사용불가 -> return false
	//중복된 id가 없을 경우 사용가능 -> return true
	public boolean checkId(String inputId) {
		for(Member mem : m) {
			if(mem == null) { // null이 나올때까지 찾았는데 못찾음
				return true;
			}
			
			if(mem.getId().equals(inputId)) { //동일한 id를 사용하는 Member 찾음
				return false;
			}
		}
		
		return true;
	} 

	public void insertMember(String id, String name, String password, String email, char gender, int age) {
		for(int i=0; i<m.length; i++) {
			if(m[i] == null) { //빈공간 체크
				m[i] = new Member(id, name, password, email, gender, age); //회원추가
				return;
			}
		}
	}

	//존재하면 검색된 회원의 정보를 return
	public String searchId(String id) {
		for(Member mem : m) {
			if(mem == null) {
				return null;
			} else if(mem.getId().equals(id)) {
				return mem.inform();
			}
		}
		return null;
	}
	//존재하면 검색된 회원의 정보를 return
	public Member[] searchName(String name) {
		Member[] mArr = new Member[SIZE];
		int index = 0;
		
		for(Member mem : m) {
			if(mem == null) {
				break;
			}
			if(mem.getName().equals(name)) { //동일한 이름을 찾음
				mArr[index++] = mem;
			}
		}
		return mArr;
	}

	public Member[] searchEmail(String email) {
		return null;
	}

	//변경 성공했으면 true
	//변경 실패하면 false
	public boolean updatePassword(String id, String password) {
		for(Member mem : m) {
			if(mem == null) {
				return false;
			} else if(mem.getId().equals(id)) {
				mem.setPassword(password);
				return true;
			}
		}
		
		return false;
	}

	
	public boolean updateName(String id, String name) {
	
		return false;
	}

	public boolean updateEmail(String id, String email) {
		
		return false;
	}

	//특정 id를 찾아서 삭제
	//id를 못찾으면 return false;
	public boolean delete(String id) {
		for(int i=0; i<m.length; i++) {
			if(m[i] == null) {
				return false;
			} else if(m[i].getId().equals(id)) { //삭제할 id요소 발견
				
				//i요소의 null이 대입되었기 때문에 뒤에있는 요소에는 값이 있으면 안됨.
				//그래서 i요소에 null을 넣는 것이 아니라 뒤에요소를 하나씩 앞으로 당겨준다.
				//발견한요소~ 맨마지막요소(null이 아닌요소)
				for(int j=i; j<m.length; j++) {
					if(j == m.length - 1) {//마지막요소는 다음요소가 없기 때문에 null을 직접대입한다.
						m[j] = null;
						break;
					} else if(m[j] == null) {
						break;
					}
					m[j] = m[j+1];
				}
				return true;
			}
		}
		
		return false;
	}

	public void delete() {
		this.m = new Member[SIZE];
	}

	public Member[] printAll() {
		return m;
	}
}
