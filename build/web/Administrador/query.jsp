<%-- 
    Document   : query
    Created on : 5/11/2021, 04:05:45 PM
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

        <title>Consulta de Usuarios</title>
    </head>


    <body onload="load(1,5)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->
            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                String empleado = (String) request.getSession().getAttribute("empleado");
                String gerente = (String) request.getSession().getAttribute("gerente");
                String proveedor = (String) request.getSession().getAttribute("proveedor");
                String cliente = (String) request.getSession().getAttribute("cliente");
                
                
                Map<String, Object> listUsuarios = (Map<String, Object>) request.getSession().getAttribute("usuarios");
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


                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="card my-4">
                            <div class="form-group">
                                <div class="container">
                                    <div class="table-responsive">

                                        <table class="table table-responsive-sm">

                                            <thead>
                                                <tr>
                                                    <th scope="col">

                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck('');" class="form-check-input" type="checkbox" value="empleado" id="fempleado" <% if("true".equals(empleado)){%>checked<%} %>>
                                                <label  class="form-check-label" for="flexSwitchCheckDefault">Empleados</label>


                                            </div>
                                            </th>
                                            <th scope="col">
                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck('');" class="form-check-input" type="checkbox" value="cliente" id="fcliente" <% if("true".equals(cliente)){%>checked<%} %>>
                                                <label class="form-check-label" for="flexSwitchCheckDefault">Clientes</label>
                                            </div>
                                            </th>
                                            <th scope="col">
                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck('');" class="form-check-input" value="proveedores" type="checkbox" id="fproveedor" <% if("true".equals(proveedor)){%>checked<%} %>>
                                                <label class="form-check-label" for="flexSwitchCheckDefault">Proveedores</label>
                                            </div> 

                                            </th>
                                            <th scope="col">
                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck('');" class="form-check-input" type="checkbox" value="gerente" id="fgerente" <% if("true".equals(gerente)){%>checked<%} %>>
                                                <label class="form-check-label" for="flexSwitchCheckDefault">Directivo</label>
                                            </div>
                                            </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td colspan="4" >
                                                        <input type="text" id="myInput"  style="width:100%;"  onkeyup="myFunction()" placeholder="Busqueda por nombre" title="Type in a name">
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>




                <!-- Content  -->
                <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-10">
                        <div class="card my-4">
                            <h5 class="card-header">Administración de usuarios</h5>

                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <div class="table-responsive">

                                            <table id="myTable" class="table table-responsive-sm">

                                                <thead>
                                                    <tr>
                                                        <th scope="col">Nombre</th>
                                                        <th scope="col">Correo electrónico (Usuario) </th>
                                                        <th scope="col">Activo</th>
                                                        <th scope="col"></th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                </thead>


                                                <tbody>

                                                    <%
                                                        int i = 1;
                                                        for (Map.Entry<String, Object> entry : listUsuarios.entrySet()) {
                                                            Map<String, String> map = (Map<String, String>) entry.getValue();
                                                    %>
                                                    <tr>
                                                        <td><%=map.get("nombres")%></td>
                                                        <td><%=map.get("user")%></td>

                                                        <td>
                                                            <div class="custom-control custom-checkbox">
                                                                <input type="checkbox" name="activo<%=i%>" class="custom-control-input" <%if (map.get("activo").equals("1")) {%> checked <%}%>>
                                                                <label onclick="loadCheckActive(<%=map.get("idUsuario")%>,<%=map.get("activo")%>)" class="custom-control-label" name="activo<%=i%>" for="activo<%=i%>"></label>
                                                            </div>
                                                        </td>
                                                      

                                                      
                                                    </tr>
                                                    <% i++;
                                                        }%>
                                                </tbody>
                                            </table>
                                        </div>



                                        <!-- Modal  -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content  -->


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
                        <!-- fin Modal success -->                    


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
                                <p>Estas seguro de eliminar el usuario del sistema?</p>
                            </div>
                            <div class="modal-footer">            
                                <a  class="btn btn-primary">Si</a>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                            </div>
                        </div>
                    </div>
                </div>



            </div>






        </div>
        <!-- Modal success -->

        <%
            }
            request.getSession().removeAttribute("msg");
            request.getSession().removeAttribute("empleado");
            request.getSession().removeAttribute("gerente");
            request.getSession().removeAttribute("proveedor");
           request.getSession().removeAttribute("cliente");
        %>

        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->

        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script>   
        <script src="../js/side-bar/admin/load-admin-1.0.js"></script> 
        <script src="../js/side-bar/admin/js.js"></script> 
        <!-- jQuery Side-bar -->    

    </body>
</html>
