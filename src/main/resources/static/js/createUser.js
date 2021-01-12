var create = {
    init: function () {
        var my = this;
        $('#btn-create').on("click", function () {
            my.createUser();
        });
        $('#btn-delete').on("click", function () {
            my.deleteUser();
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
            phoneNum: null,
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
    deleteUser : function ()
    {
        var id=$('#id').val();
        console.log()
        var data = {
            password: $('#password').val(),
        };
        $.ajax({
           type: 'DELETE',
           url :'/users/api/del/'+id,
            data_type: 'json',
            contentType: 'application/json; charset-urf-8',
            data:JSON.stringify(data)
        }).done(function()
        {
            alert("회원이 탈퇴되었습니다.")
                window.location.href='/loginForm';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
create.init();


