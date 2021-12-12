<%-- 
    Document   : documento_nuevo
    Created on : 7/12/2021, 08:41:36 AM
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
    <body onload="loadDocumentos()">
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

                            <input  name="iduser"  id="iduser"  value="<%=user.get("idUsuario")%>" type="hidden">

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
                                        <table class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Descripción</th>

                                                    <th scope="col">Ver</th>

                                                    <th scope="col">Archivo</th>


                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <input class="form-control" type="text" id="desc1" name="desc"/>
                                                    </td>

                                                    <td style="text-align:center;" >
                                                        <a onclick ='mostrarpdf("uno")' title="Mostrar" ><i class="fas fa-search" ></i></a>
                                                        <div id="uno" style="display:none"></div>

                                                    </td>

                                                    <td>
                                                        <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('getuno').click()">Subir</button>
                                                    </td> 

                                                </tr>
                                                <tr>
                                                    <td>
                                                        <input class="form-control" type="text" id="desc2" name="desc2"/>
                                                    </td>
                                                    <td style="text-align:center;" >
                                                        <a onclick ='mostrarpdf("uno")' title="Mostrar" ><i class="fas fa-search" ></i></a>
                                                        <div id="uno" style="display:none"></div>

                                                    </td>
                                                    <td>
                                                        <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('getdos').click()">Subir</button>
                                                    </td>



                                                </tr>
                                                <tr>

                                                    <td>
                                                        <input class="form-control" type="text" id="desc3" name="desc3"/>
                                                    </td>
                                                    <td style="text-align:center;" >
                                                        <a onclick ='mostrarpdf("uno")' title="Mostrar" ><i class="fas fa-search" ></i></a>
                                                        <div id="uno" style="display:none"></div>

                                                    </td>
                                                    <td>                                                        
                                                        <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('gettres').click()">Subir</button>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>

                                        <form  name="form4" action="../UploadFile.do?tipo=3" method="post" enctype="multipart/form-data"> 
                                            <div id="insertdescripcion"></div>
                                            <input type="file" id="getuno" name="file1" style="display:none"> 
                                            <input type="file" id="getdos" name="file2"style="display:none"> 
                                            <input type="file" id="gettres" name="file3" style="display:none"> 
                                        </form>
                                        <button href="#" onclick="validar()" name="subir" class="btn btn-primary">Subir Archivo(s)</button>

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
        <script src="../js/side-bar/empleado/load-empleado-perfil.js"></script>
        <!-- jQuery Side-bar -->        


    </body>
</html>
