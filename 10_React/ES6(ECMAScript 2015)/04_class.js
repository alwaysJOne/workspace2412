// JavaScript Class

//1. 클레스 선언
// - 객체를 생성하는 설계도
// - 생성자와 메서드를 가질 수 있다.

// class Person{ //기본선언 방법
const Person = class{ //변수에 클래스를 부여할 수도 있다.
    constructor(name, age){ // js에서 생성자의 명칭은 무조건 constructor
        this.name = name;
        this.age = age;
        this.gender = "M";
    }

    //메서드를 정의
    getAge(){
        return this.age;
    }
    setAge(age){
        this.age = age; 
    }

    pringtMyInfo(){
        console.log(`제 이름은 ${this.name}이며 나이는 ${this.age}입니다.`);
    }
}

const user1 = new Person("최지구", 44);
user1.pringtMyInfo();

//2. 클래스 상속
// - extends 키워드로 부모클래스의 속성과 메서드 상속
// - super()로 부모 생성자 호출 할 수 있음

class Student extends Person{
    constructor(name, age, grade){
        super(name, age);
        this.grade = grade;
    }

    introduce(){
        console.log(`제 이름은 ${this.name}이며 학년은 ${this.grade}학년 입니다.`);
    }
}

const st1 = new Student("최지팔", 20, 1);
st1.pringtMyInfo();
st1.introduce();
//프로토타입 상속

// 클레스 vs 스크립트의 객체 비교

//자바스크립트의 object로도 필드와 메서드를 포함하는 객체를 만들 수 있음
const car1 = {
    brand : "hyundai",
    drive : function(){
        console.log("부릉부릉 ~");
    }
}

console.log(car1.brand);
car1.drive();

// static 키워드 사용가능
class Math{
    static add(a, b){
        return a + b;
    }
}

console.log(Math.add(3,4));