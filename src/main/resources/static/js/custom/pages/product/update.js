

var deleteSizeBtn = document.getElementById('delete-size-btn');
var sizeListDiv = document.getElementById("sizeList");
var toppingListDiv = document.getElementById("toppingList");
var addImageBtn = document.getElementById('add-image-btn');
const imageListDiv = document.getElementById('imageList');



function addSize() {
    var parentDiv = document.createElement("div");
    var newSpan = document.createElement("span");

    var sizeInput = document.createElement("input");
    sizeInput.type = "text";
    sizeInput.required = true;
    sizeInput.name = "sizeList[#sizeListIndex#].size";
    sizeInput.className = "form-control form-control-lg inline-auto-w";
    sizeInput.placeholder = "Enter size";
    newSpan.appendChild(sizeInput);


    var priceInput = document.createElement("input");
    priceInput.type = "number";
    priceInput.required = true;
    priceInput.name = "sizeList[#sizeListIndex#].price";
    newSpan.appendChild(priceInput);
    priceInput.className = "form-control form-control-lg inline-auto-w";
    priceInput.placeholder = "Price";


    var deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.className = "btn btn-danger";
    deleteBtn.onclick = function (e) {
        e.preventDefault();
        console.log(' =>>>>>>>>', e.target.id);
        deleteSize(e.target.id);
    };
    sizeListDiv.appendChild(deleteBtn);


    var sizeListIndex = sizeListDiv.children.length;
    newSpan.innerHTML = newSpan.innerHTML.replace(/#sizeListIndex#/g, sizeListIndex - 1);
    deleteBtn.id = sizeListIndex - 1;

    parentDiv.appendChild(newSpan);
    parentDiv.appendChild(deleteBtn);

    sizeListDiv.appendChild(parentDiv);
    checkSizeDeleteBtn();
}



function deleteSize(index) {
    console.log("remove", index);
    var numIndex = parseInt(index)

    sizeListDiv.removeChild(sizeListDiv.children[numIndex]);

    for (var i = numIndex; i < sizeListDiv.children.length; i++) {
        var sizeInput = sizeListDiv.children[i].querySelector(`input[name="sizeList[${i + 1}].size"]`);
        var priceInput = sizeListDiv.children[i].querySelector(`input[name="sizeList[${i + 1}].price"]`);
        var deleteBtn = sizeListDiv.children[i].querySelector(`button[class="btn btn-danger"]`);
        console.log(deleteBtn, sizeInput, priceInput, sizeListDiv.children[i], `input[name="sizeList[${i + 1}].size"]`)
        deleteBtn.id = `${i}`;
        sizeInput.name = `sizeList[${i}].size`;
        priceInput.name = `sizeList[${i}].price`;
    }
    checkSizeDeleteBtn();
}

function checkSizeDeleteBtn() {
    if (sizeListDiv.children.length == 1) {
        var deleteBtn = sizeListDiv.children[0].querySelector(`button[class="btn btn-danger"]`);
        if (deleteBtn !== null)
            deleteBtn.remove()
        return;
    } else if (sizeListDiv.children.length > 1 && sizeListDiv.children[0].childElementCount == 1) {
        var deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";
        deleteBtn.className = "btn btn-danger";
        deleteBtn.id = `0`;
        deleteBtn.onclick = function (e) {
            e.preventDefault();
            console.log(' =>>>>>>>> 2');
            deleteSize(e.target.id);
        };
        sizeListDiv.children[0].appendChild(deleteBtn);
    }
}



function addTopping() {

    var parentDiv = document.createElement("div");
    var newSpan = document.createElement("span");

    var toppingInput = document.createElement("input");
    toppingInput.type = "text";
    toppingInput.required = true;
    toppingInput.name = "toppingList[#toppingListIndex#].name";
    toppingInput.className = "form-control form-control-lg inline-auto-w";
    toppingInput.placeholder = "Enter name";
    newSpan.appendChild(toppingInput);


    var priceInput = document.createElement("input");
    priceInput.type = "number";
    priceInput.required = true;
    priceInput.name = "toppingList[#toppingListIndex#].price";
    newSpan.appendChild(priceInput);
    priceInput.className = "form-control form-control-lg inline-auto-w";
    priceInput.placeholder = "Price";


    var deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.className = "btn btn-danger";
    deleteBtn.onclick = function (e) {
        e.preventDefault();
        deleteTopping(e.target.id);
    };
    toppingListDiv.appendChild(deleteBtn);


    var sizeListIndex = toppingListDiv.children.length;
    newSpan.innerHTML = newSpan.innerHTML.replace(/#toppingListIndex#/g, sizeListIndex - 1);
    deleteBtn.id = sizeListIndex - 1;

    parentDiv.appendChild(newSpan);
    parentDiv.appendChild(deleteBtn);

    toppingListDiv.appendChild(parentDiv);
    checkToppingDeleteBtn();

}

function deleteTopping(index) {
    var numIndex = parseInt(index)

    toppingListDiv.removeChild(toppingListDiv.children[numIndex]);

    for (var i = numIndex; i < toppingListDiv.children.length; i++) {
        var sizeInput = toppingListDiv.children[i].querySelector(`input[name="toppingList[${i + 1}].name"]`);
        var priceInput = toppingListDiv.children[i].querySelector(`input[name="toppingList[${i + 1}].price"]`);
        var deleteBtn = toppingListDiv.children[i].querySelector(`button[class="btn btn-danger"]`);

        deleteBtn.id = `${i}`;
        sizeInput.name = `toppingList[${i}].name`;
        priceInput.name = `toppingList[${i}].price`;
    }
    checkToppingDeleteBtn();
}

function checkToppingDeleteBtn() {
    if (toppingListDiv.children.length == 1) {
        var deleteBtn = toppingListDiv.children[0].querySelector(`button[class="btn btn-danger"]`);
        if (deleteBtn !== null)
            deleteBtn.remove()
        return;
    } else if (toppingListDiv.children.length > 1 && toppingListDiv.children[0].childElementCount == 1) {
        var deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";
        deleteBtn.className = "btn btn-danger";
        deleteBtn.id = `0`;
        deleteBtn.onclick = function (e) {
            e.preventDefault();
            deleteTopping(e.target.id);
        };
        toppingListDiv.children[0].appendChild(deleteBtn);
    }
}
function createImageCard(index) {
    // Tạo một div với class "col"
    const colDiv = document.createElement('div');
    colDiv.classList.add('col', 'col-image-card');
    colDiv.id = `image-card-${index}`;

    // Tạo một link để tải CSS
    const linkElement = document.createElement('link');
    linkElement.href = "/static/css/custom/upload-card.css";
    linkElement.rel = "stylesheet";
    linkElement.type = "text/css";

    const labelElement = document.createElement('label');
    labelElement.htmlFor = `file[${index}]`;

    // Tạo một input type file với id và class tương ứng
    const inputElement = document.createElement('input');

    inputElement.type = 'file';
    inputElement.required = true;
    inputElement.id = `file[${index}]`;

    inputElement.title = "Product Image";
    inputElement.hidden = true;
    inputElement.name = `imageList[${index}]`;
    // inputElement.classList.add(`imageList[${index}]`);
    inputElement.onchange = (e) => {
        const image = e.target.files[0];
        if (image.size < 2000000) {
            const imgArea = document.querySelector(`#image-card-${index} .img-area`);
            const reader = new FileReader();
            reader.onload = () => {
                // const allImg = imgArea.querySelectorAll('img');
                // allImg.forEach(item => {
                //     console.log(object)
                //     item.remove()
                // });
                const imgUrl = reader.result;
                const img = document.createElement('img');
                img.src = imgUrl;
                imgArea.appendChild(img);
                imgArea.classList.add('active');
                imgArea.dataset.img = image.name;
            }
            reader.readAsDataURL(image);
        } else {
            alert("Image size more than 2MB");
        }
    }

    // Tạo một div với class "img-area"
    const imgAreaDiv = document.createElement('div');
    imgAreaDiv.classList.add('img-area');
    imgAreaDiv.dataset.img = '';

    // Tạo một biểu tượng
    const iconElement = document.createElement('i');
    iconElement.classList.add('bx', 'bxs-cloud-upload', 'icon');

    // Tạo một tiêu đề h3
    const h3Element = document.createElement('h3');
    h3Element.textContent = 'Upload Image';

    // Tạo một đoạn văn bản p
    const pElement = document.createElement('p');
    pElement.innerHTML = 'Image size must be less than <span>2MB</span>';



    // Tạo một nút select image
    const deleteImageBtn = document.createElement('button');
    deleteImageBtn.classList.add(`delete-image`);
    deleteImageBtn.textContent = 'Delete Image';

    deleteImageBtn.onclick = function (e) {
        e.preventDefault();

        const parts = e.target.parentElement.id.split('-');
        if (parts.length === 3) {
            const number = parseInt(parts[2]);
            if (!isNaN(number)) {
                deleteImageCard(number)
            } else {
                console.log('Error');
            }
        }
    }

    // Gắn các phần tử con vào "img-area" div
    imgAreaDiv.appendChild(iconElement);
    imgAreaDiv.appendChild(h3Element);
    imgAreaDiv.appendChild(pElement);

    imgAreaDiv.onclick = (e) => {
        e.preventDefault();
        inputElement.click();
    }


    // Gắn "img-area" div và nút select image vào "col" div
    colDiv.appendChild(linkElement);
    colDiv.appendChild(labelElement);
    colDiv.appendChild(inputElement);
    colDiv.appendChild(imgAreaDiv);
    colDiv.appendChild(deleteImageBtn);

    // Lấy thẻ có id "imageList"
    const imageListDiv = document.getElementById('imageList');

    // Thêm "col" div vào "imageList" div
    imageListDiv.appendChild(colDiv);
    checkDeleteImageBtn()
}
function deleteImageCard(index) {
    const imageListDiv = document.getElementById('imageList');
    // Xác định ImageCard cần xóa dựa trên index
    const imageCardToDelete = imageListDiv.children[index];

    if (imageCardToDelete) {
        // Xóa ImageCard
        imageCardToDelete.remove();

        // Cập nhật lại index cho các ImageCard còn lại
        const remainingImageCards = document.querySelectorAll('.col-image-card');
        remainingImageCards.forEach((imageCard, i) => {
            imageCard.id = `image-card-${i}`;
            // Cập nhật các phần tử có index tương ứng (ví dụ: input, button)
            const inputElement = imageCard.querySelector('input[type="file"]');
            const labelElement = imageCard.querySelector('label');

            if (inputElement) {

                inputElement.name = `imageList[${i}]`;
                // inputElement.className = `imageList[${i}]`;
                inputElement.id = `file[${i}]`;
                inputElement.onchange = (e) => {
                    const image = e.target.files[0];
                    if (image.size < 2000000) {
                        const imgArea = document.querySelector(`#image-card-${i} .img-area`);
                        const reader = new FileReader();
                        reader.onload = () => {
                            const imgUrl = reader.result;
                            const img = document.createElement('img');
                            img.src = imgUrl;
                            imgArea.appendChild(img);
                            imgArea.classList.add('active');
                            imgArea.dataset.img = image.name;
                        }
                        reader.readAsDataURL(image);
                    } else {
                        alert("Image size more than 2MB");
                    }
                }
            }
            if (labelElement) {
                labelElement.htmlFor = `file[${i}]`;
            }
        });
    }
    checkDeleteImageBtn();
}

function checkDeleteImageBtn() {
    if (imageListDiv.children.length == 1) {
        var deleteBtn = imageListDiv.children[0].querySelector('button[class="delete-image"');
        if (deleteBtn !== null)
            deleteBtn.remove()
        return;
    } else if (imageListDiv.children.length > 1 && imageListDiv.children[0].querySelector('button[class="delete-image"') === null) {
        var deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete Image";
        deleteBtn.className = "delete-image";
        deleteBtn.id = `delete-0`;
        deleteBtn.onclick = function (e) {
            e.preventDefault();
            const parts = e.target.id.split('-');
            if (parts.length === 2) {
                const number = parseInt(parts[1]);
                if (!isNaN(number)) {
                    deleteImageCard(number)
                } else {
                    console.log('Error');
                }
            }
        };
        imageListDiv.children[0].appendChild(deleteBtn);
    }
}

createImageCard(0);
checkDeleteImageBtn();

addImageBtn.addEventListener('click', () => {
    const index = imageListDiv.childElementCount;
    createImageCard(index)
})