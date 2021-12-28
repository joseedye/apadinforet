<%-- 
    Document   : configuraciones
    Created on : 12/12/2021, 11:01:26 PM
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
        <title>Configuracion</title>
    </head>
    <body onload="load(3, 7)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">  
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, String> tipos = (Map<String, String>) request.getSession().getAttribute("tipoSolicitud");
                Map<String, String> textos = (Map<String, String>) request.getSession().getAttribute("textos");


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
                            <img src="<%=userImg%>">                        
                        </div>

                    </div>
                </nav>

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <h4>Configuracion de imagenes</h4>
                                </div>
                            </div>
                            <div id="gSignIn"></div>
                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="">Modificar imagen de :</label>
                                    <select class="form-control" id="imgb">
                                        <option  selected>Seleccionar</option>
                                        <option value="../img/fondo.png" name="1">Login</option>
                                        <option value="../img/ufps.png" name="2">Fondo login</option>
                                        <option value="../assets/img/businessman.jpg" name="3">Index fondo</option>                                            
                                    </select>
                                </div>

                                <div class="form-group col-md-3">
                                    <label for="d"> .</label>
                                    <label for="d"> .</label>
                                    <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('getuno').click()">Subir</button>
                                </div>
                                <div class="form-group col-md-5">  

                                    <img id="insertimg" src="../img/fondo.png" width="500" height="300">
                                </div>
                            </div>

                            <form  name="form4" action="../Settings.do?id=1" method="post" enctype="multipart/form-data"> 
                                <input type="file" id="getuno" name="imagen" style="display:none">  
                                <input type="text" id="idimg" name="a"  style="display:none"> 
                                <button type="submit" name="subir" class="btn btn-primary">Enviar</button>
                            </form>

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
                    </div>
                </div>
                <!-- Content  -->



                <hr>
                <br>  <br>

                <!-- tipo solicitudes  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <h4>Configuracion Tipo de Solicitudes</h4>
                                </div>
                            </div>
                            <div id="gSignIn"></div>
                            <form  name="form5" action="../Settings.do?id=2" method="post" enctype="multipart/form-data"> 
                                <!--<button type="submit" name="subir" class="btn btn-primary">Actualizar</button>-->


                                <div class="form-row">
                                    <div class="form-group col-md-4">
                                        <label for="">Modificar: </label>
                                        <select class="form-control" id="imgb" name="selecteliminar">
                                            <option  selected>Seleccionar</option>

                                            <%
                                                if (!tipos.isEmpty()) {
                                                    for (int i = 0; i < tipos.size() / 2; i++) {

                                            %>    

                                            <option  value = "<%=tipos.get(i + "n")%>" name = "opcion<%=i%>" > <%=tipos.get("" + i)%> </option> 
                                            <%  }
                                                }%>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-3">

                                    </div>
                                    <div class="form-group col-md-5">  


                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">

                                    </div>
                                </div>
                                <button type="submit" name="subir" class="btn btn-primary">Eliminar</button>
                            </form>  
                            <form  name="form8" action="../Settings.do?id=3" method="post" enctype="multipart/form-data"> 
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="">Agregar: </label>
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <input type="text" id="idimg" name="nuevo" placeholder="Nuevo" > 
                                </div>
                                <button type="submit" name="subir" class="btn btn-primary">Guardar</button>
                            </form>  
                        </div>



                    </div>
                </div>
                <hr>

                <!-- tipo solicitudes  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <h4>Configuracion Textos de la pagina web</h4>
                                </div>
                            </div>
                            <div id="gSignIn"></div>

                            <!--<button type="submit" name="subir" class="btn btn-primary">Actualizar</button>-->


                            <div class="form-row">
                                <div class="form-group col-md-4">
                                    <label for="">Seleccione el texto que va a modificar </label>
                                    <select class="form-control" id="idtexto" name="idtexto">
                                        <option  selected>Seleccionar</option>

                                        <%
                                            if (!textos.isEmpty()) {
                                                for (int i = 0; i < textos.size() / 2; i++) {

                                        %>    


                                        <option  value = "<%=textos.get(i + "n")%>" name = "opcion<%=i%>" > 
                                            <% if (textos.get("" + i).length() < 30) {%>
                                            <%=textos.get("" + i).substring(0, textos.get("" + i).length())%>
                                            <% } else {%>
                                            <%= textos.get("" + i).substring(0, 30) + "..."%> </option> 
                                            <%  }
                                                    }
                                                }%>
                                    </select>
                                </div>

                                <div class="form-group col-md-3">

                                </div>
                                <div class="form-group col-md-5">  


                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">

                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="">Texto que reemplaza: </label>
                                </div>
                            </div>
                            <form  name="form6" action="../Settings.do?id=4" method="post" enctype="multipart/form-data"> 
                                <div class="form-group col-md-6">
                                    <input type="text" id="textos" name="texto" placeholder="Nuevo" > 

                                </div>                                 

                                <button type="button" class="btn btn-primary" onclick="enviardatospagina()">Modificar</button>
                            </form>  
                        </div>



                    </div>
                </div>



            </div>
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

    <script src="../js/side-bar/admin/load-admin-1.0.js"></script>
    <!-- jQuery Side-bar -->    
    <script>
                                    $(document).ready(function () {
                                        $("#ventana2").modal('show');
                                    });

    </script> 


    <script>

        function mostrarnoti() {
            $("#ventana3").modal('show');
        }

        $('#imgb').change(function (event) {

            alert($('#insertimg').val());
//        alert($(this).val());
        });


        function enviardatospagina() {
            var cod = document.getElementById("idtexto").value;
            var contenido = document.getElementById("textos").value;           
            window.location.href = "../Settings.do?id=4&&idtexto="+cod+"&&texto="+ contenido;         
        }

    </script> 

</body>
</html>
