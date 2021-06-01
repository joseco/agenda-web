(function(){

    if(!sessionStorage.getItem("userInSession")){
        document.location.href = "login.html";
        return;
    }
    document.querySelector("body").style="display:block";
    const urlParams = new URLSearchParams(window.location.search);
    const contactoId = urlParams.get('id');

    if(contactoId && !isNaN(contactoId)){
        document.querySelector("#page-title").innerHTML = "Editar contacto"

        fetch(`api/contacto/${contactoId}`)
            .then(response =>{
                return response.json();
            })
            .then(data => {

                const nombre = document.querySelector("#nombreContacto");
                const email = document.querySelector("#email");
                const telefono = document.querySelector("#telefono");
                const contactoIdControl = document.querySelector("#contactoId");

                nombre.value = data.nombreContacto;
                email.value = data.email;
                telefono.value = data.telefono;
                contactoIdControl.value = data.contactoId;

            })

    }else{
        document.querySelector("#page-title").innerHTML = "Nuevo contacto"
    }



    document.querySelector("#btn-save").addEventListener('click', ()=>{

        const contactoId = document.querySelector("#contactoId").value
        const nombre = document.querySelector("#nombreContacto").value.trim();
        const email = document.querySelector("#email").value.trim();
        const telefono = document.querySelector("#telefono").value.trim();

        const validacionNombre = document.querySelector("#validation-name");
        const validacionEmail = document.querySelector("#validation-email");
        const validacionTelefono = document.querySelector("#validation-telefono");

        const msgError = document.querySelector("#msg-error");

        validacionNombre.style.display = "none"
        validacionEmail.style.display = "none"
        validacionTelefono.style.display = "none"
        msgError.style.display = "none"

        let hasError = false;
        if(nombre == ""){
            hasError = true;
            validacionNombre.style.display = "block";
        }
        if(email == ""){
            hasError = true;
            validacionEmail.innerHTML = "El correo electrónico es requerido"
            validacionEmail.style.display = "block";
        }else if(!isEmailValid(email)){
            hasError = true;
            validacionEmail.innerHTML = "El correo electrónico ingresado no es válido"
            validacionEmail.style.display = "block";
        }
        if(telefono == ""){
            hasError = true;
            validacionTelefono.style.display = "block";
        }

        if(hasError)
            return;

        const usuario = JSON.parse(sessionStorage.getItem("userInSession"));
        const contacto = {
            usuarioId : usuario.usuarioId,
            nombreContacto  : nombre,
            email : email,
            telefono : telefono,
            contactoId : contactoId
        }
        const method = contactoId == "0" ? "POST" : "PUT"; //POST = insert y PUT = UPDATE
        fetch('api/contacto', {
            method: method,
            headers: {
                'Accept': 'application/json', //MimeType
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(contacto)
        }).then((response) =>{
            return response.json();
        }).then((data) => {
            debugger;
            if(!data){
                msgError.innerHTML = data.message;
                msgError.style.display = "block"
                return;
            }
            document.location.href = "index.html?msg=ok_contact_saved";

        });
    })


})();

function isEmailValid(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}
