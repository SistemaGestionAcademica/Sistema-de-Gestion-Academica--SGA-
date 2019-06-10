<%-- 
    Document   : Alumnos
    Created on : 16/04/2019, 01:53:08 PM
    Author     : Usuario1
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidades.Alumno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.7/dist/css/bootstrap-select.min.css">
        
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.7/dist/js/bootstrap-select.min.js"></script>
        
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        
        
        <link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="shortcut icon" type="image/x-icon" href="../test/favicon.ico"/>
        
        
            <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


    <link type="text/css" href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css" rel="stylesheet" />
    <script src="~/Scripts/jquery-ui-1.12.1.min.js"></script>
    <script src="~/Scripts/jquery-ui-1.12.1.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" rel="Stylesheet"></>
    <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
    <link href="~/Content/css/style.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   
    <script src="~/Content/js/site.js"></script>
        <a></a>
        <style>
            .col1{
                text-align: center;
                width: 225px
            }
        </style>
        <title>Mantenimiento de alumnos</title>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" style="color:#FFFFFF;">Sistema de Gestion Academica</a>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="Alumnos.jsp">Alumnos <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Profesores.jsp">Profesores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Carreras.jsp">Carreras</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Cursos.jsp">Cursos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Ciclos.jsp">Ciclos</a>
                </li>
                <li class="nav-item">
                        <a class="nav-link" href="Grupos.jsp">Grupos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ofertaAcademica.jsp">Oferta Académica</a>
                </li>
                <li class="mav-item">
                    <a class="nav-link bordered" href="index.html" style="
                       margin-left: 430px;">Principal</a>
                </li>
                </ul>
            </div>
        </nav>
    </head>
    <body>
        <jsp:useBean id="cn" class="Controller.Alumnos" scope="page"></jsp:useBean>
        
        <div class="container">
            <div class="text-center">
                <h1>Mantenimiento Alumnos</h1>
            </div>
            <div class="row">
                <div class="col-6">
                    <form style="margin-left:25px" class="form-inline my-2 my-lg-0" action="Alumnos" method="POST">
                        <br>
                            <br>
                            <select class="selectpicker" title="Seleccione una opción." name="options">
                                <option value="cedula">Cedula</option>
                                <option value="nombre">Nombre</option>
                            </select>
                            <br>
                        <br>
                        <input class="form-control mr-sm-2" type="search" name="idBusqueda" placeholder="Search" aria-label="Search" autocomplete="off">
                        <button class="btn btn-outline-success my-2 my-sm-0" value="buscar" style="border: none; background-color: #e7e7e7; color: black;" name="action" type="submit">Buscar</button>
                    </form>
                </div> <!-- class="col-6" -->
            </div> <!-- class row -->
            
            <div class="row">
                <div class="col">
                    <br>
                    <table class="table table-striped table-hover table-condensed">
                        <thead>
                            <tr>
                                <td>Identificación</td>
                                <td>Nombre</td>
                                <td>Teléfono</td>
                                <td>Email</td>
                                <td>Fecha de nacimiento</td>
                            </tr>
                        </thead>
                    <%
                        if(request.getAttribute("alumnoListar") != null){
                        
                        List<Alumno> alumnoLista = (List<Alumno>) request.getAttribute("alumnoListar");
                    %>
                    
                    <tbody>
                        <tr>
                            <%
                                for(Alumno alu: alumnoLista){
                            %>
                            <td id='alumnoCedula'>
                                <%
                                    out.println(alu.getCedula());
                                %>
                            </td>
                            <td id='alumnoNombre'>
                                <%
                                    out.println(alu.getNombre());
                                %>                            
                            </td>
                            <td id='alumnoTelefono'>
                                <%
                                    out.println(alu.getTelefono());
                                %>
                            </td>
                            <td id='alumnoEmail'>
                                <%
                                    out.println(alu.getEmail());
                                %>
                            </td>
                            <td id='alumnoFecha'>
                                <%
                                    out.println(alu.getFecha());
                                %>
                            </td>
                            <td>
                                <form action="Alumnos" method="POST">
                                    <input hidden="true"  value="<% out.println(alu.getCedula());%>" id="deleteValue" name="cedula" type="text"/>
                                    <button class="btn btn-danger" id="btneliminar"type="button" onclick="clickDeleteButton('<%=alu.getCedula()%>')">Eliminar</button>
                                    <input hidden="true"  value="<%out.println(alu.getCedula());%>" name="cedula" type="text" />
                                    <button class="btn btn-info" value="editar" style="background-color: #555555; border:none" name="action" type="submit" data-toggle="model" data-target="#mEditar">Editar</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                    <%
                            }
                        }
                    %>
                    </table>
                    <br>
                    <br>
                    <button type="button" class="btn btn-info btn-lg"   style="background-color: #555555; border:none" data-toggle="modal" data-target="#mIngresar">Ingresar</button>
                </div> <!-- col -->
            </div> <!-- row -->
            
            <div class="text-center" style="display:none;">
                <button id="failedButton" href="#failedModal" class="trigger-btn" data-toggle="modal">FailedModal</button>
                <button id="deleteButton" href="#deleteModal" class="trigger-btn" data-toggle="modal">DeleteModal</button>
                <button id="successButton" href="#succesModal" class="trigger-btn" data-toggle="modal">SuccessModal</button>
                <button id="editButton" href="#mEditar" class="trigger-btn" data-toggle="modal">EditarModal</button>
            </div>
            
            <div id="mIngresar" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4>Agregar Alumno</h4>
                        </div><!-- HEADER -->
                        <div class="modal-body">
                            <form action="Alumnos" method="POST">
                                
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Cedula</label>
                                    <div class="col-sm-10">
                                        <input pattern=".{8,}" required title="Minimo 8 caracteres." class="form-control" type="text" name="cedula" 
                                                placeholder="Cédula del Alumno(a)" required="true" autocomplete="off"
                                                oninvalid="this.setCustomValidity('La cédula es necesaria.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Nombre</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" type="text" name="nombre" placeholder="Nombre del Alumno(a)" required="true" autocomplete="off"
                                                oninvalid="this.setCustomValidity('El nombre es necesario.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                                
                                
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Telefono</label>
                                    <div class="col-sm-10">
                                        <input pattern=".{8,}" required title="Minimo 8 caracteres." class="form-control" type="number" name="telefono" 
                                                placeholder="Teléfono del Alumno(a)" required="true" autocomplete="off"
                                                oninvalid="this.setCustomValidity('El número de télefono es necesario.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" type="email" name="email" placeholder="Correo electrónico del Alumno(a)" required="true" autocomplete="off"
                                                oninvalid="this.setCustomValidity('El correo es necesario.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Fecha de Nacimiento</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" type="date" name="fecha" placeholder="Fecha de Nacimiento necesaria" required="true" autocomplete="off"
                                            oninvalid="this.setCustomValidity('La fecha es necesaria.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                        </div><!-- BODY -->
                        <div class="modal-footer">
                            <button class="btn btn-primary" value="crear"  style="background-color: #555555; border:none" name="action" type="submit">Ingresar</button>
                            </form>
                            <button class="btn btn-danger" data-dismiss="modal" type="button">Cerrar</button>
                        </div><!-- FOOTER -->
                    </div><!-- content -->
                </div> <!-- modal-dialog -->
            </div> <!-- INGRESAR-->
            
            <!-- Manejo de Modales-->
            <div id="succesModal" class="modal fade">
                <div class="modal-dialog modal-confirm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="icon-box">
                            </div>
                            <h4 class="modal-title">¡Bien hecho!</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
                        </div>
                        <div class="modal-body text-center">
                            <p id="labelSuccess"><big>Alumno(a)<strong id="nombreAlumno">DEFAULT</strong> ha sido agregado con exito.</big></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-success" data-dismiss="modal"><span>Continuar</span> </button>
                        </div>
                    </div>
                </div>
            </div> <!-- SUCCESS MODAL -->
            
            <div id="failedModal" class="modal fade">
                <div class="modal-dialog modal-confirm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="icon-box">
                            </div>
                            <h4 class="modal-title">¡La accion ha fallado!</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body text-center">	
                            <p id="labelFailed"><big>Ha ocurrido un <strong>error.</strong> Intentelo de nuevo.</big></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-success" data-dismiss="modal"><span>Continuar</span> </button>
                        </div>
                    </div>
                </div>
            </div><!-- Modal failed -->
            
            <div id="deleteModal" class="modal fade"> <!-- -->
                <div class="modal-dialog modal-confirm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <div class="icon-box">
                            </div>				
                            <h4 class="modal-title">Eliminar Alumno</h4>	
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <p><big>¿Desea eliminar a <strong id='aluName'></strong>?</big></p>
                        </div>
                        <div class="modal-footer">
                            <form action="Alumnos" method="POST">
                                <input hidden="true" id="valueDelete" name="cedula" type="text"/>
                                <button type="button" class="btn btn-info" data-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-danger" value="eliminar" name="action">Eliminar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div> <!-- ELIMINAR MODAL-->
            
            <div id="mEditar" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar Alumno</h4>
                        </div>
                        <%
                            if(request.getAttribute("alumnoEditar") != null){
                                Alumno alumno = (Alumno)request.getAttribute("alumnoEditar");
                        %>
                        <div class="modal-body">
                            <form action="Alumnos" method="POST">
                                
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Cedula</label>
                                    <div class="col-sm-10"><!-- disabled="disabled" -->
                                        <input readonly pattern=".{8,}" required title="Minimo 8 caracteres." class="form-control" type="text" name="cedula" 
                                               value="<% out.println(alumno.getCedula());%>"
                                               placeholder="Cédula del Alumno(a)" required="true" autocomplete="off"
                                                oninvalid="this.setCustomValidity('La cédula es necesaria.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                                               
                                <div class="form-group row">
                                    <label  class="col-sm-2 col-form-label">Nombre</label>
                                    <div class="col-sm-10">
                                      <input class="form-control" type="text" value="<% out.println(alumno.getNombre()); %>" name="nombre" placeholder="Nombre del alumno(a)" 
                                             required="true"autocomplete="off"
                                             oninvalid="this.setCustomValidity('El nombre es necesario.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                            <% 
                                int valueTelefono=0;
                                try
                                {
                                    valueTelefono = Integer.parseInt(alumno.getTelefono());
                                }
                                catch(Exception ex){}
                            %>

                                <div class="form-group row">
                                    <label  class="col-sm-2 col-form-label">Email</label>
                                    <div class="col-md-10">
                                      <input class="form-control" type="email" name="email" placeholder="Correo electrónico del profesor(a)" required="true" autocomplete="off"
                                             value="<% out.println(alumno.getEmail()); %>"
                                             oninvalid="this.setCustomValidity('El correo es necesario.')" oninput="setCustomValidity('')"/>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label  class="col-sm-2 col-form-label">Teléfono</label>
                                    <div class="col-sm-10">
                                      <input pattern=".{8,}" required title="Minimo 8 caracteres." class="form-control" type="number" name="telefono" 
                                             value="<%=valueTelefono%>"
                                             placeholder="Teléfono del profesor(a)" required="true" autocomplete="off"
                                             oninvalid="this.setCustomValidity('El número de télefono es necesario.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-sm-2 col-form-label">Fecha de Nacimiento</label>
                                    <div class="col-sm-10">
                                        <input class="form-control" type="date" name="fecha" value="<%= alumno.getFecha() %>" placeholder="Fecha de Nacimiento necesaria" 
                                               required="true" autocomplete="off"
                                                oninvalid="this.setCustomValidity('La fecha es necesaria.')" oninput="setCustomValidity('')">
                                    </div>
                                </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" value="editar"  style="background-color: #555555; border:none" name="action" type="submit">Editar</button>
                            </form>
                            <button class="btn btn-danger" data-dismiss="modal" type="button" >Cerrar</button>    
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
                    
            <%
                if(request.getAttribute("alumnoEditar") != null){
            %>
            <script>
                $("#editButton").click();
            </script>
            <%
                }
                String result = "default";
                if(request.getAttribute(result) != null){
                    result = (String) request.getAttribute("result");
                    if(result == "ExitoCrear"){
                        String variable = ((String)request.getAttribute("value")).replace("\n","");
            %>
            <script>
                var nombreAlumno = "Alumno(a) <strong>"+"<%=variable%>"+"</strong> se ha creado con exito";
                $('#labelSuccess').text("");
                $('#labelSuccess').append(nombreAlumno);
                $("#successButton").click();
            </script>
            <%
                }else if(result =="ErrorCrear"){
                    String variable = "Error al crear el Alumno(a)";
            %>
            <script>
                var nombreAlumno = "<%=variable%>";
                $('#labelFailed').text("");
                $('#labelFailed').append(nombreAlumno);
                $("#failedButton").click();
            </script>
            <%
                }else if(result == "ExitoEliminar"){
                    String variable = "Exito al eliminar Alumno";
            %>
            <script>
                var nombreAlumno = "<%=variable%>";
                $('#labelSuccess').text(nombreAlumno);
                $("#successButton").click();
            </script>
            <%
                }else if(result == "ErrorEliminar"){
                    String variable = "Error al eliminar Alumno";
            %>
            <script>
                var nombreAlumno = "<%=variable%>";
                $('#labelFailed').text("");
                $('#labelFailed').append(nombreAlumno);           
                $("#failedButton").click();
            </script>
            <%
                }else if(result == "ExitoEditar"){
                    String variable = ((String)request.getAttribute("value")).replace("\n","");
            %>
            <script>
                    var nombreAlumno = "Alumno(a) <strong>"+"<%=variable%>"+"</strong> editado con exito.";
                    $('#labelSuccess').text("");
                    $('#labelSuccess').append(nombreAlumno);           
                    $("#successButton").click();
            </script>
            <%
                }else if(result == "ErrorEditar"){
                    String variable = "Error al editar el profesor";
            %>
            <script>
                var nombreAlumno = "<%=variable%>";
                $('#labelFailed').text("");
                $('#labelFailed').append(nombreAlumno);           
                $("#failedButton").click();
            </script>
            <%
                    }else{

                    }
                }
            %>
        </div> <!-- div container -->
    </body>
</html>
<script>
    function clickDeleteButton(cedula){
        document.getElementById('valueDelete').value = cedula;
        $('#aluName').text(cedula);
        $('#deleteButton').click();
    }
</script>