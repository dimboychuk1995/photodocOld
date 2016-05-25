<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="baseURL" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<body>
<div class="information-about-object">
    <img src="/photodoc/resources/img/questions_icon_blue.png">
    <div></div>
</div>
<c:if test="${!empty sessionScope.user}">
<div class="wrapper-add-photo">
    <a href="#">
        <div class="add-photo unable">Додати фотодокумент</div>
    </a>
</div>
</c:if>
<div class="title-for-form">
    Результат:
</div>
<div class="block-results">
    <ul class="list-result">
        <c:forEach var="customer" items="${searchResult}">
            <li title="${fn:replace(customer.name, '\"', '\'')}">${customer.name}
                <ul class="list-objects-for-photo">
                    <c:forEach var="object" items="${customer.objects}">
                    <li title="${object.name}" counterId="${object.counter_id}" counter_num="${object.counter_number}" remid="${rem_id}" address="${object.address}" inspector="${object.inspector}">${object.name}</li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
        </ul>
    <ul class="list-imgs-for-object">
    </ul>
</div>
<script>
    function getUrlParams() {
        var urlForParse = decodeURIComponent(window.location.search);
        var objectParams = {},
                key, value;
        if (urlForParse.length) {
            var arrayUrlForParse = urlForParse.split('&');
            for (var i = 0; i < arrayUrlForParse.length; i++) {
                key = arrayUrlForParse[i].split('=')[0];
                value = arrayUrlForParse[i].split('=')[1];
                if (i == 0) {
                    objectParams[key.substring(1)] = value;
                } else {
                    objectParams[key] = value;
                }

            }
        }
        return objectParams;
    }
    $('.unable').on('click',function(event){
        alert('виберіть спочатку обєкт!');
        return false;
    });
    $('.list-objects-for-photo li').on('click',function(){
        $('.unable').off('click');
        $('.list-objects-for-photo,.list-objects-for-photo>li').removeAttr("style").parents('li').removeAttr("style");
        $.ajax({
            url: "/photodoc/contract/getFiles",
            data: {count_id:$(this).attr("counterid"),rem_id:$(this).attr("remid"), start_date:getUrlParams().start_date,end_date:getUrlParams().end_date},
            type: "get",
            dataType: 'json',
            success: function(res){
                if(res.length){
                    for(var i = 0;i<res.length;i++){
                        if(res[i].name.substr(-4).toLowerCase() == '.pdf'){
                            $('.list-imgs-for-object').append('<li><img src="/photodoc/resources/img/pdf_icon.png" onclick="window.open(\'http://10.93.1.55:88/photodoc/'+res[i].name+'\',\'_blank\')";/>'+res[i].date+'<span class="delete hidden" id="'+res[i].id+'" name="'+res[i].name+'">x</span></li>') ;
                        }else {
                            $('.list-imgs-for-object').append('<li><div><img src="http://10.93.1.55:88/photodoc/'+res[i].name+'" onclick="window.open(\'http://10.93.1.55:88/photodoc/'+res[i].name+'\',\'_blank\')";/></div>'+res[i].date+'<span class="delete hidden" id="'+res[i].id+'" name="'+res[i].name+'">x</span></li>') ;
                        }
                    }
                }else{
                    $('.list-imgs-for-object').append('<li>Фото відсутні</li>') ;
                }
            }
        })
        $(this).parent().css({width:"300px",height:"auto"}).end().css({background:"#FF8C00"}).parents('li').css({background:"#FF8C00"});
        var information = {"Адреса":$(this).attr('address'), "Номер лічильника":$(this).attr('counter_num'), "Назва":$(this).text(), "Інспектор":$(this).attr('inspector')},inf ='';
        for(var index in information){
            inf += '<span>'+index +'</span>:' + information[index]+'<br/>';
        }
        $('.information-about-object div').html(inf);
        $('.information-about-object').css({opacity:1});
        $('.add-photo').parents('a').attr("href","contract/addDocument?rem_id="+$(this).attr("remid")+"&counter_id="+$(this).attr("counterid")+"&counterNum="+getUrlParams().counterNum+"&contractNum="+getUrlParams().contractNum+"&start_date="+getUrlParams().start_date+"&end_date="+getUrlParams().end_date);
        $('.unable').removeClass('unable');
        $('.list-imgs-for-object').children().remove();

    });
    $(document).keydown(function(e){
        if(e.ctrlKey && e.altKey && e.keyCode==68){//ctrl+alt+d
            $('.delete').toggleClass('hidden');
        }
    });
    $('.list-imgs-for-object').on('click','.delete', function(){
        var li = $(this).parent();
        var id = this.id;
        var filename = $(this).attr("name");
        if(confirm("Ви впевнені,  що хочете видалити файл?")){
            $.post("/photodoc/contract/delete",{'id':id, 'filename':filename},function(){
                alert("Видалено успішно!")
                li.remove();
            }).fail(function() {
                alert("Помилка");
            });
        }
    })
</script>
</body>
</html>
