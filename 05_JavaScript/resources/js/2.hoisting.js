/**
 * Hoisting
 * 
 * js에서 변수와 함수 선언이 코드 실행전에 메모리에 먽저 할당되는 동작.
 * 변수나 함수의 선언이 코드의 최상단으로 끌어올려지는 것 처럼 동작한다.
 * 실제로는 메모리가 미리 등록되는 것
 */

console.log("선언 전 :", name1);
var name1 = "jiwon";
console.log("선언 후 :", name1);

console.log("선언 전 :", name2);
let name2 = "sumin";
console.log("선언 후 :", name2);

//var와 다르게 let과 const는 tdz를 이용하여
//마치 호이스팅이 일어나지 않은 것처럼 동작하게 해준다.