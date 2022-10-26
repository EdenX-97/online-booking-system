<%--
  Created by IntelliJ IDEA.
  User: Ame
  Date: 2021/10/13
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">

<script src="js/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
<script
        src="//maps.googleapis.com/maps/api/js?key=AIzaSyBpfbubvFdZjdQrq8c6jCU6CFxZcYJLv7g&sensor=false&amp;language=en-US">
</script>
<html>
<head>
<style>
    .td_left{
        width: 100px;
        text-align: right;
        height: 60px;
    }
    .td_right{
        padding-left: 50px;
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
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
            </ul>
            <%--			<form class="navbar-form navbar-left">--%>
            <%--				<div class="form-group">--%>
            <%--					<input type="text" class="form-control" placeholder="Search">--%>
            <%--				</div>--%>
            <%--				<button type="submit" class="btn btn-default">Submit</button>--%>
            <%--			</form>--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">Home</a></li>
                <li><a href="/aboutPage">About Us</a></li>
                <li><a href="/clinic">Clinic</a></li>
                <li><a href="/hospital">Hospital</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a id="loginBtn" href="#" onclick="dianwo()">Log in</a></li>
                        <li><a id="registerBtn" href="/register">Register</a></li>
                        <li><a id="profileBtn" href="/profile" style="display: none;">Profile</a></li>
                        <li role="separator" class="divider" id="sepDivider" style="display: none;"></li>
                        <li><a id="logoutBtn" href="#" style="display: none;" onclick="logout()">Log out</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div id="header2" style="width: 100%;height: 30%;background: #FFD026;">
    <div style="padding-left: 10%;padding-top: 4%;padding-right: 400px;float: left"><img src="/images/02.jpeg" width="200" height="200" ></div>
    <div style="padding-left: 10%;padding-top: 4%;padding-right: 600px;float: left">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-4 control-label" id="type"></label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="name"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Address</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="address"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Appointment Required</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="appointmentRequired"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Drive Through Testing</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="driveThroughTesting"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Patient Restriction</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="patientRestriction"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Phone</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="phone"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Prescription Required</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="prescriptionRequired"></p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-4 control-label">Website</label>
                <div class="col-sm-8">
                    <p class="form-control-static" id="website"></p>
                </div>
            </div>
        </form>

    </div>
    <div class="row" style="padding-left: 68%;padding-top: 1%;">
        <div class='col-sm-6'>

        </div>
    </div>
<%--    <div style="padding-left: 65%">--%>
<%--        开放时间：--%>
<%--    </div>--%>

</div>
<div id="body2" style="width: 100%;height: 70%; ">
    <div style="padding-top: 2%;padding-left: 42%;">
        <div id="googleMap" style="width:400px;height:320px;"></div>
        <span style="padding: 20px 0px 20px 0px;">Select date time:</span>
        <div class="form-group">
            <!--指定 date标记-->
            <div class='input-group date' id='datetimepicker2' style="width: 200px;">
                <input type='text' class="form-control" id="date"/>
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
        <div style="padding-left: 10%;padding-top: 5%">
            <button style="width: 100px;height: 50px;" onclick="reserveBtn()">Reserve</button>
        </div>
    </div>

<%--</div>--%>
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

<script>
    $(function () {
        checkUser();
        $('#datetimepicker2').datetimepicker({
            format: 'YYYY-MM-DD HH:mm',
            locale: moment.locale('en-au')
        });
        var type = sessionStorage.getItem('type') + " Name";
        document.getElementById('type').innerText = type;
        document.getElementById('name').innerText = sessionStorage.getItem('agencyName');
        document.getElementById('address').innerText = sessionStorage.getItem('address') +", " +sessionStorage.getItem('states') +", " +sessionStorage.getItem('postCode');
        document.getElementById('appointmentRequired').innerText = sessionStorage.getItem('appointmentRequired');
        document.getElementById('driveThroughTesting').innerText = sessionStorage.getItem('driveThroughTesting');
        document.getElementById('patientRestriction').innerText = sessionStorage.getItem('patientRestriction');
        document.getElementById('phone').innerText = sessionStorage.getItem('phone');
        document.getElementById('prescriptionRequired').innerText = sessionStorage.getItem('prescriptionRequired');
        document.getElementById('website').innerText = sessionStorage.getItem('website');


    });

    var myCenter=new google.maps.LatLng(sessionStorage.getItem('latitude'),sessionStorage.getItem('longitude'));

    function initialize()
    {
        var mapProp = {
            center: myCenter,
            zoom:15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

        var marker = new google.maps.Marker({
            position: myCenter,
            title:'Click to zoom'
        });

        marker.setMap(map);

        google.maps.event.addListener(marker,'click',function() {
            map.setZoom(9);
            map.setCenter(marker.getPosition());
        });
    }
    google.maps.event.addDomListener(window, 'load', initialize);

    document.getElementById('zhezhao').style.display="none";
    function dianwo(){
        document.getElementById('zhezhao').style.display="";
    }
    function hidder(){
        document.getElementById('zhezhao').style.display="none";
    }

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

    function reserveBtn(){
        $.ajax({
            type:'post',
            url:'/api/makeAppointment?timeOfAppointment='+$("#datetimepicker2").find("input").val()+"&agency_id="+sessionStorage.getItem('id'),
            success:function (data){
                console.log(data)
                if(data.code == 200){
                    bootbox.alert(data.message);
                    $.ajax({
                        type:'post',
                        url:'/api/sendAppointmentEmail?appointmentDate='+$("#datetimepicker2").find("input").val(),
                        success:function (data){
                            console.log(data)
                            if(data.code == 200){
                                bootbox.alert(data.message);

                            }else{
                                bootbox.alert(data.message);
                            }
                        },
                        error:function (data){
                            console.log(data)
                        }
                    })
                }else{
                    bootbox.alert(data.message);
                }
            },
            error:function (data){
                console.log(data)
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
