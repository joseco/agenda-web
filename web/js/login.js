(function(){
    
    if(sessionStorage.getItem("userInSession")){
        document.location.href = "index.html";
        return;
    }

    const btnLogin = document.querySelector("#btn-login");

    btnLogin.addEventListener('click',(e) =>{
        e.preventDefault();
        
        const [ validationUsername, validationPassword, msgError ] =
                    document.querySelectorAll("#validation-username, #validation-password, #msg-error-login");
        /*
        const elementos = document.querySelectorAll("#validation-username, #validation-password");
        const validationUsername = elementos[0];
        const validationPassword = elementos[1];
        const msgError = elementos[2];
        */

        validationUsername.style.display = "none";
        validationPassword.style.display = "none";
        msgError.style.display = "none";

        const inputUsername = document.querySelector("#inputUsername");
        const inputPassword = document.querySelector("#inputPassword");
        let hasError = false;
        if(inputUsername.value == ""){
            hasError = true;
            validationUsername.style.display = "block";
        }
        if(inputPassword.value == ""){
            hasError = true;
            validationPassword.style.display = "block";
        }

        if(hasError){
            return;
        }

        const usuario = {
            "username" : inputUsername.value,
            "password" : inputPassword.value
        }
        fetch('api/usuario/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json', //MimeType
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        }).then((response) =>{
            return response.json();
        }).then((data) => {
            debugger;
            if(!data.isOK){
                msgError.innerHTML = data.message;
                msgError.style.display = "block"
                return;
            }
            sessionStorage.setItem("userInSession" , data.message);

            document.location.href = "index.html";

        });

        /*
        var elementos = document.querySelectorAll("#validation-username, #validation-password");
        for(let i =0 ; i < elementos.length; i++){
            elementos[i].style.display = "none";
        }

        var elementos = document.querySelectorAll("#validation-username, #validation-password");
        for(let i in elementos){
            elementos[i].style.display = "none";
        }
        */

    })



})();