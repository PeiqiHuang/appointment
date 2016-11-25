function setCookie(c_name,value)
{
var exdate=new Date()
exdate.setDate(exdate.getDate()+365)
document.cookie=c_name+ "=" +escape(value)+ ";expires="+exdate.toGMTString()
}

//取回cookie
function getCookie(c_name)
{
if (document.cookie.length>0)
  {
  c_start=document.cookie.indexOf(c_name + "=")
  if (c_start!=-1)
	{ 
	c_start=c_start + c_name.length+1 
	c_end=document.cookie.indexOf(";",c_start)
	if (c_end==-1) c_end=document.cookie.length
	return unescape(document.cookie.substring(c_start,c_end))
	} 
  }
return ""
}


// product id info
function getLocal() {
	var cookieStr = getCookie("appointment_selected_products");
	if (cookieStr == null || cookieStr == "" || cookieStr == "[]") {
		return [];
	}
	return JSON.parse(cookieStr);
}

function saveLocal(ids) {
	setCookie("appointment_selected_products", JSON.stringify(ids));
}

function delLocal(id) {
	var arr = getLocal();
	var newArr = [];
	$.each(arr, function(i){
		if (this != id) {
			newArr.push(arr[i]);
		}
	})
	saveLocal(newArr);
}

function clearLocal() {
	saveLocal([]);
}

// post info
function getPost() {
	var cookieStr = getCookie("appointment_post_infos");
	if (cookieStr == null || cookieStr == "" || cookieStr == "{}") {
		return {"name":"", "phone":"", "address":""};
	}
	return JSON.parse(cookieStr);
}

function savePost(person) {
	setCookie("appointment_post_infos", JSON.stringify(person));
}