<%--
  Created by IntelliJ IDEA.
  User: Ame
  Date: 2021/10/17
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
<html>
<head>
  <style>
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
    body{
      background-image:url("/images/01.jpeg");
    }
    .center-in-center{
      position: absolute;
      top: 50%;
      left: 50%;
      -webkit-transform: translate(-50%, -50%);
      -moz-transform: translate(-50%, -50%);
      -ms-transform: translate(-50%, -50%);
      -o-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
    }
  </style>
  <title>About us</title>
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

</div>
<div>
</div>
<div class="center-in-center" style="font-size: 70px;color: black;background-color: white;">
  Group 19
  <div style="font-size: 30px;color: black;background-color: white;">jiawei Lu<br>
    Xu Mo<br>
    Zhang Dong<br>
    Cole Dong<br>
    Liang Wang<br></div>

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
</body>
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
</html>
