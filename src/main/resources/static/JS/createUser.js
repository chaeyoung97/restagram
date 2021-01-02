var create = {
    init: function () {
        var my = this;
        $('#btn-create').on("click", function () {
            my.createUser();
        });
    },
    createUser: function () {
      /*  console.log($('#user_id').val())
        console.log( $('#password').val())
        console.log( $('#email').val())
        console.log( $('#phone_Num').val())*/
        var data = {
            username: $('#user_id').val(),
            password: $('#password').val(),
            email: $('#email').val(),
            name: $("#name").val(),
            phoneNum: $("#phone_Num").val(),
            intro: null,
            profile_Image :null
        };
        $.ajax({
            type: 'POST',
            url: '/users/api/create',
            data_type: 'json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("회원등록완료!");
            window.location.href = '/loginForm';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};
create.init();

