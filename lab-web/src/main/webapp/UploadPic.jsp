<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="test">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传图片</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/jquery-2.1.4/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/angular-1.5.8/angular.js"></script>
<style type="text/css">

</style>

</head>
<body ng-controller="testController" ng-init="ready()">
    <form action="<%=request.getContextPath()%>/activity/uploadPicture.do" method="post" enctype="multipart/form-data">  
      <input type="file" name="file" /> <input type="submit" value="Submit" />
    </form>  
</body>
<script>
  var scope;
  angular.module("test", []).controller('testController', function($scope) {

  	$scope.ready = function() {
  		scope = $scope;
  	};
  	
  });
</script>
</html>