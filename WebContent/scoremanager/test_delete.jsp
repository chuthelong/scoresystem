<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <h2>点数削除</h2>
        <form action="TestDelete.action" method="post">
            <input type="hidden" name="no" value="${test.no}" />
            <input type="hidden" name="studentNo" value="${test.studentNo}" />
            <input type="hidden" name="subjectCd" value="${test.subjectCd}" />
            <input type="hidden" name="schoolCd" value="${test.schoolCd}" />
            <p>本当に削除しますか？</p>
            <button type="submit">削除</button>
            <a href="TestList.action">キャンセル</a>
        </form>
    </c:param>
</c:import>

