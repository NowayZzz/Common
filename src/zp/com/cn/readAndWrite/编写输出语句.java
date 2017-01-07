package zp.com.cn.readAndWrite;

public class 编写输出语句
{
    public static void main(String[] args)
    {
    	int i=211;
    	int j=1;
//    	FK210 VARCHAR2(200),
//    	  FV210 VARCHAR2(200),
        while (  i <= 280){
        	//System.out.println("System.out.println( fk"+i+"+\"====\"+fv"+i+");");
//        	System.out.println("    FK"+i+"  VARCHAR2(200),");
//        	System.out.println("    FV"+i+"  VARCHAR2(200),");
//        		System.out.println("ps.setString("+i+", texes009vo.get(\"fk"+j+"\").toString());");
//        	System.out.println("String fv"+i+"=request.getParameter(\"fv"+i+"\");if(fv"+i+"==null)fv"+i+"=\"\";");
//        	String fk210=request.getParameter("fk210");if(fk210==null)fk210="";
        	//texes009.put("fk208",fk208);texes009.put("fv208",fv208);
        	
//        	System.out.print("texes009.put(\"fk"+i+"\",fk"+i+");texes009.put(\"fv"+i+"\",fv"+i+");");
//        	if(i%3==0)System.out.println();
        	
//        	fk201,fv201,
//        	System.out.print("fk"+i+",fv"+i+",");
        	
//        		i++;
        	System.out.println("ps.setString("+i*2+", texes009vo.get(\"fk"+i+"\").toString());");
        	System.out.println("ps.setString("+(i*2+1)+", texes009vo.get(\"fv"+i+"\").toString());");
        	j++;i++;
        }
    }
}
