async function addNewItem() {
    // document.getElementById('error_msg').innerText = ""
    let barcode = document.getElementById("barcode").value;
    let form = new FormData();
    form.append('barcode', barcode);
    let itemsTable = document.getElementById("items").tBodies[0];
    let itemsRow = itemsTable.rows;
    for (const row of Array.from(itemsRow)) {
        if (row.cells[0].innerText === barcode) {
            window.confirm("Código de barras já adicionado");
            return
        }
    }

    let resp = await fetch("/item/unsold", {
        method: 'POST',
        body: form,
    })
    if (resp.status === 200) {
        await addItemToTable(itemsTable, await resp.json());
        document.getElementById("barcode").value = "";
    }
    else window.confirm("Produto já foi comprado ou não possui código de barras cadastrado.")
    updatePricesSum();
}

async function addItemToTable(tableBody, item) {
    let itemRow = tableBody.insertRow(-1);

    let barcode = itemRow.insertCell(0);
    barcode.innerText = item.barcode;
    barcode.id = "barcode"

    let name = itemRow.insertCell(1);
    name.innerText = item.name;
    name.id = "name"

    let price = itemRow.insertCell(2);
    price.innerText = item.price;
    price.id = "price";

    let buttonTd = itemRow.insertCell(3);
    let button = document.createElement('button');
    button.innerText = 'excluir';
    button.onclick = () => itemRow.remove();
    button.className = "btn btn-secondary btn-md tableDelBut me-md-2"

    buttonTd.append(button);
    buttonTd.id = "remove";
}

async function updateTotalAmount() {
    let pricesSum = Number.parseFloat(document.getElementById("pricesSum").innerText || 0);
    let discount = Number.parseFloat(document.getElementById("discount").innerText || 0);
    let total = pricesSum - discount;
    if (total < 0) total = 0;
    document.getElementById("total").innerText = total;
}

async function updatePricesSum(customer) {
    let itemRows = document.getElementById("items").tBodies[0].rows;
    let pricesSum = document.getElementById("pricesSum");
    let sum = Array.from(itemRows)
        .flatMap(row => Array.from(row.cells))
        .filter(cell => cell.id === "price")
        .map(cell => Number.parseFloat(cell.innerText || 0))
        .reduce( (prev, next) => prev + next);
    pricesSum.innerText = sum;
    updateTotalAmount();
}

async function updateCustomerPoints(customer) {
    let discount = document.getElementById("discount");
    discount.innerText =  customer.fidelityPoints;
    updateTotalAmount();
}

async function selectEmployeeType(){
    let type = document.getElementById("type").value;
    let typeSelect = document.getElementById("employeeTypeSelect")
    typeSelect.value = type;
}

async function getClientData() {
    let cpf = document.getElementById("customerCpf").value;
    let input = document.getElementById("customerCpf");

    let resp = await fetch("/customer?cpf=" + cpf);
    let customer = await resp.json();
    if (resp.status === 200) {
        updateCustomerPoints(customer);
        input.readOnly = true;
    }
    else /*document.getElementById("error_msg").innerText = */ console.log("Código de barras não está cadastrado")
}

async function clearCustomerInput() {
    let input = document.getElementById("customerCpf");
    input.readOnly = false;
    input.value = "";
    let discount = document.getElementById("discount");
    discount.innerText = "";
}