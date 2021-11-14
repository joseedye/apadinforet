<%-- 
    Document   : documento_nuevo
    Created on : 5/11/2021, 11:35:29 PM
    Author     : rozo
--%>


<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
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
        <title>Subida Documento</title>
    </head>
    <body onload="load(0,0)">
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
                            <h5 class="card-header">Subida de Archivos</h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                        <form id="form1" action="../UploadFile.do" method="POST" enctype="multipart/form-data">

                                            <table class="table table-responsive-sm">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Archivo</th>
                                                        <th scope="col">Descripción</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <input class="form-control-file" type="file" id="file1" name="archivo"/>
                                                        </td>
                                                        <td>
                                                            <input class="form-control" type="text" id="desc1" name="desc"/>
                                                        </td>


                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input class="form-control-file" type="file" id="file2" name="file2"/>
                                                        </td>
                                                        <td>
                                                            <input class="form-control" type="text" id="desc2" name="desc2"/>
                                                        </td>


                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input class="form-control-file" type="file" id="file3" name="file3"/>
                                                        </td>
                                                        <td>
                                                            <input class="form-control" type="text" id="desc3" name="desc3"/>
                                                        </td>

                                                    </tr>
                                                </tbody>
                                            </table>
                                            <a onclick="validar()"><input type="button" class="btn btn-group btn-primary" value="Subir Archivo(s)"/> </a>
                                        </form>   
                                    </div>
                                </div>
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
        <!--<script src="../js/side-bar/admin/load-admin-1.0.js"></script>--> 
        <!-- jQuery Side-bar -->        

        <script>
                                                function validar() {

                                                    if ($("#file1").val() === "" && $("#file2").val() === "" && $("#file3").val() === "") {
                                                        alert("Seleccione almenos un archivo");
                                                        return;
                                                    }

                                                    for (var i = 1; i < 4; i++) {
                                                        var file = $("#file" + i).val();
                                                        var tipo = $("#tipo" + i).val();

                                                        if (file !== "" && tipo === null) {
                                                            alert("Seleccione tipo de archivo");
                                                            return;
                                                        }
                                                    }
                                                    $("#form1").submit();
                                                }

                                                $(document).ready(function () {
                                                    $("#ventana2").modal('show');
                                                });
        </script>
    </body>
</html>
