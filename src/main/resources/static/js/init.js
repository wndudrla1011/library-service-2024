var main = {
    //각 버튼에 대한 이벤트 처리 -> 댓글 처리 부분에서
    init : function () {
        var _this = this;

        //댓글 저장
        $('#btn-comment-save').on('click', function () {
            _this.save();
        });
        $('#btn-comment-update').on('click', function () {
            _this.update();
        });

    },

    save : function () {
        const data = {
            postId: $('#postId').val(),
            comment: $('#comment').val()
        }

        if(!data.comment || data.comment.trim() === ""){
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/posts/' + data.postId + '/comments/add',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    update : function () {
        const data = {
            postId: $('#postId').val(),
            commentId: $('#commentId').val(),
            comment: $('#comment').val()
        }

        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'PUT',
                url: '/posts/' + data.postId + '/comments/' + data.commentId,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 수정되었습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
};

main.init();