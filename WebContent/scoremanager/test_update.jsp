<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <h2>点数変更</h2>
        <c:if test="${!empty errors}">
            <div class="text-danger">
                <c:forEach var="error" items="${errors.values()}">
                    <p>${error}</p>
                </c:forEach>
            </div>
        </c:if>
        <form action="TestUpdate.action" method="post">
            <input type="hidden" name="originalNo" value="${test.no}" />
            <input type="hidden" name="originalStudentNo" value="${test.studentNo}" />
            <input type="hidden" name="originalSubjectCd" value="${test.subjectCd}" />
            <input type="hidden" name="originalSchoolCd" value="${test.schoolCd}" />
            <div>
                <label for="no">テスト番号</label>
                <input type="text" id="no" name="no" value="${test.no}" required />
            </div>
            <div>
                <label for="studentNo">学生番号</label>
                <input type="text" id="studentNo" name="studentNo" value="${test.studentNo}" required />
            </div>
            <div>
                <label for="subjectCd">科目コード</label>
                <input type="text" id="subjectCd" name="subjectCd" value="${test.subjectCd}" required />
            </div>
            <div>
                <label for="schoolCd">学校コード</label>
                <input type="text" id="schoolCd" name="schoolCd" value="${test.schoolCd}" required />
            </div>
            <div>
                <label for="point">点数</label>
                <input type="number" id="point" name="point" value="${test.point}" required />
            </div>
            <div>
                <label for="classNum">クラス番号</label>
                <input type="text" id="classNum" name="classNum" value="${test.classNum}" required />
            </div>
            <button type="submit">変更</button>
        </form>
    </c:param>
</c:import>
