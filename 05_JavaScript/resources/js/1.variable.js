console.log("Hello World");

/**
 * js의 변수 선언
 * 
 * 1) var - 사용하지않는다.
 * 2) let
 * 3) const
 */

//동적타입(Dynamic Typing)
//js는 변수의 타입을 명시적으로 지정하지않고, 실행중에 값에 따라 자동으로 타입이 결정됨

var name = "최지원";
var age = 55;
var height = 197.5;
var isTrue = true;

var name = "김수민";
console.log(name + " " + age + " " + height + " " + isTrue);

/**
 * let은 var에서 같은 이름의 중복생성을 하지 못하게 만든 자료형
 * 다만 let과 var는 모든 값을 변경하는 것은 가능하다.
 */

let name2 = "최지원";
name2 = "김수민";

//let name2 = "김수민";
/**
 * const는 상수를 표현하기위한 자료형
 * 값을 변경할 수 없음
 */

const name3 = "박수민";
//name3 = "김수민";

/**
 * js 네이밍 규칙
 * 
 * 변수이름 지을 때
 * 1)일반적으로 영어를 사용하며 문자와 숫자 모두 사용할 수 있음
 * 2)특수문자는 언더스코어와 달러를 사용할 수 있음
 * 3)숫자로 시작하면 안됨
 * 4)키워드를 변수로명으로 사용하면 안됨
 * 
 * 네이밍 타입
 * 1) camelCase -> 대부분의 언어에서 많이 사용하며 단어의 시작마다 대문자로 구분한다.
 * 2) snake_case -> 파이썬에서 주로 사용하며 단어의 시작마다 _로 구분을 해줌
 * 3) PascalCase -> c#및 ms계열의 언어에서 많이 사용하는 방식 매단어 시작은 무조건 대문자다
 */

let $name = "최민수";
let _name = "김민수";

/**
 * Data Types
 * 
 * 여섯개의 원시타입과 한개의 Object타입이 있다.
 * 
 * 1) Number
 * 2) String
 * 3) Boolean
 * 4) undefined
 * 5) null
 * 6) Sybol
 * 
 * 7) Object
 * - Function
 * - Array
 * - object
 * ...
 */

//Number타입
const age2 = 55;
const temperature = -10.5;
const pi = 3.14;

console.log(age2, temperature, pi);
console.log(typeof age2);
console.log(typeof temperature);
console.log(typeof pi);

console.log(typeof Infinity);
console.log(typeof -Infinity);

//String
const name4 = "최지원 66살";
const num1 = 55;
console.log(typeof name4);
console.log(num1 == "55");
console.log(num1 === "55");

//Boolean 타입
const isTrue2 = true;
const isFalse2 = false;
console.log(typeof isTrue2)

/**
 * undefined
 * 
 * 개발자가 직접 값을 초기화하지 않았을 때
 * 지정되는 값이다.
 */
let num2;
console.log(num2);

/**
 * null
 * undefined와 동일하게 값이 없음을 표시
 * 다만 js에서는 개발자가 직접 명시적으로 없는 값을 초기화할때 사용
 */
let init = 10;
init = null;
console.log(init);

/**
 * Symbol타입
 * 
 * 유일무이한값을 생성하는 타입
 * 다른 원시값들과는 다르게 Symbol함수를 호출해서 사용
 */

const tmp1 = '1';
const tmp2 = '1';
console.log("tmp1 === tmp2 : ", tmp1 === tmp2);

const symbol1 = Symbol('1');
const symbol2 = Symbol('1');
console.log("symbol1 === symbol2 : ", symbol1 === symbol2 );
console.log(symbol1, symbol2);

/**
 * Object타입
 * 
 * 키 : 벨류 쌍으로 이루어져있다.
 * key:value -> map의 구조
 * {
 *      element : input,
 *      type : text,
 *      style : color = red
 * }
 */

const jiwon = {
    name: "최지원",
    age: 47,
    address: "경기도",
    job: "강사",
    info: function(){
        console.log("안녕하세요",this.name,"입니다.");
        console.log("저는 ",age,"살 입니다.");
    }
}

console.log(jiwon.age, jiwon["name"]);
jiwon.age += 1;
console.log(jiwon.age, jiwon["name"]);
jiwon.info();

console.log(typeof jiwon);

/**
 * Array타입
 * 
 * 값을 리스트로 나열할 수 있는 타입
 */
const arr = ["초록색", "노란색"];
arr.push("빨간색");
arr.push("파란색");
arr.push("검정색");
arr.push(20);
console.log(arr);
console.log(arr.pop());
console.log(arr[0], arr[3]);
console.log(arr);