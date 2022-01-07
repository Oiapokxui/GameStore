async function addNewItem() {
    let barcode = document.getElementById("barcode").value;
    let form = new FormData();
    form.append('barcode', barcode);
    let itemsTable = document.getElementById("items");
    let itemsRow = itemsTable.rows;
    for (i = 1; i < itemsRow.length; i++) {
        if (itemsRow[i].cells[0].innerText === barcode) {
            return new Promise(
                () => document.getElementById('error_msg').innerText = "Código de barras já adicionado"
            );
        }
    }

    return fetch("/items/search/any/barcode", {
        method: 'POST',
        body: form,
    })
        .then(resp => resp.json())
        .then(
            itemJson => addItemToTable(itemsTable, itemJson),
            () => document.getElementById("error_msg").innerText = "Código de barras não está cadastrado"
        );
}

async function addItemToTable(itemsTable, item) {
    let itemRow = document.createElement("tr");

    let barcode = document.createElement('td');
    barcode.innerText = item.barcode;

    let name = document.createElement('td');
    name.innerText = item.name;

    let price = document.createElement('td');
    price.innerText = item.price;

    let buttonTd = document.createElement('td');
    let button = document.createElement('button');
    button.innerText = 'excluir';
    button.onclick = () => itemRow.remove();
    buttonTd.append(button);

    itemRow.append(barcode, name, price, buttonTd);
    itemsTable.append(itemRow);
}
