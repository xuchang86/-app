<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="test">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>逍遥派APP测试</title>
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
  				name: '许畅222',
  				password: '121121',
  				phone: '18627014277',
  				birthday: '2016-08-21',
  				address: '深圳市南山区',
  				providerid: '黄金万两22',
  				requiredid: '美女如云22',
  				city: '深圳市',
  				sex: 0,
  				username: 'xcxc1990'
  			},
  			success: function(data) {
  				console.log(data);
  			}
  		});
  	};

  	//注册
  	$scope.register = function() {
  		$.ajax({
  			url: '<%=request.getContextPath()%>/xiaoyao/register.do',
  			dataType: 'json',
  			type: 'POST',
  			data: {
  				code: '314451',
  				password: '121121',
  				phone: '18627014278'
  			},
  			success: function(data) {
  				console.log(data);
  			}
  		});
  	};

  	//立即付款
  	$scope.payment = function() {
  		$.ajax({
  			url: '<%=request.getContextPath()%>/xiaoyao/payment.do',
  			dataType: 'json',
  			type: 'POST',
  			data: {
  				payAmount: '100',
  				inviteCode: '557439',
          isPay : true,
  				payWay: 'wechart' //微信支付
  			},
  			success: function(data) {
  				console.log(data);
  			}
  		});
  	};

  	//获取用户验证码
  	$scope.getRegistCode = function() {
  		$.ajax({
  			url: '<%=request.getContextPath()%>/xiaoyao/getRegistCode.do',
  			dataType: 'json',
  			type: 'POST',
  			data: {
  				phone: '18627014278'
  			},
  			success: function(data) {
  				console.log(data);
  			},
  			error: function(xmlhttpRequest, status, errorThrown) {
  				console.log("xmlhttpRequest:" + xmlhttpRequest);
  				console.log("status:" + status);
  				console.log("errorThrown:" + errorThrown);
  			}
  		});
  	};

    //生成邀请码
  	$scope.generateInviteCode = function(){
  		$.ajax({
  			url: '<%=request.getContextPath()%>/xiaoyao/generateInviteCode.do',
  			dataType: 'json',
  			type: 'POST',
  			data: {
  			},
  			success: function(data) {
  				console.log(data);
  			},
  			error: function(xmlhttpRequest, status, errorThrown) {
  				console.log("xmlhttpRequest:" + xmlhttpRequest);
  				console.log("status:" + status);
  				console.log("errorThrown:" + errorThrown);
  			}
  		});
  	};

	//用户登陆
	$scope.login = function() {
		$.ajax({
			url: '<%=request.getContextPath()%>/xiaoyao/login.do',
			dataType: 'json',
			type: 'POST',
			data: {
				username: '18627014277',
				password:'121121'
			},
			success: function(data) {
				console.log(data);
			},
			error: function(xmlhttpRequest, status, errorThrown) {
				console.log("xmlhttpRequest:" + xmlhttpRequest);
				console.log("status:" + status);
				console.log("errorThrown:" + errorThrown);
			}
		});
	};

  });
</script>
</html>