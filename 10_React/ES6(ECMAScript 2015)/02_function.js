//함수표현식과 화살표 함수

// 1. 함수 표현식

//기본함수 선언방식
function hello(){
    console.log("hellow!");
}

hello();

//js에서는 함수도 하나의 값(변수)으로 취급된다.
let _hello = hello;
console.log(_hello);
_hello();

// 익명함수 표현식
// 이름없이도 함수를 실행할 수 있을 때 사용
const printMsg = function(){
    console.log("this is a function");
}

printMsg();


//기본 함수 자체는 호이스팅이 됨.
// console.log("tmpFunc : ", tmpFunc);
tmpFunc();

function tmpFunc(){
    console.log("나는 존재함");
}

//console.log(tmpFunc2);

const tmpFunc2 = function(){
    console.log("나는 존재함");
}

// 함수의 기본 매개변수
function greet(name = "방문자", msg = "안녕하세요."){
    console.log(`${msg} ${name}님`);
}

//js의 함수는 호출시 이름으로만 판단함 매개변수는 있어도되고 없어도 괜찮다.
greet();
greet("최수민");

//null을 사용하면 개발자가 빈값을 지정해주는 것이기 때문에 null을 변수에 대입하고
//undefined를 사용하면 시스템적으로 빈값을 처리하라는 뜻으로 기본값이 적용된다.
greet(null); // null -> 개발자가 직접넣은 빈값
greet(undefined, "없는이름?"); // undefined -> 시스템에서 정해주는 빈값

//기본값이 있는 매개변수는 보통 뒤쪽에 배치를 한다.
function greet2(name, hobby = "독서"){
    console.log(`${name}님의 취미는 ${hobby}군요.`);
}

greet2("최민수");
//앞쪽 배치시 greet2(undefined, "최민수");


// 기본값에 다른 매개변수를 사용할 수 있음
function calcul(price, tax = price * 0.1){
    console.log("금액 : ", price, " 세금 : ", tax);
}

calcul(10000, 2000);
calcul(10000);

// 2. 화살표 함수

//기본 화살표 함수
const add = (a, b) => {
    return a + b;
}

console.log("5 + 10 : ", add(5,10));

//함수의 내용에 return값만 있다면 return키워드와 {}를 생략가능
const minus = (a, b) => a - b;
console.log("10 - 5 : ", minus(10,5));

//전달할 매개변수가 한개라면 ()도 생략 가능
const square = x => x * x;
console.log("10의 제곱 : ", square(10));

// 3. 콜백 함수
//특정 함수를 실행할 때 실행하는 사람이 특정 기능을 완료한 후에 실행하고싶은 코드를 정의하는 용도
function run(callback){
    //run함수의 기능 코드 ---------
    callback();
}

run(function(){
    console.log("run을 끝냈다.");
})

