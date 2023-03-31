$(document).ready(function () {
    getUserList();
});

let page = 1;

function getUserList(){
    $.get("/api/user/all",function (data) {
        let userList = '';
        for (let i = 0; i < data.length; i+=2) {
            if(data.length%2 !== 0 && i === data.length-1){
                userList +='<div class="row col-12">' +
                    '                            <div class="row col-6">' +
                    '                                <div class="col-4">' +
                    '                                    <img src="/images/' + data[i].linkImage + '" alt="err" class="bd-placeholder-img rounded-circle" width="100%" height="150" role="img" style="max-width: 150;max-height: 150" ><rect width="100%" height="100%" fill="#868e96"></rect></img>' +
                    '                                </div>' +
                    '                                <div class="col-8">' +
                    '                                    <div class="col-12">' +
                    '                                        <div class="card-body">' +
                    '                                            <h5 class="card-title" align="center">'+ data[i].login + '</h5>' +
                    '                                            <p class="card-text" align="left">'+ data[i].email + '</p>' +
                    '                                            <form>' +
                    '                                            <input type="button" id="redirect-button" class="ui-button" value="Перейти" onclick=\'location.href="/user/' + data[i].id + '"\'/>' +
                    '                                            </form>' +
                    '                                        </div>' +
                    '                                    </div>' +
                    '                                </div>' +
                    '                            </div>' +
                    '</div>'

            } else {
                userList += '<div class="row col-12">' +
                    '                            <div class="row col-6">' +
                    '                                <div class="col-4">' +
                    '                                    <img src="/images/' + data[i].linkImage + '" alt="err" class="bd-placeholder-img rounded-circle" width="100%" height="150" role="img" style="max-width: 150;max-height: 150" ><rect width="100%" height="100%" fill="#868e96"></rect></img>' +
                    '                                </div>' +
                    '                                <div class="col-8">' +
                    '                                    <div class="col-12">' +
                    '                                        <div class="card-body">' +
                    '                                            <h5 class="card-title" align="center">' + data[i].login + '</h5>' +
                    '                                            <p class="card-text" align="left">' + data[i].email + '</p>' +
                    '                                            <form>' +
                    '                                            <input type="button" id="redirect-button" class="ui-button" value="Перейти" onclick=\'location.href="/user/' + data[i].id + '"\'/>' +
                    '                                            </form>' +
                    '                                        </div>' +
                    '                                    </div>' +
                    '                                </div>' +
                    '                            </div>' +
                    '                            <div class="row col-6">' +
                    '                                <div class="col-4">' +
                    '                                    <img src="/images/' + data[i + 1].linkImage + '" alt="err" class="bd-placeholder-img rounded-circle" width="100%" height="150" role="img" style="max-width: 150;max-height: 150"><rect width="100%" height="100%" fill="#868e96"></rect></img>' +
                    '                                </div>' +
                    '                                <div class="col-8">' +
                    '                                    <div class="col-12">' +
                    '                                        <div class="card-body">' +
                    '                                            <h5 class="card-title" align="center">' + data[i + 1].login + '</h5>' +
                    '                                            <p class="card-text" align="left">' + data[i + 1].email + '</p>' +
                    '                                            <form>' +
                    '                                            <input type="button" class="ui-button" value="Перейти" onclick=\'location.href="/user/' + data[i + 1].id + '"\'/>' +
                    '                                            </form>' +
                    '                                        </div>' +
                    '                                    </div>' +
                    '                                </div>' +
                    '                            </div>' +
                    '                        </div>';
            }
            $("#user-listed").html(userList);
        }
    });
}

function nextPage(){

}