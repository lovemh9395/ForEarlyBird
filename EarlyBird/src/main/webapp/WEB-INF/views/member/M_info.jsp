<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
<!--   Core   -->
<%@ include file="../include/core.jsp"%>
<!--  end Core -->
<script>
	$(document).ready(function() {
		$("#gender_man").click(function() {
			$("#mem_gender_select").val("남자");
			$("#mem_gender").val("0");
			$("#mem_gender").readOnly(true);
		})
		$("#gender_girl").click(function() {
			$("#mem_gender_select").val("여자");
			$("#mem_gender").val("1");
			$("#mem_gender").readOnly(true);
		})
		if ($("#mem_gender_select").val() == 1) {
			$("#mem_gender_select").val("여자");
		}
		if ($("#mem_gender_select").val() == 0) {
			$("#mem_gender_select").val("남자");
		}
	});

	$(document).ready(function() {
		$("#M_delete").click(function() {
			var result = confirm("회원탈퇴를 하시겠습니까?");

			if (result) {
				M_delete();
			} else {
				alert("취소되었습니다.");
			}
		})
	})

	function updateChecking() {
		var exppass = /^[a-zA-Z0-9]{7,15}$/;
		if (!exppass.test($("#update_mem_password").val())) {
			alert("비밀번호는 8~15자의 영문,숫자만 허용됩니다.");
			return false;
		}
		var exppass = /^[a-zA-Z0-9]{7,15}$/;
		if (!exppass.test($("#update_mem_password2").val())) {
			alert("비밀번호는 8~15자의 영문,숫자만 허용됩니다.");
			return false;
		}
		if ($("#update_mem_password").val() != $("#update_mem_password2").val()) {
			alert("비밀번호가 같지 않습니다.");
			$("#update_mem_password").val("");
			$("#update_mem_password2").val("");
			$("#update_mem_password").focus();
			return false;
		}
		return true;
	}

	$(document).ready(function() {
		$("#update").click(function() {
			if (updateChecking()) {
				var result = confirm("회원정보를 변경하시겠습니까?");

				if (result) {
					$("#infoupdate").submit();
					alert("회원님의 정보가 변경되었습니다.");
				} else {
					alert("취소되었습니다.");
				}
			}
		})
	})

	function M_delete(mem_userid) {
		$.ajax({
			async : false,
			type : "post",
			url : "${contextPath}/member/M_delete",
			data : {
				"id" : mem_userid
			},
			success : function(data) {
				alert("회원탈퇴 되었습니다.");
				location.href = "${contextPath}/"
			}
		})
	}
</script>
<script>
	function execPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 도로명 조합형 주소 변수

				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				}
				// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				console.log(data.zonecode);
				console.log(fullRoadAddr);

				$("[name=mem_zipcode]").val(data.zonecode);
				$("[name=mem_address1]").val(fullRoadAddr);
				$("[name=mem_address2]").val('');
				$("[name=mem_address2]").focus();

				/* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
				document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
				document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */
			}
		}).open();
	}
