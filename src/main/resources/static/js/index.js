var main = {
    //각 버튼에 대한 이벤트 처리
    init : function () {
        var _this = this;
        $('#book-save').on('click', function () {
            _this.save();
        });
    },
    //저장
    save : function () {
            //각 속성 값 포장
            var data = {
                title: $('#title').val(),
                writer: $('#writer').val(),
                price: $('#price').val(),
                stock: $('#stock').val(),
                status: $('#status').val()
            };

            //컨트롤러로 api 요청
            $.ajax({
                type: 'POST',
                url: '/admin/books/add',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function() {
                alert('글이 등록되었습니다.');
                window.location.href = '/';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
};

main.init();