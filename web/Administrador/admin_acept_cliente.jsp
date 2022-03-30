<%-- 
    Document   : admin_acept_cliente
    Created on : 30/10/2021, 03:02:59 PM
    Author     : rozo
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            <% Map<String, String> usera = (Map<String, String>) request.getSession().getAttribute("user");
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user2");
                String userImg = (String) request.getSession().getAttribute("userImg");
            %>

            <!-- Page Content  -->
            <div id="content">
                <!-- NavBar  -->
                <%@include file="../modules/navbar_admin.jsp" %>
                <!-- NavBar  -->

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">
                           
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Nuevo Usuario</h4>
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

                                        <label for="">Género</label>
                                        <input type="text" name="Genero" class="form-control" id="" readonly=»readonly» value="<%=user.get("genero")%>" maxlength="15" required>


                                        <label for="">Pais</label>
                                        <input type="text" name="pais" class="form-control" id="" readonly=»readonly» value="<%=user.get("pais")%>" maxlength="100" required>



                                        <label for="">Teléfono</label>
                                        <input type="number" name="Tel1" pattern="[0-9]+" readonly=»readonly» class="form-control" id="" value="<%=user.get("telefono1")%>" required >
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="">Segundo Apellido</label>
                                        <input type="text" name="Ape2" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido2")%>" maxlength="15" required>

                                        <label for="">Email</label>
                                        <input type="email" name="Email" readonly=»readonly»  pattern="+.@ufps.edu.co" class="form-control" id="emaill" placeholder="@ufps.edu.co"value="<%=user.get("email")%>" maxlength="70" required>

                                        <label for="">Teléfono 2</label>
                                        <input type="number" name="Tel2" pattern="[0-9]+" readonly=»readonly» class="form-control" id="" value="<%=user.get("telefono2")%>" >
                                    </div>
                                </div>

                                <label for="">Dirección</label>
                                <input type="text" name="Dire" class="form-control" id="" readonly=»readonly» value="<%=user.get("direccion")%>" placeholder="Dirrección" maxlength="150">
                                <br></br>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Aceptar</a>
                                <a href="#ventana2"  class="btn btn-primary" data-toggle="modal">Pendiente</a>


                                <!-- Modal aceptar --> 
                                <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                             <form name="form3" action="../Notification.do?id=<%=user.get("idUsuario")%>&&redir=1" method="post">
                                            <div class="modal-header">
                                                <h5 class="modal-title">Alerta</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <p>Esta seguro que desea aceptar a este usuario</p>
                                            </div>
                                            <div class="modal-footer">            
                                                <button type="submit"  class="btn btn-primary">Aceptar</button>
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                            </div>
                                             </form>
                                        </div>
                                    </div>
                                </div>
                                <!-- Fin Modal aceptar--> 

                                <!-- Modal pendiente--> 
                                <div class="modal fade" id="ventana2" tabindex="-1" role="dialog">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form name="form2" action="../Notification.do?id=<%=user.get("idUsuario")%>&&redir=2" method="post">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Alerta</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>Agregar comentario</p>
                                                    <input type="text" name="com" class="form-control" id="" >

                                                </div>

                                                <div class="modal-footer">            
                                                    <button type="submit"  class="btn btn-primary">Aceptar</button>
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                </div>  
                                            </form>

                                        </div>
                                    </div>
                                </div>
                                <!-- Fin Modal Pendiente--> 
                            
                        </div>

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

        <script src="../js/side-bar/admin/load-admin-acepta.js"></script>
        <!-- jQuery Side-bar -->    

    </body>
</html>
