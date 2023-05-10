var xhttp = new XMLHttpRequest();
const xhr = new XMLHttpRequest();

var userEmail;
var userName;
var userLastname;
var userPhoneNumber;
var roles;

loginControl();
async function loginControl() {
  let token = await fetchCookie("token");
  if (token !== undefined || token != null) {
    let status = await fetchData(`http://my.batuhan.com:8080/user/tokenControl?token=${token}`);
    if (status == "false") {
      document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      window.location.href = "http://my.batuhan.com:5500/pages/login.html";
    } else {
      let token = await fetchCookie("token");
      let userE = await fetchData(`http://my.batuhan.com:8080/user/getEmail?token=${token}`);
      let url = `http://my.batuhan.com:8080/person/getPerson/${userE}`;
      const person = await makeApiCall(url);

      if(!person.roles.includes("ADMIN")) {
        window.location.href = "http://my.batuhan.com:5500/pages/user.html";
    }
    }
  } else {
    window.location.href = "http://my.batuhan.com:5500/pages/login.html";
  }

}
async function logout() {
  document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  window.location.href = "http://my.batuhan.com:5500/pages/login.html";
}



async function user() {
  let token = await fetchCookie("token");
  let userE = await fetchData(`http://my.batuhan.com:8080/user/getEmail?token=${token}`);
  let url = `http://my.batuhan.com:8080/person/getPerson/${userE}`;

  const person = await makeApiCall(url);
  userEmail = person.email;
  userName = person.name;
  userLastname = person.lastName;
  userPhoneNumber = person.phoneNumber;
  roles = person.roles;
  
  document.getElementById("logo").innerText = "Hoşgeldin " + userName + " " + userLastname;
  
  return Promise.resolve();
  
}


async function category(buttonName) {
  var panel = document.getElementById("panel");
  var divs = panel.querySelectorAll("div");

  var classs = ["fee", "block", "person", "apartment"];

  for (var i = 0; i < divs.length; i++) {
    var classList = divs[i].classList;

    for (var c = 0; c < classs.length; c++) {
      if (classList.contains(classs[c])) {
        if (classList.contains(buttonName)) {
          if (classList.contains("hide")) {
            classList.remove("hide");
          } else {
            classList.add("hide");
          }
        } else {
          if (!classList.contains("hide")) {
            classList.add("hide");
          }
        }
      }
    }

  }

}

async function fetchCookie(name) {
  var cookies = document.cookie.split(";");
  for (var i = 0; i < cookies.length; i++) {
    var a = cookies[i].split("=");
    if (a[0].trim() === name)
      return ("" + a[1] + "!");
  }
}

async function fetchData(url) {
  try {
    const response = await fetch(url, {

    });
    var data = await response.text();
    return data;
  } catch (error) {
    console.error(error);
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    window.location.href = "http://my.batuhan.com:5500/pages/login.html";
  }
}

async function makeApiCall(url, method) {
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
    return x;
  }
  data = await response.json();

  //fetch(`).then(response => response.text()).then(text => {
  return data;
}

let xx = "";
async function requestBody(url, data) {

  try {
    let bearerToken = await fetchCookie("token");
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader('Authorization', `Bearer ${bearerToken}`);

    let successMessages = ["başarı", "oluşturuldu"];
    
    xhr.onreadystatechange = async function () {
      if (xhr.readyState === 4) {
        if (!url.includes("sendMessage")) {
          let x = false;

          for(var i = 0; i < successMessages.length; i++) {
            if(xhr.responseText.includes(successMessages[i])) {
              x = true;
              continue;
            }
          }

          if (x) {
            Fnon.Hint.Success(xhr.responseText);
          } else {
            Fnon.Hint.Danger(xhr.responseText, "Bir hata oluştu");
          }
        }
      }
    }

    const body = JSON.stringify(data);
    xhr.send(body);

  } catch (error) {
    Fnon.Hint.Danger('Sunucu bağlantısı kurulamadı!', 'Bağlantı Hatası');
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    window.location.href = "http://my.batuhan.com:5500/pages/login.html";
    console.error(error);
  }


}



