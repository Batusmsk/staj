async function fetchCookie(name) {
    var cookies = document.cookie.split(";");
for(var i=0; i<cookies.length;i++){
    var a = cookies[i].split("=");
    if(a[0].trim() === name)
        return(""+a[1]);
}
}

function wait(second) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve('resolved');
        }, second);
    });
}

async function logout() {
    const queryString = window.location.search; // 
    const urlParams = new URLSearchParams(queryString);
    const token = urlParams.get('token');
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";

    if(token != null ||token.length > 15) {
        window.location.href = `http://my.batuhan.com:5500/pages/reset-password.html?token=${token}`
    } else {
        window.location.href = "http://my.batuhan.com:5500/pages/login.html";
    }
  }

control();
async function control() {
    const queryString = window.location.search; // 
    const urlParams = new URLSearchParams(queryString);
    let div = document.getElementById("container");
    const token = urlParams.get('token');

    if(token == null || token == undefined || token.length < 15) {
        return div.innerHTML = `<h2 style="font-size: 50px;">Bu sayfaya şuanda erişemezsiniz.</h2>`;
    }

    let userToken = await fetchCookie("token");
    if(userToken != undefined) return div.innerHTML = `<a href="#" onClick="logout()"><h2 style="font-size: 50px;">Öncelikle hesabınızdan çıkış yapmanız gerekmektedir.</h2></a>`;
}

async function resetPassword() {
    const queryString = window.location.search; // 
    const urlParams = new URLSearchParams(queryString);
    let div = document.getElementById("container");
    const token = urlParams.get('token');

    let password = document.getElementById("password").value;
    let password2= document.getElementById("password2").value;
   
    if(password == password2) {
        
        let data = {newPassword: password, token: token};
        await requestBody("http://my.batuhan.com:8080/resetPassword/resetPassword", data);
    } else {
        Fnon.Hint.Danger("İki şifre birbirlerine uyuşmuyor"); 
    }
}

var xhttp = new XMLHttpRequest();
const xhr = new XMLHttpRequest();
async function requestBody(url, data) {
    Fnon.Wait.Infinity();
    try {
        
        xhr.open("POST", url);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = async function () {
            if(xhr.status == 200) {
                if(xhr.responseText.includes("başarıyla ")) {
                    Fnon.Hint.Success(xhr.responseText, "");
                    
                } else {
                    Fnon.Hint.Danger(xhr.responseText, ""); 
                }
                Fnon.Wait.Remove();
            } else {   
            }       
        };
        
        const body = JSON.stringify(data);
        xhr.send(body);
   
    } catch(error) {
        Fnon.Wait.Remove();
        Fnon.Hint.Danger('Sunucu bağlantısı kurulamadı!','Bağlantı Hatası');
        console.error(error);
    } 
}