<%@ page contentType="text/html; charset=UTF-8" %>
	
<script src="./js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if ("person" == localStorage.appointment_show_tab) {
			window.location.href = "./person/manage/show";
		} else if ("time" == localStorage.appointment_show_tab) {
			window.location.href = "./time/show";
		} else if ("order" == localStorage.appointment_show_tab) {
			window.location.href = "./product/manage/order";
		} else if ("product" == localStorage.appointment_show_tab) {
			window.location.href = "./product/manage/all";
		} else {
			window.location.href = "./person/manage/show";
		}
	})
</script>
