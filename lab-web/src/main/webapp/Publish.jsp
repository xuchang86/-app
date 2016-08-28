<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="test">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/jquery-2.1.4/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/angular-1.5.8/angular.js"></script>
<style type="text/css">

</style>

</head>
<body ng-controller="testController" ng-init="ready()">

  <table>
  	<tr>
  		<td><input type="button" value="发布信息" ng-click="publish()"/></td>
      <td><input type="button" value="申请加入" ng-click="applyToJoin()"/></td>
  	</tr>
  </table>
</body>
<script>
  var scope;
  angular.module("test", []).controller('testController', function($scope) {

  	$scope.ready = function() {
  		scope = $scope;
  	};

  	//发布信息
  	$scope.publish = function() {
  		$.ajax({
  			url: '<%=request.getContextPath()%>/activity/publish.do',
  			dataType: 'json',
  			type: 'POST',
  			data: {
  				type: 'sale_service',
  				address: '深圳市罗湖大酒店',
  				content: '出售陪吃陪喝各种服务',
  				date: '2016-08-21',
  				cost: 1000
  			},
  			success: function(data) {
  				console.log(data);
  			}
  		});
  	};

    //申请加入
    $scope.applyToJoin = function(){
      $.ajax({
        url: '<%=request.getContextPath()%>/activity/applyToJoin.do',
        dataType: 'json',
        type: 'POST',
        data: {
          personId: 3,
          activityId: 3
        },
        success: function(data) {
          console.log(data);
        }
      });

    };

  });
</script>
</html>