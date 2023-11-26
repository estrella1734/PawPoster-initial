<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${webName}</title>


<style type="text/css">
.backgroundImg {
	background-image: url('${root}/assets/pet.png');
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
}
</style>

</head>

<body>

	<jsp:include page="${component}/header.jsp"></jsp:include>
	
	<main>
		<div
			class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center backgroundImg">
			<div class="col-md-5 p-lg-5 mx-auto my-5">
				<h1 class="display-4 fw-normal text-light">PawPoster</h1>
				<p class="lead fw-normal text-light">我家的貓咪會寫JAVA!!</p>
				<a class="btn btn-warning" href="${root}/pets">快來康康!</a>
			</div>
		</div>
	</main>

	<jsp:include page="${component }/footer.jsp"></jsp:include>
</body>

</html>

