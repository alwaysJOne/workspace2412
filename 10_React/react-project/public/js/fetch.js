// fetch('https://jsonplaceholder.typicode.com/posts/2')
//       .then(response => response.json()) //기본적으로 fetch는 응답데이터를 string으로 가져옴 -> json
//       .then(json => {
//         console.log("post데이터 : ", json);
//       }).catch(err => {
//         console.log("문제가 있음 : ", err);
//       })

async function getPosts(){
    try{
        const response = await fetch('https://jsonplaceholder.typicode.com/posts/2');
        if(!response.ok) throw new Error("서버에서 오류 발생");
        const data = await response.json();
        console.log(" 게시글 : ", data);
    } catch(err){
        console.log("문제 발생", err);
    }
}

// getPosts();