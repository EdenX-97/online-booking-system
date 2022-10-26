<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
<%--	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">--%>
<%--	<script src="https://cdn.staticfile.org/jquery/2.2.1/jquery.min.js"></script>--%>
<%--	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
<%--	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>--%>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>

	<link href="/css/font-awesome.min.css" rel="stylesheet">

	<style type="text/css">
		.fa{
			color: #02b2b5;
		}
		.carousel-inner img {
			width: 100%;
			height: 100%;
		}
		.title{text-align: center;}
		.line {
			border-bottom: 2px solid #02b2b5;
			background: none repeat scroll 0 0 transparent;
			height: 1px;
			margin: 0 auto 45px;
			padding: 5px;
			position: relative;
			width: 120px;
		}
		.line:after, .line::before {
			border: medium solid transparent;
			content: " ";
			height: 0;
			left: 50%;
			pointer-events: none;
			position: absolute;
			top: 100%;
			width: 0;
		}
		.line:after {
			border-top-color: #02b2b5;
			border-width: 8px;
			margin-left: -8px;
		}
		.ser-item{
			text-align: center;    	margin-bottom: 30px;
			padding: 0px 20px;
		}
		.ser-item p{color: #737373;}
		.single-service {
			float: left;
			margin-bottom: 30px;
			padding: 0px 10px;
			display: inline;
			width: 100%;
			text-align: center;
		}
		.service-icon .service-icon-effect {
			box-shadow: 0 0 0 4px #02b2b5;
		}
		.service-icon-effect {
			display: inline-block;
			font-size: 0px;
			margin: 15px 30px;
			width: 90px;
			height: 90px;
			border-radius: 50%;
			text-align: center;
			position: relative;
			z-index: 1;
			color: #02b2b5;
		}
		.service-icon-effect:before {
			speak: none;
			font-size: 48px;
			line-height: 90px;
			font-style: normal;
			font-weight: normal;
			font-variant: normal;
			text-transform: none;
			display: block;
			-webkit-font-smoothing: antialiased;
		}
		body{
			margin: 0px;
		}
		.zhezhao{
			border-color: black;
			position: fixed;
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			/*opacity: 0.5;*/
		}
		.tankuang{
			position: relative;
			background-image: url("/images/03.jpeg");
			width: 25%;
			height: 40%;
			border-radius: 5px;
			margin: 5% auto;
		}
		#header{
			height: 40px;
		}
		#header-right{
			position: absolute;
			width: 25px;
			height: 25px;
			border-radius: 5px;
			background: red;
			color: #fff;
			right: 5px;
			top: 5px;
			text-align: center;
		}
	</style>

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end">
	<!-- Brand/logo -->
	<a class="navbar-brand mr-auto" href="#">Logo</a>

	<!-- Links -->
	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="/">Home</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/aboutPage">About Us</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/clinic">Clinic</a>
		</li>
		<li class="nav-item">
			<a class="nav-link" href="/hospital">Hospital</a>
		</li>
		<li class="nav-item dropdown ">
			<a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
				User
			</a>
			<div class="dropdown-menu">
				<a class="dropdown-item" id="loginBtn" href="#" onclick="dianwo()">Log in</a>
				<a class="dropdown-item" id="registerBtn" href="/register">Register</a>
				<a class="dropdown-item" id="profileBtn" href="/profile" style="display: none;">Profile</a>
		<a role="separator" class="divider" id="sepDivider" style="display: none;"></a>
				<a class="dropdown-item" id="logoutBtn" href="#" style="display: none;" onclick="logout()">Log out</a>
			</div>
		</li>
	</ul>
</nav>
<%--<nav class="navbar navbar-expand-sm navbar-dark">--%>
<%--	<div class="container-fluid">--%>
<%--		<!-- Brand and toggle get grouped for better mobile display -->--%>
<%--		<div class="navbar-header">--%>
<%--			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">--%>
<%--				<span class="sr-only">Toggle navigation</span>--%>
<%--				<span class="icon-bar"></span>--%>
<%--				<span class="icon-bar"></span>--%>
<%--				<span class="icon-bar"></span>--%>
<%--			</button>--%>
<%--			<a class="navbar-brand" href="#">Logo</a>--%>
<%--		</div>--%>

