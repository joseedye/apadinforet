<%-- 
    Document   : perfil
    Created on : 20/10/2021, 06:44:39 PM
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
        <title>Perfil</title>
    </head>
    <%

        String icargar = (String) request.getSession().getAttribute("i");
    %>
    <body onload="loadPerfil(<%=icargar%>)">
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
                            <img src="/img/fotoadmin.jpg<%//=userImg%>">                        
                        </div>

                    </div>
                </nav>

                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="contenedor">
                            <form name="form1" action="../UpdateProfile.do" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Perfil Cliente</h4>
                                    </div>
                                </div>


                                <div id="gSignIn"></div>


                                <div class="form-row">
                                    <div class="form-group col-md-4">

                                        <label for="">Nombre</label>
                                        <input type="text" name="Nom" class="form-control" readonly=»readonly» id="" placeholder="Nombre" value="<%=user.get("nombres").toUpperCase() + ""%>" maxlength="100" required>




                                        <label for="">Tipo Documento</label>
                                        <input type="text" name="Tipodoc" class="form-control" readonly=»readonly» id=""  value="<%=user.get("tipoDocumento")%>" maxlength="100" required>

                                        <label for="">Numero</label>
                                        <input type="number" name="Doc" pattern="[0-9]+" class="form-control"  readonly=»readonly» id="" placeholder="Documento" value="<%=user.get("numDocumento")%>" >

                                        <label for="">Fecha de Nacimiento</label>
                                        <input class="form-control" type="date" readonly=»readonly» name="Fecha" value="<%=user.get("fecNacimiento") + ""%>" required>

                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="">Primer Apellido</label>
                                        <input type="text" name="Ape1" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido1") + ""%>" maxlength="15" required>

                                        <label for="">Tipo Persona</label>
                                        <input type="text" name="Tipop" class="form-control" id="" readonly=»readonly» value="<%=user.get("Tipocliente")%>" maxlength="15" required>


                                        <label for="">Pais</label>
                                        <input type="text" name="pais" class="form-control" id="" readonly=»readonly» value="<%=user.get("pais")%>" maxlength="100" required>



                                        <label for="">Teléfono</label>
                                        <input type="number" name="Tel1" pattern="[0-9]+" class="form-control" id="" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono" value="<%=user.get("telefono1")%>" required >
                                    </div>

                                    <div class="form-group col-md-4">
                                        <label for="">Segundo Apellido</label>
                                        <input type="text" name="Ape2" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido2")%>" maxlength="15" required>

                                        <label for="">Email</label>
                                        <input type="email" name="Email"  pattern="+.@ufps.edu.co" class="form-control" id="emaill" placeholder="@ufps.edu.co"value="<%=user.get("email")%>" maxlength="70" required>

                                        <label for="">Teléfono 2</label>
                                        <input type="number" name="Tel2" pattern="[0-9]+" class="form-control" id="" value="<%=user.get("telefono2")%>" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono">

                                    </div>
                                </div>
                                <label for="">Razon social</label>
                                <input type="text" name="razon" class="form-control" id="" readonly=»readonly» value="<%=user.get("razon")%>" required>

                                <label for="">Reprecentante legal</label>
                                <input type="text" name="rep" class="form-control" id="" readonly=»readonly» value="<%=user.get("reprecentante")%>"  maxlength="150">



                                <label for="">Dirección</label>
                                <input type="text" name="Dire" class="form-control" id="" value="<%=user.get("direccion")%>" placeholder="Dirrección" maxlength="150">
                                <br></br>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>
                                <a href="perfil" class="btn btn-primary">Cancelar</a>

                                <br></br>
                                <!-- Modal --> 
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
                                                <p>Esta seguro de modificar sus datos personales?</p>
                                            </div>
                                            <div class="modal-footer">            
                                                <button type="submit"  class="btn btn-primary">Si</button>
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>



                                <!-- Modal  confirmacion contraseña--> 
                            </form>
                            <form  name="form2" action="../UpdatePassword.do" method="post">
                                <div class="form-row changPassword">
                                    <div class="form-group col-md-4">
                                        <label for="exampleInputPassword1">Contraseña anterior</label>
                                        <input type="password" name="ContraAnt" class="form-control" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label for="exampleInputPassword1">Contraseña nueva</label>
                                        <input type="password" name="ContraNueva" class="form-control" required>
                                    </div>
                                    <div class="form-group col-md-4">
                                        <label>&nbsp;</label><br>
                                        <button type="submit" name="Cambiar" class="btn btn-primary">Cambiar</button>
                                    </div>
                                </div>
                            </form>

                            <br></br>
                            <br></br>
                            <br></br>




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
                                                                    RUT 
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

                                                            <tr>
                                                                <td  >
                                                                    CAMARA DE COMERCIO 
                                                                </td>
                                                                <td  >
                                                                    .pdf 
                                                                </td>
                                                                <td  >
                                                                    2MB
                                                                </td>
                                                                <td style="text-align:center;" >
                                                                    <a  id="mostrar" onclick ='mostrarpdf("dos")' title="Mostrar" ><i class="fas fa-search" ></i></a>
                                                                    <div id="dos" style="display:none"></div>
                                                                </td>
                                                                <td  >
                                                                    <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('getdos').click()">Subir</button>
                                                                </td>
                                                            </tr>

                                                            <tr>  
                                                                <td>CEDULA DEL REPRECENTANTE</td>
                                                                <td>
                                                                    .pdf 
                                                                </td>
                                                                <td>
                                                                    2MB
                                                                </td>
                                                                <td style="text-align:center;" >
                                                                    <a  onclick='mostrarpdf("tres")' title="mostrar3" ><i class="fas fa-search" ></i></a>
                                                                    <div id="tres" style="display:none"></div>
                                                                </td>
                                                                <td  >
                                                                    <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('gettres').click()">Subir</button>
                                                                </td>

                                                        </tbody>
                                                    </table>


                                                    <form  name="form4" action="../UploadFile.do?tipo=1" method="post" enctype="multipart/form-data"> 
                                                        <input type="file" id="getuno" name="archivo1" style="display:none"> 
                                                        <input type="file" id="getdos" name="archivo2"style="display:none"> 
                                                        <input type="file" id="gettres" name="archivo3" style="display:none"> 
                                                        <button type="submit" name="subir" class="btn btn-primary">Enviar</button>
                                                    </form>
                                                    </div>
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
                                    <p> <%=msg%> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal success -->

                    <%   }
                        request.getSession().removeAttribute("msg");
                    %>



                    <%
                        String msg2 = (String) request.getSession().getAttribute("msg2");
                        if (msg2 != null) {
                    %>

                    <!-- Modal success -->                        
                    <div class="modal fade" id="ventana3" tabindex="-1" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title">Mensaje</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p> <%=msg2%> </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal success -->

                    <%   }
                        request.getSession().removeAttribute("msg2");
                    %>


                </div>
            </div>
            <!-- Content  -->
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

    <script src="../js/side-bar/cliente/load-cliente-perfil.js"></script>
    <!-- jQuery Side-bar -->    
    <script>
                                                            function mostrarpdf(ruta) {
                                                                var ruta = document.getElementById(ruta).innerHTML;
//                                                                            console.log("estoy dentro3"+ruta);
                                                                window.open(ruta);
                                                            }

