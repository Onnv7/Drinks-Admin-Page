var input = document.getElementById('image');

var imagePreview = document.getElementById('image-preview');

input.addEventListener('change', function () {
    var file = input.files[0];
    if (file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            imagePreview.src = e.target.result;
            imagePreview.style.display = 'block';
        };

        reader.readAsDataURL(file);
    } else {
        imagePreview.style.display = 'none';
    }
});