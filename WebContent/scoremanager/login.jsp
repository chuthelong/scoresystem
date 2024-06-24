<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts">
        <script type="text/javascript">
            $(function() {
                $('#password-display').change(function() {
                    if ($(this).prop('checked')) {
                        $('#password-input').attr('type','text');
                    } else {
                        $('#password-input').attr('type','password');
                    }
                });
            });
        </script>
    </c:param>

    <c:param name="content">
        <form action="LoginExecute.action" method="post">
            <h2>ログイン</h2>
            <c:if test="${errors.size()>0 }">
                <ul>
                    <c:forEach var="error" items="${errors }">
                        <li>${error }</li>
                    </c:forEach>
                </ul>
            </c:if>
            <input autocomplete="off" id="id-input" maxlength="20" name="id" placeholder="半角で入力してください"
                   style="ime-mode:disabled" type="text" value="${id }" required />
            <label for="id-input">ID</label>
            <input autocomplete="off" id="password-input" maxlength="20" name="password" placeholder="20文字以内の半角で入力してください"
                   style="ime-mode:disabled" type="password" required />
            <label for="password-input">パスワード</label>
            <label class="form-check-label" for="password-display">
                <input class="form-check-input" id="password-display" name="chk_d_ps" type="checkbox" />
                パスワードを表示
            </label>

            <input type="submit" name="login" value="ログイン" />
        </form>
    </c:param>
</c:import>
