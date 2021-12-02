<%-- 
    Document   : solicitud_vista
    Created on : 2/12/2021, 12:05:20 AM
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
    <body onload="load(2, 7)">
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
                            <h5 class="card-header">Solicitudes del usuario <%=userbuscado.get("nombres")%></h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                        <form id="form1" action="../UploadFile.do" method="POST" enctype="multipart/form-data">

                                            <table class="table table-responsive-sm">
                                                <thead>
                                                    <tr>
                                                        
                                                        <th scope="col">fecha</th>
                                                        <th scope="col">Tematica</th>
                                                        <th scope="col">estatus</th>
                                                        <th scope="col">Descripción</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%= solicitudes.size()%> Documentos encontrados
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
                                                        <% }%>

                                                    </tr>

                                                </tbody>
                                            </table>
                                        </form>   
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
                <script src="../js/side-bar/admin/load-admin-1.0.js"></script> 
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