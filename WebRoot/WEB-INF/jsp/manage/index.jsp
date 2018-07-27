<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>汉典管理系统</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<%@ include file="import.jsp"%>
<!-- init -->
<script type="text/javascript">
$(function() {
    BJUI.init({
        loginInfo  : {url:'/redirect.im?path=manage/login', title:'登录', width:300, height:200}, // 弹出登录对话框
        statusCode : {ok:200, error:300, timeout:301}, //[可选]
        pageInfo   : {pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]
        keys       : {statusCode:'statusCode', message:'message'}, //[可选]
        ui         : {hideMode:'display'}, //[可选]hideMode:navtab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
        debug      : false,    // [可选]调试模式 [true|false，默认false]
        theme      : 'purple' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, green]
    })
    //时钟
    var today = new Date(), time = today.getTime()
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'))
    setInterval(function() {
        today = new Date(today.setSeconds(today.getSeconds() + 1))
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'))
    }, 1000);
})

//console.log('IE:'+ (!$.support.leadingWhitespace))
//菜单-事件
function MainMenuClick(event, treeId, treeNode) {
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId)
        
        zTree.expandNode(treeNode)
        return
    }
    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name})
    else
        $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh})
}
</script>
<style>
th {
	text-align: center;
}
</style>
</head>
<body>
	<!--[if lte IE 7]>
        <div id="errorie"><div>您还在使用老掉牙的IE，正常使用系统前请升级您的浏览器到 IE8以上版本 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/ie-8-worldwide-languages">点击升级</a>&nbsp;&nbsp;强烈建议您更改换浏览器：<a href="http://down.tech.sina.com.cn/content/40975.html" target="_blank">谷歌 Chrome</a></div></div>
    <![endif]-->
	<header class="navbar navbar-default" id="bjui-header">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bjui-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<%--
			<a class="navbar-brand" href="#"><img src="images/logo.jpg">
			</a>
			 --%>
		</div>
		<nav class="collapse navbar-collapse" id="bjui-navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="datetime"><div>
						<span id="bjui-date"></span><br>
						<i class="fa fa-clock-o"></i> <span id="bjui-clock"></span>
					</div>
				</li>
				<%--
				<li><a href="#">消息 <span class="badge">4</span>
				</a>
				</li>
				 --%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">我的账户 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/redirect.im?path=manage/changepwd"
							data-toggle="dialog" data-id="changepwd_page" data-mask="true"
							data-width="400" data-height="260">&nbsp;<span
								class="glyphicon glyphicon-lock"></span> 修改密码&nbsp;</a>
						</li>
						<%--
						<li><a href="#">&nbsp;<span
								class="glyphicon glyphicon-user"></span> 我的资料</a>
						</li>
						 --%>
						<li class="divider"></li>
						<li><a href="/manage/logout.im" class="red">&nbsp;<span
								class="glyphicon glyphicon-off"></span> 注销登录</a>
						</li>
					</ul></li>
				<li class="dropdown"><a href="#"
					class="dropdown-toggle theme purple" data-toggle="dropdown"><i
						class="fa fa-tree"></i>
				</a>
					<ul class="dropdown-menu" role="menu" id="bjui-themes">
						<li><a href="javascript:;" class="theme_default"
							data-toggle="theme" data-theme="default">&nbsp;<i
								class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a>
						</li>
						<li><a href="javascript:;" class="theme_orange"
							data-toggle="theme" data-theme="orange">&nbsp;<i
								class="fa fa-tree"></i> 橘子红了</a>
						</li>
						<li class="active"><a href="javascript:;"
							class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i
								class="fa fa-tree"></i> 紫罗兰</a>
						</li>
						<li><a href="javascript:;" class="theme_blue"
							data-toggle="theme" data-theme="blue">&nbsp;<i
								class="fa fa-tree"></i> 青出于蓝</a>
						</li>
						<li><a href="javascript:;" class="theme_green"
							data-toggle="theme" data-theme="green">&nbsp;<i
								class="fa fa-tree"></i> 绿草如茵</a>
						</li>
					</ul></li>
			</ul>
		</nav>
	</header>

	<div class="bjui-leftside" id="bjui-leftside">
		<div id="bjui-sidebar-s">
			<div class="collapse">
				<div class="toggleCollapse">
					<div title="展开菜单">
						<i class="fa fa-angle-double-right"></i>
					</div>
				</div>
			</div>
		</div>
		<div id="bjui-sidebar">
			<div class="toggleCollapse">
				<h2>主菜单</h2>
				<div title="收缩菜单">
					<i class="fa fa-angle-double-left"></i>
				</div>
			</div>
			<div class="panel-group panel-main" data-toggle="accordion"
				id="bjui-accordionmenu" data-heightbox="#bjui-sidebar"
				data-offsety="26">
				<div class="panel panel-default">
					<div class="panel-heading panelContent">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#bjui-accordionmenu"
								href="#bjui-collapse0" class="active"><i
								class="fa fa-caret-square-o-down"></i>&nbsp;基本信息</a>
						</h4>
					</div>
					<div id="bjui-collapse0"
						class="panel-collapse panelContent collapse in">
						<div class="panel-body">
							<ul id="bjui-tree0" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
								<c:if test="${!empty sessionManagerRole[1] }">
									<li data-id="1" data-pid="0">操作员管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[10] }">
									<li data-id="10" data-pid="1" data-url="/m2/g1.im" data-tabid="manager">操作员信息</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[63] }">
									<li data-id="63" data-pid="1" data-url="/permission/list.im" data-tabid="permission">权限管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[41] }">
									<li data-id="41" data-pid="1" data-url="/baselog/gs.im"	data-tabid="baselog">操作记录</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[49] }">
									<li data-id="49" data-pid="1" data-url="/role/fr.im" data-tabid="roles">角色管理</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[316] }">
								<li data-id="316" data-pid="0" >产品管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[317] }">
								<li data-id="317" data-pid="316" data-url="/manageProduct/productList.im" data-tabid="product">产品列表</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[350] }">
								<li data-id="350" data-pid="0" data-url="/manageYaodian/yaodianList.im" data-tabid="yaodian">药典管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[351] }">
								<li data-id="350" data-pid="0" data-url="/manageMingci/mingciList.im" data-tabid="mingci">名词大全管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[352] }">
								<li data-id="350" data-pid="0" data-url="/manageUpFile/upfileList.im" data-tabid="upfile">调查文件管理</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[2] }">
									<li data-id="2" data-pid="0">前端管理</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[311] }">
									<li data-id="311" data-pid="2" data-url="/syn/about.im?aboutId=1" data-tabid="about1">关于我们</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[312] }">
									<li data-id="312" data-pid="2" data-url="/syn/about.im?aboutId=2" data-tabid="about2">用户协议</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[343] }">
									<li data-id="343" data-pid="2" data-url="/syn/about.im?aboutId=3" data-tabid="about3">隐私及服务条款</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[313] }">
									<li data-id="313" data-pid="2" data-url="/syn/versionList.im" data-tabid="versionList">版本信息</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[314] }">
									<li data-id="314" data-pid="2" data-url="/syn/bannerList.im" data-tabid="adList">万方文献首页banner</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[315] }">
									<li data-id="315" data-pid="0">万方文献</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[319] }">
									<li data-id="319" data-pid="315" data-url="/manageLiterature/literatureList.im" data-tabid="literature">万方文献统计</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[360] }">
									<li data-id="360" data-pid="315" data-url="/manageLiterature/literatureSearch.im" data-tabid="literaturesearch">万方文献检索</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[321] }">
									<li data-id="321" data-pid="315" data-url="/manageLiterature/keywordList.im" data-tabid="keyword">关键字统计</li>
								</c:if>
								
								
								<c:if test="${!empty sessionManagerRole[3] }">
									<li data-id="3" data-pid="0">会员管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[179] }">
									<li data-id="179" data-pid="3" data-url="/mm/memberList.im" data-tabid="userlist">会员列表</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[181] }">
									<li data-id="181" data-pid="0">评论管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[310] }">
									<li data-id="310" data-pid="181" data-url="/comment/commentList.im" data-tabid="commentList">评论列表</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[7] }">
								<li data-id="310" data-pid="0" data-url="/manageInform/inform.im" data-tabid="inform">通知管理</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[6] }">
								<li data-id="6" data-pid="0" >基础数据管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[333] }">
								<li data-id="333" data-pid="6" data-url="/hd/hospitalGradeList.im" data-tabid="grade">等级职称列表</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[334] }">
									<li data-id="334" data-pid="6" data-url="/department/list.im" data-tabid="departmentlist">科室列表</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[337] }">
									<li data-id="337" data-pid="6" data-url="/syn/dirty.im" data-tabid="dirty">脏字库</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[338] }">
									<li data-id="338" data-pid="6" data-url="/syn/guide_list.im" data-tabid="guidelist">启动页/引导页</li>
								</c:if>
								
								<c:if test="${!empty sessionManagerRole[318] }">
									<li data-id="318" data-pid="0" >健康自测管理</li>
								</c:if>
								<c:if test="${!empty sessionManagerRole[320] }">
									<li data-id="320" data-pid="318" data-url="/managesubject/subject_list.im" data-tabid="subjectlist">诊断题库列表</li>
								</c:if>
							</ul>
						</div>
					</div>
					<div class="panelFooter">
						<div class="panelFooterContent"></div>
					</div>
				</div>
				<%--              <c:if test="${!empty sessionManagerRole[2] }">
                <div class="panel panel-default">
                    <div class="panel-heading panelContent">
                        <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse1" class="active"><i class="fa fa-caret-square-o-right"></i>&nbsp;系统信息</a></h4>
                    </div>
                    <div id="bjui-collapse1" class="panel-collapse panelContent collapse in">
                        <div class="panel-body">
                            <ul id="bjui-tree1" class="ztree ztree_main"  data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                            	<c:if test="${!empty sessionManagerRole[8] }"><li data-id="8" data-pid="2">操作员管理</li></c:if>
                                 <c:if test="${!empty sessionManagerRole[10] }"><li data-id="10" data-pid="8" data-url="/role/modules.im" data-tabid="module">部门管理</li></c:if>
                                <c:if test="${!empty sessionManagerRole[11] }"><li data-id="11" data-pid="8" data-url="/role/fr.im" data-tabid="role">角色管理</li></c:if>
                                <c:if test="${!empty sessionManagerRole[12] }"><li data-id="12" data-pid="8" data-url="/m2/g1.im" data-tabid="manager">操作员信息</li></c:if>
                                <li data-id="41" data-pid="1" data-url="/baselog/gs.im" data-tabid="manager">操作记录</li>
                            	<c:if test="${!empty sessionManagerRole[13] }"><li data-id="13" data-pid="2">栏目设置</li></c:if>
                            	<c:if test="${!empty sessionManagerRole[14] }"><li data-id="14" data-pid="13" data-url="/menu/fa.im" data-tabid="menu">菜单管理</li></c:if>
                            	<c:if test="${!empty sessionManagerRole[15] }"><li data-id="15" data-pid="13" data-url="/content/fc.im" data-tabid="content">内容管理</li></c:if>
                         		<c:if test="${!empty sessionManagerRole[19] }"> <li data-id="19" data-pid="2">系统消息管理</li></c:if>
                           		<c:if test="${!empty sessionManagerRole[20] }"><li data-id="20" data-pid="19"  data-url="/adminmessage/findmessage.im" data-tabid="message">消息管理</li></c:if>
                       			<c:if test="${!empty sessionManagerRole[21] }"> <li data-id="21" data-pid="2">日志管理</li></c:if>
                           		<c:if test="${!empty sessionManagerRole[22] }"><li data-id="22" data-pid="21"  data-url="/sc/fs.im" data-tabid="systemlog">系统日志</li></c:if>
                         </ul>
                        </div>
                    </div>
                    <div class="panelFooter"><div class="panelFooterContent"></div></div>
                </div>
                </c:if>
                 --%>
			</div>
		</div>
	</div>
	<div id="bjui-container">
		<div id="bjui-navtab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent">
					<ul class="navtab-tab nav nav-tabs">
						<li data-tabid="main" class="main active"><a
							href="javascript:;"><span><i class="fa fa-home"></i>
									#maintab#</span>
						</a>
						</li>
					</ul>
				</div>
				<div class="tabsLeft">
					<i class="fa fa-angle-double-left"></i>
				</div>
				<div class="tabsRight">
					<i class="fa fa-angle-double-right"></i>
				</div>
				<div class="tabsMore">
					<i class="fa fa-angle-double-down"></i>
				</div>
			</div>
			<ul class="tabsMoreList">
				<li><a href="javascript:;">#maintab#</a>
				</li>
			</ul>
			<div class="navtab-panel tabsPageContent layoutBox">
				<div class="page unitBox">
					<div class="bjui-pageFormContent" data-layout-h="0">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">账号信息</h3>
								</div>
								<div class="panel-body">
									<div style="float:left;">
										<img src="${sessionManager.headimg }" width="100">
									</div>
									<table class="table" style="width:400px;margin: 4px;">
										<tbody>
											<tr>
												<td width="100">操作员账号：</td>
												<td width="100">${sessionManager.username }</td>
											</tr>
											<tr>
												<td>操作员姓名:</td>
												<td>${sessionManager.name }</td>
											</tr>
											<tr>
												<td>操作员编号:</td>
												<td>${sessionManager.idkey }</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">最近登录</h3>
								</div>
								<div class="panel-body">
									<table class="table table-condensed table-striped" width="100%">
										<thead>
											<tr>
												<th class="center">时间</th>
												<th class="center">登录账号</th>
												<th class="center">IP</th>
												<th class="center">登录状态</th>
												<th class="center">描述</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="ls" items="${loginlogs }">
												<tr >
													<td class="center" style="padding-left:120px;"><fmt:formatDate
															value="${ls.createtime }" pattern="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td class="center" style="padding-left:50px;">${sessionManager.username }</td>
													<td class="center" style="padding-left:90px;">${ls.ip }</td>
													<td class="center" style="padding-left:50px;"><font color='green'>${ls.status==0?"成功":"失败"
															}</font>
													</td>
													<td class="center" style="padding-left:90px;">登录成功！</td>
												</tr>
												<tr>
											</c:forEach>

										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<footer id="bjui-footer">
		Copyright &copy; 2014 <a href="javascript:void()" target="_blank">鸿睿思博</a>
	</footer>
</body>
</html>