

var xhttp = new XMLHttpRequest();


xhttp.open("GET", "http://localhost:8080", true);
xhttp.send();
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
async function fetchData(url) {
    try {
        const response = await fetch(url);
        var data = await response.text();
        return data;
    } catch (error) {
        console.error(error);
    }
}
async function addProduct(id, name, price, category) {
    var c = document.getElementById('product-list');
    var variable = get('category');
    var x = `
    <li>
    <div class="box" id="product-${id}">             
        <div class="xx">
            <i class="fa fa-shopping-cart cart" aria-hidden="true"></i>
            <h1>${name}</h1>
            <i class="fa fa-heart-o heart-empty" aria-hidden="true" onClick="heartClick(element)"></i>
        </div>
        <img src="./img/${name}.jpg">
        <h2>Fiyat: ${price} TL</h2>          
    </div>
</li>`;
    c.innerHTML = x;
}

category();
async function category() {
    const jsonResult = await fetchData('http://localhost:8080/category');
    const obj = JSON.parse(jsonResult);
    var c = document.getElementById('category-list');

    var table = "";
    table += `<li class="category-item"><a href="http://127.0.0.1:5500?category=all">Tüm Ürünler</li></a>`;
    for (var i = 0; i < obj.length; i++) {
        var name = obj[i].category;
        var id = obj[i].id;
        table += `<li class="category-item"><a href="http://127.0.0.1:5500?category=${id}">${name.charAt(0).toUpperCase() + name.slice(1)}</li></a>`;
    }
    c.innerHTML = table;
}




products();
async function products() {
    var variable = get('category');
    var p = document.getElementById('product-list');
    var content = document.getElementById("products");
    var x = "";

    console.log(variable);
    var jsonResult;
    if (variable === "all" || null) {
          jsonResult = await fetchData(`http://localhost:8080/admin/products`);
         console.log(jsonResult);
    } else {
        jsonResult = await fetchData(`http://localhost:8080/category/${variable}`);
         console.log(jsonResult);
    }

    if(jsonResult.includes("null")) {
        
        x += `<h2>KATEGORI BULUNAMADI!</h2>`;
        content.innerHTML = x;
        return;
    } else if (jsonResult.includes("[]")){
        x += `<h2>URUN BULUNAMADI!</h2>`;
        content.innerHTML = x;
        return;
    }

    const obj = JSON.parse(jsonResult);

    for (var i = 0; i < obj.length; i++) {
        var id = obj[i].productId;
        var name = obj[i].productName;
        var price = obj[i].productPrice;
        var categoryId = obj[i].category.id;
        var categoryName = obj[i].category.category;
        console.log(categoryId + variable);  
            x += `<li>
            <div class="box" id="product-${id}">             
                <div class="xx">
                    <i class="fa fa-shopping-cart cart" aria-hidden="true"></i>
                    <h1>${name}</h1>
                    <i class="fa fa-heart-o heart-empty" aria-hidden="true" onClick="heartClick(element)"></i>
                </div>
                <img src="./img/kazak.jpg">
                <h2>Fiyat: ${price} TL</h2>          
            </div>
        </li>`
    }
    p.innerHTML = x;
}