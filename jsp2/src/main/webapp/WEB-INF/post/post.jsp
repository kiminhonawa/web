<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Post</title>
        <style type="text/css">
           table, th, td {
            border: 1px solid black;
            }
            
        </style>
	</head>
	<body>
		<header>
            <h1>Post 페이지</h1>
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
                    <c:url var="mainPage" value="/"></c:url>
                    <a href="${ mainPage }">메인 페이지</a>
                </li>
                <li>
                    <c:url var="postCreate" value="/post/create"></c:url>
                    <a href= "${ postCreate }">새 포스트 작성</a>
                </li>               
            </ul>
        </nav> 
        <table>
        <thead>
            <tr>
                <th>글 번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>수정시간</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ posts }" var="post">
                <tr>
                    <td>${ post.id }</td>
                    <td>
                        <c:url value="/post/detail" var="postDetail">
                            <c:param name="id" value="${ post.id }"></c:param>
                        </c:url>
                        <a href="${ postDetail }">${ post.title }</a>
                    </td>
                    <td>${ post.author }</td>
                    <td>${ post.modifiedTime }</td>
                </tr>
            </c:forEach>
        </tbody>
       </table>
       <c:url value="/post/search" var="searchPage"></c:url>  
       <form action="${ searchPage }">
            <select name="category">
                <option value="t">제목</option>
                <option value="c">내용</option>
                <option value="tc">제목 + 내용</option>
                <option value="a">작성자</option>
            </select>
            <input type="text" name="keyword" placeholder="검색어를 입력하세요." required autofocus />
            <input type="submit" value="검색" />
       </form>
	</body>
</html>