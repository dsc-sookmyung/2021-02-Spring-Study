var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function (){
            _this.save();
        });

        $('#btn-update').on('click', function (){ // 1.
                    _this.update();
        });

        $('#btn-delete').on('click', function (){
                    _this.delete();
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
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/'; //글 등록 성공하면 메인페이지로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () { // 2.
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT', // 3.
            url: '/api/v1/posts/'+id, // 4.
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function() {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function() {
           alert('글이 삭제되었습니다.');
           window.location.href = '/';
        }).fail(function() {
           alert(JSON.stringify(error));
        });
    }
 };

 main.init();

 /* var main = {...}
 var main란 객체를 만들어 해당 객체에서 필요한 모든 function을 선언하면
 main 객체 안에서만 function이 유효하기 때문에 다른 JS와 중복될 위험 방지

 1. $('#btn-update').on('click')
 - btn-update란 id를 가진 HTML 엘리멘트에 click 이벤트가 발생할 때
   update function을 실행하도록 이벤트 등록

 2. update : function ()
 - 신규로 추가될 update function

 3. type: 'PUT'
 - HTTP 메소드 중 PUT 메소드 선택
 - PostsApiController에 있는 API에서 이미 @PutMapping으로 선언했기 때문에 PUT 사용
 - REST에서 CRUD에 매핑되는 HTTP 메소드
   CREATE - POST
   READ - GET
   UPDATE - PUT
   DELETE - DELETE

 4. url: '/api/v1/posts/'+id
 - 어느 게시글을 수정할지 URL Path로 구분하기 위해 path에 id 추가
 */