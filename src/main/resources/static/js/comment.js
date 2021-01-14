$(".comment-btn").click(addComment);

function addComment(){
    console.log("질문 버튼 클릭");

    var newComment = $("#commentInput").val();
    console.log("질문: " + newComment);
    if(newComment == "")
        return ;

    var commenturl = $("#comment-url").val();
    console.log("질문 url: " + commenturl);

    data = { content : newComment };
    $.ajax({
        type: 'post',
        url: commenturl,
        contentType: 'application/json; charset-utf-8',
        data: JSON.stringify(data),
        dataType: 'json',
        error: onError2,
        success: onSuccess2
    });

}
function onError2(e){console.log(e);}
function onSuccess2(data){
    console.log("onSuccess함수실행");
    console.log(data);
    console.log(data.profileImage);
    var commentTemplate = $("#commentTemplate").html();
    console.log(commentTemplate);
    var template = commentTemplate.format(data.profileImage, data.username, data.content, data.createdDate);
    $(".scroll_section").append(template);

    $("#commentInput").val("");
}