


$(document).ready(function () {
    $("#ventana2").modal('show');
});

$(function () {
    $(".modal-btn").click(function () {
        var id = $(this).data('some-id');
        $(".modal-footer a").attr("href", "../DeleteUser.do?idUsuario=" + id);
    })
});

function loadCheck(datos) {

    // Comprobar los checkbox seleccionados

    var empleado = false, cliente = false, proveedor = false, gerente = false;
    if ($('#fempleado').is(':checked')) {
        empleado = true;
    }
    if ($('#fcliente').is(':checked')) {
        cliente = true;
    }
    if ($('#fproveedor').is(':checked')) {
        proveedor = true;
    }
    if ($('#fgerente').is(':checked')) {
        gerente = true;
    }
    location.href = '../QueryUsers.do?empleado=' + empleado + '&cliente=' + cliente + '&proveedor=' + proveedor + '&gerente=' + gerente + ''+datos;
    ;
}

function loadCheckActive(id, activo) {
var datos = '&&idmodifi='+id+'&&activo='+activo;
loadCheck(datos);
}

//Table
$(document).ready(function () {
    $('#table').DataTable();
});

function myFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}