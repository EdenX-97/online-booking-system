<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

  <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.5.2/bootbox.min.js"></script>
  <link rel="stylesheet"href="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.css">
  <script src="https://unpkg.com/bootstrap-table@1.15.3/dist/bootstrap-table.min.js"></script>
</head>
<style>
  .side-nav-item {
    display: block;
    padding: 10px 15px 10px 15px;
    background-color: #FFFFFF;
    cursor: pointer;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
    -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
  }

  .item-title {
    background-color: #F5F5F5;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
    border-bottom: 1px solid #DDDDDD;
  }

  .panel-heading {
    margin-top: 5px;
    padding: 0;
    border-radius: 3px;
    border: 1px solid transparent;
    border-color: #DDDDDD;
  }

  .item-body {
    padding: 10px 15px 5px 15px;
    border-bottom: 1px solid #DDDDDD;
  }

  .item-second {
    margin-top: 5px;
    cursor: pointer;
  }

  .item-second a {
    display: block;
    height: 100%;
    width: 100%;
  }
  .at{ color:red;}
</style>
<body>
<div>
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
              <li><a id="profileBtn" href="/profile">Profile</a></li>
              <li role="separator" class="divider"></li>
              <li><a id="logoutBtn" href="#">Log out</a></li>
            </ul>
          </li>
        </ul>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>
</div>
<div class=".col-md-4">
  <div class="col-md-2 side-nav">
    <div class="panel-group" id="accordion">
      <div class="panel-heading panel">
        <a class="side-nav-item item-title" id="userInfo" onclick="clickUserInfo()">
          user information
        </a>
        <div class="item-body collapse" id='index'>
        </div>
      </div>

      <div class="panel-heading panel">
        <a data-toggle="collapse" data-parent="#accordion" id="resetPwd" class="side-nav-item item-title" onclick="clickResetPwd()">
          reset password
        </a>
      </div>

      <div class="panel-heading panel">
        <a data-toggle="collapse" data-parent="#accordion" id="headcaiwu" class="side-nav-item item-title collapsed" onclick="clickHistory()">
          Appointment Record
        </a>
      </div>

    </div>
  </div>
</div>
<div style="padding:5px 5px 5px 300px;display: block;"  id="userInfoDiv">
  <div class="panel panel-default" >
    <div class="panel-heading">user information</div>
    <div class="panel-body">
      <form>
        <div class="form-group">
          <label for="usernameInput">Username</label>
          <input type="text" class="form-control" id="usernameInput" placeholder="Username">
        </div>
        <div class="form-group">
          <label for="firstNameInput">First Name</label>
          <input type="text" class="form-control" id="firstNameInput" placeholder="First Name">
        </div>
        <div class="form-group">
          <label for="lastNameInput">Last Name</label>
          <input type="text" class="form-control" id="lastNameInput" placeholder="Last Name">
        </div>
        <div class="form-group">
          <label for="genderInput">Gender</label>
          <input type="text" class="form-control" id="genderInput" placeholder="Gender">
        </div>
        <div class="form-group">
          <label for="ageInput">Age</label>
          <input type="text" class="form-control" id="ageInput" placeholder="Age">
        </div>
        <div class="form-group">
          <label for="emailInput">Email</label>
          <input type="email" class="form-control" id="emailInput" placeholder="Email">
        </div>
        <div class="form-group">
          <label for="phoneInput">Phone</label>
          <input type="text" class="form-control" id="phoneInput" placeholder="Phone">
        </div>

      </form>
      <button type="submit" class="btn btn-default" onclick="changeUserInfo()">Submit</button>
    </div>
  </div>
