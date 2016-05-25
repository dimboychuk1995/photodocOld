<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>
<html>
<body>
<div class="title-for-form">
    Щоб дадати фотодокумент, <br>потрібно:
</div>
<form method="POST" action="${baseURL}/contract/addDocument" class="form-uploading-photo" enctype="multipart/form-data">
    <input type="hidden" name="counter_id" value="${counter_id}">
    <input type="hidden" name="rem_id" value="${rem_id}">
    <input type="hidden" name="search_counter" class="hidden" value="${search_counter}">
    <input type="hidden" name="search_contract" class="hidden" value="${search_contract}">
    <input type="hidden" name="start_date" class="hidden" value="${start_date}">
    <input type="hidden" name="end_date" class="hidden" value="${end_date}">
    <div class="upload_file_container">
        Вибрати фотографію або сканований документ
        <input type="file" name="file" multiple/>
        <div id="check_for_file"></div>
    </div>
    <div class="upload_file_container show-input">
        Вибрати тип фотодокументу
        <select name="type_id">
            <c:forEach items="${typesDoc}" var="type">
                <option value="${type.id}">${type.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="upload_file_container show-input">
        Вказати дату
        <input type="text" class="date" name="date">
    </div>
    <div class="container-for-submit">
        <button class="submit">Додати фотодокумент</button>
    </div>

</form>
<script>
    $('.show-input').click(function(){
        $(this).find('input,select').css({visibility:"visible"});
    })
    $('#check_for_file').click(function(){
        $(this).prev().trigger('click');
    });
    $('.date').datepicker();
    var listTypesImgs = ['jpg', 'jpeg', 'png', 'gif','bmp', 'pdf'], checkImg = true;
    $('.upload_file_container input[type="file"]').change(function(){
        if($(this).val()!=''){
            if(listTypesImgs.indexOf($(this).val().substr(-3).toLowerCase()) +1 || $(this).val().substr(-4).toLowerCase() == 'jpeg'){
                $('#check_for_file').addClass('cheked-file');
                $('#check_for_file').text($(this).val().replace('C:\\fakepath\\', ''));
                $('#check_for_file').css({color: '#7f7e7a'});
                checkImg = true;
            }else{
                $('#check_for_file').removeClass('cheked-file');
                $('#check_for_file').text('невірний формат');
                $('#check_for_file').css({color: 'red'});
                checkImg = false;
            }

        }else{
            $('#check_for_file').removeClass('cheked-file');
            $('#check_for_file').text('');
        }
    })
    $('.form-uploading-photo').submit(function(e){
        var check = true;
        $(this).find('input:not(.hidden),select').each(function(index){
            if($(this).val() == 0 || $(this).val() == '' || !checkImg){
                $(this).css({visibility:"visible"});
                $(this).css({border: "1px solid red"});
                $(this).siblings('#check_for_file').text('виберіть файл');
                $(this).siblings('#check_for_file').css({color: 'red'});
                check = false;
            }else{
                $(this).css({border: "1px solid #2c7b00"});
                $(this).siblings('#check_for_file').css({color: '#7f7e7a'});
            }
        })
        if(check === false){
            return false;
        };
        $(".submit").attr("disabled","disabled");
    });
</script>



</body>
</html>
