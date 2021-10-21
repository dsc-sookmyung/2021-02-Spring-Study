var index = {  //index라는 변수의 속성으로 function 추가. 중복된 함수 이름 발생해도 상관 없게 하려고 function의 유효범위를 index.js로 한정
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';   //글 등록이 성공하면 / 링크(=메인페이지)로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

index.init();