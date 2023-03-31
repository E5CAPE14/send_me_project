$(document).ready(function () {
    getMessageByChatId();
});

let page = 1;
let items = 10;

function getMessageByChatId() {
    let ids = window.location.pathname;
    let id = "";
    console.log(ids)
    for(let i = "/user/single/chat/".length;i < ids.length;i++){
        id += ids[i];
    }
    console.log(id);
    $.get("/api/chat/single/" + id + "?items=" + items + "&currentPage=" + page,function (data) {
        let listMessage =
            '<h6 class="border-bottom pb-2 mb-0" style="padding-left: 400px">Чат номер : ' + id + '</h6>';
        for (let i = 0;i < data.length;i++) {
            console.log(data[i]);
            listMessage +=
                '                    <div class="d-flex text-body-secondary pt-3">'+
                '                        <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"></rect><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>' +
                '                        <p class="pb-3 mb-0 small lh-sm border-bottom">' +
                '                            <strong class="d-block text-gray-dark">' + data[i].userSender +'</strong>' +
                ''                             + data[i].message + '' +
                '                        </p>' +
                '                    </div>';
        }

        $("#singleChatMessage").html(listMessage);
    })
}

function sendMessage(){
    let ids = window.location.pathname;
    let id = "";
    for(let i = "/user/single/chat/".length;i < ids.length;i++){
        id += ids[i];
    }
    let message = document.getElementById("message_text").value;
    $.ajax({
        url: '/api/chat/single/sendToChat/'+ id,
        dataType: 'json',
        contentType: 'application/json',
        type: 'POST',
        cache: false,
        data: JSON.stringify(message)
    });
}

function nextPage(){
    page++;
    getMessageByChatId();

}

function previousPage(){
    page--;
    getMessageByChatId();
}