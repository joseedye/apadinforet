<%-- 
    Document   : administrador_registrar
    Created on : 20/10/2021, 08:22:22 PM
    Author     : rozo
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
        <title> Registro de Administrador</title>
    </head>
    <body onload="load(1,2)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, String> listPaises = (Map<String, String>) request.getSession().getAttribute("country");
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
                        <form name="form1" action="../RegisterAdmin.do" method="post">
                            <h4>Registro de Administrador</h4>

                            <div class="form-row">
                                <div class="form-group col-md-4">

                                    <label for="">Nombres</label>
                                    <input type="text" name="Nom" class="form-control" id="" placeholder="Nombre" required>

                                    <label for="">Tipo Documento</label>
                                    <select class="form-control" name="Tipodoc" id="Tipodoc" required>
                                        <option disabled selected norequired>Seleccione</option>
                                        <option value="CC" name="cedula">C.C</option>
                                        <option value="CE" name="extrangeria">C.E</option>
                                        <option value="NIT" name="nit">NIT</option>
                                    </select>

                                    <label for="">Documento</label>
                                    <input type="number" name="Doc" min="0" class="form-control" id="" placeholder="Documento" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);
                                            } else if (this.value < 0) {
                                                this.value = '0';
                                            }" required>

                                    <label for="fecha">Fecha de Nacimiento</label>
                                    <input class="form-control" name="Fecha" type="date" id="fecha" name="fecha" value="" required>

                                </div>
                                <div class="form-group col-md-4">
                                    <label for="">Primer Apellido</label>
                                    <input type="text" name="Ape1" class="form-control" id="" placeholder="Primer Apellido" required>

                                    <label for="">Género</label>
                                    <select class="form-control" name="Genero" id="genero" required>
                                        <option disabled selected norequired>Seleccione</option>
                                        <option value="Masculino" name="masculino">Masculino</option>
                                        <option value="Femenino" name="femenino">Femenino</option>
                                    </select>
                                    <label for="">Pais</label>
                                    <select class="form-control" name="Pais" id="pais" required>
                                        <option disabled selected norequired>Seleccione</option>

                                        <%if (!listPaises.isEmpty()) {
                                                int i = 0;
                                                while (i != listPaises.size() / 2) {

                                                    String pais = listPaises.get("pais" + i);
                                                    String num = listPaises.get("cod" + i++);
                                        %>

                                        <option value="<%= num%>" name="<%= num%>"> <%= pais%> </option>   

                                        <% }
                                            }
                                        %>


                                    </select>
                                    <input type=hidden name="Paiss"   id="Paiss" >
                                    <label for="">Teléfono</label>
                                    <input type="number" name="Tel1" min="0" class="form-control" id="Tel1" >
                                </div>

                                <div class="form-group col-md-4">
                                    <label for="">Segundo Apellido</label>
                                    <input type="text" class="form-control" name="Ape2" id="" placeholder="Segundo Apellido" required>

                                    <label for="">Email</label>
                                    <input type="email" pattern="+.@edu.co" name="Email" class="form-control" id="Email"
                                           placeholder="email@gmail.com" required>

                                    <label for="">Teléfono 2</label>
                                    <input type="number" min="0" name="Tel2" class="form-control" id="num2" onKeyUp="if (this.value.length > 10) {
                                                this.value = this.value.substr(0, 10);
                                            } else if (this.value < 0) {
                                                this.value = '0';
                                            }" placeholder="Teléfono">
                                </div>

                            </div>

                            <label for="direccion">Dirección</label>
                            <input type="text" class="form-control" name="Dire" id="direccion" placeholder="Dirrección" required>

                            <br>       
                            <div class="form-gruop col-md-6 ">
                                <div class="form-group ">
                                    <a  href="#ventana" id="activamodal" class="btn btn-primary" >Registrar</a>
                                    <a href="administrador_registrar" class="btn btn-primary">Cancelar</a>
                                </div>

                            </div>





                            <!-- Modal  -->
                            <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Alerta</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <p>Estas seguro de registrar el nuevo usuario?</p>
                                        </div>
                                        <div class="modal-footer">            
                                            <button type="submit"  class="btn btn-primary">Si</button>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Modal  -->
                        </form>
                    </div>
                </div>
                <!-- Content  -->
            </div>
            <!-- Page Content  -->
        </div>

        <%
            String msg = (String) request.getSession().getAttribute("msg");
            if (msg
                    != null) {
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

            request.getSession()
                    .removeAttribute("msg");
        %>

        <!-- jQuery Side-bar -->
        <script
            src="https://code.jquery.com/jquery-3.6.0.min.js"
            integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"></script>
        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script>   
        <script src="../js/side-bar/admin/load-admin-1.0.js"></script>   

        <!-- jQuery Side-bar -->    

        <script>
                                        $(document).ready(function () {

                                            $("#ventana2").modal('show');
                                            $('#activamodal').click(function () {
                                                var cor = document.getElementById('Tipodoc').value;
                                                var gen = document.getElementById('genero').value;
                                                console.log("prueba");
                                                var isGenero = gen !== "Seleccione";
                                                var istipodoc = cor !== "Seleccione";
                                                if (!isGenero) {
                                                    alert("Seleccionar un género!");
                                                }
                                                else if (!istipodoc) {
                                                    alert("seleccione un tipo de documento");
                                                }
                                                else {
                                                    $("#ventana").modal('show');
                                                }

                                            });

                                        });



        </script>          
        <script>
            var select = document.getElementById('pais');
            select.addEventListener('change',
                    function () {
                        var selectedOption = this.options[select.selectedIndex];
                        var opcion = selectedOption.value;
                        document.getElementById('Tel1').value = opcion;
                        document.getElementById('Paiss').value = $('select[name="Pais"] option:selected').text();
                               
                    });



        </script>
    </body>
</html>




