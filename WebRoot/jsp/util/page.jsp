<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
    <div class="pagination  pagination-centered	">
      <ul id="page">
       	<s:if test="page.hasPrePage">
	        <li>
       	</s:if>
       	<s:else>
	        <li class="disabled">
       	</s:else>
       		<a href="javascript:void(0);" id="prev">上一页</a></li>
       		
        <s:iterator value="new int[page.totalPage]" status="i">
        	<s:if test="page.currentPage == (#i.index+1)">
		        <li class="active">
        	</s:if>
        	<s:else>
        		<li>
        	</s:else>
	        	<a href="javascript:void(0);"><s:property value="#i.index+1"/></a>
	        </li>
	    </s:iterator>
	    
       	<s:if test="page.hasNextPage">
	        <li>
       	</s:if>
       	<s:else>
	        <li class="disabled">
       	</s:else>
       		<a href="javascript:void(0);" id="next">下一页</a></li>
      </ul>
    </div>
    
    <script src="../../js/jquery.min.js"></script>
    <script>
    	$(document).ready(function(){
    		    		
    		$(document).on("click", "#page li a", function(){
    			if ($(this).parent().hasClass("disabled")) return;
    			if ($(this).parent().hasClass("active")) return;
    			var url = window.location.href;
    			var pattern = "?currentPage=";
    			var index = url.indexOf(pattern);
    			if (index > 0) {
    				url = url.substring(0, index);
    			}
    			var currentPage = $(this).text();
    			if ($(this).attr("id") == "prev") {
    				currentPage = parseInt(getCurrentPage()) - 1;
    			}
    			if ($(this).attr("id") == "next") {
    				currentPage = parseInt(getCurrentPage()) + 1;
    			}
    			// console.info(url + pattern + currentPage)
    			window.location.href = url + pattern + currentPage;
    		});
    	})
    	
    	function getCurrentPage() {
    		var currentPage = 1;
    		$("#page").children().each(function(i){
    			if ($(this).hasClass("active")) {
    				currentPage = i;
    			}
    			return;
    		});
    		// console.info(currentPage)
    		return currentPage;
    	}
    	
    	function setActive(index) {
    		$("#page li a").children().each(function(i){
    			if (index == i) {
    				$(this).addClass("active");
    				return;
    			}
    		});
    	
    	}
    </script>