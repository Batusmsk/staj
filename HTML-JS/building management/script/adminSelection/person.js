function wait(second) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve('resolved');
        }, second);
    });
}

async function createPersonButton() {
    var email = document.getElementById("emailInput").value;
    var number = document.getElementById("numberInput").value;
    var name = document.getElementById("nameInput").value;
    var lastname = document.getElementById("lastnameInput").value;

    if (email.length >= 1 && number.length >= 1 && name.length >= 1 && lastname.length >= 1) {
        let person = { "email": email, "phoneNumber": number, "name": name, "lastName": lastname };
        var response = await requestBody("http://my.batuhan.com:8080/person/createPerson", person);

    } else {
        Fnon.Hint.Danger("Lütfen tüm bilgileri duldurunuz", "");
    }
}

async function personSettings() {

}

async function createPersonSection() {
    let div = document.getElementById("selection-management");
    let html = `
    <div class="createPerson">
    <ul>
        <li>
            <h3>Email Adresi: </h3>
        </li>
        <li><input id="emailInput"></li>
    </ul>
    <ul>
        <li>
            <h3>Telefon Numarası: </h3>
        </li>
        <li><input id="numberInput"></li>
    </ul>
    <ul>
        <li>
            <h3>İsim: </h3>
        </li>
        <li><input id="nameInput"></li>
    </ul>
    <ul>
    <li>
        <h3>Soyisim: </h3>
    </li>
    <li><input id="lastnameInput"></li>
</ul>
    <button onClick="createPersonButton()" class="button">Kayıt Et</button>
</div>
    `;
    div.innerHTML = html;
    category();
}

async function personSearch() {
    let div = document.getElementById("selection-management");
    let person = await makeApiCall("http://my.batuhan.com:8080/person/getPersons");


    let html = `
    <div class="searchPerson">
                        <ul class="search">

                            <li><input type="text" id="searchPersonInput" placeholder="Kullanıcı Mail Adresi Veya Telefon Numarası" class="search-box"></li>
                            <li><button onClick="searchPersonText()" class="button">Ara</button></li>
                        </ul>
                        <div class="table-style">
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Mail Adresi</th>
                                        <th>Telefon Numarası</th>
                                        <th>İsim</th>
                                        <th>Soyisim</th>
                                        <th>Sahip Olunan Daire Sayısı</th>
                                        <th>Toplam Ödenmemiş Aidat Sayısı</th>
                                        <th>Hesabı Sil</th>
                                        <th>Şifre Sıfırla</th>
                                    </tr>
                                </thead>
                                <tbody>`

    for (var i = 0; i < person.length; i++) {
        let fee = await makeApiCall(`http://my.batuhan.com:8080/fee/findByPerson?email=${person[i].email}`);

        html += `<tr class="animation">
        <td>${person[i].id}</td>
        <td>${person[i].email}</td>
        <td>${person[i].phoneNumber}</td>
        <td>${person[i].name}</td>
        <td>${person[i].lastName}</td>
        <td>${person[i].personApartments.length}</td>
        <td><a href="#" onClick="feeList(0, '${person[i].email}')">${fee.length}</a></td>
        <td><a href="#" onClick="deletePerson('${person[i].id}')"><i class="fas fa-xmark"></i></a></td>
        <td><a href="#" onClick="adminResetPassword('${person[i].id}')"><i class="fas fa-unlock"></i></a></td>
    </tr> `
    }

    html += `
                                </tbody>
                            </table>
                        </div>
                    </div>
    `;

    div.innerHTML = html;

    category();
}

async function deletePerson(id) {
    let x = await makeApiCall(`http://my.batuhan.com:8080/person/deletePerson?id=${id}`, true);
    console.log(x);
    if(x.includes("silindi")) {
        Fnon.Hint.Success("Kullanıcı başarıyla silindi", "");
        personSearch();
    } else {
        Fnon.Hint.Danger(x, "");
    }
}



