
function deleteProduct() {
    var categoryId = document.getElementById('delete-item-id').value;
    window.location.href = '/product/delete/' + categoryId;
}
function prepareDeleteItemId(id) {
    document.getElementById('delete-item-id').value = id;
}
