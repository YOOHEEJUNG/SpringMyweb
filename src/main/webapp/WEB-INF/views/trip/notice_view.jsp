<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div id="container">
	<!-- location_area -->
	<div class="location_area customer">
		<div class="box_inner">
			<h2 class="tit_page">
				TOURIST <span class="in">in</span> TOUR
			</h2>
			<p class="location">
				고객센터 <span class="path">/</span> 공지사항
			</p>
			<ul class="page_menu clear">
				<li><a href="#" class="on">공지사항</a></li>
				<li><a href="#">문의하기</a></li>
			</ul>
		</div>
	</div>
	<!-- //location_area -->

	<!-- bodytext_area -->
	<!-- 
		삭제 시는 post로 동작하는데
		hidden 이용해서 삭제에 필요한 키값을 전달해준다
		js를 이용해서 form 전송
	 -->
	 
	<form action="deleteForm" method="post" name="actionForm">
	
	<input type="hidden" name="tno" value="${vo.tno }">
	
	<div class="bodytext_area box_inner">
		<ul class="bbsview_list">
			<li class="bbs_title">${vo.title }</li>
			<li class="bbs_hit">
				작성일 : 
				<span>
				 	<fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/>
				 </span>
			</li>
			<li class="bbs_date">작성자 : <span>${vo.writer }</span></li>
			<li class="bbs_content">
				<div class="editer_content">${vo.content }</div>
			</li>
		</ul>
		<p class="btn_line txt_right">
			<a href="notice_modify?tno=${vo.tno }" class="btn_bbs">글수정</a> 
			<a href="javascript:;"class="btn_bbs" onclick="noticeDelete()">글삭제</a> 
			<a href="notice_list" class="btn_bbs">목록</a>
		</p>
		<ul class="near_list mt20">
		
			<!-- 
				1. 글이 2개인 경우 - 이전글 < 현재글인 경우 이전글
				2. 글이 1개인 경우 - 리스트 길이가 1이고, 다음글 < 현재글인 경우는 다음글이 없는 경우
			 -->
			 
		
				<c:forEach var="data" items="${Tlist }">
				
					<c:if test="${fn:length(Tlist) == 1 and data.tno < vo.tno }">
						<li><h4 class="prev">다음글</h4>은 없습니다</li>
					 </c:if>
				
					<c:if test="${data.tno > vo.tno }">
					<li><h4 class="prev">다음글 ${data.tno }</h4><a href="notice_view?tno=${ data.tno }">${data.title }</a></li></c:if>
					
					<c:if test="${data.tno < vo.tno }">
					 <li><h4 class="next">이전글 ${data.tno }</h4><a href="notice_view?tno=${data.tno }">${data.title }</a></li></c:if>
				
					<c:if test="${fn:length(Tlist) == 1 and data.tno > vo.tno }">
						<li><h4 class="prev">이전글</h4>은 없습니다</li>
					 </c:if>
				
				</c:forEach>

		
		
		</ul>
	</div>
	</form>
	<!-- //bodytext_area -->

</div>
<!-- //container -->

<script>

 
	function noticeDelete(){
		//a링크 고유이벤트 중지
		event.preventDefault();
		
		if(confirm("정말 지울 거...야..? 이렇게 버릴 거야...?😭")){
			//폼 형식으로 삭제
			document.actionForm.submit();
		}
			
	}
		
</script>