async function addPersonToApartment() {
    let div = document.getElementById("selection-management");
    let person = await makeApiCall("http://my.batuhan.com:8080/person/getPersons");
    let html = `                    <div id="addPersonToApartment" class="addPersonToApartment">
    <div class="person-info hide" id="person-info">
        <ul class="">
            <li><h3>Kullanıcı Mail: admin</h3></li>
            <li><h3>Kullanıcı Numara: 109321823</h3></li>
            <li><h3>Kullanıcı İsim Soyisim: batuhan Şimşek</h3></li>
        </ul>
    </div>
    <div class="tables" id="tables"> 
    <h2>Kullanıcı Seçiniz</h2>
        <div class="table-style">
            <table class="GeneratedTable" id="selectPerson">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mail Adresi</th>
                        <th>Telefon Numarası</th>
                        <th>İsim</th>
                        <th>Soyisim</th>            
                    </tr>
                </thead>
                <tbody>
                    <tr class="animation">`;

                    for (var i = 0; i < person.length; i++) { 
                        html += `<tr class="animation" onClick="selectPersonButton('${person[i].email}')">
                        <td><a  href="#">${person[i].id}</a></td>
                        <td><a  href="#">${person[i].email}</a></td>
                        <td><a  href="#">${person[i].phoneNumber}</a></td>
                        <td><a  href="#">${person[i].name}</a></td>
                        <td><a  href="#">${person[i].lastName}</a></td>
                    </tr> `
                    }

                    html += `
                    </tbody>
                </table>
            </div> 
        </div>
`;

div.innerHTML = html;
    category();
}

async function selectPersonButton(email) {
    Fnon.Box.Infinity('div.tables');
    
    let div = document.getElementById("selection-management");
    let person = await makeApiCall(`http://my.batuhan.com:8080/person/getPerson/${email}`);
    
    if(person != null && person != undefined) {
        let tableDiv = document.getElementById("tables");
        let personInfo = document.getElementById("person-info")
        personInfo.classList.remove("hide");
        let personHTML = `
        <a onClick="addPersonToApartment()" href="#"> <i class="fas fa-arrow-left-long" aria-hidden="true"></i></a>
        <ul class="person-info">
        <li><h3 id="person-email">Kullanıcı Mail: ${person.email}</li></h3>
        <li><h3>Kullanıcı Numara: ${person.phoneNumber}</li></h3>
        <li><h3>Kullanıcı İsim Soyisim: ${person.name} ${person.lastName}</li></h3>
        </ul>

        
        
        `;
        personInfo.innerHTML = personHTML;
        
        let apartments = await makeApiCall("http://my.batuhan.com:8080/apartment/getApartments");

   
    let html = `
    <div class="tables">
    <h2>Daire Seçiniz</h2>
                        <div class="table-style">
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Apartman No</th>
                                        <th>Blok No</th>
                                        <th>Bulunduğu Kat</th>
                                        <th>Metre Kare</th>
                                    </tr>
                                </thead>
                                <tbody>`
                       
    let deownedApt = 0;

    for(var i = 0; i < apartments.length; i++) {
        if(apartments[i].purchaseDate == null) {
            deownedApt++;
            html += `<tr href="#" class="animation" onClick="selectApartmentButton('${apartments[i].apartmentNo}', '${apartments[i].block.blockName}')">
            <td><a href="#" >${apartments[i].apartmentNo}</a></td>
            <td><a href="#" >${apartments[i].block.blockName}</a></td>
            <td><a href="#" >${apartments[i].floor}</a></td>
            <td><a href="#" >${apartments[i].baseArea}</a></td>
           </tr> `
        }
    }

    html += `
                                </tbody>
                            </table>
                        </div>
                    </div>
    `;
    console.log(deownedApt)

    if(deownedApt == 0) {
        html = `
        <div class="table-style">
        <table class="GeneratedTable" id="table">
            <thead>
                <tr>
                    <th>Apartman No</th>
                    <th>Blok No</th>
                    <th>Bulunduğu Kat</th>
                    <th>Metre Kare</th>
                </tr>
            </thead>
            </div>
            </table>

        <h2 class="notFound">Boş daire bulunamadı</h2>`
    }

    tableDiv.innerHTML = html;
    }
}


