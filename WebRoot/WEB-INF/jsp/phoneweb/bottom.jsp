<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<footer class="fposf fixedFooter">
	<nav class="ducternav">
			<ul>
				<li><a href="/phoneweb/literature/homepage.html" <c:if test="${bottom == 1 }">class="current_nava"</c:if>><img src="/phoneweb/images/f_footer1.png"><img src="/phoneweb/images/f_footer01.png">万方文献</a></li>
				<li><a href="/phoneweb/product/list.html" <c:if test="${bottom == 2 }">class="current_nava"</c:if>><img src="/phoneweb/images/f_footer4.png"><img src="/phoneweb/images/f_footer04.png">汉典产品</a></li>
				<li><a href="/phoneweb/subject/list.html" <c:if test="${bottom == 3 }">class="current_nava"</c:if>><img src="/phoneweb/images/f_footer2.png"><img src="/phoneweb/images/f_footer02.png">健康自测</a></li>
				<li><a href="/phoneweb/user/usercenter.html" <c:if test="${bottom == 4 }">class="current_nava"</c:if>><img src="/phoneweb/images/f_footer3.png"><img src="/phoneweb/images/f_footer03.png">个人中心</a></li>
			</ul>
	</nav>
</footer>
