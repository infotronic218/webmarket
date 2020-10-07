
var mUrl = document.getElementById('mProductUrl').href;

console.log("DEF URL " ,mUrl);
var $table = $('#productsTable');
if($table.length){
	$table.DataTable({
		lengthMenu:[[3,5,10,-1],['3 Records','5 Records','10 Records','ALL']],
		pageLengh :5,
		ajax:{
			url:mUrl,
			dataSrc:''
		},columns:[{data:'code',
		           mRender:function(data,type, row){

                      return '<img class="dataTableImg" src="/market/products/images?code='+data+'" />'
		           }},
		          {data:'name'},
		          {data:'brand'},
		          {data:'price'
		          ,mRender:function(data, type, row){
		          	return '&#36; '+data;
		          }},
		          {data:'quantity',
		           mRender:function(data,type,row){
                       if(data>0){
                       	return data ;
                       }else{
                       	return '<span style="color:red ;">Out of stock !</span>';
                       }
		           }
		          },
		          {
		          	data:'id',
		          	bSortale:false,
		          	mRender:function(data, type, row){
		          		var str = '';
		          		str+='<a href="'+'/market/show/'+data+'/product" title="Click to see the product in detail " class="btn btn-secondary"> <i class="far fa-eye"></i></a> &#160;';
		          		if(row.quantity>0){
		          			str+='<a href="'+addCartUrl+'/'+data+'/product" title="Add the product to your cart" class="btn btn-success"><i class="fas fa-shopping-cart"></i></a> &#160;';
		          		}else{
		          			str+='<strike><a href="javascript:void()" title="Product out of stock" class="btn btn-success"><i class="fas fa-shopping-cart"></i></a></strike> &#160;';
		          		}
		          		
		          		
		          		return str ;
		          	}
		          }
		           ]
       
	});
}


