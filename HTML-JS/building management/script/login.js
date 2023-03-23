var xhttp = new XMLHttpRequest();
const xhr = new XMLHttpRequest();

function wait(second) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve('resolved');
        }, second);
    });
}

function get(name) {
    if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
        return decodeURIComponent(name[1]);
}
async function fetchCookie(name) {
    var cookies = document.cookie.split(";");
for(var i=0; i<cookies.length;i++){
    var a = cookies[i].split("=");
    if(a[0].trim() === name)
        return(""+a[1]+"!");
}
}

loginControl();
async function loginControl() {
    let token = await fetchCookie("token");
    console.log(token);
    if(token !== undefined || token != null) {
        let status = await fetchData(`http://localhost:8080/user/tokenControl?token=${token}`);
        console.log(status)
        if(status == "true") {
            let role = await fetchData(`http://localhost:8080/user/getUserRoles?token=${token}`);
            if(role.includes("ADMIN")) {
                window.location.href = "http://127.0.0.1:5500/admin.html";
            } else {
                window.location.href = "http://127.0.0.1:5500/user.html";
            }
        } else {
            console.log("silinecek!! " + token);
            
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            
        }
    } 
}

async function loginButton() {
    var email = document.getElementById("email");
    const password = document.getElementById("password");

    const data = {email: `${email.value}`, password: `${email.value}`};
    let x = await requestBody("http://localhost:8080/user/login", data);
}

let xx = "";
async function requestBody(url, data) {
    try {
        xhr.open("POST", url);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = async function () {
          if (xhr.readyState === 4) {
            console.log(xhr.status);
            console.log(xhr.responseText);
            document.cookie = `token=${xhr.responseText};`;
            
            if(xhr.status == 403) {
                return alert("Email veya şifre yanlış");
            } else if (xhr.status == 200) {
                let roles = await fetchData(`http://localhost:8080/user/getUserRoles?token=${xhr.responseText}`);
                if(roles.includes("ADMIN")) {
                    xx = `http://127.0.0.1:5500/admin.html`;
                    document.cookie = `token=${xhr.responseText}; path=/;`;
                } else {
                    xx = `http://127.0.0.1:5500/user.html`;
                    document.cookie = `token=${xhr.responseText}; path=/;`;
                }
                window.location.href =xx;
                
                console.log(roles);
            } else {
                return alert("HATA!");
            }
          }
        };
        
        const body = JSON.stringify(data);
        xhr.send(body);
    } catch(error) {
        console.error(error);
    }
}

async function fetchData(url) {
    try {
        const response = await fetch(url);
        var data = await response.text();
        return data;
    } catch (error) {
        console.error(error);
    }
}