//borrar
                                                            function enviarr() {
                                                                var elementos = '<form  name="form3" action="../UploadFile?id=1" method="post">' +
                                                                        '<input type="file"   id="getunoo" > ' +
                                                                        '<input type="file"   id="getdoss" style="display:none"> ' +
                                                                        ' <input type="file"   id="gettress" style="display:none"> ' +
                                                                        ' <button type="submit" name="subirr" style="display:none"></button>' +
                                                                        ' </form>' +
                                                                        '<div id="prueba"></div>';

                                                                var inyectar = document.getElementById("archivosenviar");
                                                                inyectar.innerHTML = elementos;

                                                                document.getElementById("prueba").innerHTML = document.getElementById("getuno").value;
                                                                var valueuno = document.getElementById("getuno").value;
                                                                // document.getElementById("getunoo").value = valueuno;
                                                                // document.getElementById('getunoo').click();
                                                                console.log("estoy enviando value " + valueuno);


                                                            }



    </script>

    <script>
        $(document).ready(function () {
            $("#ventana2").modal('show');
        });
        $(document).ready(function () {
            $("#ventana3").modal('show');
        });


        $('#getuno').change(function (event) {
            var tmppath = URL.createObjectURL(event.target.files[0]);

            $("#uno").html(tmppath);
        });

        $('#getdos').change(function (event) {
            var tmppath = URL.createObjectURL(event.target.files[0]);

            $("#dos").html(tmppath);
        });
        $('#gettres').change(function (event) {
            var tmppath = URL.createObjectURL(event.target.files[0]);

            $("#tres").html(tmppath);
        });

        $('#getunoo').change(function (event) {
            var tmppath = URL.createObjectURL(event.target.files[0]);

            $("#tres").html(tmppath);
        });
    </script> 
</body>
</html>
