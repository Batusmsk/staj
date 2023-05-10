async function createBlockButton() {
    let blockNo = document.getElementById("blockNo").value;
    let blockFloor = document.getElementById("blockFloor").value;
    let blockBaseArea = document.getElementById("blockBaseArea").value;

    if (blockNo.length >= 1 && blockFloor.length >= 1 && blockBaseArea.length >= 1) {
        let block = { "blockName": blockNo, "numberOfFloors": blockFloor, "baseArea": blockBaseArea };
        let request = await requestBody("http://my.batuhan.com:8080/admin/block/createBlock", block);
    } else {
        Fnon.Hint.Danger("Gerekli olan tüm bilgileri doldurunuz", " ");
    }
}

async function deleteBlock(blockName) {
    let deleteBlock = await makeApiCall(`http://my.batuhan.com:8080/block/deleteBlock?blockName=${blockName}`, true);
    console.log(deleteBlock);
    if(deleteBlock.includes("silindi")) {
        Fnon.Hint.Success(deleteBlock, "")
    } else {
        Fnon.Hint.Danger(deleteBlock, "")
    }
    searchBlock();
}

//searchBlock();
async function searchBlockText() {
    
    let input = document.getElementById("searchInput").value;  
    let div = document.getElementById("selection-management");
    blocks = await makeApiCall(`http://my.batuhan.com:8080/block/getBlock?blockName=${input}`);

    let html = `
    <div class="searchBlock">
                        <ul class="search">

                            <li><input type="text" id="searchInput" placeholder="Blok Numarası" class="search-box"></li>
                            <li><button onClick="searchBlockText()" class="button">Ara</button></li>
                        </ul>
                        <div class="table-style">
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Blok Numarası</th>
                                        <th>Toplam Metre Kare</th>
                                        <th>Kat Sayısı</th>
                                        <th>Toplam Daire Sayısı</th>
                                        <th>Sahipli Daire Sayısı</th>
                                        <th>Sahipsiz Daire Sayısı</th>
                                        <th>Blok Sil</th>
                                    </tr>
                                </thead>
                                <tbody>`
                                
    
        let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/findApartmentsByBlockName?blockName=${blocks.blockName}`);
        
        let owned = 0;
        let deowned= 0;
        for(var a = 0; a < apartments.length; a++) {
            if(apartments[a].purchaseDate !== null) {
                owned++;
            } else {
                deowned++;
            }
        }
        html += `<tr class="animation">
        <td>${blocks.blockName}</td>
        <td>${blocks.baseArea}</td>
        <td>${blocks.numberOfFloors}</td>
        <td>${apartments.length}</td>
        <td>${owned}</td>
        <td>${deowned}</td>
        <td><a href="#" onClick="deleteBlock('${blocks.blockName}')"><i class="fas fa-xmark"></i></a></td>
    </tr> 
                                </tbody>
                            </table>
                        </div>
                    </div>
    `;
    
    div.innerHTML = html;
    
    category();
  };

async function createBlock() {
    let div = document.getElementById("selection-management");
    let html = `
    <div class="createBlock">


    <div id="form-container">
    <form>
      <label>Blok Numarası:</label>
      <input type="text" id="blockNo" required>

      <label>Kat Sayısı:</label>
      <input type="text" id="blockFloor" required>

      <label>Blok Metre Kare</label>
      <input type="text" id="blockBaseArea" required>

      <button onClick="createBlockButton()" class="button">Kayıt</button>
    </form>
  </div>

    `;
    div.innerHTML = html;
    category();
}

async function searchBlock() {
    let div = document.getElementById("selection-management");
    let blocks = await makeApiCall("http://my.batuhan.com:8080/block/getBlocks");

   
    let html = `
    <div class="searchBlock">
                        <ul class="search">

                            <li><input type="text" id="searchInput" placeholder="Blok Numarası" class="search-box"></li>
                            <li><button onClick="searchBlockText()" class="button">Ara</button></li>
                        </ul>
                        <div class="table-style">
                            <table class="GeneratedTable" id="table">
                                <thead>
                                    <tr>
                                        <th>Blok Numarası</th>
                                        <th>Toplam Metre Kare</th>
                                        <th>Kat Sayısı</th>
                                        <th>Toplam Daire Sayısı</th>
                                        <th>Sahipli Daire Sayısı</th>
                                        <th>Sahipsiz Daire Sayısı</th>
                                        <th>Blok Sil</th>
                                    </tr>
                                </thead>
                                <tbody>`
                                
    for(var i = 0; i < blocks.length; i++) {
        let apartments = await makeApiCall(`http://my.batuhan.com:8080/apartment/findApartmentsByBlockName?blockName=${blocks[i].blockName}`);
        
        let owned = 0;
        let deowned= 0;
        for(var a = 0; a < apartments.length; a++) {
            if(apartments[a].purchaseDate !== null) {
                owned++;
            } else {
                deowned++;
            }
        }
        html += `<tr class="animation">
        <td>${blocks[i].blockName}</td>
        <td>${blocks[i].baseArea}</td>
        <td>${blocks[i].numberOfFloors}</td>
        <td>${apartments.length}</td>
        <td>${owned}</td>
        <td>${deowned}</td>
        <td><a href="#" onClick="deleteBlock('${blocks[i].blockName}')"><i class="fas fa-xmark"></i></a></td>
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

async function requestBody(url, data) {

    try {
        let bearerToken = await fetchCookie("token");
        xhr.open("POST", url);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader('Authorization', `Bearer ${bearerToken}`);

        let successMessages = ["başarı", "oluşturuldu", "true", "success"];

        xhr.onreadystatechange = async function () {
            if (xhr.readyState === 4) {
                
                if (!url.includes("sendMessage")) {
                    let x = false;

                    for (var i = 0; i < successMessages.length; i++) {
                        if (xhr.responseText.includes(successMessages[i])) {
                            x = true;
                            continue;
                        }
                    }

                    if (x) {
                        alert(xhr.responseText);
                        
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