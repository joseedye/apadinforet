<%-- 
    Document   : solicitud_vista
    Created on : 12/12/2021, 03:38:04 PM
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
        <title>vista solicitud</title>
    </head>
    <body onload="loadSolicitudes(2)">
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, String> solicitudes = (Map<String, String>) request.getSession().getAttribute("solicitud");

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
                            <h5><%=user.get("nombres") + ""%></h5>
                            <input  name="iduser"  id="iduser"  value="<%=user.get("idUsuario")%>" type="hidden">

                        </div>
                        <div class="img-profile">
                            <img src="<%=userImg%>">                          
                        </div>
                    </div>
                </nav>


                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="card my-4">
                            <h5 class="card-header">Solicitud </h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">



                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>

                                                    <th scope="col">fecha</th>
                                                    
                                                    <th scope="col">Descripción</th>
                                                    <th scope="col">Tematica</th>

                                                </tr>
                                            </thead>

                                            <tbody>
                                                <tr>  
                                                    <td >

                                                        <label for="" type="date" ><%=solicitudes.get("fecha")%></label>                                                      

                                                    </td>

                                                    <td >
                                                      
                                                         <label for=""><%=solicitudes.get("descripcion")%></label>
                                                    </td>

                                                    <td >
                                                         <label for="" type="text" ><%=solicitudes.get("tematica")%></label>
                                                    </td>

                                                </tr>

                                                <tr>
                                                    <td colspan="3">

                                                        <textarea class="form-control" id="mensaje" name="message" placeholder="Enter your message here..." style="height: 10rem" data-sb-validations="required"></textarea>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input  name="oculto" type="hidden" >                                                                                                            

                                                    </td>
                                                    <td>
                                                        <input type="file" id="getuno" name="archivo" >                                                                                                            

                                                    </td>
                                                    <td>

                                                        <button type="submit" onclick="enviarsol()" name="subir" class="btn btn-primary">Enviar</button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
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

                <!-- jQuery Side-bar -->
                <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
                <script src="../js/side-bar/extra/popper.min.js"></script>
                <script src="../js/side-bar/extra/menu-button.js"></script>
                <script src="../js/side-bar/extra/bootstrap.min.js"></script>   
                <script src="../js/side-bar/empleado/load-empleado-perfil.js"></script> 
                <!-- jQuery Side-bar -->  

                <script>

        function mostrarpdf(ruta) {

            window.open(ruta);
        }

        $(document).ready(function () {
            $("#ventana2").modal('show');
        });

                </script>




                </body>
                </html>