<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <h2>学生情報登録</h2>
        <c:if test="${!empty done}">
            <p>${done}</p>
        </c:if>
        <form action="StudentCreateExecute.action" method="post">
            <label class="form-label" for="student-ent-year-select">入学年度</label>
            <select class="form-select" id="student-ent-year-select" name="ent_year">
                <option value="0">------</option>
                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" <c:if test="${year==ent_year}">selected</c:if>>${year}</option>
                </c:forEach>
            </select>
            <div>${errors.get("ent_year")}</div>

            <label class="form-label" for="student-no-input">学生番号</label>
            <input class="form-control" type="text" id="student-no-input" name="no"
                placeholder="学生番号を入力してください" maxlength="10" value="${no}" required />
            <div>${errors.get("no")}</div>

            <label class="form-label" for="student-name-input">氏名</label>
            <input class="form-control" type="text" id="student-name-input"
                name="name" placeholder="氏名を入力してください" maxlength="10"
                value="${name}" required />
            <div>${errors.get("name")}</div>

            <label class="form-label" for="student-class_num-select">クラス</label>
            <select class="form-select" id="student-class_num-select" name="class_num">
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}" <c:if test="${num==class_num}">selected</c:if>>${num}</option>
                </c:forEach>
            </select>
            <input class="btn btn-secondary" type="submit" value="登録して終了" name="end">
        </form>

        <a href="StudentList.action">戻る</a>
    </c:param>
</c:import>
