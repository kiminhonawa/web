<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create</title>
</head>
<body>
    <header>
        <h1>포스트 수정 페이지</h1>
    </header>

    <nav>
        <ul>
         <li>
                <c:url value="/user/signout" var="signOut"></c:url>
                <span>${ signedInUser }</span>
                <a href="${ signOut }">로그아웃</a>
            </li>
            <li><c:url var="mainPage" value="/"></c:url> 
                <a href=" ${ mainPage }">메인 페이지</a></li>
            <li><c:url var="postPage" value="/post"></c:url> 
                <a href="${ postPage }">포스트 목록 페이지</a></li>
           <li>
            <c:url var="postDetail" value="/post/detail">
                <c:param name="id" value="${ post.id }"></c:param>
            </c:url>
            <a href="${ postDetail }">포스트 상세보기</a>
           </li> 
            
        </ul>
    </nav>
    <main>
        <form id="postModifyForm">
            <div>
                <input id="id" name="id" type="text" value="${ post.id }" readonly/>
            </div>
            <div>
                <input id="title" name="title" type="text" name="title" value="${ post.title }" autofocus/>
            </div>
            <div>
                <textarea id="content" name="content" rows="5" cols="80" name="content"  required>${ post.content }</textarea>
            </div>
            <div>
                <input type="text" name="author" value="${ post.author }" readonly/>
            </div>           
            <c:if test="${ signedInUser == post.author }">
            <div>
                <button id="btnUpdate">수정완료</button>
                <button id="btnDelete">삭제</button>
            </div>
            </c:if>
        </form>
    </main>
    
    
</body>
</html>