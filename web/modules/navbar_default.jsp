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
        <!--Foto Perfil-->
        <div class="img-profile">
            <a href="perfil#div-foto-perfil"> <img onclick="" src="<%=user.get("urlFoto")%>"></a>                 
        </div>
        <!--Foto Perfil-->
    </div>
</nav>
