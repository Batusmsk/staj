
async function createApartmentButton() {
    let blockNo = document.getElementById("blockNo").value;
    let apartmentNo = document.getElementById("apartmentNo").value;
    let apartmentFloor = document.getElementById("apartmentFloor").value;
    let apartmenBaseArea = document.getElementById("apartmenBaseArea").value;

    if (blockNo.length >= 1 && apartmentNo.length >= 1 && apartmentFloor.length >= 1 && apartmenBaseArea.length >= 1) {
        let apartment = { "blockName": blockNo, "apartmentNo": apartmentNo, "baseArea": apartmenBaseArea, "floor": apartmentFloor };
        let request = await requestBody("http://my.batuhan.com:8080/apartment/createApartment", apartment);
        selectBlock(blockNo);
    } else {
        Fnon.Hint.Danger("Gerekli olan tüm bilgileri doldurunuz", " ");
    }
}

async function createApartment() {
    let div = document.getElementById("selection-management");
    let blocks = await makeApiCall("http://my.batuhan.com:8080/block/getBlocks");


    let html = `
    <div class="searchBlock">

                        <div class="table-style">
                        <h2>Blok seçiniz</h2>
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Blok Numarası</th>
                                        <th>Toplam Metre Kare</th>
                                        <th>Kat Sayısı</th>
                                        <th>Toplam Daire Sayısı</th>
                                    </tr>
                                </thead>
                                <tbody>`

    for (var i = 0; i < blocks.length; i++) {
        let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/findApartmentsByBlockName?blockName=${blocks[i].blockName}`);

        let owned = 0;
        let deowned = 0;
        for (var a = 0; a < apartments.length; a++) {
            if (apartments[a].purchaseDate !== null) {
                owned++;
            } else {
                deowned++;
            }
        }
        html += `<tr class="animation" onClick="selectBlock('${blocks[i].blockName}')">
        <td>${blocks[i].blockName}</td>
        <td>${blocks[i].baseArea}</td>
        <td>${blocks[i].numberOfFloors}</td>
        <td>${apartments.length}</td>
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

async function selectBlock(block) {

    let div = document.getElementById("selection-management");
    let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/findApartmentsByBlockName?blockName=${block}`);


    let html = `
    <div class="createApartment">


    <div id="form-container2">
    <form>
      <label class="label2">Blok Numarası:</label>
      <input type="text" id="blockNo" value="${block}" readonly required>

      <label class="label2">Daire Numarası:</label>
      <input type="text" id="apartmentNo" required>

      <label class="label2">Daire katı</label>
      <input type="text" id="apartmentFloor" required>

      <label class="label2">Daire Metre Kare</label>
      <input type="text" id="apartmenBaseArea" required>

      <button onClick="createApartmentButton()" class="button">Oluştur</button>
    </form>
  </div>

    <div class="searchAparment">
    <h2>Blok'da bulunan daireler</h2>
                        <div class="table-style">
                        
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Apartman No</th>
                                        <th>Blok No</th>
                                        <th>Bulunduğu Kat</th>
                                        <th>Metre Kare</th>
                                        <th>Apartman Sahibi</th>
                                        <th>Satın Alım Tarihi</th>
                                    </tr>
                                </thead>
                                <tbody>`

    for (var i = 0; i < apartments.length; i++) {

        let ownerApartment = "Sahipsiz";
        let purchaseDate = "Sahipsiz";

        if (apartments[i].personId != null) {
            let person = await makeApiCall(`http://my.batuhan.com:8080/person/findById?id=${apartments[i].personId}`)
            console.log(person);
            ownerApartment = person.email;
            purchaseDate = apartments[i].purchaseDate;
        }

        html += `<tr class="animation">
        <td>${apartments[i].apartmentNo}</td>
        <td>${apartments[i].blockName}</td>
        <td>${apartments[i].floor}</td>
        <td>${apartments[i].baseArea}</td>
        <td><a href="#" onClick="searchPersonText('${ownerApartment}')">${ownerApartment}</a></td>
        <td>${purchaseDate}</td>
         </tr> `


       
            
    }

    html += `
                                </tbody>
                            </table>
                        </div>
                    </div>
    `;

    div.innerHTML = html;
}

