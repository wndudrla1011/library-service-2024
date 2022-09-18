var main = {
    //각 버튼에 대한 이벤트 처리
    init : function (){
        var _this = this;
        $('#menu-bar').on('click', function () {
            _this.appear();
        });
    },

    appear : function () {
            $('#sidebars').removeClass('visually-hidden');
            $('#menu-bar').addClass('visually-hidden');
        }

};

main.init();