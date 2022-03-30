<%-- 
    Document   : solicitud_vista
    Created on : 3/12/2021, 07:32:13 PM
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
    <body onload="load(2, 6)">
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                Map<String, String> userbuscado = (Map<String, String>) request.getSession().getAttribute("usuariobuscado");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> solicitudes = (Map<String, Object>) request.getSession().getAttribute("solicitudes");
                Map<String, Object> empleados = (Map<String, Object>) request.getSession().getAttribute("empleados");

            %>

            <!-- Page Content  -->
            <div id="content">
                <!-- NavBar  -->
                <%@include file="../modules/navbar_default.jsp" %>
                <!-- NavBar  -->


                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="card my-4">
                            <h5 class="card-header">Solicitudes del usuario <%=userbuscado.get("nombres")%></h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                  

                                            <table class="table table-responsive-sm">
                                                <thead>
                                                    <tr>

                                                        <th scope="col">fecha</th>
                                                        <th scope="col">Tematica</th>
                                                        <th scope="col">estatus</th>
                                                        <th scope="col">Descripción</th>
                                                        <th scope="col">Asignar</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%= solicitudes.size()%> Solicitudes encontrados
                                                    <%
                                                        int i = 1;
                                                        for (Map.Entry<String, Object> entry : solicitudes.entrySet()) {

                                                            Map<String, String> map = (Map<String, String>) entry.getValue();

                                                    %>

                                                    <tr>

                                                        <td>
                                                            <label for="" type="date" ><%=map.get("fecha")%></label>
                                                        </td>
                                                        <td>
                                                            <label for="" type="text" ><%=map.get("tematica")%></label>
                                                        </td>
                                                        <td>
                                                            <label for="" type="text" ><%=map.get("decripciondetallada")%></label>
                                                        </td>
                                                        <td>
                                                            <label for=""><%=map.get("descripcion")%></label>
                                                        </td>
                                                        <td>

                                                            <select class="form-control"   onchange="asignar(<%=i%>)" id="empleados<%=i++%>">
                                                                <option  >Seleccionar</option>         
                                                                <%
                                                                    int j = 1;
                                                                    for (Map.Entry<String, Object> entrys : empleados.entrySet()) {

                                                                        Map<String, String> mapp = (Map<String, String>) entrys.getValue();

                                                                %>
                                                                <option value='<%= mapp.get("idUsuario")%>&&ids=<%= map.get("ids")%>&&idus=<%=map.get("idc") + ""%>' > <%= mapp.get("nombres")%></option>
                                                                <% }%>
                                                            </select>
                                                        </td>

                                                    </tr>

                                                </tbody>
                                                <% }%>
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
                <script src="../js/side-bar/gerente/load-gerente-1.0.js"></script> 
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