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
async function getSelectedValueFromFilter(filterSelect) {
    let optionIndex = filterSelect.selectedIndex;
    return filterSelect[optionIndex].label;
}

async function reloadToNewStorage() {
    let filterSelect = document.getElementById("filters");
    let storageName = await getSelectedValueFromFilter(filterSelect);
    if (storageName === "Selecione um estoque") window.location.assign("/manager/storage")
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

async function goToEditStoragePage(){
    let row = event.target.parentNode.parentNode;
    let barcode = await getFieldValueFromRow(row, "name");

    window.location.assign("/storage/edit?name=" + barcode);
}

async function goToEditItemPage(){
    let row = event.target.parentNode.parentNode;
    let barcode = await getFieldValueFromRow(row, "barcode");

    window.location.assign("/items/edit?barcode=" + barcode);
}

async function deleteStorage(){
    let row = event.target.parentNode.parentNode;
    let form = await new FormData();
    if(!confirm("Gostaria de deletar esse estoque?")) return;
    form.append("name", await getFieldValueFromRow(row, "name"))

    postFormDataToServer(form, "storage/delete")
        .then(
            (resp) => {
                if(resp.status === 200) window.location.reload()
                else window.confirm("Não é possível deletar um estoque não vazio.")
            },
            (err) => console.log(err)
        );
}

async function deleteItem(){
    let row = event.target.parentNode.parentNode;
    let form = await new FormData();
    if(!confirm("Gostaria de deletar esse item?")) return;
    form.append("barcode", await getFieldValueFromRow(row, "barcode"))

    postFormDataToServer(form, "items/delete")
        .then(
            (resp) => {
                if(resp.status === 200) window.location.reload()
                else window.confirm("Não é possível deletar um item que já foi comprado.")
            },
            (err) => console.log(err)
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


async function createStorage(){
    let form = document.getElementById("data");
    let jason = await formDataToJson(form);
    let endpoint = "manager/storage";

    postJsonToServer(jason, "storage/create")
        .then( resp => resp.status)
        .then(
            (respStatus) => {
                if (respStatus === 200) window.location.assign('/' + endpoint)
                else window.confirm("Não foi possível adicionar o estoque.")
            },
            (err) => console.log("sadliest:error")
        );
}

async function updateStorage(){
    let form = document.getElementById("data");
    let jason = await formDataToJson(form);
    let endpoint = "manager/storage";

    postJsonToServer(jason, "storage/edit")
        .then( resp => resp.text())
        .then(
            (okBody) => window.location.assign('/' + endpoint),
            (err) => console.log("sadly:error")
        );
}

async function updateItem(){
    let form = document.getElementById("data");
    let filterSelect = document.getElementById("filters");
    let storageName = await getSelectedValueFromFilter(filterSelect);
    let jason = {
        'item': await formDataToJson(form),
        'storageName' : storageName,
    };
    let endpoint = "manager/storage";

    postJsonToServer(jason, "items/edit")
        .then( resp => resp.text())
        .then(
            (okBody) => window.location.assign('/' + endpoint),
            (err) => console.log("sadly:error")
        );
}