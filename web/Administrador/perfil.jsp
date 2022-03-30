<%-- 
    Document   : perfil
    Created on : 9/10/2021, 01:09:35 PM
    Author     : rozo
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Sidebar style  -->
        <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="../css/side-bar/style-side-bar.css">
        <script defer src="../font/solid.js"></script>
        <script defer src="../font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Perfil</title>
    </head>
    <body onload="loadPerfil()">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">  
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
            %>

            <!-- Page Content  -->
            <div id="content">
                <!-- NavBar  -->
                <%@include file="../modules/navbar_admin.jsp" %>
                <!-- NavBar  -->

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <!--div contenedor-->
                        <div class="contenedor">

                            <!--formulario datos personales-->
                            <form name="form1" action="../UpdateProfile.do" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Perfil Administrador</h4>
                                    </div>
                                </div>


                                <div id="gSignIn"></div>

                                <!--datos personales-->
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="">Nombre</label>
                                        <input type="text" name="Nom" class="form-control" readonly=»readonly» id="" placeholder="Nombre" value="<%=user.get("nombres").toUpperCase() + ""%>" maxlength="100" required>

                                        <label for="">Tipo Documento</label>
                                        <input type="text" name="Tipodoc" class="form-control" readonly=»readonly» id=""  value="<%=user.get("tipoDocumento")%>" maxlength="100" required>

                                        <label for="">Documento</label>
                                        <input type="number" name="Doc" pattern="[0-9]+" class="form-control"  readonly=»readonly» id="" placeholder="Documento" value="<%=user.get("numDocumento")%>" >

                                        <label for="">Fecha de Nacimiento</label>
                                        <input class="form-control" type="date" readonly=»readonly» name="Fecha" value="<%=user.get("fecNacimiento") + ""%>" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="">Primer Apellido</label>
                                        <input type="text" name="Ape1" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido1") + ""%>" maxlength="15" required>

                                        <label for="">Género</label>
                                        <input type="text" name="Genero" class="form-control" id="" readonly=»readonly» value="<%=user.get("genero")%>" maxlength="15" required>


                                        <label for="">Pais</label>
                                        <input type="text" name="pais" class="form-control" id="" readonly=»readonly» value="<%=user.get("pais")%>" maxlength="100" required>



                                        <label for="">Teléfono</label>
                                        <input type="number" name="Tel1" pattern="[0-9]+" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono" value="<%=user.get("telefono1")%>" required >
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="">Segundo Apellido</label>
                                        <input type="text" name="Ape2" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido2")%>" maxlength="15" required>

                                        <label for="">Email</label>
                                        <input type="email" name="Email"  pattern="+.@ufps.edu.co" class="form-control" id="emaill" placeholder="@ufps.edu.co"value="<%=user.get("email")%>" maxlength="70" required>

                                        <label for="">Teléfono 2</label>
                                        <input type="number" name="Tel2" pattern="[0-9]+" class="form-control" id="" value="<%=user.get("telefono2")%>" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono">
                                    </div>
                                </div>


                                <label for="">Dirección</label>
                                <input type="text" name="Dire" class="form-control" id="" value="<%=user.get("direccion")%>" placeholder="Dirrección" maxlength="150">
                                <!--datos personales-->
                                <br>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>
                                <a href="perfil" class="btn btn-primary">Cancelar</a>
                            </form>
                            <!--formulario datos personales-->

                            <!-- Modal set datos personales--> 
                            <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Alerta</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Esta seguro de modificar sus datos personales?</p>
                                        </div>
                                        <div class="modal-footer">            
                                            <button type="submit"  class="btn btn-primary">Si</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal set datos personales--> 
                            
                            <!--cambio foto perfil-->
                            <%@include file="../modules/actualizacion_foto.jsp" %>
                            <!--cambio foto perfil-->
                            
                            <!-- Formulario cambio contraseña-->                    
                            <form  name="form2" action="../UpdatePassword.do" method="post">
                                <div class="form-row changPassword">
                                    <div class="form-group col-md-4">
                                        <label for="exampleInputPassword1">Contraseña anterior</label>
                                        <input type="password" name="ContraAnt" class="form-control" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="exampleInputPassword1">Contraseña nueva</label>
                                        <input type="password" name="ContraNueva" class="form-control" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label>&nbsp;</label><br>
                                        <button type="submit" name="Cambiar" class="btn btn-primary">Cambiar</button>
                                    </div>
                                </div>
                            </form>
                            <!-- Formulario cambio contraseña-->                            
                        </div>
                        <!--div contenedor-->

                        <%
                            String msg = (String) request.getSession().getAttribute("msg");
                            if (msg != null) {
                        %>

                        <!-- Modal success -->                        
                        <div class="modal fade" id="ventana2" tabindex="-1" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Mensaje</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p> <%=msg%> </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal success -->

                        <%   }
                            request.getSession().removeAttribute("msg");
                        %>
                    </div>
                </div>
                <!-- Content  -->
            </div>
            <!-- Page Content  -->
        </div>

        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script> 
        <script src="../js/side-bar/admin/load-admin-1.0.js"></script>
        <!-- jQuery Side-bar -->    
        <script>
                                            $(document).ready(function () {
                                                $("#ventana2").modal('show');
                                            });

        </script> 
    </body>
</html>
