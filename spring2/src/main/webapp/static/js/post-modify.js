/**
 * post-modify.js
 * /post/modify.jsp에서 사용됨
 */

 document.addEventListener('DOMContentLoaded', function () {
     console.log("js in");
     // form 요소를 찾음.
     const form = document.querySelector('#postModifyForm');
     
     // input#id 요소를 찾음.
     const inputId = document.querySelector('input#id');
     
     // input#title 요소를 찾음.
     const inputTitle = document.querySelector('input#title');
     
     // textarea#content 요소를 찾음.
     const textareaContent = document.querySelector('textarea#content');
     
     // 수정 완료 버튼을 찾음.
     const btnUpdate = document.querySelector('button#btnUpdate');
     
     // 삭제 버튼을 찾음.
     const btnDelete = document.querySelector('button#btnDelete');
     
     // 삭제 버튼에 클릭 이벤트 핸들러를 등록
     btnDelete.addEventListener('click', (e) => {
         console.log("delete");
         e.preventDefault();
         // -> form 안에 있는 버튼 또는 input{type=submit}의 기본 (클릭) 동작은
         // 폼의 내용을 서버로 제출 (서버로 요청을 보냄).
         // 버튼의 기본 동작이 기능하지 않도록 함.
         
         const id = inputId.value;
         const result = confirm(`No.${id} 정말 삭제할까요?`);
         console.log(`삭제 확인 결과 = ${result}`);
         
         // 사용자가 confirm 창에서 '확인'을 클릭했을 때
         if (result) {
             form.action = 'delete'; // 폼 제출(요청) 주소
             form.method = 'post'; // 요청 방식
             form.submit();
         }
     });
         
     btnUpdate.addEventListener(`click`, (e) => {
         console.log("update");
         e.preventDefault(); // 폼 제출 기능을 막음.
         
         const id = inputId.value; // 포스트 번호
         const title = inputTitle.value; // 제목
         const content = textareaContent.value; //내용
         
         if (title === '' || content === '') {
             //제목 또는 내용이 비어 있으면
             alert('제목/내용을 입력하세요.');
             return; // 종료.
         }                
         const result = confirm(`NO.${id} 포스트를 수정할까요?`);
         if (result) {
             form.action = 'modify'; //요청 주소
             form.method = 'post'; //요청 방식
             form.submit(); // 폼 제출(요청 보내기)
         }
     });
     
     
     
     
     
     
 });