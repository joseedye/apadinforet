<%-- 
    Document   : index
    Created on : 7/10/2021, 06:57:34 PM
    Author     : rozo
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">        

        <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="css/style-login.css">
       
        <title>Login </title>
    </head>
    <body>          

        <div class="container">
            <div class="row align-items-center justify-content-center vh-100">
                <div class="card" style="width: 23rem;">
                    <img src="img/ufps.png" class="card-img-top" alt="">

                    <form class="" method="post" action="SignIn.do">
                        <div class="contenedor">
                            <div class="input-contenedor">

                                <label for="exampleInputEmail1" class="form-label">Correo electrónico</label>
                                <input type="email" placeholder="Email" class="form-control" name="user"  required>

                                <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                                <input type="password" placeholder="Contraseña" class="form-control" name="pass" required>
                            </div>

                            <input id="idbtn" type="submit" value="Iniciar sesión" class="button">
                            <div  class="text-center"> 
                                <!--<a href="Sesion/recuperacion_clave">¿Olvidaste tu clave?</a>-->
                                  <a href="/">Atras</a>
                            </div>
                            <hr/>
                            <div id="gSignIn"></div>  
                            <div class="g-signin2 text-center" data-onFailure="fail" data-onsuccess="onSignIn"></div>
                        </div>
                    </form>                 
                </div>
            </div>
        </div>          
        
        <%
            String msg = (String) request.getSession().getAttribute("msg");
            if (msg != null) {
        %>
        <!-- Modal success -->                        
        <div class="modal fade" id="ventana" tabindex="-1" role="dialog">
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
        <!-- Modal  -->
  <!-- Modal success 
        <%
            }
            request.getSession().removeAttribute("msg");
            String msg3 = (String) request.getSession().getAttribute("msg3");
            if (msg3 != null) {
                
            }
        %>
  -->  
        <script src="js/side-bar/extra/jquery-3.3.1.slim.min.js"></script>
        <script src="js/side-bar/extra/bootstrap.min.js"></script> 
        <script>
               $(document).ready(function () {
                $("#ventana").modal('show');
               
            });
        </script>

    </body>
</html>

