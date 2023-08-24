/**
 * 이메일 인증
 */

document.addEventListener('DOMContentLoaded', () => {
    const requestVerificationBtn = document.querySelector('button#requestVerificationBtn');
    requestVerificationBtn.addEventListener('click', () => {
    // 인풋창에 있는 이메일
    const email = document.querySelector('input#email').value;
    console.log(email);
    
    if (email === '') {
            alert('이메일을 입력하세요.');
            return;
        }
    
    // Ajax 요청에서 보낼 데이터.
        const data = {email};
        // Ajax 요청을 보낼 URL.
        const reqUrl = '/email/verify';
        
        axios
            .post(reqUrl, data) // Ajax POST 방식 요청을 보냄.
            .then((response) => {
                console.log('response:' + response.data);
                
                
                
            }) // 성공 응답(response)일 때 실행할 콜백 등록.
            .catch((error) => console.log(error)); // 실패(error)일 때 실행할 콜백 등록.
    


    });
    
    
    const emailConfirmBtn = document.querySelector('button#emailConfirmBtn');
    emailConfirmBtn.addEventListener('click', () => {
    // 인풋창에 있는 이메일
    const verificationCode = document.querySelector('input#verificationCode').value;
    console.log('사용자:'+ verificationCode);
    
    if (verificationCode === '') {
            alert('인증번호를 입력하세요.');
            return;
        }
    
    // Ajax 요청에서 보낼 데이터.
        const data = {verificationCode};
        // Ajax 요청을 보낼 URL.
        const reqUrl = '/email/confirm';
        
        axios
            .post(reqUrl, data) // Ajax POST 방식 요청을 보냄.
            .then((response) => {
                console.log('response2:', response.data);
             
                
                
            }) // 성공 응답(response)일 때 실행할 콜백 등록.
            .catch((error) => console.log(error)); // 실패(error)일 때 실행할 콜백 등록.

    });
    
});