async function removePersonToApartment() {
    let div = document.getElementById("selection-management");
    let person = await makeApiCall("http://my.batuhan.com:8080/person/getPersons");
    let html = `                    <div id="addPersonToApartment" class="addPersonToApartment">
    <div class="person-info hide" id="person-info">
        <ul class="">
            <li><h3>Kullanıcı Mail:</h3></li>
            <li><h3>Kullanıcı Numara:</h3></li>
            <li><h3>Kullanıcı İsim Soyisim:</h3></li>
        </ul>
    </div>
    <div class="tables" id="tables"> 
    <h2>Kullanıcı Seçiniz</h2>
        <div class="table-style">
            <table class="GeneratedTable" id="selectPerson">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Mail Adresi</th>
                        <th>Telefon Numarası</th>
                        <th>İsim</th>
                        <th>Soyisim</th>    
                        <th>Sahip Olunan Daire Sayısı</th>        
                    </tr>
                </thead>
                <tbody>
                    <tr class="animation">`;
                    
                    for (var i = 0; i < person.length; i++) { 
                        
                        if(person[i].personApartments.length > 0) {
                            html += `<tr class="animation" onClick="removePersonToApt('${person[i].email}')">
                            <td><a href="#">${person[i].id}</a></td>
                            <td><a  href="#">${person[i].email}</a></td>
                            <td><a  href="#">${person[i].phoneNumber}</a></td>
                            <td><a  href="#">${person[i].name}</a></td>
                            <td><a  href="#">${person[i].lastName}</a></td>
                            <td><a  href="#">${person[i].personApartments.length}</a></td>
                        </tr> `
                        }
                    }

                    html += `
                    </tbody>
                </table>
            </div> 
        </div>
`;

div.innerHTML = html;
    category();
}

async function removePersonToApt(email) {
    Fnon.Box.Infinity('div.tables');
    
    let div = document.getElementById("selection-management");  

    let person = await makeApiCall(`http://my.batuhan.com:8080/person/getPerson/${email}`);

    if(person != null && person != undefined) {
        let tableDiv = document.getElementById("tables");
        let personInfo = document.getElementById("person-info")
        personInfo.classList.remove("hide");
        let personHTML = `
        <a onClick="removePersonToApartment()" href="#"> <i class="fas fa-arrow-left-long" aria-hidden="true"></i></a>
        <ul class="person-info">
        <li><h3 id="person-email">Kullanıcı Mail: ${person.email}</li></h3>
        <li><h3>Kullanıcı Numara: ${person.phoneNumber}</li></h3>
        <li><h3>Kullanıcı İsim Soyisim: ${person.name} ${person.lastName}</li></h3>
        </ul>

        
        
        `;
        personInfo.innerHTML = personHTML;
        
        

   
    let html = `
    <div class="tables">
    <h2>Kullanıcıyı çıkarmak istediğiniz daireyi seçiniz</h2>
                        <div class="table-style">
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Apartman No</th>
                                        <th>Blok No</th>
                                        <th>Satın Alındığı Tarih</th>
                                        <th>Bulunduğu Kat</th>
                                        <th>Metre Kare</th>
                                        <th>Kaldır</th>
                                    </tr>
                                </thead>
                                <tbody>`
                       

    console.log(person.personApartments.length);
    for(var i = 0; i < person.personApartments.length; i++) {
        if(person.personApartments[i].purchaseDate != null) {
           
            html += `<tr class="animation">
            <td><a href="#">${person.personApartments[i].apartmentNo}</a></td>
            <td><a href="#">${person.personApartments[i].block.blockName}</a></td>
            <td><a href="#">${person.personApartments[i].floor}</a></td>
            <td><a href="#">${person.personApartments[i].purchaseDate}</a></td>
            <td><a href="#">${person.personApartments[i].baseArea}</a></td>
            <td><a href="#" onClick="removePersonToApartmentBtn('${email}', '${person.personApartments[i].apartmentNo}', '${person.personApartments[i].block.blockName}')"><i class="fas fa-xmark"></i></a></td>
            </tr> `
        }
    }

    html += `
                                </tbody>
                            </table>
                        </div>
                    </div>
    `;


    tableDiv.innerHTML = html;
    }
}
async function removePersonToApartmentBtn(email, apartmentNo, blockName) {
    let data = {"email": email, "apartmentNo": apartmentNo, "blockNo": blockName};
    
    requestBody("http://my.batuhan.com:8080/person/removeApartmentToPerson",data);
    await wait(500);
    removePersonToApt(email);
}


