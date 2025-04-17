//utils.js
//해당 파일에 여러기능을 모듈로 등록하여 외부에서 사용할 수 있도록 하겠다.

//named export : 이름을 지정해서 내보낸다.
export function add(a, b){
    return a+b;
}

//변수도 등록가능
export const pi = 3.14159;

//default export : 이름없이 1개만 내보낼 수 있음
// -> 해당 모듈을 불러와 사용하는 쪽에서 이름을 자유롭게 지을 수 있음
export default function say(name = "방문자"){
    console.log(`안녕하세요 ${name}님`);
}

//이 파일 자체는 실행되는 코드가 없으며,
//다른 파일에서 import를 통해 사용할 수 있음.