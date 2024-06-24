<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="col-3" style="height:100vh; background-color: #f8f9fa;">
    <div class="sidebar">
        <h2>Menu</h2>
        <ul class="menu_1">
        	<li>
                <div class="m-item">
                    <a href="../scoremanager/Menu.action">メニュー</a>
                </div>
            </li>
            <li>
                <div class="m-item">
                    <a href="../scoremanager/StudentList.action">学生管理</a>
                </div>
            </li>
            <li>
                <div class="m-item">
                    <a href="../scoremanager/TestList.action">成績管理</a>
                    <a href="../scoremanager/TestListAll.action">成績参照</a>
                </div>
            </li>
            <li>
                <div class="m-item">
                    <a href="../scoremanager/SubjectList.action">科目管理</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<style>
.sidebar {
    width: 100%;
    padding: 20px;
}

.menu_1 {
    list-style-type: none;
    padding: 0;
}

.m-item {
    margin-bottom: 10px;
}

.m-item a {
    text-decoration: none;
    color: #000;
    display: block;
    padding: 10px;
    border-radius: 5px;
}

.m-item a:hover {
    background-color: #ddd;
}

</style>