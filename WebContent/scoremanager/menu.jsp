<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <div class="container">
            <div class="row">
                <!-- Menu chính -->
                <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">メニュー表示</h2>
                <div class="col-md-12">
                    <div class="menu-container">
                        <ul class="menu">
                            <!-- Các menu item -->
                            <li class="menu-item">
                                <a href="../scoremanager/StudentList.action">学生管理</a>
                            </li>
                            <li class="menu-item">
                                <a href="../scoremanager/TestList.action">成績管理</a>
                                <a href="../scoremanager/TestListAll.action">成績参照</a>
                            </li>
                            <li class="menu-item">
                                <a href="../scoremanager/SubjectList.action">科目管理</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </c:param>
</c:import>

<!-- CSS -->
<style>
.menu-container {
    display: flex;
    justify-content: center;
}

.menu {
    list-style-type: none;
    padding: 0;
    display: flex;
    justify-content: space-around;
    width: 100%;
    max-width: 900px; /* Adjust as needed */
}

.menu-item {
    flex: 1;
    margin: 10px;
    padding: 20px;
    background-color: #dbb;
    text-align: center;
    border-radius: 8px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.menu-item a {
    text-decoration: none;
    color: #000;
    width: 100%;
    padding: 10px 0;
}

.menu-item a:hover {
    background-color: #ddd;
}

.menu-item:nth-child(2) a {
    line-height: 2rem; /* Adjust line-height to split the section evenly */
}

.menu-item:nth-child(2) a + a {
    border-top: 1px solid #ccc;
}
</style>
