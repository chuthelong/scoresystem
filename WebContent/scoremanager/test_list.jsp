<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            <form method="get" action="TestList.action">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">----------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">-----------</option>
                            <c:forEach var="num" items="${class_num_set}">
                                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-4">
                        <label class="form-label" for="subject-f3-select">科目</label>
                        <select class="form-select" id="subject-f3-select" name="f3">
                            <option value="0">-----------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class=" text-end">
                        <button class="btn btn-secondary" id="filter-button">絞込み</button>
                    </div>
                    <div class="mt-2 text-warning">${errors.get("f1")}</div>
                </div>
            </form>
            <c:choose>
                <c:when test="${not empty tests}">
                    <form action="TestUpdateBulk.action" method="post">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>テスト番号</th>
                                    <th>学生番号</th>
                                    <th>科目コード</th>
                                    <th>学校コード</th>
                                    <th>点数</th>
                                    <th>クラス番号</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="test" items="${tests}">
                                    <tr>
                                        <td>${test.no}</td>
                                        <td>${test.studentNo}</td>
                                        <td>${test.subjectCd}</td>
                                        <td>${test.schoolCd}</td>
                                        <td><input type="text" name="points_${test.no}" value="${test.point}"></td>
                                        <td>${test.classNum}</td>
                                        <td><a href="TestDelete.action?no=${test.no}&studentNo=${test.studentNo}&subjectCd=${test.subjectCd}&schoolCd=${test.schoolCd}">削除</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="text-end">
                            <button type="submit" class="btn btn-primary">更新</button>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <p>点数情報が存在しませんでした。</p>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
