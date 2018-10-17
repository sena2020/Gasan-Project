<!-- 순수하게 출력용만 기재 -->

<%@ tag body-content="empty" pageEncoding="utf-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 사용자 태그를 정의하는 소스 -->
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ attribute name="var" type="java.lang.String" rtexprvalue="false" required="true"%>
<%@ variable name-from-attribute="var" alias="result" variable-class="java.lang.String" scope="AT_END"%>
<%

//변수 설정

value=value.replace("&","&amp;");
value=value.replace("<","&lt;");
value=value.replace("\"","&quot;");
value=value.replace(">","&gt;");
value=value.replace("\n","<br>");
value=value.replace(" ","&nbsp;");
%>

<!-- 변수 출력 -->
<c:set var="result" value="<%=value %>"></c:set>
<%-- <%=value%> --%>