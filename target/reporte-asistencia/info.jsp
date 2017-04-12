<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<!-- Default panel contents -->
			<div class="panel-heading text-center">
				<strong>Informaci&oacute;n del Empleado</strong>
			</div>
			<table class="table table-striped table-condensed">
				<tbody>
					<tr>
						<td><strong>Empresa:</strong></td>
						<c:forEach items="${companies}" var="company">
							<td>${company.companyName}</td> 
						</c:forEach>
					</tr>
					<tr>
						<td><strong>RUT Empresa:</strong></td>
						<c:forEach items="${companies}" var="company">
							<td>${company.companyRut}</td> 
						</c:forEach>
					</tr>
					<tr>
						<td><strong>Dirección</strong></td>
						<c:forEach items="${companies}" var="company">
							<td>${company.companyAddress}</td> 
						</c:forEach>
					</tr>
					<tr>
						<td><strong>Empleado:</strong></td>
						<td>${employeeName}</td>
					</tr>
					<tr>
						<td><strong>RUT:</strong></td>
						<td>${employeeRut}</td>
					</tr>
					<tr>
						<td><strong>Departamento:</strong></td>
						<td>${employeeDepartment}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="col-md-3"></div>
</div>
<hr
	style="border: 0 !important; height: 1px !important; background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important; background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important; background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important; background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important;">
<div class="row">
	<div class="col-md-12"></div>
</div>
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">
		<div class="panel panel-primary">
			<!-- Default panel contents -->
			<div class="panel-heading text-center">
				<strong>Buscar informaci&oacute;n de asistencia</strong>
			</div>
			<form role="form" class="form-horizontal">
				<div class="form-group">
					<label for="dateofbirth" class="col-sm-3 control-label">Rango
						de Fechas:</label>
					<div class="col-sm-9">
						<input class="form-control" type="text" name="datefilter" value=""
							placeholder="Seleccione Rango de Fechas a Consultar" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="col-md-3"></div>
</div>
<hr
	style="border: 0 !important; height: 1px !important; background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important; background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important; background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important; background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0) !important;">
<div class="row">
	<div class="col-md-3"></div>
	<div class="col-md-6">

		<div class="panel panel-primary">
			<!-- Default panel contents -->

			<div class="panel-heading text-center">Registro de asistencias
			</div>
			<table id="records_table" class="table table-striped">
				<thead class="thead-inverse">
					<tr>
						<th>Fecha</th>
						<th>Hora</th>
						<th>Entrada / Salida</th>
						<th>Reloj</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
		</div>

		<div class="col-md-3"></div>
	</div>
</div>
<script type="text/javascript">
	$('input[name="datefilter"]').daterangepicker(
			{
				alwaysShowCalendars : true,
				autoUpdateInput : false,
				locale : {
					format : 'YYYY-MM-DD',
					separator : ' | ',
					applyLabel : 'Consultar',
					cancelLabel : 'Cancelar',
					monthNames : [ 'enero', 'febrero', 'marzo', 'abril',
							'mayo', 'junio', 'julio', 'agosto', 'septembre',
							'octubre', 'noviembre', 'diciembre' ],
				}
			},
			function(start, end, label) {
				console.log("A new date range was chosen: "
						+ start.format('YYYY-MM-DD') + ' to '
						+ end.format('YYYY-MM-DD'));
				//loadXMLDoc(start.format('YYYY-MM-DD'), end.format('YYYY-MM-DD'));
				testFunction(start.format('YYYY-MM-DD'), end
						.format('YYYY-MM-DD'));
			});

	$('input[name="datefilter"]').on(
			'apply.daterangepicker',
			function(ev, picker) {
				$(this).val(
						picker.startDate.format('YYYY-MM-DD') + ' | '
								+ picker.endDate.format('YYYY-MM-DD'));
			});

	$('input[name="datefilter"]').on('cancel.daterangepicker',
			function(ev, picker) {
				$(this).val('');
			});
</script>
<!-- <script type="text/javascript">
	function loadXMLDoc(start, end) {
		var xmlhttp;
		var config = $('input[name="datefilter"]').daterangepicker({})[0].value;
		var url = "grid";
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				document.getElementById("records_table").innerHTML = xmlhttp.responseText;
			}
		}

		xmlhttp.open("GET", url + "?" + start + "." + end, true);
		xmlhttp.send();
	}
</script> -->

<!-- 		<script type="text/javascript">
		function testFunction(start, end)
	      {// When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		    $.get("grid"+"?"+start + "." + end, function(responseJson) {          // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
		        var $table = $("<table>").appendTo($("#myDiv")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
		        $.each(responseJson, function(index, product) {    // Iterate over the JSON array.
		        	
		        	$("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
		                .append($("<td>").text(product.userId))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
		                .append($("<td>").text(product.checkTime))      // Create HTML <td> element, set its text content with name of currently iterated product and append it to the <tr>.
		                .append($("<td>").text(product.machineAlias)
		                .append("<tr>"));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
		        	console.log('checkTime: ' + product.checkTime + ' checkType: ' + product.checkType + ' machineAlias: ' + product.machineAlias);
		        });
		    });
		}
		</script> -->
<script>
	function testFunction(start, end) {
		$.ajax({
			url : "grid" + "?" + start + "." + end,
			type : "GET",
			dataType : "json",
			success : function(response) {
				var trHTML = '';
				//remove previous data
				var T= document.getElementById("records_table");
				console.log('T: ' + T);
				var B=T.getElementsByTagName('tbody');
				var L=B.length;
				while(L)T.removeChild(B[--L]);
				
				trHTML += '<tbody>';
				$.each(response, function(key, value) {
							trHTML += '<tr><td>' + value.checkDate + '</td><td>'
							+ value.checkTime + '</td><td>'
							+ value.checkType + '</td><td>'
							+ value.machineAlias
					'</td></tr>';
					console.log('checkDate: ' + value.checkDate
							+ 'checkTime: ' + value.checkTime
							+ ' checkType: ' + value.checkType
							+ ' machineAlias: ' + value.machineAlias);
				});
				trHTML += '</tbody>';
				$('#records_table').append(trHTML);
				console.log('this is it: ' + trHTML);
				testFunction2();
			}
		});
	}
</script>

<script>
	function testFunction2() {		
		console.log('calling dataTable');
		$('#records_table').DataTable({
			destroy: true,
			pagingType : 'full',
			searching : false,
			language : {
				emptyTable : "No se encontr$oacute; informaci$oacute;n de asistencia en el rango consultado",
				info : "Mostrando _START_ a _END_ de _TOTAL_ registros",
				lengthMenu : "Mostrar _MENU_ registros",
				paginate : {
					first : '<<',
					previous : '<',
						next:     '>',
					last : '>>'
				},
				aria : {
					paginate : {
						first : 'Primero',
						previous : 'Anterion',
						next : 'Siguiente',
						last : 'Ultimo'
					}
				}
			}
		});
	}
</script>
</body>
</html>