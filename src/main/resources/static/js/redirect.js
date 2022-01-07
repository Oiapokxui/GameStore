async function submitForm() {
    let cpf = document.getElementById("floatingInput").value;
    let form = new FormData();
    form.append('cpf', cpf);

    let resp = await fetch('/', {
        method: 'POST',
        body: form,
    });
    let body = await resp.text();

    if(resp.status !== 200) document.getElementById("error_msg").innerText = "Gerente ou Caixa de CPF " + cpf + " não encontrado";
    else window.location.assign("/" + body);
}

async function reloadToNewStorage() {
    let filterSelect = document.getElementById("filters");
    let optionIndex = filterSelect.selectedIndex;
    let storageName = filterSelect[optionIndex].label;
    if (storageName === "Selecione um estoque") window.location.assign("/storages")
    else window.location.assign("?storage=" + storageName);
}

async function getFieldValueFromRow(row, fieldId) {
    let cells = row.children;
    let fieldValue;
    Array.from(cells).forEach(
        (cell) => {
            if (cell.id === fieldId) fieldValue = cell.innerText;
        }
    );
    console.log(fieldValue);
    return fieldValue;
}

async function goToEditItemPage(){
    let row = event.target.parentNode.parentNode;
    let barcode = await getFieldValueFromRow(row, "barcode");

    window.location.assign("/items/edit?barcode=" + barcode);
}

async function deleteItem(){
    let row = event.target.parentNode.parentNode;
    let form = await new FormData();
    if(!confirm("Gostaria de deletar esse item?")) return;
    form.append("barcode", await getFieldValueFromRow(row, "barcode"))

    postFormDataToServer(form, "items/delete")
        .then(
        (resp) => window.location.reload(),
        () => console.log("deu ruim e sinceramente não tenho tempo pra descobrir o porquê")
    );
}

async function formDataToJson(form){
    let formJson = {}
    Array.from(form.children)
        .map(div => div.children[0])
        .forEach(form => formJson[form.id] = form.value);
    return formJson;
}

async function postJsonToServer(json, endpoint) {
    return fetch("/" + endpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(json),
    });
}

async function postFormDataToServer(form, endpoint) {
    return fetch("/" + endpoint, {
        method: 'POST',
        body: form,
    });
}

async function updateItemValues(){
    let form = document.getElementById("data");
    let formJson = await formDataToJson(form);
    postJsonToServer(formJson, "items/edit")
        .then( resp => resp.text())
        .then( (endpoint) => window.location.assign('/' + endpoint));
}