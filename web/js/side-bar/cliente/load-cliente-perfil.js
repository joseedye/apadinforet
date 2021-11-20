var sidebar = '<div class="sidebar-header">' +
        '<h3 style="color: #fff !important">INFORET</h3>' +
        '<strong style="color: #fff !important">CS</strong>' +
        '</div>' +
        '<ul class="list-unstyled components">' +
        '<li id="liperfil" >' +
        '<a href="perfil">' +
        '<i class="fas fa-home"></i>' +
        '&nbspPerfil' +
        '</a>' +
        '</li>' +
        
        '<li id="lidocumentos" >' +
          '<a href="#documentsSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">' +
             '<i class="fas fa-copy"></i>' +
            '&nbspDocumentos' +
          '</a>' +
        '<ul class="collapse list-unstyled" id="documentsSubmenu">' +
        '<li id="documentsnew">' +
        '<a id="consdocumentos" href="#" onclick="consultar()">Consultar</a>' +
        '</li>' +
        '</ul>' +
        '</li>' +     
        
        '<li id="lisolicitudes" >' +
         '<a href="#solicitudesSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">' +
             '<i class="fas fa-question"></i>' +
             '&nbspSolicitudes' +
          '</a>' +
        '<ul class="collapse list-unstyled " id="solicitudesSubmenu">' +
              '<li id="solicitudcotizacion">' +
                  '<a  href="#" >Cotizacion</a>' +
              '</li>' +
             '<li id="liconsultar" >' +
                    '<a href="../QueryCompanies.do">Consultar</a>' +
             '</li>' +
        '</ul>' +
        '</li>' +
        
        '<li id="livisitas" >' +
        '<a href="../QueryVisitas.do">' +
        '<i class="fa fa-building"></i>' +
        '&nbspProductos o servicios' +
        '</a>' +
        '</li>' +
        
        '<li>' +
        '<a href="../LogOut.do">' +
        '<i class="fas fa-lock">' + '</i>' +
        '&nbspCerrar Sesión' +
        '</a>' +
        '</li>' +
        '</ul>';

var sidebar1 = '<div class="sidebar-header">' +
        '<h3 style="color: #fff !important">INFORET</h3>' +
        '<strong style="color: #fff !important">CS</strong>' +
        '</div>' +
        '<ul class="list-unstyled components">' +
        '<li id="liperfil" >' +
        '<a href="perfil">' +
        '<i class="fas fa-home"></i>' +
        '&nbspPerfil' +
        '</a>' +
        '</li>' +
        '<li>' +
        '<a href="../LogOut.do">' +
        '<i class="fas fa-lock">' + '</i>' +
        '&nbspCerrar Sesión' +
        '</a>' +
        '</li>' +
        '</ul>';

function loadPerfil() {

    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liperfil').className = "active";
    document.getElementsByTagName('a')[0].href = "#";

}

var mostrar = ["documentsSubmenu", "usersSubMenu"];
var active = ["documentsnew", "documentsquery", "consultar"];


//carga el sidebar 
function load(a, b) {

    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById(mostrar[a]).className += " show";
    document.getElementById(active[b]).className = "active";
}

function mostrarpdf(ruta) {
    var ruta = document.getElementById(ruta).innerHTML;
    window.open(ruta);
}


$(document).ready(function () {
    $("#ventana2").modal('show');
});
$(document).ready(function () {
    $("#ventana3").modal('show');
});


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

$('#getunoo').change(function (event) {
    var tmppath = URL.createObjectURL(event.target.files[0]);

    $("#tres").html(tmppath);
});



function consultar() {
    var id = document.getElementById("iduser").value;
    window.location.href = "../SeeDocuments.do?idUserQuery=" + id;

    console.log("id es " + id);

}