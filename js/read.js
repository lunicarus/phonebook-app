document.addEventListener('DOMContentLoaded', function() {
    const contacts = JSON.parse(localStorage.getItem('contacts')) || [];
    const tableBody = document.querySelector('#contactsTable tbody');
    tableBody.innerHTML = '';

    contacts.forEach(contact => {
        const row = document.createElement('tr');

        const nameCell = document.createElement('td');
        nameCell.textContent = contact.name;
        row.appendChild(nameCell);

        const phonesCell = document.createElement('td');

        contact.phones.forEach(phone => {

            phoneItem.textContent = formatDisplayPhoneNumber(phone);
            phonesList.appendChild(phoneItem);
        });
        phonesCell.appendChild(phonesList);
        row.appendChild(phonesCell);

        const actionsCell = document.createElement('td');
        const actionsContainer = document.createElement('div');
        actionsContainer.className = 'actions-container';

        const editButton = document.createElement('img');
        editButton.src = '../media/botao-editar.png';
        editButton.alt = 'Editar';
        editButton.className = 'button-read';
        editButton.addEventListener('click', function() {
            if (editButton.getAttribute('data-editing') !== 'true') {
                makeEditable(row, contact);
                editButton.setAttribute('data-editing', 'true');
                editButton.src = '../media/botao-salvar.png'; // Altera a imagem para "Salvar"
            } else {
                saveEdits(row, contact.id, editButton);
                editButton.setAttribute('data-editing', 'false');
                editButton.src = '../media/botao-editar.png'; // Altera a imagem para "Editar"
            }
        });
        actionsContainer.appendChild(editButton);

        const deleteButton = document.createElement('img');
        deleteButton.src = '../media/botao-excluir.png';
        deleteButton.alt = 'Excluir';
        deleteButton.className = 'button-read';
        deleteButton.addEventListener('click', function() {
            if (confirm('Tem certeza de que deseja excluir este contato?')) {
                const updatedContacts = contacts.filter(c => c.id !== contact.id);
                localStorage.setItem('contacts', JSON.stringify(updatedContacts));
                alert('Contato excluído com sucesso!');
                window.location.reload();
            }
        });
        actionsContainer.appendChild(deleteButton);

        actionsCell.appendChild(actionsContainer);
        row.appendChild(actionsCell);
        tableBody.appendChild(row);
    });
});

function makeEditable(row, contact) {
    const nameCell = row.children[0];
    const phonesCell = row.children[1];

    const nameInput = document.createElement('input');
    nameInput.type = 'text';
    nameInput.value = contact.name;
    nameCell.innerHTML = '';
    nameCell.appendChild(nameInput);

    phonesCell.innerHTML = '';
    contact.phones.forEach(phone => {
        const phoneInput = document.createElement('input');
        phoneInput.type = 'tel';
        phoneInput.value = formatDisplayPhoneNumber(phone);
        phoneInput.addEventListener('input', function(event) {
            event.target.value = formatPhoneNumber(event.target.valueOf());
        });
        phonesCell.appendChild(phoneInput);
    });
}

function saveEdits(row, contactId, editButton) {
    const nameInput = row.children[0].querySelector('input');
    const phoneInputs = Array.from(row.children[1].querySelectorAll('input'));

    const updatedName = nameInput.value.trim();
    if (!validateName(updatedName)) {
        alert('Nome inválido. Por favor, insira um nome válido sem números ou caracteres especiais.');
        return;
    }

    const updatedPhones = phoneInputs.map(input => input.value.trim());
    for (let phone of updatedPhones) {
        if (!validatePhone(phone)) {
            alert('Número de telefone inválido. Por favor, insira um número válido no formato correto.');
            return;
        }
        phone = phone.replace(/\D/g, ''); // Remove non-digit characters
    }

    const contacts = JSON.parse(localStorage.getItem('contacts')) || [];
    const contactIndex = contacts.findIndex(c => c.id === contactId);
    if (contactIndex === -1) {
        alert('Contato não encontrado.');
        return;
    }

    contacts[contactIndex] = {
        id: contactId,
        name: updatedName,
        phones: updatedPhones
    };
    localStorage.setItem('contacts', JSON.stringify(contacts));

    alert('Contato atualizado com sucesso!');
    window.location.reload();
}
