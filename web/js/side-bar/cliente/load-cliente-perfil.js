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
        '<li>' +
        '<a href="../QueryTypeDocuments.do">Nuevo</a>' +
        '</li>' +
        '<li>' +
        '<a href="../QueryDocuments.do">Consultar</a>' +
        '</li>' +
        '</ul>' +
        '</li>' +
        '<li id="lisolicitudes" >' +
        '<a href="../QuerySolicitudes.do">' +
        '<i class="fas fa-question"></i>' +
        '&nbspSolicitudes' +
        '</a>' +
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

function loadPerfil(i) {

    if (i == '1') {
        document.getElementById('sidebar').innerHTML = sidebar;
        document.getElementById('liperfil').className = "active";
        document.getElementsByTagName('a')[0].href = "#";

    } else {
        document.getElementById('sidebar').innerHTML = sidebar1;
        document.getElementById('liperfil').className = "active";
        document.getElementsByTagName('a')[0].href = "#";
    }

}




function loadDocumentos() {
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lidocumentos').className = "active";
    document.getElementById('documentsSubmenu').className += " show";
}

function loadSolicitudes(i) {
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('lisolicitudes').className = "active";

    if (i === 1) {
        loadVisitas();
    }
}

function loadVisitas() {
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('livisitas').className = "active";
}

function loadEmpresas() {
    document.getElementById('sidebar').innerHTML = sidebar;
    document.getElementById('liempresas').className = "active";
    document.getElementById('CompaniesSubmenu').className += " show";
}




