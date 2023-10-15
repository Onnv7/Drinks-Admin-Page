var input = document.getElementById('image');

var oldPreview = document.getElementById('old-image-preview');

var imagePreview = document.getElementById('image-preview');

input.addEventListener('change', function () {
    console.log("Picked image preview");
    var file = input.files[0];
    if (file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            imagePreview.src = e.target.result;
            imagePreview.style.display = 'block';
        };

        reader.readAsDataURL(file);
        oldPreview.style.display = 'none';
    } else {
        imagePreview.style.display = 'none';
    }
});