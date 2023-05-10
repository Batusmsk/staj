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
    if(token !== undefined || token != null) {
        
        let status = await fetchData(`http://my.batuhan.com:8080/user/tokenControl?token=${token}`);

        if(status == "true") {
            let role = await fetchData(`http://my.batuhan.com:8080/user/getUserRoles?token=${token}`);
            if(role.includes("ADMIN")) {
                window.location.href = "http://my.batuhan.com:5500/pages/admin.html";
            } else {
                window.location.href = "http://my.batuhan.com:5500/pages/user.html";
            }
            
        } else {
 
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/pages;";         
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";     
            
        }
    }   
}

async function loginButton() {
    
    var email = document.getElementById("email");
    const password = document.getElementById("password");

    const data = {email: `${email.value}`, password: `${password.value}`};
    let x = await requestBody("http://my.batuhan.com:8080/user/login", data);
   
    
}

async function iForgotMyPass()
{
    Fnon.Dialogue.Danger({
        title:' ',
        message:`<div><input type="text" id="reset-email" placeholder="Mail Adresinizi Giriniz" class="reset-email"/></div>`,
        buttons:[
            {text: "iptal et", style: "Danger"}, { text:'Sıfırla', style: "Success", callback:( html) => {
                let email = document.getElementById("reset-email");
                if(!email.value.includes("@"))  {
                    return Fnon.Hint.Danger("Lütfen geçerli bir mail adresi giriniz"); 
                    
                }
                let x = makeApiCall(`http://my.batuhan.com:8080/resetPassword/createToken?email=`+email.value, true);
                

              }},
        ]
   });

}
let xx = "";
async function requestBody(url, data) {
    
    try {
        Fnon.Wait.Infinity();
        xhr.open("POST", url);
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = async function () {
          if (xhr.readyState === 4) {
             
            if(xhr.status == 403) {
                Fnon.Hint.Danger('Kullanıcı Adı Veya Şifre Hatalı',{
                    position: 'center-center',
                    fontSize: '14px',
                    width: '300px',
                    title: 'Hata'
                });
                
                Fnon.Wait.Remove();
            } else if (xhr.status == 200) {
                let roles = await fetchData(`http://my.batuhan.com:8080/user/getUserRoles?token=${xhr.responseText}`);
                if(roles.includes("ADMIN")) {
                    xx = `http://my.batuhan.com:5500/pages/admin.html`;
                } else {
                    xx = `http://my.batuhan.com:5500/pages/user.html`;
                    
                }
                document.cookie = `token=${xhr.responseText}; path=/`;
                window.location.href =xx;
                Fnon.Wait.Remove();
            } else {
                Fnon.Hint.Danger('Sunucu bağlantısı kurulamadı!','Bağlantı Hatası');
                Fnon.Wait.Remove();
                
            }
          }
        };
        
        const body = JSON.stringify(data);
        xhr.send(body);
        
    } catch(error) {
       
        Fnon.Hint.Danger('Sunucu bağlantısı kurulamadı!','Bağlantı Hatası');
        console.error(error);
        Fnon.Wait.Remove();
    }
    
    
}

async function fetchData(url) {
    try {
        Fnon.Wait.Infinity();
        const response = await fetch(url);
        var data = await response.text();
        Fnon.Wait.Remove();
        return data;
    } catch (error) {
        //Fnon.Hint.Danger('Sunucu bağlantısı kurulamadı!','Bağlantı Hatası');
        console.error(error);
        Fnon.Wait.Remove();
    }
    
}

async function makeApiCall(url, method) {
    Fnon.Wait.Infinity();

    let bearerToken = await fetchCookie("token");
    //var methodd = method.length > 1 ? "get" : method;
    const response = await fetch(url, {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${bearerToken}`,
      },
    });
  
    if (!response.ok) {
      //document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      //window.location.href = "http://my.batuhan.com:5500/pages/login.html";
      Fnon.Hint.Danger('Sunucu bağlantısı kurulamadı!', 'Bağlantı Hatası');
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  
    if (method == true) {
      var x;
      await response.text().then(text => {
        x = text;
      });

      if(x.includes("gönderildi")) {
        Fnon.Hint.Success(x, "");
    } else {
        Fnon.Hint.Danger(x, "");
    }
        Fnon.Wait.Remove();
      return x;
    }
    data = await response.json();
  
    //fetch(`).then(response => response.text()).then(text => {
    Fnon.Wait.Remove();
    return data;
  }
  