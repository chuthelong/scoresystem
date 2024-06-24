<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.container {
		max-width: 600px;
        margin: 50px auto;
	}
	.alert {
		margin-top: 20px;
	}
</style>
<c:import url="/common/base.jsp">
    <c:param name="title">学生登録</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
    <link rel="stylesheet" href="path/to/your/css/bootstrap.min.css">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">登録完了</h2>
			    <div class="container">
			        <div class="alert alert-success" role="alert">
			            学生登録が完了しました。
			        </div>
			        <div class="mt-4">
			            <a href="StudentList.action" class="btn btn-primary">学生一覧に戻る</a>
			        </div>
			    </div>
		</section>
	</c:param>
</c:import>