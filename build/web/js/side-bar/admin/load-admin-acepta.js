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
                        '<ul class="collapse list-unstyled" id="documentsSubmenu">'+
                            '<li>'+
                                '<a href="../QueryTypeDocuments.do">Nuevo</a>'+
                            '</li>'+
                            '<li>'+
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
                            '<li>'+
                                '<a href="../LoadCountries.do?id=1">Registro Administrador</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="estudiantes_registrar">Registro Cliente</a>'+
                            '</li>'+
                            '<a href="estudiantes_registrar">Registro Proveedor</a>'+
                            '</li>'+
                            '<a href="estudiantes_registrar">Registro Gerente</a>'+
                            '</li>'+
                            '<a href="../LoadCountries.do?id=2">Registro Empleado</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="../QueryUsers.do">Consultar</a>'+
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
                    '<li id="liempresas" >'+                        
                        '<a href="#CompaniesSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">'+
                            '<i class="fas fa-briefcase"></i>'+
                            '&nbspCliente'+
                        '</a>'+
                        '<ul class="collapse list-unstyled" id="CompaniesSubmenu">'+
                            '<li>'+
                                '<a href="empresa_registrar">Registrar Cliente</a>'+
                            '</li>'+
                            '<li>'+
                                '<a href="../QueryCompanies.do">Consultar</a>'+
                            '</li>'+
                        '</ul>'+
                    '</li>'+
                    '<li>'+
                        '<a href="../LogOut.do">'+
                            '<i class="fas fa-lock">'+'</i>'+
                            '&nbspCerrar Sesión'+
                        '</a>'+
                    '</li>'+                    
                '</ul>';


function loadPerfil(){    
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
    document.getElementsByTagName('a')[0].href = "#";
}





