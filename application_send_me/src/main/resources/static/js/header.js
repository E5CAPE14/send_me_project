$(document).ready(function () {
    getUserForHead();
});

function getUserForHead(){
    $.get("/api/user/profile", function (data) {
        let userHeader =  '<label>' + data.email + '</label>'+
            '             <a href="/" class="d-block link-dark text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">' +
            '                    <img src="/images/' + data.linkImage + '" alt="err" width="32" height="32" class="rounded-circle">' +
            '             </a>' +
            '             <ul class="dropdown-menu text-small" style="">' +
            '                <li><a class="dropdown-item" href="/user/seting">Настройки</a></li>' +
            '                <li><a class="dropdown-item" href="/user/profile">Профиль</a></li>' +
            '                <li><hr class="dropdown-divider"></li>' +
            '                <li><a class="dropdown-item" href="/logout">Выход</a></li>' +
            '             </ul>';
        $("#user-h").html(userHeader);
        let profile = '<li>' +
            '<label class="nav-list" style="padding-top: 15px;padding-left:15px">Email : '+ data.email + '</label>' +
            '</li>' +
            '<li>' +
            '<label class="nav-list" style="padding-top: 15px;padding-left:15px">Login : ' + data.login + '</label>' +
            '</li>';
        $("#user-info").html(profile);
    });
}
//function editPassword(){
//    let form = $("#editPasswordForm");
//
//    console.log(form.currentPassword.value)
//    console.log(form.editPassword.value)
//
//    let dataObject = {
//        'currentPassword' : form.currentPassword.value,
//        'editPassword' : form.editPassword.value
//    };
//
//    $.ajax({
//        url: '/api/user/edit/profile',
//        dataType: 'json',
//        contentType: 'application/json',
//        type: 'PUT',
//        cache: false,
//        data: JSON.stringify(dataObject)
//    });
//
//}
