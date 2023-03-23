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
    if(token !== undefined || token != null) {
        let status = await fetchData(`http://localhost:8080/user/tokenControl?token=${token}`);
        if(status == "false") {
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = "http://127.0.0.1:5500/login.html";
        } 
    }  else {
        window.location.href = "http://127.0.0.1:5500/login.html";
    }
}

async function fetchData(url) {
    try {
        const response = await fetch(url);
        var data = await response.text();
        return data;
    } catch (error) {
        console.error(error);
        document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        window.location.href = "http://127.0.0.1:5500/login.html";
    }
}