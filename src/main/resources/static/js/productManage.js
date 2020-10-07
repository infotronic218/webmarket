
 var cols =[];
 cols.push({data:'id'}) ;
 cols.push({data:'code',mRender:function(data, type, row){
    return '<img class="adminTableImage" src="/market/products/images?code='+data+'" />';
 }});
 cols.push({data:'name'});
 cols.push({data:'brand'});
 cols.push({data:'quantity',mRender:function(data, type, row){
      if(data>0){
            return data;
          }else{
            return '<span style="color:red ;">Out of stock !</span>';
          }
        }
 });
 cols.push({data:'price', mRender:function(data, type, row){
   return '$ '+data;
 }});
 cols.push({data:'active',mRender:function(data, type, row){
          var str ='';
         str+='<label class="switch">';
         if(data>0){
          str+='<input  type="checkbox" value="'+row.id+'" checked="checked" />';
         }
         else{
          str+=' <input value="'+row.id+'" type="checkbox"  />';
         }
         str+='<span class="slider round"></span> </label>' ; 
         return str;           
 }});
 cols.push({data:'id',mRender:function(data,type, row){
              var str ='';
              str+='<a class="btn btn-danger m-1" href="'+rootUrl.delete+'?id='+data+'"><i class="fas fa-trash-alt"></i></a>';
              str+='<a class="btn btn-primary  m-1" href="'+rootUrl.edit+'?id='+data+'"><i class="fas fa-edit"></i></a>';
              return str;
 }});


  var $adminProductsTable = $('#adminProductsTable');
 if($adminProductsTable.length){
      var jsonUrl = "/json/data/admin/all/products";
      console.log(jsonUrl);
      console.log($adminProductsTable);
      $adminProductsTable.DataTable({
        lengthMenu:[[5,10,20, -1],['5 Records','10 Records','20 Records','ALL']],
        pageLength: 5,
        ajax:{
          url:jsonUrl,
          dataSrc:''
        }, columns:cols,initComplete:function(){
             var api = this.api();
             api.$('.switch input[type="checkbox"]').on('change',function(){
               var checkbox =$(this);
               var checked = checkbox.prop('checked');
               var dMsg = (checked)?'You want to active the product?':'You want to desactive the product ?';
               var value = checkbox.prop('value');
               bootbox.confirm({
                size:'medium',
                title:'Product Activation & Desactivation',
                message:dMsg,
                callback:function(confirmed){
                  if(confirmed){
                      console.log('Confime '+confirmed+"value : "+value);
                      var activationUrl =rootUrl.root+"/"+value+"/activation";
                      $.post(activationUrl, function(data){
                           bootbox.alert({
                           size:'medium',
                           title:'Information',
                           message:data
                      });
                      });
                      
                  }else{
                    checkbox.prop('checked',!checked);
                  }
                }
               });
             });
        }
            
      });
}



//Product Management Part
