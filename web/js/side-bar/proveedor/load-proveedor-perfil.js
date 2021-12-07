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
        '<a href="#" onclick="consultar()">Consultar</a>' +
        '</li>' +
        '</ul>' +
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

function autoretenedor(a){
    
    var soloboton = '<div class="form-group col-md-4" >'+
                              '<label>&nbsp;</label><br>'+
                              '<button  onclick="autoretenedor(2)" name="Cambiar" class="btn btn-primary">Auto retenedor</button>'+
                         '</div>';
    
    var autoretenedor = '<div class="form-group col-md-4" >'+
                              '<label>&nbsp;</label><br>'+
                              '<button  onclick="autoretenedor(1)" name="Cambiar" class="btn btn-primary">Auto retenedor</button>'+
                         '</div>'+
                         
                       '<div class="form-group col-md-4" >'+
                           '<label for="">Fecha</label>'+
                           '<input class="form-control" type="date"  name="fechaa">'+
                       '</div>'+
                      '<div class="form-group col-md-4" >'+
                          '<label for="">Resolución</label>'+
                           '<input type="text" name="resola" class="form-control" id="" placeholder="123456" maxlength="100" >'+
                       '</div>'; 
               
    var botonEmviar = '<button type="submit" name="guardarformapago" class="btn btn-primary">Guardar</button>';
  
     document.getElementById('botonesauto').innerHTML = a===1 ? soloboton : autoretenedor;
     document.getElementById('gurdargran').innerHTML = a===1 ? "" : botonEmviar;
}

function grancontribuyente(a){
    
    var soloboton = '<div class="form-group col-md-4" >'+
                              '<label>&nbsp;</label><br>'+
                              '<button  onclick="grancontribuyente(2)" name="Cambiar" class="btn btn-primary">Gran Contribuyente</button>'+
                         '</div>';
    
    var grancontribuyente = '<div class="form-group col-md-3" >'+
                              '<label>&nbsp;</label><br>'+
                              '<button  onclick="grancontribuyente(1)" name="Cambiar" class="btn btn-primary">Gran Contribuyente</button>'+
                         '</div>'+
                         
                       '<div class="form-group col-md-3" >'+
                           '<label for="">Fecha</label>'+
                           '<input class="form-control" type="date"  name="fechag">'+
                       '</div>'+
                      '<div class="form-group col-md-3" >'+
                          '<label for="">Resolución</label>'+
                           '<input type="text" name="resolg" class="form-control" id="" placeholder="123456" maxlength="100" >'+
                       '</div>'+
                       '<div class="form-group col-md-3" >'+
                          '<label for="">Código actividad (ICA)</label>'+
                           '<input type="text" name="ica" class="form-control" id="" placeholder="123456" maxlength="100" >'+
                       '</div>'; 
               
    var botonEmviar = '<button type="submit" name="guardarformapago" class="btn btn-primary">Guardar</button>';
  
     document.getElementById('grancontribuyente').innerHTML = a===1 ? soloboton : grancontribuyente;
     document.getElementById('gurdargran').innerHTML = a===1 ? "" : botonEmviar;
}

function consultar() {
    var id = document.getElementById("iduser").value;
    window.location.href = "../SeeDocuments.do?idUserQuery=" + id; 
}

 $(document).ready(function () {
                                                $("#ventana2").modal('show');
                                            });
