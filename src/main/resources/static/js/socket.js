
var chat={
    init:function (){
        var t=this;
        var socket = new SockJS('/ws-stomp');
        stompClient = Stomp.over(socket);
        stompClient.connect({},function (frame)
        {
            console.log(frame);
            var x=localStorage.getItem('roomId');
            stompClient.subscribe('/sub/chat/room/'+x,function (msg)
            {
                var y=localStorage.getItem('sender');
                console.log("-----------body--->"+msg.body)
                console.log("------------msg>"+msg)
                let chatLi = $('div.chat.format ul li').clone();

                // 값 채우기
                chatLi.addClass("left");
                chatLi.find('.sender span').text(y);
                chatLi.find('.message span').text(msg.body);
                $('div.chat:not(.format) ul').append(chatLi);
                $('div.chat').scrollTop($('div.chat').prop('scrollHeight'));


                $('#greetings').append("<tr><td>"+y+" : "+(msg.body)+"<br>"+"</td></tr>");
            });
        });
        //--------------------------------------------//
        $(document).on('keydown', 'div.input-div textarea', function(e){
            if(e.keyCode == 13 && !e.shiftKey) {
                e.preventDefault();
                const message = $(this).val();

                // 메시지 전송
                t.send();
                // sendMessage(message);
                // 입력창 clear
                $('div.input-div textarea').val('');
            }
        });

    },
    send : function (){
        var data={
            'roomId':localStorage.getItem('roomId'),
            'sender':localStorage.getItem('sender'),
            'message':$('#msg').val(),
            'chatRoomTable':$()
        }
        stompClient.send("/pub/chat/message",{},JSON.stringify(data));
    },
}
chat.init();