<%--		<!-- Collect the nav links, forms, and other content for toggling -->--%>
<%--		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">--%>
<%--			<ul class="nav navbar-nav">--%>
<%--			</ul>--%>
<%--			<ul class="nav navbar-nav navbar-right">--%>
<%--				<li><a href="/mainPage">Home</a></li>--%>
<%--				<li><a href="/aboutPage">About Us</a></li>--%>
<%--				<li><a href="/clinic">Clinic</a></li>--%>
<%--				<li><a href="/hospital">Hospital</a></li>--%>
<%--				<li class="dropdown">--%>
<%--					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User<span class="caret"></span></a>--%>
<%--&lt;%&ndash;					<ul class="dropdown-menu">&ndash;%&gt;--%>
<%--&lt;%&ndash;						<li><a id="loginBtn" href="#" onclick="dianwo()">Log in</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;						<li><a id="registerBtn" href="/register">Register</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;						<li><a id="profileBtn" href="/profile" style="display: none;">Profile</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;						<li role="separator" class="divider" id="sepDivider" style="display: none;"></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;						<li><a id="logoutBtn" href="#" style="display: none;" onclick="logout()">Log out</a></li>&ndash;%&gt;--%>
<%--&lt;%&ndash;					</ul>&ndash;%&gt;--%>
<%--				</li>--%>
<%--			</ul>--%>
<%--		</div><!-- /.navbar-collapse -->--%>
<%--	</div><!-- /.container-fluid -->--%>
<%--</nav>--%>
<div id="demo" class="carousel slide" data-ride="carousel">

	<!-- 指示符 -->
	<ul class="carousel-indicators">
		<li data-target="#demo" data-slide-to="0" class="active"></li>
		<li data-target="#demo" data-slide-to="1"></li>
	</ul>

	<div class="carousel-inner">
		<div class="carousel-item active">
			<img src="/images/07.jfif">
		</div>
		<div class="carousel-item">
			<img src="/images/05.jpeg">
		</div>
	</div>

	<a class="carousel-control-prev" href="#demo" data-slide="prev">
		<span class="carousel-control-prev-icon"></span>
	</a>
	<a class="carousel-control-next" href="#demo" data-slide="next">
		<span class="carousel-control-next-icon"></span>
	</a>

</div>
<div class="service">
	<div class="container p50">
		<div class="row">
			<div class="col-md-12 title">
				<h2>Our services</h2>
				<div class="line"></div>
			</div>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<div class="ser-item">
					<div class="service-icon">
						<span class="fa fa-heartbeat service-icon-effect"></span>
					</div>
					<h3><a href="/hospital">Hospital appointment</a></h3>
				</div>
			</div>
			<div class="col-md-6 col-sm-6 col-xs-12">
				<div class="ser-item">
					<div class="service-icon">
						<span class="fa fa-stethoscope service-icon-effect"></span>
					</div>
					<h3><a href="/clinic">Clinic appointment</a></h3>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="zhezhao" id='zhezhao'>
	<div class="tankuang">
		<div id="header">
			<span style="font-size: 24px;">Log in</span>
			<div id="header-right" onclick="hidder()">x</div>
		</div>
		<div id="body">
			<div class="login-item" style="padding-left: 40%;padding-top: 15%"><input type="text" style="width: 200px;height: 30px;" id="InputUsername" placeholder="please enter the Username"/></div>
			<div class="login-item" style="padding-left: 40%;padding-top: 5%"><input type="text" style="width: 200px;height: 30px;" id="InputPassword" placeholder="Please enter the Password"/></div>
			<div class="login-item" style="padding-left: 40%;padding-top: 5%"><input type="button" style="width: 50px;height: 50px;" value="login" onclick="login()"></div>
		</div>
	</div>
</div>

<script type="text/javascript">

	$(function() {
		checkUser();
	});


	function checkUser(){
		$.ajax({
			type:'GET',
			url:'/api/getLoginUser',
			success:function (data){
				//console.log(data)
				if(data.code == 200){
					document.getElementById("loginBtn").style.display="none";
					document.getElementById("registerBtn").style.display="none";
					document.getElementById("profileBtn").style.display="block";
					document.getElementById("logoutBtn").style.display="block";
					document.getElementById("sepDivider").style.display="block";
				}else{
					document.getElementById("loginBtn").style.display="block";
					document.getElementById("registerBtn").style.display="block";
					document.getElementById("profileBtn").style.display="none";
					document.getElementById("logoutBtn").style.display="none";
					document.getElementById("sepDivider").style.display="none";
				}
			},
			error:function (data){
				console.log(data)
			}
		})
	}

	document.getElementById('zhezhao').style.display="none";
	function dianwo(){
		document.getElementById('zhezhao').style.display="";
	}
	function hidder(){
		document.getElementById('zhezhao').style.display="none";
	}

	function login(){
		const username = document.getElementById('InputUsername').value;
		const password = document.getElementById('InputPassword').value;
		$.ajax({
			type:'post',
			url:'/api/login?username='+username+'&password='+password,
			data:{},
			success:function (data){
				console.log(data)
				if(data.code == 200){
					hidder();
					document.getElementById("loginBtn").style.display="none";
					document.getElementById("registerBtn").style.display="none";
					document.getElementById("profileBtn").style.display="block";
					document.getElementById("logoutBtn").style.display="block";
					document.getElementById("sepDivider").style.display="block";
				}else{
					bootbox.alert(data.message)
				}

			}
		})
	}

	function logout(){
		$.ajax({
			type:'get',
			url:'/api/logout',
			success:function (data){
				console.log(data)
				if(data.code == 200){
					hidder();
					document.getElementById("loginBtn").style.display="block";
					document.getElementById("registerBtn").style.display="block";
					document.getElementById("profileBtn").style.display="none";
					document.getElementById("logoutBtn").style.display="none";
					document.getElementById("sepDivider").style.display="none";
					location.reload();
				}else{
					bootbox.alert(data.message)
				}

			}
		})
	}
</script>


</body>

</html>