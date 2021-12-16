<%-- 
    Document   : consulta_solicitud
    Created on : 12/12/2021, 11:54:53 AM
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
        <title>Consulta solicitud</title>
    </head>
    <body onload="loadSolicitudes(2)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> listUsuarios = (Map<String, Object>) request.getSession().getAttribute("usuarios");
                Map<String, Object> listSolicitudes = (Map<String, Object>) request.getSession().getAttribute("solicitud");

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
                            <h5><%=user.get("nombres").toUpperCase() + ""%></h5>
                            <input  name="iduser"  id="iduser"  value="<%=user.get("idUsuario")%>" type="hidden">

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
                            <h5 class="card-header">Solicitudes por Cliente</h5>

                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">
                                        <div class="table-responsive">

                                            <table id="myTable" class="table table-responsive-sm">

                                                <thead>
                                                    <tr>
                                                        <th scope="col">Nombre</th>
                                                        <th scope="col">Correo electrónico (Usuario) </th>
                                                        <th scope="col">tematica</th>
                                                        <th scope="col">ver</th>
                                                        <th scope="col"></th>
                                                    </tr>
                                                </thead>

                                                <tbody>
                                                    <%
                                                        int i = 1;
                                                        for (Map.Entry<String, Object> entry : listUsuarios.entrySet()) {

                                                            Map<String, String> map = (Map<String, String>) entry.getValue();
                                                            Map<String, String> mapp = (Map<String, String>) listSolicitudes.get(entry.getKey());
                                                    %>
                                                    <tr>
                                                        <td><%=map.get("nombres")%></td>
                                                        <td><%=map.get("user")%></td>
                                                        <td>

                                                            <label class="text-center" ><%=mapp.get("tematica")%></label>

                                                        </td>
                                                        <td>
                                                            <a href="../SeeApplicationEmployes.do?idSolQuery=<%=mapp.get("ids")%>" title="ver"><i class="fas fa-eye"></i></a>
                                                        </td>
                                                    </tr>
                                                    <% }%>
                                                </tbody>
                                            </table>
                                        </div>



                                        <!-- Modal  -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Content  -->



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
            </div>
        </div>


        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script>   
        <script src="../js/side-bar/empleado/load-empleado-perfil.js"></script> 
        <!-- jQuery Side-bar -->   

        <script>
        $(document).ready(function () {
            $("#ventana2").modal('show');
        });

        </script> 

        <script>
            function myFunction() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>

    </body>
</html>
