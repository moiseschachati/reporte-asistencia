<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
				<% if (session.getAttribute("errorMessage") != null) { %>
				<div class="alert alert-danger alert-dismissible fade in" align="center" id = "myAlert">
					<!-- <a href="#" class="close" data-dismiss="alert" aria-label="cerrar">&times;</a> -->
					<strong>Error!</strong> ${sessionScope.errorMessage}
				</div>
				<%
				session.removeAttribute("errorMessage");
				} %>
				
				<c:if test="${not empty requestScope.successMessage}" >
				<div class="alert alert-success alert-dismissable">
					<!--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">
						×
					</button> -->
					${successMessage}
				</div>
				<c:remove var="successMessage" scope="request" />
				</c:if>
				
				 

				<h3 class="text-center text-primary">
					Inicio de Sesión
				</h3>
				<div class="row">
					<div class="col-md-4">
					</div>
					<div class="col-md-4">
						<form role="form" class="form-signin" action="checkauth" method="post">
							<div class="form-group">
								
								<label for="exampleInputEmail1">
									RUT
								</label>
								<input type="text" class="form-control" id="exampleInputEmail1" name="username"	placeholder="XXXXXXXX-X" required="" autofocus="" >
							</div>
							<div class="form-group">
								
								<label for="exampleInputPassword1" value = "6pmdrd69">
									Contraseña
								</label>
								<input type="password" class="form-control" id="exampleInputPassword1" name="password" required="" />
							</div>
							<button type="submit" class="btn btn-primary btn-block">
							Ingresar
							</button>
						</form>
					</div>
					<div class="col-md-4">
					</div>
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
