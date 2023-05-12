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

    }
  } else {
    window.location.href = "http://my.batuhan.com:5500/pages/login.html";
  }

  await user();
  await staticsButton();

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
  console.log(person.email);
  userEmail = person.email;
  userName = person.name;
  userLastname = person.lastName;
  userPhoneNumber = person.phoneNumber;
  roles = person.roles;
  document.getElementById("logo").innerText = "Hoşgeldin " + userName + " " + userLastname;
  return Promise.resolve();
}


async function pay(id) {
  var x;
  Fnon.Ask.Success('Aidat borcunu ödemek için emin misiniz', 'Bilgi', 'Öde', 'Ödeme Yapma', (result) => {
    if (result !== false) {
      pay2(id);
    }
  });
}

async function pay2(id) {

  fetch(`http://my.batuhan.com:8080/fee/payFee?id=${id}`).then(response => response.text()).then(text => {
    const jsonData = text;
    if (text.includes('önceden')) {
      Fnon.Hint.Danger(text, 'Hata');
    } else {
      Fnon.Hint.Success(text, `${id} İd'li aidat başarıyla ödendi.`);
    }
    showFees();
  })
    .catch(error => console.log(error));


}
async function saveProfile() {
  var oldPassword = document.getElementById("oldPassword").value;
  var newPassword = document.getElementById("newPassword").value;
  if (oldPassword.length >= 1 || newPassword >= 1) {
    passwordSave();
  }

  var e = document.getElementById("email").value;
  var n = document.getElementById("phoneNumber").value;

  var email;
  var number;
  if (e === userEmail || e.length <= 1) {
    email = null;
  } else {
    email = e;
  }
  if (n === userPhoneNumber || n.length <= 1) {
    number = null;
  } else {
    number = n;
  }

  const data = { email: `${email}`, phoneNumber: `${number}` };
  var x = await requestBody("http://my.batuhan.com:8080/person/updateEmailAndNumber", data);

}

async function passwordSave() {
  var oldPassword = document.getElementById("oldPassword").value;
  var newPassword = document.getElementById("newPassword").value;
  if (oldPassword.length > 1 && newPassword.length > 1) {
    if (oldPassword !== newPassword) {
      var message = await makeApiCall(`http://my.batuhan.com:8080/person/changePassword?newPassword=${newPassword}&oldPassword=${oldPassword}`, true);
      if (message.includes("uyuşmuyor")) {
        Fnon.Hint.Danger("Eski şifrenizi hatalı girdiniz.");
      } else {
        Fnon.Hint.Success("Şifreniz başarıyla değiştirildi");
      }
    } else {
      Fnon.Hint.Danger("Şifreler aynı olamaz");
    }
  } else {
    Fnon.Hint.Danger("Eski ve yeni şifrenizi giriniz");
  }
}

async function showFees() {

  let menu = document.getElementById("menu");

  let fees = await makeApiCall(`http://my.batuhan.com:8080/fee/findByPerson?email=${userEmail}`);

  let x = `                
    <div class="aidat">
    <h2>Mevcut Aidat Borçların</h2>
      <div class="table-style">
        
        <table class="GeneratedTable" id="table">
          <thead>
            <tr>
      <th>id</th>
      <th>Blok No</th>
      <th>Apartman No</th>
      <th>Aidat Tarih</th>
      <th>Aidat Tutarı</th>
      <th>Ödenen Tutar</th>
      <th>Aidat Durumu</th>
      <th></th>
    </tr>,
    </thead>
    <tbody>"`;

  for (let i = 0; i < fees.length; i++) {
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
        <td><a href="#" onClick="pay(${fees[i].id})">Ödeme Yap</a></td>
      </tr>
        `;
  }
  x += `          </tbody>
    </table>
  </div>
</div>`;
  menu.innerHTML = x;
}

async function showApartments() {
  let menu = document.getElementById("menu");

  let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/findApartmentsByPerson?email=${userEmail}`);

  let x = `
  <div class="apartment">
  <h2>Sahip olduğun daireler</h2>
  <div class="table-style">
    <table class="GeneratedTable" id="table">
      <thead>
        <tr>
          <th>Blok Numarası</th>
          <th>Daire Numarası</th>
          <th>Daire Metre Karesi</th>
          <th>Bulunduğu Kat</th>
          <th>Satın Alım Tarihi</th>
        </tr>
        </thead>
        <tbody>
  `;

  for (var i = 0; i < apartments.length; i++) {
    x += `
    <tr class="animation">
    <td>${apartments[i].blockName}</td>
    <td>${apartments[i].apartmentNo}</td>
    <td>${apartments[i].baseArea}</td>
    <td>${apartments[i].floor}</td>
    <td>${apartments[i].purchaseDate}</td>
  </tr>
  
    `;
  }
  x += `</tbody>
  </thead>
</table>
</div>
</div>`;
  menu.innerHTML = x;
}

