async function createAccount(){
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

    const responseStatus = await axios({
        method:"POST",
        url:`/api/account`,
        headers:{
            "X-CSRF-TOKEN" : $("meta[name='_csrf']").attr("content"),
            "Content-Type": "application/json"
        },
        data:JSON.stringify(requestCreateAccountDto)
    }).then(response=> response.status)
    .catch(error => {
        console.log(error.response.data);
        return error.response.data.status;
    });

    console.log(responseStatus);

    if(responseStatus == 200){
        alert("계정 생성 성공!");
        location.href = "/sign-in.html";
    }
}