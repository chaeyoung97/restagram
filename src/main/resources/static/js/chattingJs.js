var chat = {

    init: function () {
        var my = this;
        $('#chat-create').on('click', function () {
            my.create();
        });
        $('#gogo').on('click', function () {
            var x = $('#id').val();
            console.log(x);
            my.enterRoom(x);
        })

        // my.enterRoom();
    },
    create: function () {
        var x = this;
        var data = {
            responseUserId: $('#responseUserId').val(),
            roomId: null
        };
        $.ajax({
            type: 'POST',
            url: '/chat/room',
            dataType: 'json',
            contentType: 'application/json; charset-utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("채팅방 개설 성공");
            location.href = '/room';
        }).fail(function () {
            alert("False 입니다. code 확인")
            alert(JSON.stringify(onerror));
        });
    },
    enterRoom: function (roomId) {
        var sender=$('#username').val();
        localStorage.setItem('sender',sender);
        localStorage.setItem('roomId', roomId);
        window.open("/chat/room/"+roomId);
    }

}
chat.init();

