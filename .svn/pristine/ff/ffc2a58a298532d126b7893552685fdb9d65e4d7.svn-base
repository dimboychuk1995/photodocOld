<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<html>
<body>
<div class="div-for-form">
    <div class="title-for-form">
        Пошук PTO:
    </div>
    <form  method="GET" action="${baseURL}/contract">
        <div> <input type="text" placeholder="Номер лічильника" name="counterNum"></div>
        <div> <input type="text" placeholder="Номер договору" name="contractNum"></div>

        <c:if test="${empty sessionScope.user}">
            <select name="rem_id">
                <c:forEach items="${rems}" var="rem">
                    <option value="${rem.id}">${rem.name}</option>
                </c:forEach>
            </select>
        </c:if>
        <c:if test="${!empty sessionScope.user.rem.id}">
            <div>Період</div><br/>
            <div>
                <span>з </span><input type="text" name="start_date" placeholder="дата" class="date-for-search date"><span>   по   </span>
                <input type="text" name="end_date" placeholder="дата" class="date-for-search date">
            </div>
            <input type="hidden" name="rem_id" value="${sessionScope.user.rem.id}">
        </c:if>
        <button class="submit-sign-in">пошук</button>
    </form>
</div>
<script>
    $('.date').datepicker();
</script>
</body>
</html>
