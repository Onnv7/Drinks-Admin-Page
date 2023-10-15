
var agreeBtn = document.getElementById('agree-btn');
var deleteBtn = document.getElementById('delete-category');

function prepareDeleteCategory(categoryId) {
    document.getElementById('delete-category-id').value = categoryId;
}
function deleteCategory() {
    var categoryId = document.getElementById('delete-category-id').value;
    window.location.href = '/category/delete/' + categoryId;
}
