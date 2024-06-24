<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <h2>テスト登録</h2>
        <c:if test="${!empty errors}">
            <div class="alert alert-danger">
                <ul>
                    <c:forEach var="error" items="${errors.values()}">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form action="TestCreate.action" method="post">
            <div class="mb-3">
                <label class="form-label" for="test-no-input">テスト番号</label>
                <input class="form-control" type="text" id="test-no-input" name="no" placeholder="テスト番号を入力してください" maxlength="10" value="${no}" required />
            </div>
            <div class="mb-3">
                <label class="form-label" for="test-studentNo-input">学生番号</label>
                <input class="form-control" type="text" id="test-studentNo-input" name="studentNo" placeholder="学生番号を入力してください" maxlength="10" value="${studentNo}" required />
            </div>
            <div class="mb-3">
                <label class="form-label" for="test-subjectCd-input">科目コード</label>
                <input class="form-control" type="text" id="test-subjectCd-input" name="subjectCd" placeholder="科目コードを入力してください" maxlength="10" value="${subjectCd}" required />
            </div>
            <div class="mb-3">
                <label class="form-label" for="test-schoolCd-input">学校コード</label>
                <input class="form-control" type="text" id="test-schoolCd-input" name="schoolCd" placeholder="学校コードを入力してください" maxlength="10" value="${schoolCd}" required />
            </div>
            <div class="mb-3">
                <label class="form-label" for="test-point-input">点数</label>
                <input class="form-control" type="number" id="test-point-input" name="point" placeholder="点数を入力してください" value="${point}" required />
            </div>
            <div class="mb-3">
                <label class="form-label" for="test-classNum-input">クラス番号</label>
                <input class="form-control" type="text" id="test-classNum-input" name="classNum" placeholder="クラス番号を入力してください" maxlength="10" value="${classNum}" required />
            </div>
            <button class="btn btn-primary" type="submit">登録</button>
        </form>
        <div class="mt-3">
            <a href="TestList.action">戻る</a>
        </div>
    </c:param>
</c:import>
