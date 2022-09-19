var main = {
    //각 버튼에 대한 이벤트 처리
    init : function () {
        var _this = this;
        $('#menu-bar').on('click', function () {
            $('#sidebars').addClass('active');
            $('.overlay').addClass('fade-in');
            $('.fixed-footer').addClass('awake')
            $('#menu-bar').addClass('fade-in');
        });
        $('.overlay').on('click', function () {
            $('#sidebars').removeClass('active');
            $('.overlay').removeClass('fade-in');
            $('.fixed-footer').removeClass('awake');
            $('#menu-bar').removeClass('fade-in');
        });

    },

};

main.init();