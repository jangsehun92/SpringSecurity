function createAccount(){
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let role = document.getElementById("role").value;

    let requestCreateAccountDto = {
        "email" : email,
        "password" : password,
        "name" : name,
        "role" : role
    };

    console.log(requestCreateAccountDto);

}