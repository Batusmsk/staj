var email;
var userName;
var userLastname;
var userPhoneNumber;
var roles;

loginControl();
async function loginControl() {
    let token = await fetchCookie("token");
    if(token !== undefined || token != null) {
        let status = await fetchData(`http://my.batuhan.com:8080/user/tokenControl?token=${token}`);
        if(status == "false") {
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = "http://my.batuhan.com:5500/pages/login.html";

        } 
    }  else {
        window.location.href = "http://my.batuhan.com:5500/pages/login.html";
    }
    await user();
    await table();
}

async function user() {
    let token = await fetchCookie("token");
    let userE = await fetchData(`http://my.batuhan.com:8080/user/getEmail?token=${token}`);
    let url = `http://my.batuhan.com:8080/person/getPerson/${userE}`;

    const person = await makeApiCall(url);
    console.log(person.email);
    email = person.email;
    userName = person.name;
    userLastname = person.lastName;
    userPhoneNumber = person.phoneNumber;
    roles = person.roles;
    document.getElementById("logo").innerText = "Hoşgeldin " + userName + " " + userLastname;
    return Promise.resolve();
} 

async function table() {
    
    let table = document.getElementById("table");
    
    let fees = await makeApiCall(`http://my.batuhan.com:8080/fee/findByPerson?email=${email}`);
    
    let x = `                
    <thead>
    <tr>
      <th>id</th>
      <th>Blok No</th>
      <th>Apartman No</th>
      <th>Aidat Tarih</th>
      <th>Aidat Miktarı</th>
      <th>Ödenen Tutar</th>
      <th>Aidat Durumu</th>
      <th></th>
    </tr>
    </thead>
    <tbody>";`;
  
    for(let i = 0; i < fees.length; i++) {
        let durum = fees[i].status != false ? "Ödenmemiş" : "Ödenmiş";
        let color = durum == "Ödenmemiş" ? "red" : "green";
        x += `
        <tr class="animation">
        <td>${fees[i].id}</td>
        <td>${fees[i].blockName}</td>
        <td>${fees[i].apartmentNo}</td>
        <td>${fees[i].feeDate}</td>
        <td>${fees[i].feeAmount}</td>
        <td>${fees[i].paidAmount}</td>
        <td class="${color}">${durum}</td>
        <td>Ödeme Yap</td>
      </tr>
        `;
    }
    x += `                </tbody>
    </table>`;
    table.innerHTML = x;
    console.log(fees);
}
async function fetchCookie(name) {
    var cookies = document.cookie.split(";");
for(var i=0; i<cookies.length;i++){
    var a = cookies[i].split("=");
    if(a[0].trim() === name)
        return(""+a[1]+"!");
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

async function makeApiCall(url) {
    let bearerToken = await fetchCookie("token");
    const response = await fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${bearerToken}`,
      },
    });
  
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  
    const data = await response.json();
    return data;
  }