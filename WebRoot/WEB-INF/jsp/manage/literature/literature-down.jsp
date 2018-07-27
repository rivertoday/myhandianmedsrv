<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="bjui-pageContent">
    <form action="/manageLiterature/literatureDetail.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        
        <input type="hidden" name="articleId" value="${!empty articleId?articleId:literature.articleId}" >
        
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="title" class="control-label x85">文章标题：</label>
                            <input type="text" name="title" id="title" value="${literature.title }"  data-rule="required"  size="50">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="title" class="control-label x85">下载链接：</label>
                            <c:if test="${fn:length(literature.spare1) > 0 }">
                            <input type="text" name="title" id="title" value="${literature.spare1 }"  data-rule="required"  size="80">
                            </c:if>
                            <c:if test="${fn:length(literature.spare1) <= 0 }">
                            抱歉，该文献没有提供全文下载，或者下载失败。
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                    <td>
                    请自行拷贝上述链接至下载软件或浏览器地址栏并回车，即可看到全文
                    </td>
                    </tr>
                	
                </tbody>
            </table>
        </div>
        <div class="bjui-footBar">
            <ul>
                <li><button type="button" class="btn-close" data-icon="close">取消</button></li>
            </ul>
        </div>
    </form>
</div>