<%-- 
    Document   : errorRedir
    Created on : 21/10/2021, 06:42:51 PM
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
        <link rel="stylesheet" href="/css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="/css/side-bar/style-side-bar.css">
        <script defer src="/font/solid.js"></script>
        <script defer src="/font/fontawesome.js"></script>
        <!-- Sidebar style  -->
        <title>Error</title>
        <style>
            body {
                background-image: url(/img/fondo.png);
                background-repeat: no-repeat;
                background-size: cover;
            }
        </style>
    </head>
    <body>

        <!--Mensaje cuando se regresa a login y ya existe una sesion activa-->
        <%
            String msg2 = (String) request.getSession().getAttribute("msg2");
            if (msg2 != null) {
        %>

        <!-- Modal success -->                        
        <div class="modal fade" id="ventana2" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Mensaje</h5>
                        <button type="button" onclick="history.go(-1);" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <p> <%=msg2%> </p>
                        <button onclick="closeSesion()" type="button" class="btn btn-danger">Cerrar sesión</button>
                        <button onclick="history.go(-1)" type="button" class="btn btn-secondary">Regresar</button>                        
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal success -->

        <%   }
            request.getSession().removeAttribute("msg2");
        %>



        <%
            String msg = (String) request.getSession().getAttribute("msg");
            if (msg != null) {
        %>

        <!-- Modal success -->                        
        <div onclick="history.go(-1);" class="modal fade" id="ventana" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Mensaje</h5>
                        <button type="button" onclick="history.go(-1);" class="close" data-dismiss="modal" aria-label="Close">
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

        <!-- jQuery Side-bar -->
         <script src="../js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <script src="/js/side-bar/extra/popper.min.js"></script>
        <script src="/js/side-bar/extra/bootstrap.min.js"></script>   
       
        `<!-- jQuery Side-bar --> 
        <script>

            $(document).ready(function () {
                $("#ventana").modal('show');
                $("#ventana2").modal('show');
            });

            function closeSesion() {
                window.location.href = "../LogOut.do";
            }

            $('#ventana, #ventana2').on('hidden.bs.modal', function () {
                history.go(-1);
            });
        </script>        
    </body>
</html>
