<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="<%=request.getContextPath() %>/static/pages/js/videoList.js?v=1.1" type="text/javascript"></script>

	<!-- BEGIN PAGE HEADER-->
	<!-- BEGIN PAGE BAR -->
	<div class="page-bar">
	    <ul class="page-breadcrumb">
	        <li>
	            <span>首页</span>
	            <i class="fa fa-circle"></i>
	        </li>
	        <li>
	            <span>短视频信息</span>
	            <i class="fa fa-circle"></i>
	        </li>
	        <li>
	            <span>短视频列表</span>
	        </li>
	    </ul>
	</div>
	<!-- END PAGE BAR -->
	<!-- END PAGE HEADER-->
        
    <!-- 用户信息列表 jqgrid start -->                
	<div class="row">
	
		
		<!-- 搜索内容 -->
		<div class="col-md-12">
			<br/>
				<form id="searchVideoListForm" class="form-inline" method="post" role="form">
					<div class="form-group">
						<label class="sr-only" for="username">用户名:</label>
						<input id="username" name="username" type="text" class="form-control" placeholder="用户名" />
					</div>
					<div class="form-group">
						<label class="sr-only" for="nickname">视频内容:</label>
						<input id="videoDesc" name="videoDesc" type="text" class="form-control" placeholder="视频内容" />
					</div>
					<button id="searchVideoListButton" class="btn yellow-casablanca" type="button">搜    索</button>
				</form>
			</div>
			
    	<div class="col-md-12">
			<br/>
			
			<div class="videoList_wrapper">  
			    <table id="videoList"></table>  
			    <div id="videoListPager"></div>  
			</div>  
			
		</div>
	</div>
	<!-- 用户信息列表 jqgrid end -->
	
