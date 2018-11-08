<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

  <link rel="stylesheet" href="CustomerHeader.css">

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top navbar-custom">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
	  <form class="navbar-form navbar-left" action="search">
	    <div class="input-group col-lg-12">
	      <input type="text" class="form-control" id="search" placeholder="Search for product, brand and more" >
	      <div class="input-group-btn">
		<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
	      </div>
	    </div>
	  </form>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#"> Anantharaj &nbsp;<span class="glyphicon glyphicon-chevron-down"></span></a></li>
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Login & SignUp</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
    </ul>
  </div>
</nav>
  


</body>
</html>
