<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生の得点一覧</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生の得点一覧</h2>
            <c:choose>
                <c:when test="${not empty tests}">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>テスト番号</th>
                                <th>学生番号</th>
                                <th>科目コード</th>
                                <th>学校コード</th>
                                <th>点数</th>
                                <th>クラス番号</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.no}</td>
                                    <td>${test.studentNo}</td>
                                    <td>${test.subjectCd}</td>
                                    <td>${test.schoolCd}</td>
                                    <td>${test.point}</td>
                                    <td>${test.classNum}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>点数情報が存在しませんでした。</p>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
