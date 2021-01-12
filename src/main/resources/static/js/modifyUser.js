var modify = {
    init: function () {
        var _this = this;
        $('#btn-modify').on("click", function () {
            _this.modifyUser();
        });
    },
    modifyUser: function () {

        var data = {
            username: $('#username').val(),
            email: $('#email').val(),
            name: $("#name").val(),
            phoneNum: $("#phone_num").val(),
            intro: $("#intro").val(),
            profile_Image :null
        };
        var url = $('.profileUpdateForm').attr('action');

        var userName = $('#username').val();

        $.ajax({
            type: 'PUT',
            url: url,
            data_type: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("회원정보수정완료!");
            window.location.href = '/profile/' + userName;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
modify.init();

