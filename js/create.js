document.getElementById('addContactForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const nameInput = document.getElementById('nome').value.trim();
    const phoneInput = document.getElementById('tel').value.trim();

    if (!validateName(nameInput)) {
        alert('Nome inválido. Por favor, insira um nome válido sem números ou caracteres especiais.');
        return;
    }

    if (!validatePhone(phoneInput)) {
        alert('Número de telefone inválido. Por favor, insira um número válido no formato correto.');
        return;
    }

    const formattedPhone = phoneInput.replace(/\D/g, ''); // Remove non-digit characters
    if (isPhoneDuplicate(formattedPhone)) {
        alert('Número de telefone já existe. Por favor, insira um número de telefone único.');
        return;
    }

    const contactData = {
        id: Date.now().toString(),
        name: nameInput,
        phones: [formattedPhone]
    };

    const contacts = JSON.parse(localStorage.getItem('contacts')) || [];
    contacts.push(contactData);
    localStorage.setItem('contacts', JSON.stringify(contacts));

    alert('Contato adicionado com sucesso!');
    document.getElementById('addContactForm').reset();
});

document.getElementById('tel').addEventListener('input', function(event) {
    event.target.value = formatPhoneNumber(event.target.valueOf().value);
});