async function selectApartmentButton(apartmentNo, blockName) {
    let personEmail = document.getElementById("person-email");
    let person = personEmail.innerText.split(":")[1];

    Fnon.Dialogue.Success(`${person} Mail adresine sahip kullancı'ya ${apartmentNo} numaralı daire'yi eklemek istiyor musunuz?`,'','Evet','İptal Et',(
    )=>{
        const options = { timeZone: 'Europe/Istanbul' }
        const date = new Date().toLocaleString('tr-TR', options).split(" ")[0];
       
        let data = {"email": person.replace(" ", ""), "apartmentNo": apartmentNo, "blockNo": blockName, "purchaseDate": date};
        console.log(data);
        let x = requestBody("http://my.batuhan.com:8080/person/addApartmentToPerson",data);
        addPersonToApartment();
     },()=>{
        console.log('cancel callback');
     });
}

async function searchPersonText(email) {
    let div = document.getElementById("selection-management");
    
    let input;

    if(email) {
        if(email === "Sahipsiz") {
            return;
        }
        input = email;
    } else {
        input = document.getElementById("searchPersonInput").value;
    }
    
    if (input.length >= 1) {


        
        let person = await makeApiCall(`http://my.batuhan.com:8080/person/getPerson/${input}`);
        if (person != null && person != "[]" && person != undefined) {

            let fee = await makeApiCall(`http://my.batuhan.com:8080/fee/findByPerson?email=${person.email}`);

            let html = `
            <div class="searchPerson">
                                <ul class="search">
        
                                    <li><input type="text" id="searchPersonInput" placeholder="Mail Adresi Veya Telefon Numarası" class="search-box"></li>
                                    <li><button onClick="searchPersonText()" class="button">Ara</button></li>
                                </ul>
                                <div class="table-style">
                                    <table class="GeneratedTable" id="table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Mail Adresi</th>
                                                <th>Telefon Numarası</th>
                                                <th>İsim</th>
                                                <th>Soyisim</th>
                                                <th>Sahip Olunan Daire Sayısı</th>
                                                <th>Toplam Ödenmemiş Aidat Sayısı</th>
                                                <th>Hesabı Sil</th>
                                                <th>Şifre Sıfırla/th>
                                            </tr>
                                        </thead>
                                        <tbody>`

            html += `<tr class="animation">
            <td>${person.id}</td>
            <td>${person.email}</td>
            <td>${person.phoneNumber}</td>
            <td>${person.name}</td>
            <td>${person.lastName}</td>
            <td>${person.personApartments.length}</td>
            <td><a href="#" onClick="feeList(0, '${person.email}')">${fee.length}</a></td>
            <td><a href="#" onClick="deletePerson('${person.id}')"><i class="fas fa-xmark"></i></a></td>
            <td><a href="#" onClick="adminResetPassword('${person.id}')"><i class="fas fa-unlock"></i></a></td>
        </tr> `
            html += `
            </tbody>
        </table>
    </div>
</div>
`;
            div.innerHTML = html;
        } else {
            Fnon.Hint.Danger("Aradığınız kullanıcı bulunamadı", "");
        }
    } else {
        Fnon.Hint.Danger("Lütfen aramak istediğiniz kullanıcının email adresini veya telefon numarasını giriniz.", "")
        personSearch();
    }

}

async function adminResetPassword(id) {
    let x = await makeApiCall(`http://my.batuhan.com:8080/person/resetPassword?id=${id}`);
    console.log(x);
    if(x==true) {
        Fnon.Hint.Success("Kullanıcının şifresi başarıyla sıfırlandı.");
    } else {
        Fnon.Hint.Danger("Şifre sıfırlanırken bir hata oluştu.");
    }
}