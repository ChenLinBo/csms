<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<body>
<div id="ctnBdmEvaluationIndex">
	<!-- <a id="btnDoNew" class="k-button" smart-ability="edit"><span class="fa fa-plus"></span>新增</a> -->
	<script id="template" type="text/x-kendo-template">		
		<a id="btnDoEdit" class="k-button" smart-ability="edit"><span class="fa fa-edit"></span>编辑</a>
		<a id="btnDoDelete" class="k-button" smart-ability="delete"><span class="fa fa-remove"></span>删除</a>
	</script>

	<div id="mainGrid"></div>
</div>
<div id="ctnBdmEvaluationEditWrap"></div>

<script src="${ctx}/views/pages/bdmEvaluation/index.js"></script>
</body>