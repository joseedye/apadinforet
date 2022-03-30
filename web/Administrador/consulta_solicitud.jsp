<%-- 
    Document   : consulta_solicitud
    Created on : 17/11/2021, 12:17:15 PM
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
    <body onload="load(2, 6)">
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
                Map<String, String> listCantidades= (Map<String, String>) request.getSession().getAttribute("cantidad");

            %>

            <!-- Page Content  -->
            <div id="content">
                <!-- NavBar  -->
                <%@include file="../modules/navbar_admin.jsp" %>
                <!-- NavBar  -->

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
                                                        <th scope="col">cantidad</th>
                                                        <th scope="col">ver</th>
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

                                                            <label class="text-center" ><%=listCantidades.get(entry.getKey())%></label>

                                                        </td>
                                                        <td>
                                                            <a href="../SeeApplication.do?idUserQuery=<%=map.get("idUsuario")%>" title="ver"><i class="fas fa-eye"></i></a>
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


            </div>
        </div>


        <!-- jQuery Side-bar -->
        <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <script src="../js/side-bar/extra/popper.min.js"></script>
        <script src="../js/side-bar/extra/menu-button.js"></script>
        <script src="../js/side-bar/extra/bootstrap.min.js"></script>   
        <script src="../js/side-bar/admin/load-admin-1.0.js"></script> 
        <!-- jQuery Side-bar -->   

        <script>
                                                            $(document).ready(function () {
                                                                $("#ventana2").modal('show');
                                                            });

                                                           function loadCheck() {

                                                                        // Comprobar los checkbox seleccionados
                                                                        
                                                                        var empleado=false,cliente=false,proveedor=false,gerente = false;
                                                                        if ($('#fempleado').is(':checked')) {
                                                                            empleado=true;
                                                                        }
                                                                        if ($('#fcliente').is(':checked')) {
                                                                            cliente=true;
                                                                        }
                                                                        if ($('#fproveedor').is(':checked')) {
                                                                            proveedor=true;
                                                                        }
                                                                        if ($('#fgerente').is(':checked')) {
                                                                            gerente=true;
                                                                        }
                                                                            
                                                                 location.href = '../QueryDocuments.do?empleado=' + empleado + '&cliente=' + cliente + '&proveedor=' + proveedor + '&gerente=' + gerente;;
                                                                    }

                                                            $(function () {
                                                                $(".modal-btn").click(function () {
                                                                    var id = $(this).data('some-id');
                                                                    $(".modal-footer a").attr("href", "../DeleteDocument.do?idDoc=" + id);
                                                                })
                                                            });

                                                            if (window.performance.navigation.type === 1) {
                                                                location.href = "../QuerySolicitudes.do";
                                                            }

                                                            function doSearch() {
                                                                var tipo = $("#tipoDocument").val();
                                                                location.href = "../QueryDocuments.do?tipoDocument=" + tipo;
                                                            }
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
