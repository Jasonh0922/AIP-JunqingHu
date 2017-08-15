<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
      integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


<%--<link rel="stylesheet" href="resources/js/jquery.mobile-1.4.5.min.js">--%>
<%--<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>--%>
<%--<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>--%>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"--%>
      <%--integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<div data-role="page" id="default">
    <div data-role="header">
        <h1>Movie Recommendation</h1>
    </div>

    <div data-role="main" class="ui-content">
        <table border="1 solid #eeeeee" id="datagrid">
            <tr>
                <th>Title</th>
                <td><c:out value="${movie.title}"/></td>
            </tr>
            <tr>
                <th>Release Date</th>
                <td><c:out value="${movie.releaseDate}"/></td>
            </tr>
            <tr>
                <th>Duration</th>
                <td><c:out value="${movie.duration}"/></td>
            </tr>
            <tr>
                <th>Genre</th>
                <td><c:out value="${movie.genre}"/></td>
            </tr>
            <tr>
                <th>Synopsis</th>
                <td><c:out value="${movie.synopsis}"/></td>
            </tr>
        </table>
        <a href="#myPopup" data-rel="popup" data-position-to="window"
           class="ui-btn ui-btn-inline ui-corner-all ui-icon-check ui-btn-icon-left">Update</a>

        <div data-role="popup" id="myPopup" class="ui-content" style="min-width:250px;" data-dismissible="false">
            <form id="movieRecommendationForm" method="post" action="">
                <div>
                    <h3>Update Movie Recommendation</h3>
                    <label for="title" class="ui-hidden-accessible">Title:</label>
                    <input type="text" name="title" id="title" placeholder="Title">
                    <label for="releaseDate" class="ui-hidden-accessible">Release Date:</label>
                    <input type="text" name="releaseDate" id="releaseDate" placeholder="Release Date">
                    <label for="duration" class="ui-hidden-accessible">Duration:</label>
                    <input type="text" name="duration" id="duration" placeholder="Duration">
                    <label for="genre" class="ui-hidden-accessible">Genre:</label>
                    <input type="text" name="genre" id="genre" placeholder="Genre">
                    <label for="synopsis" class="ui-hidden-accessible">Synopsis:</label>
                    <input type="text" name="synopsis" id="synopsis" placeholder="Synopsis">
                    <input type="button" data-inline="true" value="Update" onclick="updateMovie(this)">
                    <a href="#" id="goBack"
                       class="ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b ui-icon-back ui-btn-icon-left right"
                       data-rel="back">Cancel</a>
                </div>
            </form>
        </div>
    </div>

    <footer class="fixed-bottom">
        <div data-role="footer">
            <h1>Copyright &copy; ShankarBrand. All rights reserved.</h1>
        </div>
    </footer>
</div>
<script>
    function updateMovie() {
        var movieRecommendationForm = $('#movieRecommendationForm');
        $.ajax({
            type: movieRecommendationForm.attr("method"),
            url: movieRecommendationForm.attr("action"),
            data: movieRecommendationForm.serialize(),
            success: function (result) {
                $('#datagrid').load(document.URL + ' #datagrid');
                $("#goBack").click();
//                movieRecommendationForm.trigger("reset");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                debugger;
                alert(xhr.status);
                alert(thrownError);
            },
            onComplete: function () {
                debugger;
            }
        });
    }

//    $( ".selector" ).popup( "option", "draggable", true );
//    $(document).on('pagebeforeshow', '#default', function(){
//        $('#myPopup').draggable({
//            cursor: 'move'
//        });
//    });

</script>
</body>
</html>