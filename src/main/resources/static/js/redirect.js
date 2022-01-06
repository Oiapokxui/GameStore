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
    if (storageName === "Selecione um estoque") storageName = ""
    window.location.assign("?storage=" + storageName);
}