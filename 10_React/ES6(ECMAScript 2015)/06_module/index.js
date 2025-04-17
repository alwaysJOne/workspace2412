//index.js
//utils.js에서 export한 기능을 import해서 사용할 js파일

//named export는 중괄로로 감싸서 가져와준다.
import {add, pi} from "./utils.js";
import sayFunc from "./utils.js";

console.log("2 + 3 :", add(2,3));
console.log("pi : ", pi);

sayFunc("김지수");

//모듈은 각 파일이 독립된 scope를 가진다.
//다른 파일의 변수나 함수는 반드시 import해야 사용이 가능