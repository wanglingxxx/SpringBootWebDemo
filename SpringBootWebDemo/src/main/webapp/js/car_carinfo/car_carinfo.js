/**
 * Created by Adm on 2016/12/11.
 */


/**
 *多条件查询表单提交
 */
function mulSearchBtn(){
    var url = "http://localhost:8887/car_carinfo/queryByObject";
    //alert(url);
    var user_id = $("#user_id").val();
    if(!user_id){
        user_id = "";
    }
    var name = $("#name").val();
    if(!name){
        name = "";
    }
    var brand = $("#brand").val();
    if(!brand){
        brand = "";
    }
    var audi = $("#audi").val();
    if(!audi){
        audi = "";
    }
    //alert(user_id + " 1 "+name + " 1 "+brand + " 1 "+audi);

    $.post(url,{user_id:user_id, name:name, brand:brand, audi:audi},function(dat){
        if(dat){
            build_list(dat);
            //  car_carinfo_buildPagination(dat);
        }else{
            alert("查询也要按照基本法!")
        }
    });
}

/**
 * 添加系统用户打开弹出框
 */
function car_carinfo_save(){
    alert("Aaa");
    //alert($("#car_carinfo_saveBtn").html());
    $("#car_carinfo_saveBtn").click();
}

function initList(){
    var url = "http://localhost:8887/car_carinfo/queryAll";
    $.post(url,function(dat){
        if(dat){
            build_list(dat);
            //  car_carinfo_buildPagination(dat);
        }else{
            alert("查询也要按照基本法!")
        }
    });

}

/**
 * 重新构建表单
 * @param dat
 */
 function build_list(dat){

        var div = $("#showList");

        //alert("已将标题头清空!");
        div.empty();

        var tr = "";
        tr += "<tr><td><a class='btn btn-primary  btn-xs' href='javascript:;' onclick='checkbox()'>全选</a> <a class='btn btn-warning btn-xs' href='javascript:;' onclick='car_carinfo_deleteAll()'>批量删除</a> </td> <th>编号</th> <th>用户ID</th> <th>汽车名称</th> <th>车辆品牌</th> <th>车辆车系</th> <th>车辆价格</th> <th>车辆原价</th> <th>里程数</th> <th>购买时间</th> <th>车辆图片</th> <th><a class='btn btn-primary btn-xs' href='javascript:;' onclick='car_carinfo_save()' >添加</a> <a class='btn btn-danger btn-xs' href='javascript:;' onclick='car_carinfo_mulSearch()'>查询</a></th> </tr>";

        //alert(dat.length+" or " + dat.length);
        for(var i=0;i<dat.length;i++){

            tr += "<tr><td><input type='checkbox' value='"+dat[i].id+"' class='checkbox'  name='checkbox' /></td><td>"+dat[i].id+"</td><td>"+dat[i].user_id+"</td><td>"+dat[i].name+"</td><td>"+dat[i].brand+"</td><td>"+dat[i].audi+"</td><td>"+dat[i].price+"</td><td>"+dat[i].cost_price+"</td><td>"+dat[i].mileage+"</td><td>"+dat[i].buy_date+"</td><td>"+dat[i].photo+"</td><th><a class='btn btn-primary btn-xs' href='javascript:;' onclick='car_carinfo__deleteById("+dat[i].id+")' >删除</a><a class='btn btn-warning btn-xs' href='javascript:;' onclick='car_carinfo_findById("+dat[i].id+")' >修改</a></th></tr>";

        }


        div.append(tr);
        //alert($("#showList").html());
        div.trigger("create");
}