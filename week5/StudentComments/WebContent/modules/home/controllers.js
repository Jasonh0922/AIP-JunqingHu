'use strict';

angular.module('Home', [ 'ui.bootstrap' ]).controller(
		'HomeController',
		[ '$scope', '$rootScope', '$cookieStore', '$http', '$uibModal',
				function($scope, $rootScope, $cookieStore, $http, $uibModal) {
					 $scope.commentList = $rootScope.globals.commentList;
					 $scope.currentUser = $rootScope.globals.currentUser;
					 $scope.scoreList = ["HIGH", "MEDIUM", "LOW"];
					 
					 $scope.listAll = function() {
						 $scope.commentList = $rootScope.globals.commentList;
					 };
					
					
					 $scope.createCommentSubmit = function() {
						 console.log("submit");
						 //console.log($scope.score.pop());
						 var comment = {
							 'className' : $scope.className,
							 'score' : $scope.score.pop(),
							 'createBy' : $scope.currentUser.username
						 };
					
						 $scope.commentList.push(comment);
						 $cookieStore.put('globals', $rootScope.globals);
						
						 $http({
							 method : 'POST',
							 url : 'create',
							 data : angular.toJson(comment),
							 headers : {
								 "Content-Type" : "application/json"
							 }
							 }).then(function(response) {
								 console.log(response);
							 }, function(error) {
							 });
						 	$scope.modalInstance.close();
						 };
					
					$scope.createComment = function() {
						console.log("create");
						$scope.commentList = $rootScope.globals.commentList;
						$scope.modalInstance = $uibModal.open({
							templateUrl : 'view/createNotePage.jsp',
							scope : $scope,
							controller : 'HomeController',
							windowClass : 'app-modal-window'
						});
					 };
					
					 $scope.deleteComment = function(index) {
						 var comment = $scope.commentList[index];
						 $scope.commentList.splice(index, 1);
						 $rootScope.globals.commentList = $scope.commentList;
						 $cookieStore.put('globals', $rootScope.globals);
						 $http({
							method : 'DELETE',
							url : 'delete',
							data : angular.toJson(comment),
							headers : {
								"Content-Type" : "application/json"
							}
						}).then(function(response) {
						}, function(error) {
						});
					 };
				} ]);
