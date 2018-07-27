<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
function changeZtree(t) {
    var $panel = $.CurrentNavtab
    var $t     = $('#ztree1'),
        $span  = $('#ztree-'+ t),
        $log   = $('#ztree-log'),
        op     = $t.data()
    
    if (t != 7)
        $.fn.zTree.destroy('ztree1')
    
    switch(t) {
        case 1:
            var edit   = op.showRenameBtn ? false : true,
                rename = op.showRenameBtn ? false : true,
                remove = op.showRemoveBtn ? false : true
                
            $t.data('editEnable', edit).data('showRenameBtn', rename).data('showRemoveBtn', remove)
            
            $log.val('data-edit-enable="'+ edit +'"\r\n' + 'data-show-rename-btn="'+ rename +'"\r\n' + 'data-show-remove-btn="'+ remove +'"')
            $span.text(($span.text() == '无') ? '显示' : '无')
            break
        case 2:
            var add    = op.addHoverDom ? null : 'edit',
                remove = op.removeHoverDom ? null : 'edit'
            
            $t.data('addHoverDom', add).data('removeHoverDom', remove)
            
            $log.val('data-add-hover-dom="'+ add +'"\r\n' + 'data-remove-hover-dom="'+ remove +'"')
            $span.text(($span.text() == '无') ? '显示' : '无')
            break
        case 3:
            var check = op.checkEnable ? false : true
            
            $t.data('checkEnable', check)
            
            $log.val('data-check-enable="'+ check +'"')
            $span.text(($span.text() == '无') ? '显示' : '无')
            break
        case 4:
            var edit   = op.editEnable ? true : true,
                bDrag  = op.beforeDrag ? '' : 'M_BeforeNodeDrag',
                bDrop  = op.beforeDrop ? '' : 'M_BeforeNodeDrop',
                onDrop = op.onDrop ? '' : 'M_NodeDrop'
            
            $t.data('editEnable', edit).data('beforeDrag', bDrag).data('beforeDrop', bDrop).data('onDrop', onDrop)
            
            $log.val('data-edit-enable="'+ edit +'"\r\n' + 'data-before-drag="'+ bDrag +'"\r\n' + 'data-before-drop="'+ bDrop +'"\r\n' + 'data-on-drop="'+ onDrop +'"')
            $span.text(($span.text() == '无') ? 'M_BeforeNodeDrag、M_BeforeNodeDrop、M_NodeDrop' : '无')
            break
        case 5:
            var add    = op.beforeRemove ? null : 'edit',
                remove = op.beforeRemove ? null : 'edit',
                bR     = op.beforeRemove ? null : 'M_BeforeRemove'
                oR     = op.onRemove ? null : 'M_NodeRemove'
            
            $t.data('addHoverDom', add).data('removeHoverDom', remove).data('beforeRemove', bR).data('onRemove', oR)
            
            $log.val('data-add-hover-dom="'+ add +'"\r\n' + 'data-remove-hover-dom="'+ remove +'"\r\n' + 'data-before-remove="'+ bR +'"\r\n' + 'data-on-remove="'+ oR +'"')
            $('#ztree-2').text(($('#ztree-2').text() == '无') ? '显示' : '无')
            $span.text(($span.text() == '无') ? 'M_BeforeRemove、M_NodeRemove' : '无')
            break
        case 6:
            var diy = op.addDiyDom ? null : 'M_AddDiyDom'
            
            $t.data('addDiyDom', diy)
            
            $log.val('data-add-diy-dom="'+ diy +'"')
            $span.text(($span.text() == '无') ? 'M_AddDiyDom' : '无')
            break
        case 7:
            var tree = $.fn.zTree.getZTreeObj("ztree1")
            var menus = tree.getNodes()
            $log.val(JSON.stringify(menus))
            break
    }
    $panel.initui()
}
//单击事件
function ZtreeClick(event, treeId, treeNode) {
    var $detail = $('#ztree-detail')
    
    if ($detail.attr('tid') == treeNode.tId) return
	if (treeNode.name) $('#j_menu_name').val(treeNode.name)
	if (treeNode.url) {
		$('#j_menu_url').val(treeNode.url)
	} else {
		$('#j_menu_url').val('')
	}
	if (treeNode.tabid) {
		$('#j_menu_tabid').val(treeNode.tabid)
	} else {
		$('#j_menu_tabid').val('')
	}
	if (treeNode.target) {
		$('#j_menu_target').selectpicker('val', treeNode.target)
	} else {
		$('#j_menu_target').selectpicker('val', '')
	}
	$detail.attr('tid', treeNode.tId)
    $detail.show()
}
//保存属性
function M_Ts_Menu() {
	var zTree  = $.fn.zTree.getZTreeObj("ztree1")
	var name   = $('#j_menu_name').val()
	var url    = $('#j_menu_url').val()
	var tabid  = $('#j_menu_tabid').val()
	var target = $('#j_menu_target').val()
	
	if ($.trim(name).length == 0) {
		alertMsg.error('菜单名称不能为空！')
		return;
	}
	var upNode = zTree.getSelectedNodes()[0]
	
	if (!upNode) {
		alertMsg.error('未选中任何菜单！')
        return
	}
	upNode.name   = name
	upNode.url   = url
	upNode.tabid    = tabid
	upNode.target = target
	zTree.updateNode(upNode)
}
//
function M_BeforeNodeDrag(treeId, treeNodes) {
    var $log = $('#ztree-log')
    $log.val('开始拖拽：'+ treeNodes[0].name +'！\n'+ $log.val())
    return true
}
//监听拖拽事件
function M_BeforeNodeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
    /*禁止插入层级为2的节点*/
    if (moveType == 'inner' && targetNode.level == 2) {
        return false
    }
    /*禁止插入剩余层级不足的子节点*/
    if (moveType == 'inner' && treeNodes[0].isParent) {
        var molevel = 2 - targetNode.level //剩余层级
        var maxlevel = 1
        var zTree = $.fn.zTree.getZTreeObj("ztree1")
        var nodes = zTree.transformToArray(treeNodes)
        var level = nodes[0].level
        
        for (var i = 1; i < nodes.length; i++) {
            if (nodes[i].level == (level + 1)) {
                maxlevel++
                level++
            }
        }
        if (maxlevel > molevel) {
            return false
        }
    }
    return true
}
//拖拽结束事件
function M_NodeDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
    var $log = $('#ztree-log')
    $log.val('拖拽结束！\n'+ $log.val())
}
//删除前事件
function M_BeforeRemove(treeId, treeNode) {
    var $log = $('#ztree-log')
    $log.val('准备删除：'+ treeNode.name +'！\n'+ $log.val())
    return true
}
//删除结束事件
function M_NodeRemove(event, treeId, treeNode) {
    var $log = $('#ztree-log')
    $log.val('删除成功！\n'+ $log.val())
}
//自定义DOM
function M_AddDiyDom(treeId, treeNode) {
    var aObj = $('#' + treeNode.tId + '_a')
    
	if ($('#diyBtn_'+ treeNode.id).length > 0) return
	aObj.append('<button type="button" class="diyBtn1" id="diyBtn_' + treeNode.id +'" title="'+ treeNode.name +'" onfocus="this.blur();"><i class="fa fa-plane"></i></button>')
	$('#diyBtn_'+ treeNode.id).bind('click', function() {$(this).alertmsg('info', (treeNode.name +' 的飞机！'))})
}
</script>
<div class="bjui-pageContent">
    <div class="pageFormContent" data-layout-h="0">
        <div style="float:left; margin:5px 5px 0; width:550px;">
            <fieldset>
                <legend>树型菜单</legend>
                <div class="clearfix">
                    <div style="float:left; width:220px; height:240px; overflow:auto;">
                        <ul id="ztree1" class="ztree" data-toggle="ztree" data-expand-all="true" data-on-click="ZtreeClick">
                            <li data-id="1" data-pid="0">表单元素</li>
                            <li data-id="10" data-pid="1" data-url="form-button.html" data-tabid="form-button">按钮</li>
                            <li data-id="11" data-pid="1" data-url="form-input.html" data-tabid="form-input">文本框</li>
                            <li data-id="12" data-pid="1" data-url="form-select.html" data-tabid="form-select">下拉选择框</li>
                            <li data-id="13" data-pid="1" data-url="form-checkbox.html" data-tabid="table">复选、单选框</li>
                            <li data-id="14" data-pid="1" data-url="form.html" data-tabid="form">表单综合演示</li>
                            <li data-id="2" data-pid="0">表格</li>
                            <li data-id="20" data-pid="2" data-url="table.html" data-tabid="table">普通表格</li>
                            <li data-id="21" data-pid="2" data-url="table-fixed.html" data-tabid="table-fixed">固定表头表格</li>
                            <li data-id="22" data-pid="2" data-url="table-edit.html" data-tabid="table-edit">可编辑表格</li>
                        </ul>
                    </div>
                    <div id="ztree-detail" style="display:none; margin-left:230px; width:300px; height:240px;">
                        <div class="bs-example" data-content="详细信息">
                            <div class="form-group">
                                <label for="j_menu_name" class="control-label x85">菜单名称：</label>
                                <input type="text" class="form-control validate[required] required" name="m.name" id="j_menu_name" size="15" placeholder="名称" />
                            </div>
                            <div class="form-group">
                                <label for="j_menu_url" class="control-label x85">URL：</label>
                                <input type="text" class="form-control" name="m.url" id="j_menu_url" size="15" placeholder="Url" />
                            </div>
                            <div class="form-group">
                                <label for="j_menu_tabid" class="control-label x85">tabid：</label>
                                <input type="text" class="form-control" name="m.tabid" id="j_menu_tabid" size="15" placeholder="tabid" />
                            </div>
                            <div class="form-group">
                                <label for="j_menu_target" class="control-label x85">target：</label>
                                <select class="selectpicker show-tick" id="j_menu_target" data-style="btn-default btn-sel" data-width="auto">
                                    <option value=""></option>
                                    <option value="navTab">navTab</option>
                                    <option value="dialog">dialog</option>
                                </select>
                            </div>
                            <div class="form-group" style="padding-top:8px; border-top:1px #DDD solid;">
                                <label class="control-label x85"></label>
                                <button class="btn btn-green" onclick="M_Ts_Menu();">更新菜单</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix" style="margin-top:20px;">
                    <div style="float: left; width: 220px;">
                        <p><label class="control-label x120">默认编辑删除按钮：</label><span id="ztree-1" class="label label-default">无</span></p>
                        <p><label class="control-label x120">自定义添/删按钮：</label><span id="ztree-2" class="label label-default">无</span></p>
                        <p><label class="control-label x120">复选框：</label><span id="ztree-3" class="label label-default">无</span></p>
                        <p><label class="control-label x120">拖拽事件：</label><span id="ztree-4" class="label label-default">无</span></p>
                        <p><label class="control-label x120">删除事件：</label><span id="ztree-5" class="label label-default">无</span></p>
                        <p><label class="control-label x120">自定义DOM：</label><span id="ztree-6" class="label label-default">无</span></p>
                    </div>
                    <div style="margin-left:230px;">
                        <textarea style="width:300px; font-size:12px;" rows="6" id="ztree-log"></textarea>
                    </div>
                </div>
                <hr>
                <div class="btn-group" style="margin-top:5px;">
                    <button type="button" class="btn-default btn-sm" onclick="changeZtree(1);">默认编/删按钮</button>
                    <button type="button" class="btn-default btn-sm" onclick="changeZtree(2);">自定义添/删按钮</button>
                    <button type="button" class="btn-default btn-sm" onclick="changeZtree(3);">复选框</button>
                    <button type="button" class="btn-default btn-sm" onclick="changeZtree(4);">拖拽</button>
                    <button type="button" class="btn-default btn-sm" onclick="changeZtree(5);">删除</button>
                    <button type="button" class="btn-default btn-sm" onclick="changeZtree(6);">自定义DOM</button>
                    <button type="button" class="btn-orange btn-sm" style="float: right;" onclick="changeZtree(7);">提交树</button>
                </div>
                <hr>
            </fieldset>
        </div>
        <div style="margin-right:10px; margin-top:5px; margin-left:570px;">
            <fieldset>
                <legend>参数信息</legend>
                <h5>属性：如[data-edit-enable]，大写字母前加中横线，才会自动转换为驼峰格式属性[editEnable]</h5>
                <table class="table table-condensed table-striped table-hover">
                    <thead>
                        <tr>
                            <th>属性名称</th>
                            <th>类型、参数</th>
                            <th>默认值</th>
                            <th>描述信息</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>expandAll</td>
                            <td>Boolean</td>
                            <td>false</td>
                            <td>展开整棵树</td>
                        </tr>
                        <tr>
                            <td>simpleData</td>
                            <td>Boolean</td>
                            <td>true</td>
                            <td>使用简单JSON数据</td>
                        </tr>
                        <tr>
                            <td>addHoverDom</td>
                            <td>function(treeId, treeNode)</td>
                            <td>null</td>
                            <td>鼠标放到节点上时，显示的自定义DOM，函数可选字符串"edit"：显示自定义的添加和删除按钮，删除按钮点击时有确认事件，如果定义了删除事件[onRemove]，则删除时将调用该事件，否则直接删除</td>
                        </tr>
                        <tr>
                            <td>removeHoverDom</td>
                            <td>function(treeId, treeNode)</td>
                            <td>null</td>
                            <td>鼠标离开节点时，隐藏自定义DOM，函数可选字符串"edit"：隐藏自定义的添加和删除按钮，addHoverDom和removeHoverDom需要同时出现</td>
                        </tr>
                        <tr>
                            <td>maxAddLevel</td>
                            <td>Int</td>
                            <td>2</td>
                            <td>允许添加的最大子节点深度，仅使用默认的{"addHoverDom":"edit"}时有效，level > maxAddLevel 的节点上将不会显示添加按钮。</td>
                        </tr>
                        <tr>
                            <td>addDiyDom</td>
                            <td>function(treeId, treeNode)</td>
                            <td>false</td>
                            <td>用于在节点上固定显示用户自定义DOM</td>
                        </tr>
                        <tr>
                            <td>editEnable</td>
                            <td>Boolean</td>
                            <td>false</td>
                            <td>允许编辑节点</td>
                        </tr>
                        <tr>
                            <td>showRemoveBtn</td>
                            <td>Boolean</td>
                            <td>false</td>
                            <td>显示默认的编辑按钮</td>
                        </tr>
                        <tr>
                            <td>showRenameBtn</td>
                            <td>Boolean</td>
                            <td>false</td>
                            <td>显示默认的删除按钮</td>
                        </tr>
                        <tr>
                            <td>checkEnable</td>
                            <td>Boolean</td>
                            <td>false</td>
                            <td>设置 zTree 的节点上是否显示 checkbox / radio</td>
                        </tr>
                        <tr>
                            <td>chkStyle</td>
                            <td>String</td>
                            <td>checkbox</td>
                            <td>勾选框类型(checkbox 或 radio）[ {"checkEnable":true} 生效 ]</td>
                        </tr>
                        <tr>
                            <td>radioType</td>
                            <td>String</td>
                            <td>all</td>
                            <td>radio 的分组范围，参数'all'：整棵树为一个分组，参数'level'：每一节点范围当作一个分组</td>
                        </tr>
                        <tr>
                            <td>onClick</td>
                            <td>function(event, treeId, treeNode, clickFlag)</td>
                            <td>null</td>
                            <td>用于捕获节点被点击的事件回调函数</td>
                        </tr>
                        <tr>
                            <td>beforeDrag</td>
                            <td>function(treeId, treeNodes)</td>
                            <td>null</td>
                            <td>用于捕获节点被拖拽之前的事件回调函数，并且根据返回值确定是否允许开启拖拽操作<br>
                                拖拽需要开启(editEnable)：[ {"editEnable":true} 生效 ]
                            </td>
                        </tr>
                        <tr>
                            <td>beforeDrop</td>
                            <td>function(treeId, treeNodes, targetNode, moveType, isCopy)</td>
                            <td>null</td>
                            <td>用于捕获节点拖拽操作结束之前的事件回调函数，并且根据返回值确定是否允许此拖拽操作</td>
                        </tr>
                        <tr>
                            <td>onDrop</td>
                            <td>function(event, treeId, treeNodes, targetNode, moveType, isCopy)</td>
                            <td>null</td>
                            <td>用于捕获节点拖拽操作结束的事件回调函数</td>
                        </tr>
                        <tr>
                            <td>beforeRemove</td>
                            <td>function(treeId, treeNode)</td>
                            <td>null</td>
                            <td>用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作。</td>
                        </tr>
                        <tr>
                            <td>onRemove</td>
                            <td>function(event, treeId, treeNode)</td>
                            <td>null</td>
                            <td>用于捕获删除节点之后的事件回调函数。</td>
                        </tr>
                        <tr>
                            <td>onCheck</td>
                            <td>function(event, treeId, treeNode)</td>
                            <td>null</td>
                            <td>用于捕获 checkbox / radio 被勾选 或 取消勾选的事件回调函数</td>
                        </tr>
                    </tbody>
                </table>
                <hr>
                <h5>nodes结构：(简单JSON数据)： </h5>
                <pre>&lt;li data-id=&quot;13&quot; data-pid=&quot;1&quot; data-url=&quot;form-checkbox.html&quot; data-tabid=&quot;table&quot;&gt;复选、单选框&lt;/li&gt;</pre>
                <hr>
                <h5>更多API及DEMO：<a href="http://www.ztree.me/" target="_blank">http://www.ztree.me/</a></h5>
            </fieldset>
        </div>
    </div>
    <div class="bjui-footBar">
        <ul>
            <li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
        </ul>
    </div>
</div>