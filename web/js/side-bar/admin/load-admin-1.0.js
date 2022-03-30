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
                            '<li id="asignar">'+
                                '<a href="../AssignEmployes.do?cod=0">Asignar empleados</a>'+
                            '</li>'+  
                        '</ul>'+
                    '</li>'+
                    '<li id="lisolicitudes" >'+
                        '<a href="../QuerySolicitudes.do">'+
                            '<i class="fas fa-question"></i>'+
                            '&nbspSolicitudes'+
                        '</a>'+
                    '</li>'+
                     '<li id="liconfig" >'+
                        '<a href="../Settings.do?id=0">'+
                            '<i class="fa fa-cogs"></i>'+
                            '&nbspConfiguracion'+
                        '</a>'+
                    '</li>'+
                    
                    '<li>'+
                        '<a href="../LogOut.do">'+
                            '<i class="fas fa-lock">'+'</i>'+
                            '&nbspCerrar Sesión'+
                        '</a>'+
                    '</li>'+                    
                '</ul>';

var mostrar = ["documentsSubmenu", "usersSubMenu", "lisolicitudes","liconfig"];
var active = ["documentsnew", "documentsquery", "registroadmin", "registrodirectivo", "registroempleado", "consultar", "lisolicitudes","liconfig","asignar"];

//carga el sidebar cuando entro a perfil
function loadPerfil() {
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
    document.getElementsByTagName('a')[0].href = "#";
}
//carga el sidebar 
function load(a, b) {

    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById(mostrar[a]).className += " show";
    document.getElementById(active[b]).className = "active";
}

function validar() {


    if ($("#file1").val() === "" && $("#file2").val() === "" && $("#file3").val() === "") {
        alert("Seleccione almenos un archivo");
        return;
    }
    
    var descripcion1 = document.getElementById('desc1').value;
    var descripcion2 = document.getElementById('desc2').value;
    var descripcion3 = document.getElementById('desc3').value;
    
    
    
    var des = '<input class="form-control" type="text" id="descr1" value="'+descripcion1+'" name="desc1" style="display:none"/>'+
            '<input class="form-control" type="text" id="descr2" value="'+descripcion2+'" name="desc2" style="display:none"/>'+
            '<input class="form-control" type="text" id="descr3" value="'+descripcion3+'" name="desc3" style="display:none" />';
            
    
    
    document.getElementById('insertdescripcion').innerHTML = des;
    
    document.form4.submit();
   }

$(document).ready(function () {
    $("#ventana2").modal('show');
});

function mostrarpdf(ruta) {
    var ruta = document.getElementById(ruta).innerHTML;
    window.open(ruta);
}
$(document).ready(function () {
                $('[data-toggle="popover"]').popover();
            });
            
function mostrarnoti() {
    $("#ventana3").modal('show');
}

$('#getuno').change(function (event) {
    var tmppath = URL.createObjectURL(event.target.files[0]);

    $("#uno").html(tmppath);
});

$('#getdos').change(function (event) {
    var tmppath = URL.createObjectURL(event.target.files[0]);

    $("#dos").html(tmppath);
});
$('#gettres').change(function (event) {
    var tmppath = URL.createObjectURL(event.target.files[0]);

    $("#tres").html(tmppath);
});