</div>
<div style="padding:5px 5px 5px 300px;display: none;" id="resetPwdDiv">
  <div class="panel panel-default" >
    <div class="panel-heading">reset password</div>
    <div class="panel-body">
      <form name="changePwdPanel">
        <div class="form-group">
          <label for="oldPwd">Old Password</label>
          <input type="password" class="form-control" id="oldPwd" placeholder="Old Password">
        </div>
        <div class="form-group">
          <label for="newPwd">New Password</label>
          <input type="password" class="form-control" id="newPwd" placeholder="New Password">
        </div>
        <div class="form-group">
          <label for="comfirmPwd">Comfirm Password</label>
          <input type="password" class="form-control" id="comfirmPwd" placeholder="Comfirm Password">
        </div>

      </form>
      <button class="btn btn-default" onclick="changePwd()">Submit</button>
    </div>
  </div>
</div>
<div style="padding:5px 5px 5px 300px;display: none;" id="historyDiv">
  <div class="panel panel-default" >
    <div class="panel-heading">Appointment Record</div>
    <div class="panel-body">
      <div style="padding: 30px;">
        <table id="table" class="table text-nowrap  table-condensed"
               data-show-columns="true"
               data-show-columns-search="true"
               data-search="true">
        </table>
      </div>
    </div>
  </div>
</div>
<div style="padding:5px 5px 5px 300px;display: none;" id="history2Div">
  <div class="panel panel-default" >
    <div class="panel-heading">History</div>
    <div class="panel-body">

    </div>
  </div>
