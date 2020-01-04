<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>团购模块管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
	<div class="ibox">
	<div class="ibox-title">
		<h5>团购模块列表 </h5>
		<div class="ibox-tools">
			<a class="collapse-link">
				<i class="fa fa-chevron-up"></i>
			</a>
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-wrench"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="#">选项1</a>
				</li>
				<li><a href="#">选项2</a>
				</li>
			</ul>
			<a class="close-link">
				<i class="fa fa-times"></i>
			</a>
		</div>
	</div>
    
    <div class="ibox-content">
	<sys:message content="${message}"/>
	
	<!--查询条件-->
	<div class="row">
	<div class="col-sm-12">
	<form:form id="searchForm" modelAttribute="xhGroups" action="${ctx}/xhgroups/xhGroups/" method="post" class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
		<div class="form-group">
			<span>商品信息：</span>
				<form:select path="xhGoods"  class="form-control m-b">
					<form:option value="" label="">---请选择商品---</form:option>
					<c:forEach items="${xlist}" var="xlist">
							<form:option value="${xlist.id}" label="">${xlist.name}</form:option>
							<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</c:forEach>
				</form:select>
		 </div>	
	</form:form>
	<br/>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div class="row">
	<div class="col-sm-12">
		<div class="pull-left">
			<shiro:hasPermission name="xhgroups:xhGroups:add">
				<table:addRow url="${ctx}/xhgroups/xhGroups/form" title="团购模块" height="450px"></table:addRow><!-- 增加按钮 -->
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="xhgroups:xhGroups:edit">
			    <table:editRow url="${ctx}/xhgroups/xhGroups/form" title="团购模块" id="contentTable"></table:editRow><!-- 编辑按钮 -->
			</shiro:hasPermission> --%>
			<shiro:hasPermission name="xhgroups:xhGroups:del">
				<table:delRow url="${ctx}/xhgroups/xhGroups/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
			</shiro:hasPermission>
			<%-- <shiro:hasPermission name="xhgroups:xhGroups:import">
				<table:importExcel url="${ctx}/xhgroups/xhGroups/import"></table:importExcel><!-- 导入按钮 -->
			</shiro:hasPermission>
			<shiro:hasPermission name="xhgroups:xhGroups:export">
	       		<table:exportExcel url="${ctx}/xhgroups/xhGroups/export"></table:exportExcel><!-- 导出按钮 -->
	       	</shiro:hasPermission> --%>
			</div>
		<div class="pull-right">
			<button  class="btn btn-primary btn-rounded btn-outline btn-sm " onclick="search()" ><i class="fa fa-search"></i> 查询</button>
		</div>
	</div>
	</div>
	
	<!-- 表格 -->
	<table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
		<thead>
			<tr>
				<th> <input type="checkbox" class="i-checks"></th>
				<th>商品</th>
				<th>团号</th>
				<th>标题</th>
				<!-- <th>小标题</th> -->
				<th>团购周期</th>
				<!-- <th>区域</th> -->
				<th>开团时间</th>
				<th>截止时间</th>
				<th>开团人数</th>
				<th>团购最低金额</th>
				<th>团购最高金额</th>
				<th>团购状态</th>
				<!-- <th  class="sort-column remarks">备注信息</th> -->
				<!-- <th  class="sort-column status">状态</th> -->
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="xhGroups">
			<tr>
				<td> <input type="checkbox" id="${xhGroups.id}" class="i-checks"></td>
				<td><a  href="#" onclick="openDialogView('查看团购模块', '${ctx}/xhgroups/xhGroups/form?id=${xhGroups.id}','800px', '450px')">
					${xhGroups.xhGoods.name}
				</a></td>
				<td>
					${xhGroups.groupNo}
				</td>
				<td>
					${xhGroups.groupTitle}
				</td>
				<%-- <td>
					${xhGroups.lTitle}
				</td> --%>
				<td>
					${xhGroups.groupCycle}天
				</td>
				<%-- <td>
					${xhGroups.groupArea}
				</td> --%>
				<td>
					<fmt:formatDate value="${xhGroups.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${xhGroups.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${xhGroups.maxNum}
				</td>
				<td>
					${xhGroups.lPrice}
				</td>
				<td>
					${xhGroups.maxPrice}
				</td>
				<td>
					${fns:getDictLabel(xhGroups.groupStatus, 'groupStatus', '')}
				</td>
				<%-- <td>
					${xhGroups.remarks}
				</td> --%>
				<%-- <td>
					${xhGroups.status}
				</td> --%>
				<td>
					<shiro:hasPermission name="xhgroups:xhGroups:view">
						<a href="#" onclick="openDialogView('查看团购模块', '${ctx}/xhgroups/xhGroups/form?id=${xhGroups.id}','800px', '450px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="xhgroups:xhGroups:edit">
    					<a href="#" onclick="openDialog('修改团购模块', '${ctx}/xhgroups/xhGroups/form?id=${xhGroups.id}','800px', '450px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
    				</shiro:hasPermission>
    				<shiro:hasPermission name="xhgroups:xhGroups:del">
						<a href="${ctx}/xhgroups/xhGroups/delete?id=${xhGroups.id}" onclick="return confirmx('确认要删除该团购模块吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
					</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
		<!-- 分页代码 -->
	<table:page page="${page}"></table:page>
	<br/>
	<br/>
	</div>
	</div>
</div>
</body>
</html>