async function searchApartmentText() {
    let blockNo = document.getElementById("blockSearchInput").value;
    let apartmentNo = document.getElementById("apartmentSearchInput").value;

    if (blockNo.length >= 1 || apartmentNo.length >= 1) {
        let div = document.getElementById("selection-management");
        let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/getApartment?blockName=${blockNo}&apartmentId=${apartmentNo}`);
        if (apartments == null || apartments == "[]" || apartments == undefined) { Fnon.Hint.Danger("Daire bulunamadı", ""); return; }
        let html = `
        <div class="searchAparment">
                            <ul class="search">
                            <li><input type="text" id="blockSearchInput" placeholder="Blok Numarası" class="search-box"></li>
                                <li><input type="text" id="apartmentSearchInput" placeholder="Apartman Numarası" class="search-box"></li>
                                <li><button onClick="searchApartmentText()" class="button">Ara</button></li>
                            </ul>
                            <div class="table-style">
                                <table class="GeneratedTable" id="table">
                                    <thead>
                                        <tr>
                                            <th>Apartman No</th>
                                            <th>Blok No</th>
                                            <th>Bulunduğu Kat</th>
                                            <th>Metre Kare</th>
                                            <th>Apartman Sahibi</th>
                                            <th>Satın Alım Tarihi</th>
                                            <th>Apartman Sil</th>
                                        </tr>
                                    </thead>
                                    <tbody>`


        let ownerApartment = "Sahipsiz";
        let purchaseDate = "Sahipsiz";

        if (apartments.personId != null) {
            let person = await makeApiCall(`http://my.batuhan.com:8080/person/findById?id=${apartments.personId}`)
            console.log(person);
            ownerApartment = person.email;
            purchaseDate = apartments.purchaseDate;
        }

        html += `<tr class="animation">
            <td>${apartments.apartmentNo}</td>
            <td>${apartments.block.blockName}</td>
            <td>${apartments.floor}</td>
            <td>${apartments.baseArea}</td>
            <td><a href="#" onClick="searchPersonText('${ownerApartment}')">${ownerApartment}</a></td>
            <td>${purchaseDate}</td>
            <td><a href="#" onClick="deleteApartment('${apartments.block.blockName}', '${apartments.apartmentNo}')"><i class="fas fa-xmark"></i></a></td>
        </tr> `


        html += `
                                    </tbody>
                                </table>
                            </div>
                        </div>
        `;

        div.innerHTML = html;

        category();
    }
}

async function searchApartment() {
    let div = document.getElementById("selection-management");
    let apartments = await makeApiCall("http://my.batuhan.com:8080/apartment/getApartments");


    let html = `
    <div class="searchAparment">
                        <ul class="search">
                        <li><input type="text" id="blockSearchInput" placeholder="Blok Numarası" class="search-box"></li>
                            <li><input type="text" id="apartmentSearchInput" placeholder="Apartman Numarası" class="search-box"></li>
                            <li><button onClick="searchApartmentText()" class="button">Ara</button></li>
                        </ul>
                        <div class="table-style">
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Apartman No</th>
                                        <th>Blok No</th>
                                        <th>Bulunduğu Kat</th>
                                        <th>Metre Kare</th>
                                        <th>Apartman Sahibi</th>
                                        <th>Satın Alım Tarihi</th>
                                        <th>Apartman Sil</th>
                                    </tr>
                                </thead>
                                <tbody>`

    for (var i = 0; i < apartments.length; i++) {

        let ownerApartment = "Sahipsiz";
        let purchaseDate = "Sahipsiz";

        if (apartments[i].personId != null) {
            let person = await makeApiCall(`http://my.batuhan.com:8080/person/findById?id=${apartments[i].personId}`)
            console.log(person);
            ownerApartment = person.email;
            purchaseDate = apartments[i].purchaseDate;
        }

        html += `<tr class="animation">
        <td>${apartments[i].apartmentNo}</td>
        <td>${apartments[i].block.blockName}</td>
        <td>${apartments[i].floor}</td>
        <td>${apartments[i].baseArea}</td>
        <td><a href="#" onClick="searchPersonText('${ownerApartment}')">${ownerApartment}</a></td>
        <td>${purchaseDate}</td>
        <td><a href="#" onClick="deleteApartment('${apartments[i].block.blockName}', '${apartments[i].apartmentNo}')"><i class="fas fa-xmark"></i></a></td>
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

async function deleteApartment(blockName, apartmentNo) {
    let deleteApartment = await makeApiCall(`http://my.batuhan.com:8080/apartment/delete?blockName=${blockName}&apartmentId=${parseInt(apartmentNo)}`, true);
    console.log(deleteApartment);
    if (deleteApartment.includes("silindi")) {
        Fnon.Hint.Success(deleteApartment, "")
    } else {
        Fnon.Hint.Danger(deleteApartment, "")
    }
    searchApartment();
}


async function requestBody(url, data) {

    try {
        let bearerToken = await fetchCookie("token");
        xhr.open("POST", url);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader('Authorization', `Bearer ${bearerToken}`);

        let successMessages = ["başarı", "oluşturuldu", "Başarıyla"];

        xhr.onreadystatechange = async function () {
            if (xhr.readyState === 4) {
                if (!url.includes("sendMessage")) {
                    let x = false;

                    for (var i = 0; i < successMessages.length; i++) {
                        if (xhr.responseText.toLocaleLowerCase().includes(successMessages[i].toLocaleLowerCase())) {
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