async function showPayments() {
  let menu = document.getElementById("menu");

  let payments = await makeApiCall(`http://my.batuhan.com:8080/payment/findByPerson?email=${userEmail}`);

  let x = `
  <div class="payment">
  <h2>Geçmiş Ödemeler</h2>
  <div class="table-style">
    <table class="GeneratedTable" id="table">
      <thead>
        <tr>
        <th>Id</th>
        <th>Aidat Id</th>
        <th>Ödeme Tarihi</th>
        <th>Ödeme Tutarı</th>
        </tr>
        </thead>
        <tbody>
  `;

  for (var i = 0; i < payments.length; i++) {
    x += `
    <tr class="animation">
    <td>${payments[i].id}</td>
    <td>${payments[i].feeId}</td>
    <td>${payments[i].paymentDate}</td>
    <td>${payments[i].paymentAmount}</td>
  </tr>
    `;
  }
  x += `</tbody>
  </thead>
</table>
</div>
</div>`;
  menu.innerHTML = x;
}

async function supportButton() {
  let menu = document.getElementById("choice");
  let x = `
  <div>
          <label for="tickets">Talep Seçin: </label>
          <select onchange="selectedTicket()" class="form-control" id="ticktes" name="tickets">

          </select>
        </div>

        <div class="createTicket">
          <p>Talep Başlığı: </p>
          <input id="createTicketText" type="text" placeholder="talep başlığı yazınız">
          <button onClick="createTicket()" class="createTicketButton">Talep Oluştur</button>
        </div>
        <div id="messages" class="messages">
          <ul>

          </ul>
          
          
          <button onClick="sendMessage()" class="sendMessage">Gönder</button>
        </div>
        <input id="messageText" class="messageText" type="text" placeholder="Mesajınızı giriniz.">`;
  menu.innerHTML = x;

  let dropBox = document.getElementById("ticktes");
  var ticket = await makeApiCall(`http://my.batuhan.com:8080/ticket/findByPersonTickets?email=${userEmail}`);
  let xx = ``;
 
  for (var i = 0; i < ticket.length; i++) {
    xx += `<option>${ticket[i].id}. ${ticket[i].createDate} - ${ticket[i].ticketName}</option>`
    //console.log(tickets);
  }
  dropBox.innerHTML = xx;
}

async function createTicket() {
  var input = document.getElementById("createTicketText").value
  if(input.length >= 1) {
    const data = {ticketName: input};
    var ticket = await requestBody(`http://my.batuhan.com:8080/ticket/createTicket`, data);
    supportButton();
  }
}
async function sendMessage() {
  let input = document.getElementById("messageText").value;
 
  var selectBox = document.getElementById("ticktes");

  var selectedValue = selectBox.options[selectBox.selectedIndex].value;
  var id = selectedValue.split(".")[0];

  if(input.length >= 1) {
    const data = {ticketId: parseInt(id), message: input};
    var ticket = await requestBody(`http://my.batuhan.com:8080/ticket/sendMessage`, data);
    
  } else {
    Fnon.Hint.Danger("Lütfen göndermek istediğiniz mesajı girin", "");
  }
}


