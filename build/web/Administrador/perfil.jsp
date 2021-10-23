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
                String userImg = (String) request.getSession().getAttribute("userImg");
            %>

            <!-- Page Content  -->
            <div id="content">
                <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">

                        <button type="button" id="sidebarCollapse" class="btn btn-info">
                            <i class="fas fa-align-left"></i>
                            <span>Menú</span>
                        </button>
                        <div>
                            <h5><%=user.get("nombres").toUpperCase() + ""%></h5>
                        </div>
                        <div class="img-profile">
                            <img src="/img/fotoadmin.jpg<%//=userImg%>">                        
                        </div>

                    </div>
                </nav>

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">
                            <form name="form1" action="../UpdateProfile.do" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Perfil Administrador</h4>
                                    </div>
                                </div>


                                <div id="gSignIn"></div>


                                <div class="form-row">
                                    <div class="form-group col-md-4">

                                        <label for="">Nombre</label>
                                        <input type="text" name="Nom" class="form-control" id="" placeholder="Nombre" value="<%=user.get("nombres")%>" maxlength="100" required>




                                        <label for="">Tipo Documento</label>
                                        <select class="form-control" name="Tipodoc" id="Tipodoc" required>

                                            <% if (user.get("tipoDocumento").equals("cc")) {%>
                                            <option selected value="cc" name="cc">C.C</option>
                                            <option value="ce" name="ce">C.E</option>
                                            <option value="nit" name="nit">NIT</option>
                                            <%}
                                                if (user.get("tipoDocumento").equals("ce")) {%>
                                            <option value="cc" name="cc">C.C</option>
                                            <option  selected value="ce"  name="ce">C.E</option>
                                            <option value="nit" name="nit">NIT</option>
                                            <%}
                                                 if (user.get("tipoDocumento").equals("nit")) {%>
                                            <option value="cc" name="cc">C.C</option>
                                            <option value="ce" name="ce">C.E</option>
                                            <option selected value="nit" name="nit">NIT</option>
                                            <%}%>
                                        </select>




                                        <label for="">Documento</label>
                                        <input type="number" name="Doc" pattern="[0-9]+" class="form-control"  readonly=»readonly» id="" placeholder="Documento" value="<%=user.get("numDocumento")%>" >

                                        <label for="">Fecha de Nacimiento</label>
                                        <input class="form-control" type="date" name="Fecha" value="<%=user.get("fecNacimiento") + ""%>" required>

                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="">Primer Apellido</label>
                                        <input type="text" name="Ape1" class="form-control" id="" placeholder="Primer Apellido" value="<%=user.get("apellido1") + ""%>" maxlength="15" required>

                                        <label for="">Género</label>
                                        <select class="form-control" name="Genero">
                                            <option disabled >Seleccionar</option>
                                            <% if (user.get("genero").equals("Masculino")) {%>
                                            <option selected value="Masculino" name="masculino">Masculino</option>
                                            <option value="Femenino" name="femenino">Femenino</option>
                                            <%} else { %>
                                            <option value="Masculino" name="masculino">Masculino</option>
                                            <option selected value="Femenino" name="femenino">Femenino</option>
                                            <%}%>
                                        </select>

                                        <label for="">Teléfono</label>
                                        <input type="number" name="Tel1" pattern="[0-9]+" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono" value="<%=user.get("telefono1")%>" required >
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="">Segundo Apellido</label>
                                        <input type="text" name="Ape2" class="form-control" id="" placeholder="Segundo Apellido" value="<%=user.get("apellido2")%>" maxlength="15" required>

                                        <label for="">Email</label>
                                        <input type="email" name="Email"  readonly=»readonly» pattern="+.@ufps.edu.co" class="form-control" id="emaill" placeholder="@ufps.edu.co"value="<%=user.get("email")%>" maxlength="70" required>

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
                                <br></br>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>
                                <a href="perfil" class="btn btn-primary">Cancelar</a>


                                <!-- Modal --> 
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
                                <!-- Modal  --> 
                            </form>
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
                        </div>


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
        <script src="../js/side-bar/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/popper.min.js"></script>
        <script src="../js/side-bar/menu-button.js"></script>
        <script src="../js/side-bar/bootstrap.min.js"></script> 
        <script src="../js/side-bar/load-admin-1.0.js"></script>
        <!-- jQuery Side-bar -->    
        <script>
                                            $(document).ready(function () {
                                                $("#ventana2").modal('show');
                                            });
        </script> 

    </body>
</html>
