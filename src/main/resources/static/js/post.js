var post = {
    init : function (){
        var _this = this;
        $('#btn-save').on("click",  function (){
            _this.save();
        });

        $("#btn-update").on("click", function(){
            _this.update();
        })

    },

    save : function (){

        var formData = new FormData();

        var files = $('#files')[0].files;
        for(i=0; i<files.length;i++){
            formData.append("files", files[i]);
        }
        formData.append("content", $('#content').val());

        $.ajax({
            type : 'POST',
            url : "/api/posts",
            enctype : "multipart/form-data",
            data : formData,
            processData : false,
            contentType :false,
        }).done(function () {
            alert("등록완료!");
            window.location.href = '/';
        }).fail(function (error) {
            alert("실패");
        });
    },

    update : function (){

        var formData = new FormData();

        var files = $('#files')[0].files;
        for(i=0; i<files.length;i++){
            formData.append("files", files[i]);
        }
        formData.append("content", $('#content').val());

        id =  $("#post-id").val();

        $.ajax({
            type : 'PUT',
            url : "/api/posts/" + id ,
            enctype : "multipart/form-data",
            data : formData,
            processData : false,
            contentType :false,
        }).done(function () {
            alert("수정완료!");
            window.location.href = '/posts/detail/'+id;
        }).fail(function (error) {
            alert("실패");
        });
    },


};
post.init();
