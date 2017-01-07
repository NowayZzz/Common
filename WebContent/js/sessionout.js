/**
 * if (ajax request exception) do login
 */
Ext.Ajax.on('requestexception',function(conn,response,options){
	alert('--------------'+'requestexception'+response.status);
	if(response.status == 401){
		Ext.Msg.alert('提示','seesion过期,请重新登录!');
	}
});

//Ext.Ajax.on('requestcomplete',function(conn,response,options){
//	alert('--------------'+'requestcomplete'+response.status);
//});
