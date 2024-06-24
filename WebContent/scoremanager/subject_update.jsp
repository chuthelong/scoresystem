<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../common/base.jsp">
    <c:param name="title">
        科目変更
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目変更</h2>
        <form action="SubjectUpdate.action" method="post">
            <input type="hidden" name="schoolCd" value="${subject.schoolCd}" />

            <label class="form-label" for="subject-cd-input">科目コード</label>
            <input class="form-control" type="text" id="subject-cd-input" name="cd" value="${subject.cd}" readonly />
            <div class="text-danger">${errors.cd}</div>

            <label class="form-label" for="subject-name-input">科目名</label>
            <input class="form-control" type="text" id="subject-name-input" name="name" value="${subject.name}" required />
            <div class="text-danger">${errors.name}</div>

            <input class="btn btn-secondary mt-3" type="submit" value="更新" />
        </form>
        <a class="btn btn-link mt-3" href="SubjectList.action">戻る</a>
    </c:param>
</c:import>
