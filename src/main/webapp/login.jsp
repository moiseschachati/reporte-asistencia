<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
if (session.getAttribute("errorMessage") != null) { 
%>
<div class="alert alert-danger alert-dismissible fade in" align="center" id = "myAlert">
	<strong>Error!</strong> ${sessionScope.errorMessage}
</div>
<%
	session.removeAttribute("errorMessage");}
%>
				
<c:if test="${not empty requestScope.successMessage}" >
	<div class="alert alert-success alert-dismissable">
		${successMessage}
	</div>
	<c:remove var="successMessage" scope="request" />
</c:if>

<h3 class="text-center text-primary"> Inicio de Sesión </h3>

			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<form role="form" class="form-signin" action="checkauth" method="post">
						<div class="form-group">
							
							<label for="inputRUT">
								RUT
							</label>
							<input type="text" class="form-control" id="inputRUT" name="username"	placeholder="XXXXXXXX-X" required="" autofocus="" >
						</div>
						<div class="form-group">
							<label for="inputPassword">
								Contrase&ntilde;a
							</label>
							<input type="password" class="form-control" id="inputPassword" name="password" required="" />
						</div>
						<button type="submit" class="btn btn-primary btn-block">
						Ingresar
						</button>
					</form>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</div>
</div>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/scripts.js"></script>
<script type = "text/javascript">
   $(function(){
      $(".close").click(function(){
         $("#myAlert").alert();
      });
   });  
</script>   
</body>
</html>
