package com.hrsb.cg.controller.manage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrsb.cg.model.Comment;
import com.hrsb.cg.service.CommentService;
import com.hrsb.cg.util.AjaxUtil;
import com.hrsb.cg.util.Page;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/commentList")
	public ModelAndView commentList(@RequestParam(defaultValue="1",required=false)Integer pageCurrent,
			@RequestParam(defaultValue="15",required=false)Integer pageSize,HttpServletRequest request){
		ModelAndView mv=new ModelAndView("/manage/comment/comment-list");
		Page<Comment> page=new Page<Comment>(pageCurrent, pageSize);
		page.setOrderDirection("desc");
		page.setOrderField("comm.create_time");
		List<Comment> comments=commentService.selectByPage(page);
		page.setResults(comments);
		mv.addObject("page", page);
		return mv;
	}
	
	@RequestMapping(value="/commentDel")
	public void commentDel(@RequestParam(value="commentId")Long commentId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		int i=commentService.delById(commentId);
		if(i==1){
			new AjaxUtil(request, response).JsonType("200", "删除成功！", "", "commentList", "", "", "保存成功！",false);
		}else{
			new AjaxUtil(request, response).JsonType("300", "删除失败！", "", "commentList", "", "", "保存失败",false);	
		}
	}
	
}
