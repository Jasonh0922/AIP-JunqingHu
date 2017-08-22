<section id="login">
	

    <div class="container">
	    <div class="alert alert-info">
			Username: tom/ Password: tom<br /> 
			Username: yuvia/ Password: yuvia<br /> 
			Username: petter/ Password: petter<br /> 
			Username: william/ Password: william<br /> 
			
		</div>
    	<div class="row">
    	    <div class="col-xs-12">
        	    <div class="form-wrap"> 
                <h1>Log in</h1>
                	<div ng-show="error" class="alert alert-danger">{{error}}</div>
                    <form role="form" name="form" ng-submit="login()">
                        <div class="form-group">
                            <label for="username" class="sr-only">UserName</label>
                            <input type="text" name="username" id="username" class="form-control" placeholder="UserName" ng-model="username" required/>
                            <span ng-show="form.username.$dirty && form.username.$error.required"
							class="help-block">Username is required</span>
                        </div>
                        <div class="form-group">
                            <label for="password" class="sr-only">Password</label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="Password" ng-model="password" required>
                        	<span ng-show="form.password.$dirty && form.password.$error.required"
							class="help-block">Password is required</span>
                        </div>
                        
                        <input type="submit" id="btn-login" class="btn btn-custom btn-lg btn-block" ng-disabled="form.$invalid" value="Log in">
                    </form>
                    <hr>
        	    </div>
    		</div> <!-- /.col-xs-12 -->
    	</div> <!-- /.row -->
    </div> <!-- /.container -->
</section>