</div>
</body>
<script>

  $(function() {

    getuserInfo();
  });

  function getuserInfo(){
    $.ajax({
      type:'GET',
      url:'/api/getLoginUser',
      success:function (data){
        console.log(data)
        if(data.code == 200){
          document.getElementById("loginBtn").style.display="none";
          document.getElementById("registerBtn").style.display="none";
          document.getElementById("profileBtn").style.display="none";
          document.getElementById("logoutBtn").style.display="block";
          $("#usernameInput").val(data.data.account);
          $("#firstNameInput").val(data.data.firstname);
          $("#lastNameInput").val(data.data.lastname);
          $("#emailInput").val(data.data.email);
          $("#phoneInput").val(data.data.phone);
          $("#ageInput").val(data.data.age);
          $("#genderInput").val(data.data.gender);
        }else{
          document.getElementById("loginBtn").style.display="block";
          document.getElementById("registerBtn").style.display="block";
          document.getElementById("profileBtn").style.display="none";
          document.getElementById("logoutBtn").style.display="none";
        }
      },
      error:function (data){
        console.log(data)
      }
    })
  }

  $(document).ready(function(){
    var path=window.location.pathname;
    var regExp=/[\/\.\?]+/;
    str=path.split(regExp);
    var node=str.slice(-2,-1);
    //$('#'+node+' a').addClass('at');
    $('#'+node).parent().parent().parent().addClass('in');

  })

  function clickUserInfo(){
    document.getElementById("userInfoDiv").style.display="block";
    document.getElementById("resetPwdDiv").style.display="none";
    document.getElementById("historyDiv").style.display="none";
    document.getElementById("history2Div").style.display="none";

  }

  function clickResetPwd(){
    document.getElementById("userInfoDiv").style.display="none";
    document.getElementById("resetPwdDiv").style.display="block";
    document.getElementById("historyDiv").style.display="none";
    document.getElementById("history2Div").style.display="none";

  }

  Date.prototype.format = function(format)
  {
    var o = {
      "M+" : this.getMonth()+1, //month
      "d+" : this.getDate(),    //day
      "h+" : this.getHours(),   //hour
      "m+" : this.getMinutes(), //minute
      "s+" : this.getSeconds(), //second
      "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
      "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
            (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
      format = format.replace(RegExp.$1,
              RegExp.$1.length==1 ? o[k] :
                      ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
  }

  function addFunctionAlty(value, row, index) {
    return [
      '<button id="delete" type="button" class="btn btn-default" onclick="operateEvents()">Delete</button>',
    ].join('');
  }

  let rowDate,rowid;
  let onclick = 0;
  function clickHistory(){
    document.getElementById("userInfoDiv").style.display="none";
    document.getElementById("resetPwdDiv").style.display="none";
    document.getElementById("historyDiv").style.display="block";
    document.getElementById("history2Div").style.display="none";
    $('#table').bootstrapTable({
      url: '/api/getRecord',
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
      sortable: true,
      singleSelect: true,
      //customSort: customSort,
      responseHandler: function (res) {
        console.log(res)
        return{
          "total":res.data.Records.length,
          "rows":res.data.Records
        }
      },
      columns: [
        {
          checkbox: true,
        },{
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
          title: 'Agency Name',
          formatter: function (value, row, index) {
            return row.agency.agencyName;
          }
        }, {
          field: 'recordDatetime',
          title: 'Date',
          formatter: function (value, row, index) {
            return new Date(row.recordDatetime).format("yyyy-MM-dd hh:mm");
          }
        },{
          field: 'state',
          title: 'Record state',
        },
        {
          field: 'operate',
          title: 'action',
          formatter: addFunctionAlty,
        }
      ],
      onDblClickRow: function (row) {
      },
      onUncheck:function(row){
        onclick = 0;
      },
      onCheck:function(row){
        console.log(row);
        rowDate = row.recordDatetime;
        rowid = row.agency.id;
        onclick = 1;
      },
    });
  }

  function operateEvents(){
    console.log(new Date(rowDate).format("yyyy-MM-dd hh:mm:ss"))
    if(onclick == 0){
      bootbox.alert("Please check one record")
    }else{
      $.ajax({
        type:'post',
        url:'/api/deleteAppointment?timeOfAppointment='+new Date(rowDate).format("yyyy-MM-dd hh:mm:ss")+"&agency_id="+rowid,
        dataType:'json',
        contentType:'application/json',
        success:function (data){
          console.log(data)
          if(data.code == 200){
            bootbox.confirm({
              title: "Tips",
              message: "Deleted successfully！",
              buttons: {
                confirm: {
                  label: 'Yes',
                  className: 'btn-success'
                },
                cancel: {
                  label: 'No',
                  className: 'btn-danger'
                }
              },
              callback: function (result) {
                location.reload()
              }
            });
          }else{
            bootbox.alert(data.message);
          }


        },
        error:function (data){
          console.log(data)
        }
      })
    }
  }

  function clickHistory2(){
    document.getElementById("userInfoDiv").style.display="none";
    document.getElementById("resetPwdDiv").style.display="none";
    document.getElementById("historyDiv").style.display="none";
    document.getElementById("history2Div").style.display="block";

  }

  function changeUserInfo(){
    $("#usernameInput").val();
    let firstname = $("#firstNameInput").val();
    let lastname = $("#lastNameInput").val();
    let email = $("#emailInput").val();
    let phone = $("#phoneInput").val();
    let age = $("#ageInput").val();
    let gender = $("#genderInput").val();
    $.ajax({
      type:'post',
      url:'/api/changeProfile?firstname='+firstname +'&lastname='+lastname+'&age='+age+'&gender='+gender+'&phone='+phone+'&email='+email,
      dataType:'json',
      contentType:'application/json',
      success:function (data){
        bootbox.alert(data.message)
        console.log(data)
        //location.reload()
      },
      error:function (data){
        console.log(data)
      }
    })
  }

  function changePwd(){
    let old = $("#oldPwd").val();
    let newpwd = $("#newPwd").val();
    let comfirm = $("#comfirmPwd").val();
    if(comfirm == newpwd){
        $.ajax({
          type:'post',
          url:'/api/changePassword?newPassword='+newpwd+'&nowPassword='+old,
          dataType:'json',
          contentType:'application/json',
          success:function (data){
            bootbox.alert(data.message)
            console.log(data)
            //location.reload()
          },
          error:function (data){
            console.log(data)
          }
        })
    }else{
      bootbox.alert("Please enter verify code before submit!");
      return;
    }
  }

</script>
</html>