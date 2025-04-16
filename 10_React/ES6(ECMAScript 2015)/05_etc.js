//es6주요문법

// 1. 템플릿 리터럴
// - 문자열과 변수 결합시 유용
// 여러 줄 문자열도 간편하게 처리 가능

const userName = "jiwon";
console.log(`안녕하세요. ${userName}님`);

const multiLine = `이 변수에는 
여러줄로 작성된
문자열 입니다.`;
console.log(multiLine);

// 2. (객체) 구조 분해 항당
// - 객체에서 필요한 값을 바로 변수로 추출할 때 사용
const userInfo = {
    name: "jiwon",
    age: 55,
    job: "developer"
}

const { name, age } = userInfo;
console.log(name);
console.log(age);

//변수 이름을 변경해서 추출도 가능
const { job: occupation } = userInfo;
console.log(occupation);

//React에서는 props라는 객체를 전달하고 매개변수로 그 내부 값들을 받을 때 주로 사용
function myInfo({name, age}){
    console.log(name);
    console.log(age);
}

myInfo(userInfo);

// 3. 배열 구조 분해할당
// - 배열 요소를 순서대로 변수에 담아준다.

const numbers = [10,20,30];
const [first, second] = numbers;

console.log(first);
console.log(second);

//필요없는 값은 생략 가능
const [, , third] = numbers;

//react에서는 state라는 값을 생성할 때 배열에 값과 해당값을 변경하는 함수가 순차적으로 전달된다.
// useState() -> return [값, 값을 변경할 함수]
// const [count, setCount] = useState();

// 4.스프레드 연산자 (...)
// - 배열/객체 복사, 병합, 수정, 나머지값을 처리등등
// - 불변성 유지에 필수(변경x 다시생성o)

let fruits = ["사과", "바나나"];
//fruits.push("키위");
fruits = [...fruits, "키위"];

let user = {
    name: "jiwon",
    age: 55,
    job: "developer"
}

//user.job = "chef";수정
user = {
    ...user,
    job: "chef"
}

//함수에서도 사용가능
function sum(...nums){
    console.log(nums)
}

sum(1,2,3,4,5,6);

