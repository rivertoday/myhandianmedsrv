<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bjui-pageContent">
    <form action="/manageLiterature/literatureDetail.im" id="memberForm" class="pageForm" method="post" data-toggle="validate">
        
        <input type="hidden" name="articleId" value="${!empty articleId?articleId:literature.articleId}" >
        
        <div class="pageFormContent" data-layout-h="0">
            <table class="table table-condensed table-hover" width="100%">
                <tbody>
                	<tr>
                        <td>
                            <label for="title" class="control-label x85">标题：</label>
                            <input type="text" name="title" id="title" value="${literature.title }"  data-rule="required"  size="50">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="title" class="control-label x85">关键词：</label>
                            <input type="text" name="title" id="title" value="${literature.keywords }"  data-rule="required"  size="50">
                        </td>
                    </tr>
                	<tr>
                        <td>
                            <label for="issue" class="control-label x85">期刊号：</label>
                            <input type="text" name="issue" id="issue" value="${literature.source }"  data-rule="required"  size="50">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            <label for="issue" class="control-label x85">日期：</label>
                            <input type="text" name="issue" id="issue" value="${literature.year }"  data-rule="required"  size="50">
                        </td>
                        
                    </tr>
                    
                    <tr>
                        <td>
                            <label for="issue" class="control-label x85">卷目：</label>
                            <input type="text" name="issue" id="issue" value="${literature.volume}"  data-rule="required"  size="50">
                        </td>
                        
                    </tr>
                    
                    <tr>
                        <td>
                            <label for="issue" class="control-label x85">所在页码：</label>
                            <input type="text" name="issue" id="issue" value="${literature.page}"  data-rule="required"  size="50">
                        </td>
                        
                    </tr>

                    <tr>
                        <td>
                            <label for="author" class="control-label x85">作者：</label>
                            <input type="text" name="author" id="author" value="${literature.creator}"  data-rule="required" size="50">
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="summary" class="control-label x85">摘要</label>
                        	<textarea rows="10" cols="80" name="summary" id="summary" data-rule="required">${literature.abstracts}</textarea>
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