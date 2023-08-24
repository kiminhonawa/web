<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>POST</title>
	</head>
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
	<body>
	   <header>	  
          <h1>메인 페이지</h1>
       </header> 
       
       <nav>
          <ul>
            <c:if test="${ empty signedInUser }">  
                <li>
                    <c:url var="signInPage" value="/user/signin"></c:url>
                    <a href="${ signInPage }">로그인</a>
                </li>
            <li>
                <c:url var="signUpPage" value="/user/signup"></c:url>
                <a href="${ signUpPage }">회원가입</a>
            </li>
            </c:if>    
            <c:if test="${ not empty signedInUser }">   
                <li>
                    <span>${ signedInUser }</span>
                    <c:url var="signOut" value="/user/signout"></c:url>
                    <a href="${ signOut }">로그아웃</a>
                </li>
            </c:if>
            <li>     
                <c:url var="postList" value="/post"></c:url>
                <a href="${ postList }">포스트 목록 페이지</a>
            </li>
          </ul>      
       </nav>
       
	</body>
</html>