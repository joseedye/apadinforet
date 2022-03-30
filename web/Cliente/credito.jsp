<%-- 
    Document   : credito
    Created on : 19/11/2021, 11:51:54 PM
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
        <title>Credito</title>
    </head>

    <%

        String icargar = (String) request.getSession().getAttribute("i");
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        String userImg = (String) request.getSession().getAttribute("userImg");
    %>
    <body onload="load(2, 4)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">  
            </nav>
            <!-- Sidebar  -->
            <input  name="iduser"  id="iduser"  value="<%=user.get("idUsuario")%>" type="hidden">



            <!-- Page Content  -->
            <div id="content">
                <!-- NavBar  -->
                <%@include file="../modules/navbar_default.jsp" %>
                <!-- NavBar  -->

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <h4>Solicitud de Credito</h4>
                                </div>
                            </div>

                            <div>
                                <label for="">Formato de Solicitud</label>

                                <p>
                                    <a href="/Files/formato credito.docx">Pincha aqui</a>
                                </p>
                            </div>
                            <div style="justify-content:center;" class="form-row">
                                <div class="form-group col-md-12">
                                    <div class="card my-4">
                                        <div class="form-group">
                                            <div class="container">
                                                <div class="table-responsive">
                                                    Documentacion necesaria requerida


                                                    <table class="table table-responsive-sm">

                                                        <thead>
                                                            <tr>
                                                                <th scope="col">

                                                        <div class="form-check form-switch">
                                                            <label  class="form-check-label" for="flexSwitchCheckDefault">Documento</label>


                                                        </div>
                                                        </th>
                                                        <th scope="col">
                                                        <div class="form-check form-switch">
                                                            <label class="form-check-label" for="flexSwitchCheckDefault">Extencion</label>
                                                        </div>
                                                        </th>
                                                        <th scope="col">
                                                        <div class="form-check form-switch">
                                                            <label class="form-check-label" for="flexSwitchCheckDefault">Peso</label>
                                                        </div> 

                                                        </th>
                                                        <th scope="col">
                                                        <div class="form-check form-switch">
                                                            <label class="form-check-label" for="flexSwitchCheckDefault">Ver archivo</label>
                                                        </div>
                                                        </th>
                                                        <th scope="col">
                                                        <div class="form-check form-switch">
                                                            <label class="form-check-label" for="flexSwitchCheckDefault">Cargado</label>
                                                        </div>
                                                        </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    Formato Credito
                                                                </td>

                                                                <td  >
                                                                    .pdf 
                                                                </td>

                                                                <td >
                                                                    2MB
                                                                </td>

                                                                <td style="text-align:center;" >
                                                                    <a onclick ='mostrarpdf("uno")' title="Mostrar" ><i class="fas fa-search" ></i></a>
                                                                    <div id="uno" style="display:none"></div>

                                                                </td>

                                                                <td>
                                                                    <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('getuno').click()">Subir</button>
                                                                </td> 
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                    <form  name="form4" action=" ../UploadFile.do?tipo=4" method="post" enctype="multipart/form-data"> 
                                                        <input type="file" id="getuno" name="archivo" style="display:none">                                                                                                            
                                                        <button type="submit" name="subir" class="btn btn-primary">Enviar</button>
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

        <script src="../js/side-bar/cliente/load-cliente-perfil.js"></script>
        <!-- jQuery Side-bar -->    

    </body>
</html>