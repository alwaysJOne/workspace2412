/**
 * 
 * 클로저는 어떤 함수와 해당 함수가 선언된 정적환경의 조합이다.
 * 
 * 내부함수에서 외부함수의 변수를 사용할 때 우리는 이를 클로저라고한다.
 */

let num = 5;
let num1 = 5;
let num2 = 5;
let num3 = 5;

function getNum(){
    let num = 5;

    function inner(){
        num++;
        return num;
    }

    return inner;
}

let run = getNum();

// console.log(run())
// console.log(run())
// console.log(run())
// console.log(run())
// console.log(run())
// console.log(run())
// console.log(num)

function out(outValue){
    function inner(innerValue){
        console.log("outValue : " + outValue);
        console.log("innerValue : " + innerValue);
    }

    return inner;
}

const numOutFunk = out("외부함수");
numOutFunk('내부함수');


function getNumber(){
    let number = 5;

    function inner(){
        number++;
        return number;
    }

    return inner;
}

let run1 = getNumber();
let run2 = getNumber();
let run3 = getNumber();

console.log(run1());
console.log(run1());
console.log(run1());
console.log(run1());
console.log(run2());
console.log(run2());
console.log(run3());
console.log(run3());
console.log(run3());