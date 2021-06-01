(function(){

    if(!sessionStorage.getItem("userInSession")){
        document.location.href = "login.html";
        return;
    }
    document.querySelector("body").style="display:block"

    const urlParams = new URLSearchParams(window.location.search);
    const msg = urlParams.get('msg');
    if(msg){
        document.querySelector("#" + msg).style.display = "block"
        setTimeout(function(){ 
            document.querySelector("#" + msg).style.display = "none"
        }, 5000);
    }

    cargarContactos();
    
    document.getElementById("btn-salir").addEventListener('click',(e) =>{
        e.preventDefault();
        sessionStorage.removeItem("userInSession");
        document.location.href = "login.html";        
    })

})();


function cargarContactos(){
    let userInSession = JSON.parse(sessionStorage.getItem("userInSession"))
    fetch("api/contacto/usuario/" + userInSession.usuarioId)
        .then((response) => {
            return response.json();
        })
        .then(function(data){
            console.log("imprimiendo contactos")
            console.log(data)
            mostrarContactos(data);
        })
        .catch((error) => {
            console.log(error);
            alert("Ocurrion un error al obtener los usuarios");
        });
    
}

function mostrarContactos(contactos){
    const contactosHTML = document.querySelector("#contactos");
    contactosHTML.innerHTML = "";
    
    if(contactos.length == 0){
        contactosHTML.innerHTML = '<div class="col-12 text-center mb-5">No tiene contactos registrados. Presione el boton "Nuevo contacto" para registrar uno nuevo.</div>'
        return;
    }
    

    let html = ""
    for(const i in contactos){
        const obj = contactos[i];

        let contactoHTML = getContactoInHTML(obj);

        html += contactoHTML;
    }
    contactosHTML.innerHTML = html;
}

function getContactoInHTML(obj){
    
    return `<div class="col-sm-12 col-md-6 col-lg-3 ">
                <div class="card mb-4">
                    <div class="text-center mt-3 mb-3">
                        <img src="img/User-icon.png" class="card-img-top contacto-img" alt="...">
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">${ obj.nombreContacto }</h5>
                        <div class="card-text mb-2">
                            <div class="mb-1"><strong>Teléfono:</strong> ${ obj.telefono }</div>
                            <div><strong>Correo electrónico:</strong> ${ obj.email }</div>
                        </div>
                        <a href="contacto-frm.html?id=${ obj.contactoId }" class="btn btn-primary btn-block">Editar</a>
                        <button type="button" class="btn btn-danger btn-block" onclick="eliminarContacto(${ obj.contactoId })">Eliminar</button>
                    </div>
                </div>
            </div>`
}

function eliminarContacto(contactoId){
    if(!confirm("¿Esta seguro que desea eliminar el contacto seleccionado?")){
        return;
    }
    
    console.log("Eliminando contacto " + contactoId)
    
    fetch('api/contacto/' + contactoId, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json', //MimeType
            'Content-Type': 'application/json'
        }
    }).then((response) =>{
        return response.json();
    }).then((data) => {
        debugger;
        if(!data.isOK){
            alert(data.message);
            return;
        }
        cargarContactos();

    });
}

function editarContacto(evt, contactoId){
    evt.preventDefault();
    debugger;
}