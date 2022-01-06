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

async function getFieldValueFromRowOnButtonClick(fieldId) {
    let cells = event.target.parentNode.parentNode.children;
    let fieldValue;
    Array.from(cells).forEach(
        (cell) => {
            if (cell.id === fieldId) fieldValue = cell.innerText;
        }
    );
    console.log(fieldValue);
    return fieldValue;
}

async function editBarcode(){
    let barcode = await getFieldValueFromRowOnButtonClick("barcode");

    window.location.assign("/items/edit?barcode=" + barcode);
}