<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>

    <link rel="stylesheet" href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">
    <script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>
    <script
            src="//maps.googleapis.com/maps/api/js?key=AIzaSyBpfbubvFdZjdQrq8c6jCU6CFxZcYJLv7g&sensor=false&libraries=geometry">
    </script>

    <style type="text/css">
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

<div style="padding: 30px;">
    <table id="table" class="table text-nowrap  table-condensed"
           data-show-columns="true"
           data-show-columns-search="true"
           data-search="true">
        <%--        <thead>--%>
        <%--        <tr>--%>
        <%--            <th data-field="agencyName">ID</th>--%>
        <%--        </tr>--%>
        <%--        </thead>--%>
    </table>
</div>

<%--<div class="row row-no-gutters" style="padding: 30px 0px 0px 150px">--%>

<%--    <div class="col-xs-6 col-md-4">--%>
<%--        <div class="container">--%>

<%--            <div class="card img-fluid" style="width:200px">--%>
<%--                <img class="card-img-left" src="https://static.runoob.com/images/mix/img_avatar.png" style="width: 100%">--%>
<%--                <div class="card-img-overlay">--%>
<%--                    <h4 class="card-title">hospital</h4>--%>
<%--                    <p class="card-text">address</p>--%>
<%--                    <p class="card-text">open date</p>--%>
<%--                    <a href="#" class="btn btn-primary">pre</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col-xs-6 col-md-4">--%>
<%--        <div class="container">--%>

<%--            <div class="card img-fluid" style="width:200px">--%>
<%--                <img class="card-img-left" src="https://static.runoob.com/images/mix/img_avatar.png" style="width: 100%">--%>
<%--                <div class="card-img-overlay">--%>
<%--                    <h4 class="card-title">hospital</h4>--%>
<%--                    <p class="card-text">address</p>--%>
<%--                    <p class="card-text">open date</p>--%>
<%--                    <a href="#" class="btn btn-primary">pre</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="col-xs-6 col-md-4">--%>
<%--        <div class="container">--%>

<%--            <div class="card img-fluid" style="width:200px">--%>
<%--                <img class="card-img-left" src="https://static.runoob.com/images/mix/img_avatar.png" style="width: 100%">--%>
<%--                <div class="card-img-overlay">--%>
<%--                    <h4 class="card-title">hospital</h4>--%>
<%--                    <p class="card-text">address</p>--%>
<%--                    <p class="card-text">open date</p>--%>
<%--                    <a href="#" class="btn btn-primary">pre</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
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
<script type="text/javascript">
    var pos;

    $(function() {
        checkUser();
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    pos = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,
                    };
                    $('#table').bootstrapTable({
                        url: '/api/getHospital?longitude='+pos.lng+"&latitude="+pos.lat,
                        method: 'get',
                        toolbar: '#toolbar',
                        pagination: true,
                        sortOrder: "asc",
                        sortName: 'distance',
                        sidePagination: "client",
                        pageNumber: 1,
                        pageSize: 15,
                        pageList: [10, 20, 50, 100],
                        height: $(window).height() - 200,
                        uniqueId: "id",
                        cardView:true,
                        sortable: true,
                        //customSort: customSort,
                        responseHandler: function (res) {
                            // var rows = [];
                            // for(var i = 0; i < res.data.Clinic.length;i++){
                            //     var currentRow = res.data.Clinic[i];
                            //     var lat = currentRow.latitude;
                            //     var lng = currentRow.longitude;
                            //     var oldMarker = new google.maps.Marker({
                            //         position: new google.maps.LatLng(lat, lng),
                            //         title:"old"
                            //     });
                            //     var newMarker = new google.maps.Marker({
                            //         position: new google.maps.LatLng(pos.lat, pos.lng),
                            //         title:"new"
                            //     })
                            //     var distance = google.maps.geometry.spherical.computeDistanceBetween(oldMarker.getPosition(), newMarker.getPosition());
                            //     distance = Math.round(distance)/1000;
                            //     currentRow['Distance(km)'] = distance;
                            //     rows[i] = currentRow;
                            // }

                            console.log(res);
                            return{

                                "total":res.data.Hospital.length,
                                "rows":res.data.Hospital
                            }
                        },
                        columns: [{
                            title: 'RowName',
                            field: 'id',
                            align: 'center',
                            width: '40',
                            visible: false,
                            formatter: function (value, row, index) {
                                return index + 1;
                            }
                        },
                            {
                                field: 'agencyName',
                                title: 'Hospital Name',
                            }, {
                                field: 'address',
                                title: 'Address',
                            }, {
                                field: 'states',
                                title: 'States',
                            }, {
                                field: 'postCode',
                                title: 'PostCode',
                            },
                            {
                                title: 'Distance(km)',
                                field: 'distance',
                                sortable:true,
                            }
                        ],
                        // rowStyle: function (row, index) {
                        //     var style = { css: { 'heigth': '50' } };
                        //     return style;
                        // },
                        onDblClickRow: function (row) {
                            //location.replace("job_detail.html?jobId=" + index);
                            sessionStorage.setItem('address',row.address);
                            sessionStorage.setItem('agencyName',row.agencyName);
                            sessionStorage.setItem('appointmentRequired',row.appointmentRequired);
                            sessionStorage.setItem('driveThroughTesting',row.driveThroughTesting);
                            sessionStorage.setItem('fridayOpeningHours',row.fridayOpeningHours);
                            sessionStorage.setItem('latitude',row.latitude);
                            sessionStorage.setItem('longitude',row.longitude);
                            sessionStorage.setItem('mondayOpeningHours',row.mondayOpeningHours);
                            sessionStorage.setItem('patientRestriction',row.patientRestriction);
                            sessionStorage.setItem('phone',row.phone);
                            sessionStorage.setItem('postCode',row.postCode);
                            sessionStorage.setItem('prescriptionRequired',row.prescriptionRequired);
                            sessionStorage.setItem('saturdayOpeningHours',row.saturdayOpeningHours);
                            sessionStorage.setItem('states',row.states);
                            sessionStorage.setItem('suburb',row.suburb);
                            sessionStorage.setItem('sundayOpeningHours',row.sundayOpeningHours);
                            sessionStorage.setItem('thursdayOpeningHours',row.thursdayOpeningHours);
                            sessionStorage.setItem('suburb',row.suburb);
                            sessionStorage.setItem('tuesdayOpeningHours',row.tuesdayOpeningHours);
                            sessionStorage.setItem('type',row.type);
                            sessionStorage.setItem('website',row.website);
                            sessionStorage.setItem('wednesdayOpeningHours',row.wednesdayOpeningHours);
                            sessionStorage.setItem('id',row.id);
                            window.location.href="/reserve";
                        }
                    });
                },
                () => {
                    handleLocationError(true, infoWindow, map.getCenter());
                }
            );

        } else {
            // Browser doesn't support Geolocation
            handleLocationError(false, infoWindow, map.getCenter());
        }

    })



    function customSort(sortName, sortOrder, data){
        //console.log(data)
        return 0;
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