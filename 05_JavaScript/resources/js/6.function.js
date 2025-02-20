
function test1(){
    console.log("test1함수");
    console.log(this);
}

const test2 = function(){
    console.log("test2함수");
    console.log(this);
}

let test3 = (name) => {
    //본인의 this를 가지지않고 상위 스코프의 this를 사용
    //arguments 객체가 없음
    console.log("test3함수");
    console.log(this);
}

test3 = name => {
    return 10;
}

test3 = name => 10;




test1();
test2();
test3();

function Human(name, age){
    this.name = name;
    this.age = age;
    this.hello = function(){
        return "안녕하세요" + this.name + "입니다";
    }
}

const choi = new Human("최지원", 22);
console.log(choi.hello());