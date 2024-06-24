<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../common/base.jsp">
    <c:param name="title">
        科目登録
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">新規科目登録</h2>
        <form action="SubjectCreate.action" method="post">
            <label class="form-label" for="subject-cd-input">科目コード</label>
            <input class="form-control" type="text" id="subject-cd-input" name="cd" placeholder="科目コードを入力してください" required />
            <div>${errors.get("cd")}</div>

            <label class="form-label" for="subject-name-input">科目名</label>
            <input class="form-control" type="text" id="subject-name-input" name="name" placeholder="科目名を入力してください" required />
            <div>${errors.get("name")}</div>

            <input class="btn btn-secondary" type="submit" value="登録" />
        </form>
        <a href="SubjectList.action">戻る</a>
    </c:param>
</c:import>
