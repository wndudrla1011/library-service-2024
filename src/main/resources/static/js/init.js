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
        $('#btn-comment-delete').on('click', function () {
            _this.delete();
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
            memberId: $('#memberId').val(),
            writerId: $('#writerId').val(),
            comment: $('#comment').val()
        }

        if (data.memberId !== data.writerId) {
            alert("본인이 작성한 댓글만 수정 가능합니다.");
            return false;
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
    },

    delete : function () {
        const data = {
            postId: $('#postId').val(),
            commentId: $('#commentId').val(),
            memberId: $('#memberId').val(),
            writerId: $('#writerId').val()
        }

        if (data.memberId !== data.writerId) {
            alert("본인이 작성한 댓글만 삭제 가능합니다.");
            return false;
        } else {
            const check = confirm("삭제하시겠습니까?");

            if (check === true) {
                $.ajax({
                    type: 'DELETE',
                    url: '/posts/' + data.postId + '/comments/' + data.commentId,
                    contentType: 'application/json; charset=utf-8'
                }).done(function () {
                    alert('댓글이 삭제되었습니다.');
                    window.location.href='/posts/' + data.postId;
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        }
    }
};

main.init();