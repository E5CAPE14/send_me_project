$(document).ready(function () {
    getFriends();
});

function getFriends(){
    $.get("/api/friends/",function (data) {
        console.log(data);
        let userList = '<table class="table">' +
            '                    <thead>' +
            '                    <tr>' +
            '                        <th>Аватарка</th>' +
            '                        <th>Email</th>' +
            '                        <th>Login</th>' +
            '                        <th>Перейти</th>' +
            '                    </tr>' +
            '                    </thead>' +
            '                    <tbody>';
        for (let i = 0;i < data.length;i++) {
            userList +=
                '                    <tr>' +
                '                        <td>' + data[i].user.linkImage + '</td>' +
                '                        <td>' + data[i].user.email + '</td>' +
                '                        <td>' + data[i].user.login + '</td>' +
                '                        <td>' +
                '                           <button class="ui-button" onclick="getUser('+ data[i].user.id + ')" id="getButton"><strong>Перейти</strong></button>'
                '                       </td>' +
                '                    </tr>';
        }
        userList += '                    </tbody>' +
                    '           </table>';
        $("#friendListId").html(userList);

    });
}
function getUser(id){
    window.location.href = "/user/"+ id;
}
