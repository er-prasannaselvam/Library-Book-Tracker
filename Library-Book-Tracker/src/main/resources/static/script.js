async function loadBooks() {
    const response = await fetch('/api/books');
    const books = await response.json();
    const tableBody = document.querySelector('#bookTable tbody');

    tableBody.innerHTML = books.map(book => `
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.availableQuantity}/${book.quantity}</td>
            <td>
                <button onclick="checkoutBook(${book.id})">Check Out</button>
                <button onclick="returnBook(${book.id})">Return</button>
            </td>
        </tr>
    `).join('');
}

// Checkout a book
async function checkoutBook(id) {
    await fetch(`/api/books/${id}/checkout`, { method: 'PUT' });
    loadBooks();  // Refresh the list
}

// Initialize
loadBooks();