<%-- 
    Document   : asignar
    Created on : 16/12/2021, 07:07:16 PM
    Author     : rozo
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <title>Cotizacion</title>
    </head>
    <%
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        String userImg = (String) request.getSession().getAttribute("userImg");
        Map<String, Object> usuarios = (Map<String, Object>) request.getSession().getAttribute("usuarios");


    %>
    <body onload="load(1, 8)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">  
            </nav>
            <!-- Sidebar  -->
            <input  name="iduser"  id="iduser"  value="<%=user.get("idUsuario")%>" type="hidden">



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
                            <img src="/img/imagencliente.jpg<%//=userImg%>">                        
                        </div>

                    </div>
                </nav>

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <h4>Asignacion de cargos</h4>
                                </div>
                            </div>
                            <form  name="form4" action=" ../AssignEmployes.do?cod=1" method="post" > 
                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <h4>Empleados:</h4>
                                    </div>
                                    <div class="form-group col-md-4"> 
                                        <select class="form-control" name="idu" id="tematica" required>
                                            <option disabled selected >Seleccione</option>
                                            <%
                                                int i = 1;
                                                for (Map.Entry<String, Object> entry : usuarios.entrySet()) {
                                                    Map<String, String> map = (Map<String, String>) entry.getValue();
                                            %>

                                            <option  value = "<%=map.get("idUsuario")%>" > <%=map.get("nombres").toUpperCase() + " " + map.get("apellido1").toUpperCase()%> </option> 
                                            <%  } %>



                                        </select>
                                    </div>
                                    <div class="form-group col-md-4">
                                    </div>
                                </div>

                                <div class="form-row">

                                    <div class="form-group col-md-4">
                                        <h4>Tipo de cargo</h4>
                                    </div>

                                    <div class="form-group col-md-4">
                                        <select class="form-control" name="idt" id="tematica" required>
                                            <option disabled selected >Seleccione</option>
                                            <option  value = "2" > Gerente </option> 
                                            <option  value = "1" > Administrador </option> 
                                            <option  value = "5" > Administrativo y Financiero </option> 
                                            <option  value = "6" > Ingenieria </option> 
                                            <option  value = "7" > Soporte </option> 
                                            <option  value = "8" > Ventas</option> 
                                            <option  value = "9" > Tecnico</option> 
                                            <option  value = "10" >General</option> 

                                        </select>
                                    </div>
                                </div>

                                <div class="form-row">

                                    <div class="form-group col-md-4">
                                       
                                    </div> <div class="form-group col-md-4">
                                        <button type="submit" name="subir" class="btn btn-primary">Guardar</button>

                                    </div>



                                </div>
                            </form>


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
                                            <p><%=msg%></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal success -->   
                            <%
                                }

                                request.getSession().removeAttribute("msg");
                            %>


                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->

        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script> 
        <!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>-->

        <script src="../js/side-bar/admin/load-admin-1.0.js"></script> 
        <!-- jQuery Side-bar -->    

    </body>
</html>
