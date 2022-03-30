<%-- 
    Document   : navbar
    Created on : 29/03/2022, 11:59:06 PM
    Author     : Leonardo
--%>

<nav  class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button type="button" id="sidebarCollapse" class="btn btn-info">
            <i class="fas fa-align-left"></i>
            <span>Menú</span>
        </button>
        <div>
            <h5><%=user.get("nombres").toUpperCase() + ""%></h5>
        </div>
        <div>
            <%
                String msg3 = (String) request.getSession().getAttribute("msg3");
                String id3 = (String) request.getSession().getAttribute("id3");
                if (msg3 != null) {
            %>
            <button type="button"  data-toggle="popover" title="Nueva notificacion"

                    data-trigger="focus" data-html="true"
                    data-content="
                    <div class=form-row >
                    <div class=form col-md-8 >
                    <label><%=msg3%></label> 
                    </div>
                    <div class=form col-md-4>
                    <form action='../AceptUser.do?id=<%=id3%>' method='post' >
                    <input type='submit' name='Nom' class=form-control placeholder=Nombre value=VER class=`btn btn-danger btn-circle btn-xl`>
                    </form>
                    </div>
                    </div>"
                    >

                <i  class="icon fa fa-bell fa-fw ">  
                </i>
            </button>
            <%  } else { %>
            <button type="button"  data-toggle="popover" title="No tienes notificaciones" data-trigger="focus"
                    data-content="vacio! " class="btn btn-danger btn-circle btn-xl">
                <i class="icon fa fa-bell fa-fw ">  
                </i>
            </button>
            <%  }%>
        </div>
        <!--Foto Perfil-->
        <div class="img-profile">
            <a href="perfil#div-foto-perfil"> <img onclick="" src="<%=user.get("urlFoto")%>"></a>                 
        </div>
        <!--Foto Perfil-->
    </div>
</nav>

<!-- Modal notificacion -->                        
<%@include file="modal_notificaciones.jsp" %>
<!-- Modal notificacion -->