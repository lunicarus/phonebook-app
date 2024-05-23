function validateName(name) {
    if (name.length <= 1 || name.length >= 50) return false;
    const namePattern = /^[a-zA-Z\s]+$/;
    return namePattern.test(name);
}

function validatePhone(phone) {
    if (phone.length < 8 || phone.length > 14) return false;
    const phonePattern = /^\(\d{2}\)\s\d{5}-\d{4}$/;
    return phonePattern.test(phone);
}

function isPhoneDuplicate(phone) {
    const contacts = JSON.parse(localStorage.getItem('contacts')) || [];
    return contacts.some(contact => contact.phones.includes(phone));
}

function formatPhoneNumber(value) {
    const phoneNumber = value.replace(/\D/g, ''); // Remove non-digit characters
    const phonePattern = /^(\d{2})(\d{5})(\d{4})$/;
    if (phoneNumber.length === 11 && phonePattern.test(phoneNumber)) {
        return phoneNumber.replace(phonePattern, '($1) $2-$3');
    } else if (phoneNumber.length <= 11) {
        return phoneNumber;
    }
    return value;
}

function formatDisplayPhoneNumber(phone) {
    const phonePattern = /^(\d{2})(\d{5})(\d{4})$/;
    if (phonePattern.test(phone)) {
        return phone.replace(phonePattern, '($1) $2-$3');
    }
    return phone;
}
