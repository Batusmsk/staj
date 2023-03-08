var json;


function wait(second) {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve('resolved');
        }, second);
    });
}

const xx = async(url) => {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            json = xhr.response;
            handleResponse(json);
        }
    }
    xhr.open('get', url, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8');
    xhr.send();
}
function likeProduct(element) {
    var x = document.getElementById(1);
    console.log(element.id);
}
async function veri() {
    await xx('http://localhost:8080/admin/products');
}


/*
<li>
					<div class="box" id="product-123">
						<img src="./img/kazak.jpg" class="img">
						<div class="xx">
							<div>
								<h2>pantalon</h2>
								<p>Fiyat: 149 TL</p>
							</div>
							<i class="fas fa-heart heart-red" aria-hidden="true"></i>
						</div>
					</div>
				</li>
                */

function handleResponse(json){
    const obj = JSON.parse(json);
    //const result = await wait(100);
    var p = document.getElementById('products');
    
    console.log(obj);
    var table= "";
    for(var i=0;i<obj.length;i++){
        table += `<li>`;
        table += `<div class="box" id="product-${obj[i].productId}">`;
        table += `<img src="./img/${obj[i].productName}.jpg" class="img">`; 
        table += `<div class="xx">`;
        table += `<div>`; 
        table += `<h2>${obj[i].productName}</h2>`;
        table += `<p>Fiyat: ${obj[i].productPrice} TL</p>`;
        table += `</div>`;
        table += `<i class="fas fa-heart heart-red" aria-hidden="true"></i>`;
        table += `</div>`;
        table += `</div>`;
        table += `</li>`;
    }
    p.innerHTML=table;


    //p.innerHTML = `Ürün id: ${obj.productId} Ürün Isim: ${obj.productName} Stok Sayısı: ${obj.productCountStocks} Ürün fiyatı: ${obj.productPrice}`;
    
}
