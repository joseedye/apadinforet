<%-- 
    Document   : ver_documentos
    Created on : 3/12/2021, 11:08:03 PM
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
        <title>Documentos ver</title>
    </head>
    <body onload="loadDocumentos()">
        <div class="wrapper">
            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                Map<String, String> userbuscado = (Map<String, String>) request.getSession().getAttribute("usuariobuscado");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> Lidocumentos = (Map<String, Object>) request.getSession().getAttribute("documentos");

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
                            <h5 class="card-header">Documentos del usuario <%=userbuscado.get("nombres")%></h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                        <form id="form1" action="../UploadFile.do" method="POST" enctype="multipart/form-data">

                                            <table class="table table-responsive-sm">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Descripción</th>
                                                        <th scope="col">fecha</th>
                                                        <th scope="col">Descargar</th>
                                                        <th scope="col">ver</th>
                                                        <th scope="col">Eliminar</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%= Lidocumentos.size()%> Documentos encontrados
                                                    <%
                                                        int i = 1;
                                                        for (Map.Entry<String, Object> entry : Lidocumentos.entrySet()) {

                                                            Map<String, String> map = (Map<String, String>) entry.getValue();

                                                    %>

                                                    <tr>
                                                        <td>
                                                            <label for=""><%=map.get("nombre")%></label>
                                                        </td>
                                                        <td>
                                                            <label for="" type="date" ><%=map.get("fecha")%></label>


                                                        </td>
                                                        <td>
                                                            <a download href="/<%=map.get("rutaDoc")%>" title="Descargar"><i class="fas fa-cloud-download-alt"></i></a>
                                                        </td>
                                                        <td>
                                                            <a onclick ='mostrarpdf("/<%=map.get("rutaDoc")%>")' title="Mostrar"><i class="fas fa-search"></i></a>
                                                        </td>
                                                        <td>
                                                            <a href="../DeleteDoc.do?idDoc=<%=map.get("idDoc")%>&&soy=<%=map.get("soy")%>&&idDue=<%=userbuscado.get("idUsuario")%>&&ruta=<%=map.get("rutaDoc")%>" title="Eliminar"><i class="fas fa-trash"></i></a>
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
                <script src="../js/side-bar/proveedor/load-proveedor-perfil.js"></script> 
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
