$(document).ready(function () {
    getUserById();
});

function getUserById(){
    let ids = window.location.pathname
    let id = "";
    for(let i = 6;i < ids.length;i++){
        id += ids[i];
    }
    console.log(id);
    let user;
    $.get("/api/user/" + id,function (data) {
        console.log(data);
        user = '<div class="row col-12"> ' +
            '<div class="col-4">' +
            '<img align="center" src="/images/' + data.linkImage + '" alt="err" class="bd-placeholder-img rounded-circle" width="150" height="150" role="img" style="max-width: 150;max-height: 150"><rect width="100%" height="100%" fill="#868e96"></rect></img>' +
            '</div>' +
            '<div class="col-8">' +
            '<h3>'+ data.login +'</h3>' +
            '<label style="padding-bottom: 50px">' + data.email +'</label>' +
            '<br>' +
            '<input type="button" class="ui-button" value="Написать сообщение" onclick="createChat('+ data.id + ')">' +
            '</div>' +
            '</div>';

        $("#find-user-id").html(user);
    });
}

function createChat(id) {
    let bool;
    $.get("/api/chat/check-single-chat/" + id,function (data) {

        bool = data;
        console.log(bool)
        if(bool === false) {
            $.ajax({
                url: '/api/chat/single/create/',
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                cache: false,
                data: JSON.stringify(id),
            });
        }

        $.get("/api/chat/single/find/" + id,function (data1) {
            console.log(data1);
            window.location.href = "/user/single/chat/"+ data1;
        })
    });
}