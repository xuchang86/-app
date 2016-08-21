<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="test">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员成长规则</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/jquery-2.1.4/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/angular-1.5.8/angular.js"></script>
<style type="text/css">

</style>

</head>
<body ng-controller="testController" ng-init="ready()">

  <table>
  	<tr>
  		<td><input type="button" value="登陆" ng-click="login()"/></td>
  	</tr>

    <tr>
        <td><input type="button" value="确认提交" ng-click="confirm()"/></td>
    </tr>
    <tr>
    	<td><input type="button" value="注册" ng-click="register()"/></td>
    </tr>
    <tr>
    	<td><input type="button" value="立即支付" ng-click="payment()"/></td>
    </tr>
    <tr>
    	<td><input type="button" value="获取验证码" ng-click="getRegistCode()"/></td>
    </tr>
    <tr>
    	<td><input type="button" value="生成邀请码" ng-click="generateInviteCode()"/></td>
    </tr>

  </table>
</body>
<script>
  var scope;
  angular.module("test", []).controller('testController', function($scope) {

  	$scope.ready = function() {
  		scope = $scope;
  	};

  	//确认提交
  	$scope.confirm = function() {
  		$.ajax({
  			url: '<%=request.getContextPath()%>/xiaoyao/confirmSubmit.do',
  			dataType: 'json',
  			type: 'POST',
  			data: {
  				name: '许畅',
  				password: '121121',
  				phone: '18627014275',
  				birthday: '2016-08-21',
  				address: '深圳市南山区',
  				providerid: '111',
  				requiredid: '2121',
  				city: '深圳市',
  				sex: 0,
  				username: 'xcxc1990'
  			},
  			success: function(data) {
  				console.log(data);
  			}
  		});
  	};

  	

  });
</script>
</html>