function validateName(name) {
    if (name.length <= 1 || name.length >= 50) return false;
    const namePattern = /^[a-zA-Z\s]+$/;
    return namePattern.test(name);
}
function validatePhone(phone) {
    const rawPhone = phone.replace(/\D/g, '');
    const length = rawPhone.length;
    return length === 10 || length === 11 || length === 13;
}

function isPhoneDuplicate(phone) {
    const contacts = JSON.parse(localStorage.getItem('contacts')) || [];
    return contacts.some(contact => contact.phones.includes(phone));
}

function formatPhoneNumber(value) {
    const phoneNumber = value.replace(/\D/g, ''); // Remove non-digit characters
    const length = phoneNumber.length;

    if (length === 10) { // Landline: (16) 3622-8922
        return phoneNumber.replace(/^(\d{2})(\d{4})(\d{4})$/, '($1) $2-$3');
    } else if (length === 11) { // Mobile: (14) 92000-3421
        return phoneNumber.replace(/^(\d{2})(\d{5})(\d{4})$/, '($1) $2-$3');
    } else if (length === 13) { // International: +55 (14) 92000-6398
        return phoneNumber.replace(/^(\d{2})(\d{2})(\d{5})(\d{4})$/, '+$1 ($2) $3-$4');
    }
    return value; // If none of the formats match, return the original value
}

function formatDisplayPhoneNumber(phone) {
    return formatPhoneNumber(phone);
}

function savePhoneToDatabase(phone) {
    return phone.replace(/\D/g, '');
}
