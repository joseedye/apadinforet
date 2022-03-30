<%-- 
    Document   : perfil
    Created on : 2/11/2021, 11:51:54 PM
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
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        Map<String, String> banca = (Map<String, String>) request.getSession().getAttribute("banca");
        Map<String, String> extra = (Map<String, String>) request.getSession().getAttribute("extra");

        String userImg = (String) request.getSession().getAttribute("userImg");
    %>
    <body onload="loadPerfil(1)">
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
                            <form name="form1" action="../UpdateProfile.do" method="post">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <h4>Perfil Proveedor</h4>
                                    </div>
                                </div>


                                <div id="gSignIn"></div>
                                <label for="">Razon social</label>
                                <input type="text" name="razon" class="form-control" id="" readonly=»readonly» value="<%=user.get("razon")%>" required>


                                <div class="form-row">
                                    <div class="form-group col-md-4">

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

                                        <label for="">Nombre</label>
                                        <input type="text" name="Nom" class="form-control" readonly=»readonly» id="" placeholder="Nombre" value="<%=user.get("nombres").toUpperCase() + ""%>" maxlength="100" required>


                                        <label for="">Fecha de Nacimiento</label>
                                        <input class="form-control" type="date" readonly=»readonly» name="Fecha" value="<%=user.get("fecNacimiento") + ""%>" required>

                                    </div>
                                    <div class="form-group col-md-4">

                                        <label for="">Tipo Documento</label>
                                        <input type="text" name="Tipodoc" class="form-control" readonly=»readonly» id=""  value="<%=user.get("tipoDocumento")%>" maxlength="100" required>

                                        <label for="">Dirección</label>
                                        <input type="text" name="Dire" class="form-control" id="" value="<%=user.get("direccion")%>" placeholder="Dirrección" maxlength="150">

                                        <label for="">Teléfono 2</label>
                                        <input type="number" name="Tel2" pattern="[0-9]+" class="form-control" id="" value="<%=user.get("telefono2")%>" onKeyUp="if (this.value.length > 10) {
                                                    this.value = this.value.substr(0, 10);
                                                } else if (this.value < 0) {
                                                    this.value = '0';
                                                }" placeholder="Teléfono">

                                        <label for="">Primer Apellido</label>
                                        <input type="text" name="Ape1" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido1") + ""%>" maxlength="15" required>

                                        <label for="">Teléfono Reprecentante Legal</label>
                                        <input type="number" name="Tel2" pattern="[0-9]+" class="form-control" readonly=»readonly» id="" value="<%=user.get("telefono2")%>" placeholder="Teléfono">





                                    </div>

                                    <div class="form-group col-md-4">

                                        <label for="">Numero</label>
                                        <input type="number" name="Doc" pattern="[0-9]+" class="form-control"  readonly=»readonly» id="" placeholder="Documento" value="<%=user.get("numDocumento")%>" >

                                        <label for="">Codigo Postal</label>
                                        <input type="text" name="codp" class="form-control" id="" readonly=»readonly» value="540005" maxlength="15" required>

                                        <label for="">Email</label>
                                        <input type="email" name="Email"  pattern="+.@ufps.edu.co" class="form-control" id="emaill" placeholder="@ufps.edu.co"value="<%=user.get("email")%>" maxlength="70" required>


                                        <label for="">Segundo Apellido</label>
                                        <input type="text" name="Ape2" class="form-control" id="" readonly=»readonly» value="<%=user.get("apellido2")%>" maxlength="15" required>

                                        <label for="">Email Reprecentante</label>
                                        <input type="email" name="Email"  pattern="+.@ufps.edu.co" class="form-control" id="emaill" placeholder="@ufps.edu.co" readonly=»readonly» value="reprecentante@gmail.com" maxlength="70" required>



                                    </div>
                                    <label for="">Reprecentante legal</label>
                                    <input type="text" name="rep" class="form-control" id="" readonly=»readonly» value="<%=user.get("reprecentante")%>"  maxlength="150">

                                </div>

                                <br>
                                <a href="#ventana" class="btn btn-primary" data-toggle="modal">Actualizar</a>

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

                            </form>

                            <!--cambio foto perfil-->
                            <%@include file="../modules/actualizacion_foto.jsp" %>
                            <!--cambio foto perfil-->       

                            <!-- Formulario cambio contraseña-->   
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
                            <!-- Formulario cambio contraseña-->    




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
                                                                    REGISTRO MERCANTIL
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
                                                            </tr>   
                                                            <tr>  
                                                                <td>CERTIFICADO BANCARIO</td>
                                                                <td>
                                                                    .pdf 
                                                                </td>
                                                                <td>
                                                                    2MB
                                                                </td>
                                                                <td style="text-align:center;" >
                                                                    <a  onclick='mostrarpdf("cuatro")' title="mostrar4" ><i class="fas fa-search" ></i></a>
                                                                    <div id="tres" style="display:none"></div>
                                                                </td>
                                                                <td  >
                                                                    <button style="display:block;width:120px; height:30px;" onclick="document.getElementById('getcuatro').click()">Subir</button>
                                                                </td>
                                                            </tr>  
                                                        </tbody>
                                                    </table>


                                                    <form  name="form4" action="../UploadFile.do?tipo=6" method="post" enctype="multipart/form-data"> 
                                                        <input type="file" id="getuno" name="archivo1" style="display:none"> 
                                                        <input type="file" id="getdos" name="archivo2"style="display:none"> 
                                                        <input type="file" id="gettres" name="archivo3" style="display:none"> 
                                                        <input type="file" id="getcuatro" name="archivo4" style="display:none"> 
                                                        <button type="submit" name="subir" class="btn btn-primary">Enviar</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>



                            <% if (extra != null) {%> 


                            <!-- Content  -->
                            <div style="justify-content:center;" class="form-row">
                                <div class="form-group col-md-12">
                                    <div class="card my-12">
                                        <h5 class="card-header">Auto retenedor / Gran contribuyente</h5>

                                        <div class="form-group">
                                            <div class="container">
                                                <form  name="form6" action="../Extra_provider.do">
                                                    <div>

                                                        <div class="form-row" id="botonesauto">

                                                            <div class="form-group col-md-4" >
                                                                <label>&nbsp;</label><br>
                                                                <label class="btn btn-primary">Auto retenedor</label><br>

                                                            </div>

                                                            <div class="form-group col-md-4" >
                                                                <label for="">Fecha</label>
                                                                <input class="form-control" type="date"  value="<%= extra.get("fechar")%>" name="fechaa">
                                                            </div>
                                                            <div class="form-group col-md-4" >
                                                                <label for="">Resolución</label>
                                                                <input type="text" name="resola" class="form-control" id="" placeholder="123456" value="<%= extra.get("rer")%>" maxlength="100" >
                                                            </div>
                                                        </div>

                                                        <div class="form-row" id="grancontribuyente">


                                                            <div class="form-group col-md-3" >
                                                                <label>&nbsp;</label><br>
                                                                <label class="btn btn-primary">Gran Contribuyente</label><br>
                                                            </div>

                                                            <div class="form-group col-md-3" >
                                                                <label for="">Fecha</label>
                                                                <input class="form-control" type="date"  value="<%= extra.get("fechag")%>" name="fechag">
                                                            </div>
                                                            <div class="form-group col-md-3" >
                                                                <label for="">Resolución</label>
                                                                <input type="text" name="resolg" class="form-control" id="" placeholder="123456" value="<%= extra.get("reg")%>" maxlength="100" >
                                                            </div>
                                                            <div class="form-group col-md-3" >
                                                                <label for="">Código actividad (ICA)</label>
                                                                <input type="text" name="ica" class="form-control" id="" placeholder="123456" value="<%= extra.get("ica")%>" maxlength="100" >
                                                            </div>

                                                        </div>

                                                        <div class="form-row" id="gurdargran">
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>   

                            <% } else { %>


                            <!-- Content  -->
                            <div style="justify-content:center;" class="form-row">
                                <div class="form-group col-md-12">
                                    <div class="card my-12">
                                        <h5 class="card-header">Auto retenedor / Gran contribuyente</h5>

                                        <div class="form-group">
                                            <div class="container">
                                                <form  name="form6" action="../Extra_provider.do">
                                                    <div>

                                                        <div class="form-row" id="botonesauto">
                                                            <div class="form-group col-md-4" >

                                                                <label>&nbsp;</label><br>
                                                                <button  onclick="autoretenedor(2)" name="Cambiar" class="btn btn-primary">Auto retenedor</button>
                                                            </div>
                                                        </div>

                                                        <div class="form-row" id="grancontribuyente">
                                                            <div class="form-group col-md-4" >
                                                                <button type="submit"  onclick="grancontribuyente(2)" name="Cambiar" class="btn btn-primary">Gran contribuyente</button>
                                                            </div>
                                                        </div>

                                                        <div class="form-row" id="gurdargran">
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>      




                            <% }
                                if (banca != null) { %>

                            <!-- Content  -->
                            <div style="justify-content:center;" class="form-row">
                                <div class="form-group col-md-12">
                                    <div class="card my-4">
                                        <h5 class="card-header">Informacion Bancaria</h5>

                                        <div class="card-body">
                                            <div class="form-group">
                                                <div class="container">
                                                    <div class="table-responsive">
                                                        <form  name="form7" action="../BankInfo.do?">
                                                            <div class="form-row" id="fomadepago">
                                                                <div class="form-group col-md-4" >

                                                                    <label for="">Forma de pago</label>
                                                                    <select class="form-control" name="forma" id="forma" required>
                                                                        <% if (banca.get("formap").equals("Contado")) {%>
                                                                        <option disabled  norequired>Seleccione</option>
                                                                        <option value="Contado" name="Contado" selected>Contado</option>
                                                                        <option value="Credito" name="Credito">Credito</option>
                                                                        <% } else { %>

                                                                        <option disabled  norequired>Seleccione</option>
                                                                        <option value="Contado" name="Contado" >Contado</option>
                                                                        <option value="Credito" name="Credito" selected>Credito</option>

                                                                        <% }%>
                                                                    </select>                               
                                                                </div>
                                                                <div class="form-group col-md-4" >

                                                                    <label for="">Numero de cuenta</label>
                                                                    <input type="text" name="numerocuenta" class="form-control" id="" value="<%=banca.get("ncuenta")%>" placeholder="123456789" required>

                                                                </div>
                                                                <div class="form-group col-md-4" >

                                                                    <label for="">Tipo</label>
                                                                    <select class="form-control" name="tipocuenta" id="tipocuenta" required>
                                                                        <option disabled  norequired>Seleccione</option>
                                                                        <% if (banca.get("tipoc").equals("Corriente")) {%>
                                                                        <option value="Corriente" name="Corriente" selected>Corriente</option>
                                                                        <option value="Ahorro" name="Ahorro">Ahorro</option>
                                                                        <% } else { %>
                                                                        <option value="Corriente" name="Corriente">Corriente</option>
                                                                        <option value="Ahorro" name="Ahorro" selected>Ahorro</option>
                                                                        <% }%>
                                                                    </select>
                                                                </div>
                                                            </div>

                                                            <div class="form-row" id="fomadepago">
                                                                <div class="form-group col-md-3" >

                                                                    <label for="">Banco</label>
                                                                    <input type="text" name="banco" class="form-control" id="banco" value="<%=banca.get("banco")%>" placeholder="Banco" >

                                                                </div>
                                                                <div class="form-group col-md-2" >

                                                                    <label for="">Cod Banco</label>
                                                                    <input type="text" name="codigobaco" class="form-control" id="" value="<%=banca.get("codbanco")%>" placeholder="123456789" >

                                                                </div>
                                                                <div class="form-group col-md-2" >

                                                                    <label for="">Sucursal</label>
                                                                    <input type="text" name="sucursal" class="form-control" id="sucursal" value="<%=banca.get("sucursal")%>" placeholder="Sucursal" >

                                                                </div>
                                                                <div class="form-group col-md-2" >

                                                                    <label for="">Ciudad</label>
                                                                    <input type="text" name="ciudad" class="form-control" id="ciudad" value="<%=banca.get("ciudad")%>" placeholder="Bogota" >

                                                                </div>
                                                                <div class="form-group col-md-3" >

                                                                    <label for="">Pais</label>
                                                                    <input type="text" name="pais" class="form-control" id="pais" value="<%=banca.get("pais")%>" placeholder="Colombia" >

                                                                </div>
                                                            </div>
                                                            <button type="submit" name="guardarformapago" class="btn btn-primary">Actualizar</button>
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
                </div>

                <% } else { %>


                <!--si esta vacia-->

                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-12">
                        <div class="card my-4">
                            <h5 class="card-header">Informacion Bancaria</h5>

                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <div class="table-responsive">
                                            <form  name="form7" action="../BankInfo.do?">
                                                <div class="form-row" id="fomadepago">
                                                    <div class="form-group col-md-4" >

                                                        <label for="">Forma de pago</label>
                                                        <select class="form-control" name="forma"  id="forma" required>                                                                        
                                                            <option disabled  selected norequired>Seleccione</option>
                                                            <option value="Contado" name="Contado" >Contado</option>
                                                            <option value="Credito" name="Credito">Credito</option>

                                                        </select>                               
                                                    </div>
                                                    <div class="form-group col-md-4" >

                                                        <label for="">Numero de cuenta</label>
                                                        <input type="text" name="numerocuenta" class="form-control" id="" placeholder="123456789" required>

                                                    </div>
                                                    <div class="form-group col-md-4" >

                                                        <label for="">Tipo</label>
                                                        <select class="form-control" name="tipocuenta" id="tipocuenta" required>
                                                            <option disabled  selected norequired>Seleccione</option>                                                                       
                                                            <option value="Corriente" name="Corriente" >Corriente</option>
                                                            <option value="Ahorro" name="Ahorro">Ahorro</option>

                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-row" id="fomadepago">
                                                    <div class="form-group col-md-3" >

                                                        <label for="">Banco</label>
                                                        <input type="text" name="banco" class="form-control" id="banco"  placeholder="Banco" >

                                                    </div>
                                                    <div class="form-group col-md-2" >

                                                        <label for="">Cod Banco</label>
                                                        <input type="text" name="codigobaco" class="form-control" id=""  placeholder="123456789" >

                                                    </div>
                                                    <div class="form-group col-md-2" >

                                                        <label for="">Sucursal</label>
                                                        <input type="text" name="sucursal" class="form-control" id="sucursal" placeholder="Sucursal" >

                                                    </div>
                                                    <div class="form-group col-md-2" >

                                                        <label for="">Ciudad</label>
                                                        <input type="text" name="ciudad" class="form-control" id="ciudad"  placeholder="Bogota" >

                                                    </div>
                                                    <div class="form-group col-md-3" >

                                                        <label for="">Pais</label>
                                                        <input type="text" name="pais" class="form-control" id="pais"  placeholder="Colombia" >

                                                    </div>
                                                </div>
                                                <button type="submit" name="guardarformapago" class="btn btn-primary">Guardar</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 



                <!--mensaje-->
                <%
                    }
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

                    request.getSession()
                            .removeAttribute("msg");
                %>



                <%
                    String msg2 = (String) request.getSession().getAttribute("msg2");
                    if (msg2
                            != null) {
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

                    request.getSession()
                            .removeAttribute("msg2");
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

<script src="../js/side-bar/proveedor/load-proveedor-perfil.js"></script>
<!-- jQuery Side-bar -->    

</body>
</html>
