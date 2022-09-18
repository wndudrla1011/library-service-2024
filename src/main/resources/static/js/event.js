var main = {
    //각 버튼에 대한 이벤트 처리
    init : function () {
        var _this = this;
        $('#menu-bar').hover(
            function () {
                _this.appear();
            },
            function () {
                _this.disappear();
            }
        );
    },

    appear : function () {
            $('#sidebars').removeClass('visually-hidden');
            $('#menu-bar').addClass('visually-hidden');
        },

    disappear : function () {
            $('#sidebars').addClass('visually-hidden');
            $('#menu-bar').removeClass('visually-hidden');
        }

};

main.init();