</script>
<body class="">
	<!-- side bar -->
	<%@ include file="../include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="../include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="../include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<div class="container-fluid mt-7">
			<div class="row">
				<div class="col-xl-4 order-xl-2 mb-5 mb-xl-0">
					<div class="card card-profile shadow">
						<div class="row justify-content-center">
							<div class="col-lg-3 order-lg-2">
								<div class="card-profile-image">
									<a href="#"> <img
										src="/resources/uploadimage/${profilephoto}"
										class="rounded-circle">
									</a>
								</div>
							</div>
						</div>
						<div
							class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
							<div class="d-flex justify-content-between">
								<div class="col-md-auto"><%@ include
										file="../member/M_profileUpload.jsp"%></div>
								<a href="#" class="btn btn-sm btn-default float-right">추천하기</a>
							</div>
						</div>
						<div class="card-body pt-0 pt-md-4">
							<div class="row">
								<div class="col">
									<div
										class="card-profile-stats d-flex justify-content-center mt-md-5">
										<div>
											<span class="heading">22</span> <span class="description">뭐넣을래</span>
										</div>
										<div>
											<span class="heading">10</span> <span class="description">쓴글</span>
										</div>
										<div>
											<span class="heading">89</span> <span class="description">쓴댓글</span>
										</div>
									</div>
								</div>
							</div>
							<div class="text-center">
								<h3>
									${info.mem_username }<span class="font-weight-light">,
										${info.mem_birthday }</span>
								</h3>
								<div class="h5 font-weight-300">
									<i class="ni location_pin mr-2"></i> ${info.mem_address1}
								</div>
								<div class="h5 mt-4">
									<i class="ni business_briefcase-24 mr-2"></i> 여기는 뭐 넣을까
								</div>
								<div>
									<i class="ni education_hat mr-2"></i> 여기도 뭐 넣을까
								</div>
								<hr class="my-4" />
								<p>여기는 오늘의 한마디</p>
							</div>
						</div>
					</div>
					<div class="text-right" align="right">
						<button type="button" class="btn btn-sm btn-primary" id="M_delete">회원탈퇴</button>
					</div>
				</div>
				<div class="col-xl-8 order-xl-1">
					<div class="card bg-secondary shadow">
						<div class="card-header bg-white border-0">
							<div class="row align-items-center">
								<div class="col-8">
									<h3 class="mb-0">내 정보</h3>
								</div>
								<div class="col-4 text-right">
									<button type="button" class="btn btn-sm btn-primary"
										id="update">변경하기</button>
								</div>
							</div>
						</div>
						<div class="card-body">
							<form id="infoupdate" method="post"
								action="${contextPath }/member/M_update">
								<h6 class="heading-small text-muted mb-4">${user.name }님의
									상세정보</h6>
								<div class="pl-lg-4">
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="infouserid">아이디</label>
												<input type="text" id="update_mem_userid"
													name="update_mem_userid" style="disabled: true" readonly
													class="form-control form-control-alternative"
													placeholder="${info.mem_userid }"
													value="${info.mem_userid }">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="input-first-name">이름
												</label> <input type="text" id="infousername" name="infousername"
													class="form-control form-control-alternative" readonly
													placeholder="${info.mem_username }"
													value="${info.mem_username }">
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="input-last-name">닉네임
												</label> <input type="text" name="update_mem_nickname"
													style="disabled: true"
													class="form-control form-control-alternative"
													value="${info.mem_nickname }">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="input-first-name">비밀번호
												</label> <input type="password" id="update_mem_password"
													name="update_mem_password"
													class="form-control form-control-alternative"
													style="disabled: true" placeholder="비밀번호를 입력해주세요">
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<label class="form-control-label" for="input-last-name">비밀번호
													재입력 </label> <input type="password" id="update_mem_password2"
													name="update_mem_password2" style="disabled: true"
													class="form-control form-control-alternative"
													placeholder="비밀번호를 다시 입력해주세요">
											</div>
										</div>
									</div>
								</div>
								<hr class="my-4" />
								<!-- Address -->
								<h6 class="heading-small text-muted mb-4">연락처 정보</h6>
								<div class="pl-lg-4">
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												<label class="form-control-label" for="input-address">전화번호</label>
												<input name="update_mem_phone"
													class="form-control form-control-alternative"
													value="${info.mem_phone }" type="text">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-4">
											<div class="form-group">
												<label class="form-control-label" for="input-city">생일</label>
												<input type="text" id="infobirth" name="update_mem_birthday"
													style="disabled: false"
													class="form-control form-control-alternative"
													placeholder="${info.mem_birthday }"
													value="${info.mem_birthday }">
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<label class="form-control-label">성별</label> <input
													type="text" id="mem_gender_select" name="update_mem_gender"
													class="form-control form-control-alternative"
													style="align: left; cursor: pointer" data-toggle="dropdown"
													value="${info.mem_gender }"> <span class="caret"></span>
												<span class="sr-only"> </span>
												<ul class="dropdown-menu" role="menu">
													<li><a id="gender_man" style="cursor: pointer">남자</a></li>
													<li><a id="gender_girl" style="cursor: pointer">여자</a></li>
												</ul>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<label class="form-control-label">우편번호 </label> <input
													type="text" id="update_mem_zipcode"
													name="update_mem_zipcode"
													class="form-control form-control-alternative"
													value="${info.mem_zipcode }" onClick="execPostcode();"
													placeholder="우편번호">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-8">
											<div class="form-group">
												<label class="form-control-label">주소</label> <input
													name="update_mem_address1" id="update_mem_address1"
													class="form-control form-control-alternative"
													value="${info.mem_address1 }" placeholder="주소를 입력해주세요"
													onClick="execPostcode();" type="text">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="form-control-label">상세주소</label> <input
													name="update_mem_address2" id="update_mem_address2"
													class="form-control form-control-alternative"
													value="${info.mem_address2 }" placeholder="상세주소를 입력해주세요"
													type="text">
											</div>
										</div>
									</div>
								</div>

								<hr class="my-4" />
								<!-- Description -->
								<h6 class="heading-small text-muted mb-4">자기소개</h6>
								<div class="pl-lg-4">
									<div class="form-group">
										<label>자기소개</label>
										<textarea rows="4"
											class="form-control form-control-alternative"
											placeholder="A few words about you ..."
											id="update_mem_profile_content"
											name="update_mem_profile_content">${info.mem_profile_content }</textarea>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- footer -->
			<%@ include file="../include/main_footer.jsp"%>
			<!-- end footer -->
			<!-- end body -->
		</div>
</body>
</html>