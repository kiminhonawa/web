/**
 * 
 */

document.addEventListener('DOMContentLoaded', function () {
    const itemInput = document.querySelector('input#itemInput');    
    const btnInput = document.querySelector('button#btnInput');
    const itemList = document.querySelector('ul#itemList');
    
    btnInput.addEventListener('click', function () {
       //input에 입력된 값을 읽음.
       const value = itemInput.value;
       //console.log(value);
       
       //input에 입력된 값으로 li 요소를 만듦.
       const item = `<li>${value}</li>`;
       
       //li 요소를 ul에 추가
       itemList.innerHTML += item;
       
       //input의 값을 지움
       itemInput.value = '';
       
       //input에 포커스를 줌.
       itemInput.focus();
       
    });
 });
 
 document.addEventListener('DOMContentLoaded', function() {
    const input = document.querySelector('input#input');
    const list = document.querySelector('ul#list');
    const username = document.querySelector('input#username');
    const age = document.querySelector('input#age');
    const result = document.querySelector('div#result');
    input.addEventListener('keydown', function (event){
       // console.log(event); -> event: keyboardEvent 객체
       // 모든 이벤트 핸들러 함수(콜백)은 이벤트 객체를 argument로 전달받음.
       if (event.key === 'Enter') {
       
       const value = input.value;
       
       const item = `<li>${value}</li>`;
       
       list.innerHTML += item; 
       
       input.value = '';
       input.focus();
       }
    });
    
username.addEventListener('change', function(e){
    updateNameAndAge();
});

age.addEventListener('change', function(e){
    updateNameAndAge();
});
 
function updateNameAndAge() {
  const name = username.value;
  const age2 = age.value;
  const text = `<b>이름:</b> ${name}, <b>나이:</b> ${age2}`;
  result.innerHTML = text;

 }


 
 
 
 
 });
 