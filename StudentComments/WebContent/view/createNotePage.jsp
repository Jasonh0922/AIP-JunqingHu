
<div class="modal-content" >
	<div class="modal-header">
		<h3 class="modal-title">Create New Note</h3>
	</div>
	<div class="modal-body">
		<form name="createNoteForm" class="form-signin" novalidate>
			<div class="form-group">
				<label for="title">Class Name</label> 
				<input type="text" id="className"
					name="className" class="form-control" ng-model="className" required />
			</div>
			<div class="form-group">
				<label for="labels">Score</label> 
				<select multiple class="form-control" id="score" ng-model="score" required>
					<option ng-repeat="score in scoreList">{{score}}</option>
			    </select>
			</div>
			<div class="modal-footer" style="padding: 5px;">
				<button class="btn btn-primary" type="submit"
					style="height: 30px; padding: 4px 12px;" ng-click="createCommentSubmit()"
					ng-disabled="createNoteForm.$invalid">Create</button>
			</div>
		</form>
	</div>
</div>