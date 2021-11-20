var sidebar =   '<div class="sidebar-header">'+
                    '<h3 style="color: #fff !important">INFORET</h3>'+
                    '<strong style="color: #fff !important">CS</strong>'+
                '</div>'+

                '<ul class="list-unstyled components">'+
                    '<li id="liperfil" >'+
                        '<a href="perfil">'+
                            '<i class="fas fa-home"></i>'+
                            '&nbspPerfil'+
                        '</a>'+
                    '</li>'+
                    
                    '<li id="lidocumentos" >'+                        
                        '<a href="#documentsSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                            '<i class="fas fa-copy"></i>'+
                            '&nbspDocumentos'+
                        '</a>'+
                        '<ul class="collapse list-unstyled"  id="documentsSubmenu">'+
                            '<li id="documentsnew">'+
                                '<a href="../Administrador/documento_nuevo.jsp">Nuevo</a>'+
                            '</li>'+
                            '<li id="documentsquery">'+
                                '<a href="../QueryDocuments.do">Consultar</a>'+
                            '</li>'+
                        '</ul>'+
                    '</li>'+
                    '<li id="liusuarios" >'+                        
                        '<a href="#usersSubMenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                            '<i class="fas fa-users"></i>'+
                            '&nbspUsuarios'+
                        '</a>'+
                        '<ul class="collapse list-unstyled" id="usersSubMenu">'+
                            '<li id="registroadmin">'+
                                '<a href="../LoadCountries.do?id=1">Registro Administrador</a>'+
                            '</li >'+
                            '<li id="registrodirectivo">'+
                            '<a href="../LoadCountries.do?id=3">Registro Directivo</a>'+
                            '</li >'+
                            '<li id="registroempleado">'+
                            '<a href="../LoadCountries.do?id=2">Registro Empleado</a>'+
                            '</li>'+
                            '<li id="consultar">'+
                                '<a href="../QueryUsers.do">Administracion</a>'+
                            '</li>'+                            
                        '</ul>'+
                    '</li>'+
                    '<li id="lisolicitudes" >'+
                        '<a href="../QuerySolicitudes.do">'+
                            '<i class="fas fa-question"></i>'+
                            '&nbspSolicitudes'+
                        '</a>'+
                    '</li>'+
                     '<li id="livisitas" >'+
                        '<a href="../QueryVisitas.do">'+
                            '<i class="fa fa-building"></i>'+
                            '&nbspVentas'+
                        '</a>'+
                    '</li>'+
                    
                    '<li>'+
                        '<a href="../LogOut.do">'+
                            '<i class="fas fa-lock">'+'</i>'+
                            '&nbspCerrar Sesión'+
                        '</a>'+
                    '</li>'+                    
                '</ul>';

var mostrar = ["documentsSubmenu", "usersSubMenu", "lisolicitudes"];
var active = ["documentsnew", "documentsquery","registroadmin","registrodirectivo","registroempleado","consultar","lisolicitudes"];

//carga el sidebar cuando entro a perfil
function loadPerfil(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
    document.getElementsByTagName('a')[0].href = "#";
}
//carga el sidebar 
function load(a,b){ 
   
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById(mostrar[a]).className += " show";
    document.getElementById(active[b]).className = "active";
}