setInterval(function() {
  const isProfileClassExist = document.querySelector('.messages') !== null;
  if(isProfileClassExist) {
    var selectBox = document.getElementById("ticktes"); 
    var selectedValue = selectBox.options[selectBox.selectedIndex].value;
    if(selectedValue.length > 1) {
      selectedTicket();
    }
  }
}, 1000);
let messageCount = 0;
async function selectedTicket() {
  
  var selectBox = document.getElementById("ticktes");
  var doc = document.getElementById("messages");

  var selectedValue = selectBox.options[selectBox.selectedIndex].value;
  var id = selectedValue.split(".")[0];
  var messages = await makeApiCall(`http://my.batuhan.com:8080/ticket/getMessage?id=${id}`);
  let x = `<ul>`;
  for(var i =0; i < messages.length; i++) {
    
    let icon = messages[i].personEmail === userEmail ? "fa fa-user" : "fas fa-user-shield";
    let a = messages[i].personEmail === userEmail ? "user" : "admin";
    x += `
    
    <li class="${a} box">
      <i class="${icon}" aria-hidden="true"></i>
      <h3>${messages[i].message}</h3>
      <p>${messages[i].sendTime}</p>
    </li>

    `;
  }
  x += `</ul>
  
  <button onClick="sendMessage()" class="sendMessage">Gönder</button>
        </div>
        `;
  doc.innerHTML = x;
  

  if(messageCount != messages.length) {
    const messagesDiv = document.querySelector('#messages');
    messagesDiv.scrollTop = messagesDiv.scrollHeight - messagesDiv.clientHeight;
  }
  messageCount = messages.length;
}

async function staticsButton() {
  let menu = document.getElementById("menu");

  let fees = await makeApiCall(`http://my.batuhan.com:8080/fee/findByPerson?email=${userEmail}`);
  let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/findApartmentsByPerson?email=${userEmail}`);
  let payments = await makeApiCall(`http://my.batuhan.com:8080/payment/findByPerson?email=${userEmail}`);
  let person = await makeApiCall(`http://my.batuhan.com:8080/person/getPerson/${userEmail}`);
  let rol = "";
  
  if(person.roles.includes("ADMIN")) {
    rol = `<li><a href="http://my.batuhan.com:5500/pages/admin.html">Admin Paneli</a></li>`;
  }

  let unPaid = 0;
  let paid = 0;
  let totalFee = 0;
  let totalPaidAmount = 0;
  for (var i = 0; i < fees.length; i++) {
    if (fees[i].status == true) {
      unPaid++
    } else if (fees[i].status == false) {
      paid++
    }
    totalFee += fees[i].feeAmount;
    totalPaidAmount += fees[i].paidAmount;
  }

  let x = `
  <div class="profile">
  <div class="slide-menu">
    <ul>
      <li><p href="#" onClick="staticsButton()">Hesap Istatistikleri</p></li>
      <li><p href="#" onClick="settingsButton()">Hesap Ayarları</p></li>
      <li><p href="#" onClick="supportButton()">Destek</p></li>
      ${rol}
    </ul>
  </div>
  <div class="choice" id="choice">
  <ul>
  <li><p>Toplam Daire Sayısı:</p> <p>${apartments.length}</p></li>    
  <li><p>Ödenmemiş toplam aidat sayısı: </p><p>${unPaid}</p> </li>
  <li><p>Ödenmiş toplam aidat sayısı: </p><p>${paid}</p></li>
  <li><p>Toplam aidat borcu: </p><p>${totalFee.toLocaleString('tr-TR')}&#8378</p></li>
  <li><p>Ödenmiş toplam aidat borcu: </p><p>${totalPaidAmount.toLocaleString('tr-TR')}&#8378</p></li>
</ul>
</div>
      
    </div>
  `
  menu.innerHTML = x;
}

async function settingsButton() {
  let menu = document.getElementById("choice");
  let x = `

  <ul>
  <li><p>Isim:</p><input disabled value="${userName}"></li>
  <li><p>Soyisim:</p><input disabled value="${userLastname}"></li>
  <li><p>Telefon Numarası: </p><input type="number" id="phoneNumber" value="${userPhoneNumber}"></li>
  <li><p>Mail Adresi: </p><input id="email" value="${userEmail}"></li>
  <li>
  <p>Eski Şifre:</p>
  <input id="oldPassword" type="password">
  <p>Yeni Şifre:</p>
  <input id="newPassword" type="password">
  
</li>
</ul>
<div class="btn">
  <button onClick="saveProfile()" class="profileSaveBtn">Değişiklikleri Kayıt Et</button>
</div>

`
  menu.innerHTML = x;
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
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    window.location.href = "http://my.batuhan.com:5500/pages/login.html";
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
    let x;
    xhr.onreadystatechange = async function () {
      if (xhr.readyState === 4) {
        if(!url.includes("sendMessage")) {
          if (xhr.responseText.includes("başarı")) {
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
