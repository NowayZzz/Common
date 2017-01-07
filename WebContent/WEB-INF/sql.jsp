<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<!doctype html>
<body>
<h1>Study SQL</h1>
<ul>
<li>Oracle</li>
<li>Select nvl2(t1,’true’,’false’) t2 fom table </li>
<li> 
	综合数据类型函数
	NVL (expr1, expr2)
	如果expr1为非空，就返回expr1, 如果expr1为空返回expr2，两个表达式的数据类型一定要相同。
	NVL2 (expr1, expr2, expr3) 
	如果expr1为非空，就返回expr2, 如果expr1为空返回expr3
	NULLIF (expr1, expr2)
	如果expr1和 expr2相同就返回空，否则返回expr1
	COALESCE (expr1, expr2, ..., exprn)
	返回括号内第一个非空的值。
</li>
</ul>

<ul>
<li>Oracle</li>
<li>同义词(可能有点错误): Create [public] synonym ttcmcs023560 for baan. ttcmcs023560</li>
</ul>

<ul>
<li>Oracle</li>
<li>将字符串切分为结果集(以逗号分隔):</li>
<li>
	SELECT REGEXP_SUBSTR('first field, second field , third field', '[^,]+',  1,  rownum) FROM DUAL 
	CONNECT BY ROWNUM <= LENGTH('first field, second field , third field') - LENGTH(REPLACE('first field, second field , third field',  ',', '')) + 1;
</li>
</ul>

<ul>
<li>Oracle</li>
<li>convert(varchar(8),@dateid,112): 日期转成字符串  类型112,也就是20150102这种类型</li>
</ul>

<ul>
<li>Oracle效率，卡</li>
<li>用户的会话数，定位到machine，username，osuser：</li>
<li>select count(0),s.OSUSER,s.USERNAME,s.MACHINE from v$session s group by s.OSUSER,s.USERNAME,s.MACHINE order by count(0) desc;</li>
</ul>

<ul>
<li>Oracle历史记录</li>
<li>select * from mlot as of timestamp to_timestamp('2014-10-21 08:57:47', 'yyyy-mm-dd hh24:mi:ss') where mlotid=92027524 </li>
</ul>

<ul>
<li>Oracle触发器</li>
<li>

	CREATE OR REPLACE TRIGGER trigger_ttiitm240610
	BEFORE UPDATE ON ttiitm240400
	FOR EACH ROW
	begin
	  if updating and :NEW.T$OSTA = 3 and :OLD.T$COMP = 610 then
	     insert into ttiitm240610@subinfors
	     values (:OLD.T$TYPE, :OLD.T$DBNO, :OLD.T$YSFG);
	     insert into ttiitm241610@subinfors select t3.t$dbno,t3.t$seqn,t3.t$item from ttiitm241400 t3 where t3.t$dbno=:OLD.T$DBNO;
	  end if;
	end;
	
	
	create or replace trigger TRI_TTIITM240_ZRDW_1
	before insert or update on TTIITM240400 for each row
	 declare
	  user_var     varchar2(200);	  ip_add_var   varchar2(200);	  terminal_var varchar2(200);	  host_var     varchar2(200);	  session_id   number;
	begin
	  select sys_context('USERENV', 'OS_USER'),	         sys_context('USERENV', 'IP_ADDRESS'),	         sys_context('USERENV', 'TERMINAL'),
	         sys_context('USERENV', 'HOST'),	         sys_context('USERENV', 'SESSIONID')
	    into user_var, ip_add_var, terminal_var, host_var, session_id	    from dual;
	
	  if updating and trim(:NEW.T$ZRDW) = '1' then	   insert into  GCP_TIITM240_ZRDW@ERPCX
	   values
	    (	      :old.t$dbno,	      sysdate,	      'update',	       user_var,	       ip_add_var,	       terminal_var,
	       host_var,	       session_id,	       ora_login_user,	       :OLD.T$ZRDW
	    );
	  end if;
	
	  if inserting and trim(:NEW.T$ZRDW) = '1' then	   insert into  GCP_TIITM240_ZRDW@ERPCX
	   values
	    (	      :new.t$dbno,	      sysdate,	      'insert',	       user_var,	       ip_add_var,	       terminal_var,
	       host_var,	       session_id,	       ora_login_user,	        :OLD.T$ZRDW
	    );
	  end if;
	end;
</li>
</ul>







</body>
</html>