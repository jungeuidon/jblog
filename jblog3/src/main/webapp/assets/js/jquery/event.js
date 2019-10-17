$(document).ready(function(){
	
	
	$(document).on("click", "#categorysub", categoryInsert);
	$(document).on("click", ".delCategory", categoryDel);
	
	$("#id").change(function(){
		$("#btn-checkid").show();
		$("#img-checkid").hide();
	});	
	
	
	
	$("#btn-checkid").click(function(){
		var id = $("#id").val();
		if(id == ""){
			alert("아이디를 입력해주세요.")
			return;
		}
	
		// ajax 통신
		$.ajax({
			url: "/jblog3/api/user/checkid?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result == "fail"){
					console.error(response.message);
					return;
				}
				
				if(response.data == true){
					alert("이미 존재하는 아이디입니다.");
					$("#id").val("");
					$("#id").focus();
					return;
				}
				
				$("#btn-checkid").hide();
				$("#img-checkid").show();
			},
			error:function(xhr, error) {
				console.error("error : " + error);
			}
		});
	});
});

function categoryInsert(){
	var userId = $("#userId").val();
	var name = $("#name").val();
	var info = $("#info").val();
	
//	alert("categoryInsert" + userId + " "+ name + " " + info)
	$.ajax({
		url: "/jblog3/api/category/categoryInsert",
		type: "post",
		dataType: "json",
		data: {"userId":userId, "name":name, "info":info},
		success: function(response){
			
			$(".admin-cat").remove();
			categoryList(userId);
			$("#name").val("");
			$("#info").val("");
		},
		error:function(xhr, error) {
			alert("에러발생")
//			console.error("error : " + error);
		}
	});
}

function categoryDel(){
	var userId = $("#userId").val();
	var clickObj = $(this);
	var no = clickObj.attr("id");
//	alert(no+ " " + userId);
	
	$.ajax({
		url: "/jblog3/api/category/categoryDel",
		type: "post",
		dataType: "json",
		data: {"userId":userId, "no":no},
		success: function(response){
			
			$(".admin-cat").remove();
			categoryList(userId);
			
		},
		error:function(xhr, error) {
			alert("에러발생")
//			console.error("error : " + error);
		}
	});
}

function categoryList(userId){
	
	var theDiv = $("#cartlist");
//	var theDiv = document.getElementById("cartlist");
//	alert("categoryList 유저아이디 : " + userId);
	$.ajax({

		url: "/jblog3/api/category/categoryList",
		type: "get",
		dataType: "json",
		data: {"userId":userId},
		success: function(response){
			
			var str ="<table class='admin-cat'>";
			str += "<tr><th>번호</th><th>카테고리명</th><th>포스트 수</th><th>설명</th><th>삭제</th></tr>";

//			$.each(data, function(index, response){ //반복문
//				str += "<tr>"
//				str += "<td>" + response.categoryVo.no + "</td>"
//				str += "<td>" + response.categoryVo.name + "</td>"
//				str += "<td>" + response.categoryVo.count + "</td>"
//				str += "<td>" + response.categoryVo.info + "</td>"
//				str += "<td><a href='#' class='delCategory' id='" + categoryVo.no + "'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td>";
//				str += "</tr>"
//			});
			
			
			
			for(var i =0; i< response.categoryList.length; i++){
				str += "<tr>"
					str += "<td>" + response.categoryList[i].no + "</td>"
					str += "<td>" + response.categoryList[i].name + "</td>"
					str += "<td>" + response.categoryList[i].count + "</td>"
					str += "<td>" + response.categoryList[i].info + "</td>"
					str += "<td><img id ='" + response.categoryList[i].no  + "' class='delCategory' src='/jblog3/assets/images/delete.jpg' style='cursor:pointer'></td>"
					str += "</tr>"
			}
			str +="</table>";
			theDiv.append(str);

		},
		error:function(xhr, error) {
			alert("에러발생 categoryList");
//			console.error("error : " + error);
		}
	});
}
