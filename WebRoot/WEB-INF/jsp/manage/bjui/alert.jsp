<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
function pic_form_upload_success(data) {
    var json = jQuery.parseJSON(data);
    DWZ.ajaxDone(json);
    if (json.statusCode == DWZ.statusCode.ok) {
        $('#j_form_pic').val(json.navTabId);
        $('#j_form_span_pic').html('<img src="'+ json.navTabId +'" width="100" />');
    }
}
</script>
<div class="bjui-pageContent">
    <div class="pageFormContent" data-layout-h="0">
        <div style="margin:15px auto 0; width:800px;">
            <fieldset>
                <legend>ä¿¡æ¯æç¤º</legend>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>æ ·ä¾</th>
                            <th>è°ç¨è¯´æ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <a href="javascript:;" class="btn btn-default" data-toggle="alertmsg" data-type="info" data-msg="ææ¯æ®éçæç¤ºä¿¡æ¯ï¼">ä¿¡æ¯æç¤º</a>
                            </td>
                            <td>$(selector).alertmsg('info', 'æ®éä¿¡æ¯çæç¤ºåå®¹ï¼')</td>
                        </tr>
                        <tr>
                            <td>
                                <a href="javascript:;" class="btn btn-default" data-toggle="alertmsg" data-type="correct" data-msg="ææ¯æ­£ç¡®çæç¤ºä¿¡æ¯ï¼">æ­£ç¡®æç¤º</a>
                            </td>
                            <td>$(selector).alertmsg('correct', 'æ­£ç¡®çæç¤ºåå®¹ï¼')</td>
                        </tr>
                        <tr>
                            <td>
                                <a href="javascript:;" class="btn btn-default" data-toggle="alertmsg" data-type="warn" data-msg="ææ¯è­¦åçæç¤ºä¿¡æ¯ï¼">è­¦åæç¤º</a>
                            </td>
                            <td>$(selector).alertmsg('warn', 'è­¦åçæç¤ºåå®¹ï¼')</td>
                        </tr>
                        <tr>
                            <td>
                                <a href="javascript:;" class="btn btn-default" data-toggle="alertmsg" data-type="error" data-msg="ææ¯éè¯¯çæç¤ºä¿¡æ¯ï¼">éè¯¯æç¤º</a>
                            </td>
                            <td>$(selector).alertmsg('error', 'éè¯¯çæç¤ºåå®¹ï¼')</td>
                        </tr>
                        <tr>
                            <td>
                                <a href="javascript:;" class="btn btn-default" data-toggle="alertmsg" data-type="confirm" data-msg="ææ¯ç¡®è®¤åå®¹çæç¤ºä¿¡æ¯ï¼">ç¡®è®¤æç¤º</a>
                            </td>
                            <td>$(selector).alertmsg('confirm', 'ç¡®è®¤åå®¹çæç¤ºä¿¡æ¯ï¼')</td>
                        </tr>
                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
    <div class="bjui-footBar">
        <ul>
            <li><button type="button" class="btn-close" data-icon="close">å³é­</button></li>
        </ul>
    </div>
</div>