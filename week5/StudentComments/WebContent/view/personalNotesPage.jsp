<!-- nav wrapper -->
<div class="navbar-wrapper">
<div class="container">
	<nav class="navbar navbar-default navbar-inverse" role="navigation">
		<div class="container-fluid">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#/login">Log out? &nbsp;&nbsp; {{currentUser.username}}</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
</div>
<!-- / nav wrapper -->
<div class="container-wrapper" style="padding-top:100px; padding-bottom:50px">
	<div class="container">
		<div class="page-header">
			<h1>Comments For Classes</h1>
			<p class="lead" align="right"><button class="btn btn-default" 
			ng-click="createComment()">Create New Comment</button></p>
		</div>
		<table class="table table-striped table-hover">
			<thead>
				<tr class="bg-success">
					<th>Class</th>
					<th>Student Name</th>
					<th>Score</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tr class="bg-success" ng-repeat="comment in commentList">
				<td>{{comment.className}}</td>
				<td>{{comment.createBy}}</td>
				<td><span class="label label-primary">{{comment.score}}</span></td>
				<td><button class="btn btn-default" 
				ng-click="deleteComment($index)" 
				ng-show="'{{comment.createBy}}' == '{{currentUser.username}}'">Delete</button></td>
			</tr>
		</table>
	</div>
</div>
