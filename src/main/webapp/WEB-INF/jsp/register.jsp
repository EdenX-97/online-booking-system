<%-- Created by IntelliJ IDEA. User: Ame Date: 2021/10/13 Time: 15:54 To change
this template use File | Settings | File Templates. --%> <%@ page
contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <link
    rel="stylesheet"
    href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css"
  />
  <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
  <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <style>
    * {
      margin: 0px; /*所有的外边距为0*/
      padding: 0px; /*所有的内边距为0*/
      box-sizing: border-box; /*规定两个并排的带边框的框*/
    }
    body {
      padding-top: 25px;
    }
    .rg_layout {
      width: 900px;
      height: 500px;
      border: 8px solid #eeeeee; /*solid 定义实线*/
      background-color: white;
      margin: auto;
    }
    .rg_left {
      float: left;
      margin: 15px;
    }
    .rg_left > p:first-child {
      color: #ffd026;
      font-size: 20px;
    }
    .rg_left > p:last-child {
      color: #ffd026;
      font-size: 20px;
    }

    .rg_center {
      float: left;
    }

    .td_left {
      width: 100px;
      text-align: right;
      height: 45px;
    }
    .td_right {
      padding-left: 50px;
    }
    #Username,
    #Password,
    #Email,
    #Firstname,
    #Lastname,
    #Tel,
    #Matchpassword,
    #ConfirmP {
      width: 251px;
      height: 32px;
      border: 1px solid #a6a6a6;
      /*设置边框圆角*/
      border-radius: 5px;
      padding-left: 10px;
    }
    #checkcode {
      width: 110px;
    }
    #btn_sub {
      font-weight: bold;
      width: 100px;
      height: 40px;
      background-color: #ffd026;
      border: 1px solid #ffd026;
      padding-left: 10px;
    }

    #btn_checkcode {
      font-weight: bold;
      width: 120px;
      height: 40px;
      background-color: #ffd026;
      border: 1px solid #ffd026;
      padding-left: 10px;
    }
  </style>
  <head>
    <meta charset="UTF-8" />
    <title>注册页面</title>
    <!--    引入格式文件-->
    <link rel="stylesheet" href="html&css.css" />
  </head>
  <body>
    <nav
      class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-end"
    >
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
      </ul>
    </nav>
    <div class="rg_layout">
      <div class="rg_left">
        <p>USER REGISTER</p>
      </div>
      <div class="rg_center">
        <div class="rg_form">
          <form>
            <table>
              <tr>
                <!--label 标签的作用是当点击文字也会跳到文本输出框-->
                <!--for属性与ID属性对应规定 label 绑定到哪个表单元素。-->
                <td class="td_left"><label for="Username">Username</label></td>
                <td class="td_right">
                  <input type="text" name="username" id="Username" />
                </td>
              </tr>
              <tr>
                <td class="td_left"><label for="Password">Password</label></td>
                <td class="td_right">
                  <input type="password" name="password" id="Password" />
                </td>
              </tr>
              <tr>
                <td class="td_left"><label for="ConfirmP">Confirm</label></td>
                <td class="td_right">
                  <input type="password" name="confirmpassword" id="ConfirmP" />
                </td>
              </tr>
              <tr>
                <!--label 标签的作用是当点击文字也会跳到文本输出框-->
                <td class="td_left"><label for="Email">Email</label></td>
                <td class="td_right">
                  <input type="email" name="email" id="Email" />
                </td>
                <td></td>
              </tr>
              <tr>
                <td class="td_left"><label for="Tel">Phone</label></td>
                <td class="td_right">
                  <input type="text" name="tel" id="Tel" />
                </td>
              </tr>
              <tr>
                <td class="td_left">
                  <label for="Firstname">FirstName</label>
                </td>
                <td class="td_right">
                  <input type="text" name="firstname" id="Firstname" />
                </td>
              </tr>
              <tr>
                <td class="td_left"><label for="Lastname">LastName</label></td>
                <td class="td_right">
                  <input type="text" name="lastname" id="Lastname" />
                </td>
              </tr>
              <tr>
                <td class="td_left">
                  <label for="Matchpassword">VeryfiCode</label>
                </td>
                <td class="td_right">
                  <input type="text" name="matchpassword" id="Matchpassword" />
                </td>
              </tr>
            </table>
          </form>
          <div style="align-content: center">
            <table>
              <tr>
                <td style="padding: 0 0 0 100px">
                  <button id="btn_checkcode" onclick="btnCheckCode()">
                    Send Checkcode
                  </button>
                </td>
                <td width="100px"></td>
                <td>
                  <button id="btn_sub" onclick="register()">Register</button>
                </td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
  <script type="text/javascript">
    function btnCheckCode() {
      var email = document.getElementById("Email").value;
      if (email == "") {
        alert("Please enter email before send verify code!");
      } else {
        var regexp = new RegExp(
          "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"
        );
        if (regexp.test(email)) {
          $.ajax({
            type: "post",
            url: "/api/sendVerifyEmail?email=" + email,
            data: {},
            success: function (data) {
              bootbox.alert(data.message);
              console.log(data);
            },
          });
        } else {
          alert("Please enter correct email!");
        }
      }
    }

    function register() {
      var Username = document.getElementById("Username").value;
      if (Username == "") {
        bootbox.alert("Please enter username before submit!");
        return;
      }
      var Password = document.getElementById("Password").value;
      if (Password == "") {
        bootbox.alert("Please enter password before submit!");
        return;
      }
      var ConfirmP = document.getElementById("ConfirmP").value;
      if (ConfirmP == "") {
        bootbox.alert("Please enter confirm password before submit!");
        return;
      }
      if (ConfirmP != Password) {
        bootbox.alert("Confirm password incorrect!");
        return;
      }
      var Email = document.getElementById("Email").value;
      if (Email == "") {
        bootbox.alert("Please enter email before submit!");
        return;
      }
      var Firstname = document.getElementById("Firstname").value;
      if (Firstname == "") {
        bootbox.alert("Please enter firstname before submit!");
        return;
      }
      var Lastname = document.getElementById("Lastname").value;
      if (Lastname == "") {
        bootbox.alert("Please enter lastname before submit!");
        return;
      }
      var Matchpassword = document.getElementById("Matchpassword").value;
      if (Matchpassword == "") {
        bootbox.alert("Please enter verify code before submit!");
        return;
      }

      $.ajax({
        type: "post",
        url: "/api/register?verifyCode=" + Matchpassword,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
          account: Username,
          password: Password,
          firstname: Firstname,
          email: Email,
          lastname: Lastname,
          matchPassword: ConfirmP,
        }),
        success: function (data) {
          if (data.code == 200) {
            window.location.href = "http://localhost:8080/";
          }
          if (data.code == 400) {
          }
          bootbox.alert(data.message);
          return;
        },
        error: function (data) {
          console.log(data);
        },
      });
    }
  </script>
</html>
