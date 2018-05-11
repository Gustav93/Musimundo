var cron;
var sv_min = 0;
var sv_hor = 0;
var sv_seg = 0;
var seg = document.getElementById('seg');
var min = document.getElementById('min');
var hor = document.getElementById('hor');
var iniciado = false; //init status of cron
var totalCarrosActual = 0;
var totalCarrosNueva = 0;

actualizarCarros();

$(document).ready(function () {
    setInterval(actualizarTotales, 1000);
    setInterval(actualizarCarros, 20000);
    $.ajaxSetup({cache: false});
});
$(document).ready(function() {
    var table = $('#datatable_carros').DataTable();

    $('#datatable_carros tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();

        // console.log(data["id"]);
        //
        //
        // alert( 'You clicked on '+data["id"]+'\'s row' );
        // location.href = "get_carro?idCarro=" + data["id"]
        window.open("get_carro?idCarroDelDia=" + data["id"]);
    } );
} );
function correrCronometro() {
    if (!iniciado) {
        iniciado = true;
        cronometro();
    }
}

function cronometro() {
    cron = setInterval(function () {
        sv_seg++;
        if (sv_seg < 60) {
            if (sv_seg < 10) {
                seg.innerHTML = "0" + sv_seg;
            } else {
                seg.innerHTML = sv_seg;
            }
        } else {
            sv_seg = 0;
            seg.innerHTML = "00";
            sv_min++;
            if (sv_min < 60) {
                if (sv_min < 10) {
                    min.innerHTML = "0" + sv_min;
                } else {
                    min.innerHTML = sv_min;
                }
            } else {
                sv_min = 0;
                min.innerHTML = "00";
                sv_hor++;
                if (sv_hor < 10) {
                    hor.innerHTML = "0" + sv_hor;
                } else {
                    hor.innerHTML = sv_hor;
                }
            }
        }
    }, 1000);
}

function resetCronometro() {
    sv_min = 0;
    sv_hor = 0;
    sv_seg = 0;
    seg.innerHTML = "00";
    min.innerHTML = "00";
    hor.innerHTML = "00";
    clearInterval(cron);
    iniciado = false;
}

function actualizarTotales() {
    $("#totalCarros").text(function () {
        $("#totalCarros").load("/total_carros");
    });

    $("#totalCarrosCarsa").text(function () {
        $("#totalCarrosCarsa").load("/total_carros_carsa");
    });

    $("#totalCarrosEmsa").text(function () {
        $("#totalCarrosEmsa").load("/total_carros_emsa");
    });

    $("#totalRecaudado").text(function () {
        $("#totalRecaudado").load("/total_recaudado");
    });

    $("#totalRecaudadoCarsa").text(function () {
        $("#totalRecaudadoCarsa").load("/total_recaudado_carsa");
    });

    $("#totalRecaudadoEmsa").text(function () {
        $("#totalRecaudadoEmsa").load("/total_recaudado_emsa");
    });

    $("#totalProductos").text(function () {
        $("#totalProductos").load("/total_productos");
    })

    $("#totalProductosCarsa").text(function () {
        $("#totalProductosCarsa").load("/total_productos_carsa");
    })

    $("#totalProductosEmsa").text(function () {
        $("#totalProductosEmsa").load("/total_productos_emsa");
    })

    console.log("nueva: " + totalCarrosNueva);
    console.log("actual: " + totalCarrosActual)

    verificarCarrosNuevos();
}

function actualizarCarros() {
    $(document).ready(function () {
        $("#actualizarReporte").load("/actualizar_carros");
        $('#datatable_carros').DataTable();
    })
}

function verificarCarrosNuevos() {
    totalCarrosNueva = $("#totalCarros").html();

    if(totalCarrosActual != totalCarrosNueva) {
        resetCronometro();
        correrCronometro();
        totalCarrosActual = totalCarrosNueva;
    }
}