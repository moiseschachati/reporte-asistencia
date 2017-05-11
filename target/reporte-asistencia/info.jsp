<jsp:include page="header.jsp"></jsp:include>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
/**
 * Override feedback icon position
 * See http://formvalidation.io/examples/adjusting-feedback-icon-position/
 */
#eventForm .form-control-feedback {
    top: 0;
    right: -15px;
}
</style>
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
				<strong>Consultar informaci&oacute;n de asistencia</strong>
			</div>
			<form role="form" class="form-horizontal" id="eventForm">
				<br>
				<div class="form-group">
					<label for="dateofbirth" class="col-sm-3 control-label">Desde:</label>
					<div class="col-sm-8">
						<input type='text' class="form-control" id='datetimepicker' name="from"/>
					</div>
					<div class="col-sm-3"></div>
					<div class="col-sm-9 messageContainer"></div>
				</div>
				<div class="form-group">
					<label for="dateofbirth2" class="col-sm-3 control-label">Hasta:</label>
					<div class="col-sm-8">
						<input type='text' class="form-control" id='datetimepicker2' name="to"/>
					</div>
					<div class="col-sm-3">
					</div>
					<div class="col-sm-9 messageContainer">
					</div>
				</div>
				<!-- #messages is where the messages are placed inside -->
			    <div class="form-group">
			        <div class="col-md-9 col-md-offset-3">
			            <div id="messages"></div>
			        </div>
			    </div>
				<div class="form-group">
					<div class="col-sm-3">
					</div>
					<div class="col-sm-9">
						<button id="savenewappointment" type="button" class="btn btn-primary" >Consultar</button>
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
	<div class="col-md-2"></div>
	<div class="col-md-8">

		<div class="panel panel-primary">
			<!-- Default panel contents -->

			<div class="panel-heading text-center"><strong>Registro de asistencia</strong></div>
			<table id="records_table" class="table table-striped">
				<thead class="thead-inverse">
					<tr>
						<th>Fecha</th>
						<th>Hora</th>
						<th>Tipo Mov</th>
						<th>M&eacute;todo Verificaci&oacute;n</th>
						<th>C&oacute;digo Lector</th>
						<th>Reloj</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
		</div>

		<div class="col-md-2"></div>
	</div>
</div>
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
							trHTML += '<tr><td class="col-md-2">' + value.checkDate + '</td><td>'
							+ value.checkTime + '</td><td>'
							+ value.checkType + '</td><td>'
							+ value.verifyCode + '</td><td>'
							+ value.sn + '</td><td>'
							
							+ value.machineAlias
					'</td></tr>';
					console.log('checkDate: ' + value.checkDate
							+ 'checkTime: ' + value.checkTime
							+ ' checkType: ' + value.checkType
							+ ' machineAlias: ' + value.machineAlias
							+ ' metodo verficacion: ' + value.machineAlias
							+ ' codigo lector: ' + value.machineAlias);
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
			aoColumns : [
				{ "sType": "date-uk" },
	            null,
	            null,
	            null,
	            null,
	            null
	        ],
			language : {
				emptyTable : "No se encontro informacion de asistencia en el rango consultado",
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
<script type="text/javascript">
    $(function () {
        $('#datetimepicker').datetimepicker({
            format: 'DD/MM/YYYY',
            locale: 'es',
            useCurrent: false
      });
        $('#datetimepicker2').datetimepicker({
            format: 'DD/MM/YYYY',
            locale: 'es',
            useCurrent: false //Important! See issue #1075
        });
        $("#datetimepicker").on("dp.change", function (e) {
        	$('#eventForm').formValidation('revalidateField', 'from');
            $('#datetimepicker2').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker2").on("dp.change", function (e) {
        	$('#eventForm').formValidation('revalidateField', 'to');
            $('#datetimepicker').data("DateTimePicker").maxDate(e.date);
        });
    });
    $('#savenewappointment').click(function () {
        var start_date = $('#datetimepicker').data("DateTimePicker").date();
        var end_date = $('#datetimepicker2').data("DateTimePicker").date();
        
        if ($("#datetimepicker").val() && $("#datetimepicker2").val()){
        	//alert("is not an empty string"+start_date.format('YYYY-MM-DD')+ " " + end_date.format('YYYY-MM-DD'));
        	console.log('calling testfunction');
        	testFunction(start_date.format('YYYY-MM-DD'), end_date.format('YYYY-MM-DD'));
        }
    });
</script>

<script type="text/javascript">
$(document).ready(function() {
    $('#eventForm').formValidation({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            from: {
                validators: {
                    notEmpty: {
                        message: 'La fecha inicial es requerida'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'El formato de la fecha inicial no es valido'
                    }
                }
            },
            to: {
                validators: {
                    notEmpty: {
                    	format: 'DD/MM/YYYY',
                        message: 'La fecha final es requerida'
                    },
                    date: {
                        format: 'DD/MM/YYYY',
                        message: 'El formato de la fecha final no es valido'
                    }
                }
            }
        }
    })
    .on('success.field.fv', function(e, data) {
    	console.log('invalid fields: '+data.fv.getInvalidFields().length);
            if (data.fv.getInvalidFields().length > 0) {    // There is invalid field
            	console.log('invalid fields: '+data.fv.getInvalidFields().length);
                data.fv.disableSubmitButtons(true);
            }
        });
});

jQuery.extend( jQuery.fn.dataTableExt.oSort, {
	"date-uk-pre": function ( a ) {
	    var ukDatea = a.split('-');
		console.log('SORT date-uk-pre: ');
	    return (ukDatea[2] + ukDatea[1] + ukDatea[0]) * 1;
	},

	"date-uk-asc": function ( a, b ) {
		console.log('SORT date-uk-asc: ');
	    return ((a < b) ? -1 : ((a > b) ? 1 : 0));
	},

	"date-uk-desc": function ( a, b ) {
		console.log('SORT date-uk-desc: ');
	    return ((a < b) ? 1 : ((a > b) ? -1 : 0));
	}
	});
</script>
</body>
</html>