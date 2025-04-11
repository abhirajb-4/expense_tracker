// Handle edit modal data population
    document.getElementById('editExpenseModal').addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        const modal = this;

        modal.querySelector('#editId').value = button.getAttribute('data-id');
        modal.querySelector('#editExpenseName').value = button.getAttribute('data-name');
        modal.querySelector('#editCategory').value = button.getAttribute('data-category');
        modal.querySelector('#editAmount').value = button.getAttribute('data-amount');
        modal.querySelector('#editExpenseType').value = button.getAttribute('data-type');
        modal.querySelector('#editDate').value = button.getAttribute('data-date');
    });

    // Add animation to modals when they appear
    document.querySelectorAll('.modal').forEach(modal => {
        modal.addEventListener('shown.bs.modal', function () {
            const modalContent = this.querySelector('.modal-content');
            modalContent.style.transform = 'translateY(0)';
            modalContent.style.opacity = '1';
        });
        modal.addEventListener('hide.bs.modal', function () {
            const modalContent = this.querySelector('.modal-content');
            modalContent.style.transform = 'translateY(-20px)';
            modalContent.style.opacity = '0';
        });
    });