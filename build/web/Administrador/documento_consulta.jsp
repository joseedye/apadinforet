<%-- 
    Document   : documento_consulta
    Created on : 9/11/2021, 11:37:43 PM
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
        <title>Consulta documentos</title>
    </head>
    <body onload="load(0,1)">
        <div class="wrapper">

            <!-- Sidebar  -->
            <nav id="sidebar">
            </nav>
            <!-- Sidebar  -->

            <%
                Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
                String userImg = (String) request.getSession().getAttribute("userImg");
                Map<String, Object> listDocumentos = (Map<String, Object>) request.getSession().getAttribute("documentos");
                
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
                        <h4>Consulta de documentos</h4> <br>                            
                       
                          <div style="justify-content:center;" class="form-row">
                    <div class="form-group col-md-12">
                        <div class="card my-4">
                            <div class="form-group">
                                <div class="container">
                                    <div class="table-responsive">

                                        <table class="table table-responsive-sm">

                                            <thead>
                                                <tr>
                                                    <th scope="col">

                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck(1);" class="form-check-input" type="checkbox" value="empleado" id="fempleado" >
                                                <label  class="form-check-label" for="flexSwitchCheckDefault">Empleados</label>


                                            </div>
                                            </th>
                                            <th scope="col">
                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck(2);" class="form-check-input" type="checkbox" value="cliente" id="fcliente" >
                                                <label class="form-check-label" for="flexSwitchCheckDefault">Clientes</label>
                                            </div>
                                            </th>
                                            <th scope="col">
                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck(3);" class="form-check-input" value="proveedores" type="checkbox" id="fproveedor" >
                                                <label class="form-check-label" for="flexSwitchCheckDefault">Proveedores</label>
                                            </div> 

                                            </th>
                                            <th scope="col">
                                            <div class="form-check form-switch">
                                                <input onclick="loadCheck(4);" class="form-check-input" type="checkbox" value="gerente" id="fgerente" >
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
                                                
                                                
                                                
                                                
                                                
                        

                        <div class="card my-4">
                            <h5 class="card-header">Documentos en el sistema: </h5>
                            <div class="card-body">
                                <div class="form-group">
                                    <div class="container">

                                        <table id="myTable" class="table table-responsive-sm">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Descripción</th>
                                                     <th scope="col">Usuario</th>
                                                    <th scope="col">Descarga</th>
                                                    <th scope="col">Eliminar</th>
                                                   
                                                    <th scope="col"></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    int i = 1;
                                                    for (Map.Entry<String, Object> entry : listDocumentos.entrySet()) {
                                                        Map<String, String> map = (Map<String, String>) entry.getValue();
                                                %>
                                                <tr>
                                                    <td><%=map.get("nombre")%></td>
                                                    
                                                    <td><%=map.get("iduser")%></td>
                                                    <td>
                                                        <a download href="/<%=map.get("rutaDoc")%>" title="Descargar"><i class="fas fa-cloud-download-alt"></i></a>
                                                    </td>
                                                    <td>
                                                        <a href="#ventana" class="modal-btn" data-some-id="<%=map.get("idDoc")%>" title="Eliminar" data-toggle="modal"><i class="fas fa-trash-alt"></i></a>
                                                    </td>
                                                </tr>
                                                <% i++;
                                                    }%>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                <p>Estas seguro de eliminar el documento del sistema? </p>
                                <p>Sólo se eliminará si el documento no está referenciado en alguna solicitud!
                                </p>
                            </div>
                            <div class="modal-footer">            
                                <a class="btn btn-primary">Si</a>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Modal  -->  
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
        <!-- jQuery Side-bar -->   

        <script>
                                                                $(document).ready(function () {
                                                                    $("#ventana2").modal('show');
                                                                });

                                                                function loadCheck(a, b) {
                                                                    location.href = '../UpdateActivDoc.do?idDocumento=' + a + '&publico=' + b;
                                                                }

                                                                $(function () {
                                                                    $(".modal-btn").click(function () {
                                                                        var id = $(this).data('some-id');
                                                                        $(".modal-footer a").attr("href", "../DeleteDocument.do?idDoc=" + id);
                                                                    })
                                                                });

                                                                if (window.performance.navigation.type === 1) {
                                                                    location.href = "../QueryDocuments.do";
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
