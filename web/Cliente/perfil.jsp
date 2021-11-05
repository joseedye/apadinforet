<%-- 
    Document   : perfil
    Created on : 20/10/2021, 06:44:39 PM
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
    <%

        String icargar = (String) request.getSession().getAttribute("i");
    %>
    <body onload="loadPerfil(<%=icargar%>)">
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
                <nav  class="navbar navbar-expand-lg navbar-light bg-light">

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
                                        <h4>Perfil Cliente</h4>
                                    </div>
                                </div>


                                <div id="gSignIn"></div>


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

                                        <label for="">Tipo </label>
                                        <input type="text" name="Tipop" class="form-control" id="" readonly=»readonly» value="<%=user.get("tipop")%>" maxlength="15" required>


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
                                        <label for="">Razon social</label>
                                        <input type="text" name="razon" class="form-control" id="" readonly=»readonly» value="<%=user.get("razon")%>" required>

                                    </div>
                                </div>

                                <label for="">Reprecentante legal</label>
                                <input type="text" name="rep" class="form-control" id="" value="<%=user.get("reprecentante")%>"  maxlength="150">



                                <label for="">Dirección</label>
                                <input type="text" name="Dire" class="form-control" id="" value="<%=user.get("direccion")%>" placeholder="Dirrección" maxlength="150">
                                <br></br>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>
                                <a href="perfil" class="btn btn-primary">Cancelar</a>

                                <br></br>
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



                                <!-- Modal  confirmacion contraseña--> 
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

                            <br></br>
                            <br></br>
                            <br></br>

                            <div class="form-row">
                                <div class="form-group col-md-3">

                                    <label for="rut">Rut</label>
                                    <input class="form-control-file" type="file" id="file1" name="file1"/>
                                    <br></br>
                                    <label for="camara de comercio">Camara de comercio</label>
                                    

                                </div>
                                <div class="form-group col-md-6">


                                    <label for="camara de comercio">Camara de comercio</label>
                                    <input class="form-control-file" type="file" id="file1" name="file1"/>
                                    
                                   
                                </div>
                                <div class="form-group col-md-3">
                                  
                                    <label for="cedularep">Cedula de reprecentante legal</label>
 <input class="form-control-file" type="file" id="file1" name="file1"/>
                                </div>
                                <label>&nbsp;</label><br>
                                <button type="submit" name="subir" class="btn btn-primary">Subir</button>
                            </div>


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



                        <%
                            String msg2 = (String) request.getSession().getAttribute("msg2");
                            if (msg2 != null) {
                        %>

                        <!-- Modal success -->                        
                        <div class="modal fade" id="ventana3" tabindex="-1" role="dialog">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Mensaje</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <p> <%=msg2%> </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Modal success -->

                        <%   }
                            request.getSession().removeAttribute("msg2");
                        %>


                    </div>
                </div>
                <!-- Content  -->
            </div>
            <!-- Page Content  -->
        </div>

        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->

        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script> 
        <!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>-->

        <script src="../js/side-bar/cliente/load-cliente-perfil.js"></script>
        <!-- jQuery Side-bar -->    
        <script>
                                            $(document).ready(function () {
                                                $("#ventana2").modal('show');
                                            });
                                            $(document).ready(function () {
                                                $("#ventana3").modal('show');
                                            });

        </script> 
    </body>
</html>
