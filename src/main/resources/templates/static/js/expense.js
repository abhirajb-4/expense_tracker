document.addEventListener('DOMContentLoaded', function() {
    // Handle edit modal data population
    const editModal = document.getElementById('editExpenseModal');
    if (editModal) {
        editModal.addEventListener('show.bs.modal', function(event) {
            const button = event.relatedTarget;
            const id = button.getAttribute('data-id');
            const name = button.getAttribute('data-name');
            const amount = button.getAttribute('data-amount');
            const category = button.getAttribute('data-category');
            const type = button.getAttribute('data-type');
            const date = button.getAttribute('data-date');

            document.getElementById('editId').value = id;
            document.getElementById('editExpenseName').value = name;
            document.getElementById('editAmount').value = amount;
            document.getElementById('editCategory').value = category;
            document.getElementById('editExpenseType').value = type;
            document.getElementById('editDate').value = date;
        });
    }

    // Add confirmation for delete actions
    const deleteForms = document.querySelectorAll('form[action^="/dashboard/delete/"]');
    deleteForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!confirm('Are you sure you want to delete this expense?')) {
                e.preventDefault();
            }
        });
    });
});