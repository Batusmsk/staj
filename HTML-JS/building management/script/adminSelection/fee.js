
feeList(2);
async function feeList(status, email) {

    let div = document.getElementById("selection-management");
    let fees = null;

    console.log(status);

    if(email == undefined || email == null) {
         fees = await makeApiCall(`http://my.batuhan.com:8080/fee/getFees?status=${status}`);
    } else {
        fees = await makeApiCall(`http://my.batuhan.com:8080/fee/findByPerson?email=${email}`)
    }

    let html = `
    <div class="fee-list" id="fee-list">
    <div>
        <h2>Aidat Listesi</h2>
        
    </div>
    <ul class="search">

    <li><input type="text" id="searchFeeInput" placeholder="Kullanıcı Mail Adresi" class="search-box"></li>
    <li><button onClick="searchFeeText()" class="button">Ara</button></li>
    <li><button onClick="refreshFee()" style="width: 40px; margin-left:15px;" class="button"><i class="fas fa-arrow-rotate-left" aria-hidden="true"></i></button></li>
</ul>
        <div class="table-style">
            <table class="GeneratedTable">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Kullanıcı</th>
                        <th>Blok No</th>
                        <th>Apartman No</th>
                        <th>Aidat Tarihi</th>
                        <th>Aidat Tutarı</th>
                        <th>Ödenmiş Tutar</th>
                        <th>Durum <i onClick="filterFee()" id="filter" class="fas fa-sort-up" aria-hidden="true"></i></th>
                        <th>Borç Kapat</th>
                        <th>Uyarı Yolla</th>
                    </tr>
                </thead>
                <tbody>`
    
    for (var i = 0; i < fees.length; i++) { 

        let durum = fees[i].status != false ? "Ödenmemiş" : "Ödenmiş";
        let color = durum == "Ödenmemiş" ? "red" : "green";

        html += `
        <tr class="animation">
            <td><a>${fees[i].id}</a></td>
            <td><a>${fees[i].person.email}</a></td>
            <td><a>${fees[i].blockNo}</a></td>
            <td><a>${fees[i].apartment}</a></td>
            <td><a>${fees[i].feeDate}</a></td>
            <td><a>${fees[i].feeAmount}</a></td>
            <td><a>${fees[i].paidAmount}</a></td>
            <td class="${color}">${durum}</td>
            <td><a onClick="payByTheManager('${fees[i].id}')"><i class="fas fa-xmark" aria-hidden="true"></i></a></td>
            <td><a onClick="warnUserForFee('${fees[i].id}', '${fees[i].person.email}')"><i class="fa fa-exclamation" aria-hidden="true" style="font-size: 25px; color: red;"></i></a></td>
        </tr>
        `;
    }
    html += `
    </tbody>
    </table>
    </div> 
    </div>
    `;
    div.innerHTML = html;

    var filter = document.getElementById("filter");
    if(filterStatus === false) {
        filter.classList.remove("fa-sort-up")
        filter.classList.add("fa-sort-down")
    } else {
        filter.classList.add("fa-sort-up")
        filter.classList.remove("fa-sort-down")
    }

}

async function warnUserForFee(feeId, personMail) {
    let fee = await makeApiCall(`http://my.batuhan.com:8080/fee/getFee?id=${feeId}`);
    let data = {text: `${fee.feeAmount} Tutarında ödenmemiş aidat borcunuz bulunuyor lütfen en kısa sürede ödeme sağlayınız.`, subject: `Aidat Borcu`, to: personMail};
    let x = await requestBody("http://my.batuhan.com:8080/api/mail/normal", data); 
    
}

async function payByTheManager(id) {
    fetch(`http://my.batuhan.com:8080/fee/payFee?id=${id}`).then(response => response.text()).then(text => {
        const jsonData = text;
        if (text.includes('önceden')) {
          Fnon.Hint.Danger(text, 'Hata');
        } else {
          Fnon.Hint.Success(text, `${id} İd'li aidat başarıyla ödendi.`);
        }
        feeList(0);
      })
        .catch(error => console.log(error));
}

async function searchFeeText(email) {
    let div = document.getElementById("selection-management");
    
    let input;

    if(email) {
        if(email === "Sahipsiz") {
            return;
        }
        input = email;
    } else {
        input = document.getElementById("searchFeeInput").value;
        feeList(0, input);
    }
}

let filterStatus = false;
async function filterFee() {
    var filter = document.getElementById("filter");
    console.log(filterStatus);
    if(filterStatus === false) {
        feeList(2);
        filterStatus = true;
    } else {
        feeList(0);
        filterStatus = false;
    }
}
function refreshFee() {
    feeList(0);
}
async function payList(email) {

    let div = document.getElementById("selection-management");

    let payments = null;

    if(email == undefined || email == null) {
         payments = await makeApiCall(`http://my.batuhan.com:8080/payment/getPayments`);
    } else {
        payments = await makeApiCall(`http://my.batuhan.com:8080/payment/findByPerson?email=${email}`)
    }

    let html = `
    <div class="payment-list" id="payment-list">
    <div>
        <h2>Ödeme Listesi</h2>
        
    </div>
    <ul class="search">

    <li><input type="text" id="searchpaymentInput" placeholder="Kullanıcı Mail Adresi" class="search-box"></li>
    <li><button onClick="searchpaymentText()" class="button">Ara</button></li>
    <li><button onClick="refreshpayment()" style="width: 40px; margin-left:15px;" class="button"><i class="fas fa-arrow-rotate-left" aria-hidden="true"></i></button></li>
</ul>
        <div class="table-style">
            <table class="GeneratedTable">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Aidat id</th>
                        <th>Kullanıcı Mail</th>
                        <th>Ödeme Tutarı</th>
                        <th>Ödeme Tarihi</th>
                    </tr>
                </thead>
                <tbody>`
    
    for (var i = 0; i < payments.length; i++) { 

        html += `
        <tr class="animation">
            <td><a>${payments[i].id}</a></td>
            <td><a>${payments[i].feeId}</a></td>
            <td><a>${payments[i].personEmail}</a></td>
            <td><a>${payments[i].paymentAmount}</a></td>
            <td><a>${payments[i].paymentDate}</a></td>
           </tr>
        `;
    }
    html += `
    </tbody>
    </table>
    </div> 
    </div>
    `;
    div.innerHTML = html;
}

async function searchpaymentText(){
    let input = document.getElementById("searchpaymentInput").value;
    payList(input);
}
function refreshpayment() {
    payList();
}
