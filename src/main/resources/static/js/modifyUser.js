var create = {
    init: function () {
        var _this = this;
        $('#btn-modify').on("click", function () {
            _this.modifyUser();
        });
    },
    modifyUser: function () {
      /*  console.log($('#user_id').val())
        console.log( $('#password').val())
        console.log( $('#email').val())
        console.log( $('#phone_Num').val())*/
        var data = {
            username: $('#username').val(),
            email: $('#email').val(),
            name: $("#name").val(),
            phoneNum: $("#phone_num").val(),
            intro: $("#intro").val(),
            profile_Image :null
        };
        var url = $('.profileUpdateForm').attr('action');
        $.ajax({
            type: 'PUT',
            url: url,
            data_type: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("회원정보수정완료!");
            window.location.href = '/profile';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
create.init();

