function validarForm(event) {

    // FORMULÁRIO

    const form = new FormData(event.target);


    // CAMPOS DO FORMULÁRIO

    const nome = form.get('nome');
    const email = form.get('email');
    const senha = form.get('senha');
    const confirmarSenha = form.get('confirmarSenha');


    // VALIDAÇÕES

    if (nome != '' && email != '' && senha != '' && confirmarSenha != '') {


        console.log("Certo, usuário preencheu os campos, agora iremos verificar!");


        // EMAIL SEM '@'

        if (!email.includes('@')) {

            console.log('Email precisa conter "@"');
            alert('Email precisa conter "@"');
            event.preventDefault();
            return false;

        }

        
        // EMAIL SEM '.'

        if (!email.includes('.')) {

            console.log('Email precisa conter "."');
            alert('Email precisa conter "."');
            event.preventDefault();
            return false;

        }


        // SENHA COM MENOS DE 6 CARACTERES

        if (senha.length <= 6) {

            console.log("Senha precisa ter mais que 6 caracteres!");
            alert("Senha precisa ter mais que 6 caracteres!");
            event.preventDefault();
            return false;

        }


        // SENHA INCORRETA (CONFIRMAR É DIFERENTE DA SENHA)

        if (confirmarSenha != senha) {

            console.log("Senha Incorreta!");
            alert("Senha Incorreta!");
            event.preventDefault();
            return false;

        }

        console.log("Formulário validado com Sucesso!");
        alert("Formulário validado com Sucesso!");

    }

    else {

        console.log("Preencha os Campos!");
        alert("Preencha os campos!");
        event.preventDefault();
        return false;

    }

}



document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("form-cadastro").addEventListener("submit", validarForm);

});