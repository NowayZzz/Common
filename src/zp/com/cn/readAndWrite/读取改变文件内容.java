package zp.com.cn.readAndWrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class 读取改变文件内容 {

    /**
     * @param args
     */
    public static void main(String[] args) {
//    	System.out.println(5.334*30.000);
        readFileByLines();
        //System.out.println(CodeUtils.encodeString("590123")+"----"+CodeUtils.decodeString("MzMwMTA2"));
//    	List<HashMap<String, String>> strList = new ArrayList<HashMap<String, String>>();
//    	HashMap<String, String> map = new HashMap<String, String>();
//    	map.put("张三", "S");
//    	map.put("张三2", "S");
//    	map.put("张三1", "A");
//    	strList.add("c");
//    	strList.add("a");
//    	SortedMap<String, String> sort = new TreeMap<String, String>();
//    	sort.putAll(map);
//    	System.out.println(sort);
    }

    static void readFileByLines() {
        File file = new File("F:\\111.txt");
        File file1 = new File("F:\\222.txt");
        BufferedReader reader = null;
        BufferedReader reader1 = null;
        BufferedWriter writer = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1),"utf-8"));
            writer = new BufferedWriter(new FileWriter("F:/123.txt"));
            String tempString = "";
            int line = 0;
            //Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
            //System.out.println("1111===" + tempString.indexOf("]"));
            // 一次读入一行，直到读入null为文件结束rea
            //for (int i = 0; i < 20; i++) {
//          
                int count=0;
                HashMap<String, String> map = new HashMap<String, String>();
                while ((tempString = reader.readLine())!=null ) {
                    //String temp = tempString.replaceAll("<th>", "<td class=\"table_td_head\">");
                    //temp=temp.replaceAll("</th>", "</td>");
                    /*String temp = tempString;
                    for(int i=0;i<9;i++){
                        writer.write(temp);
                        writer.write("\r\n");
                    }*/
                	count++;
                	map.put(tempString+count, reader1.readLine());
//                    String temp = tempString.trim();
//                    System.out.print("'"+temp+"',");
//                    System.out.println("insert into gcp_user_role_ships values('"+temp+"',300006);");
//                    System.out.println("insert into gcp_user_role_ships values('"+temp+"',100028);");
                    /*Matcher m = p.matcher(temp);
                    if(m.find()){
                        count++;
                        System.out.println("<input type=\"hidden\" name=\"fk"+count+"\" value=\""+m.group(0)+"\" />");
                    }*/
                    // sts_wastedetails   sts_waste_prices
                    //writer.write("元/"+temp);
//                  writer.write("(to_char(t.wfid)='"+temp+"' and t.wdid='"+tempString1+"') or ");
//                  writer.write("'"+temp+"',");
//                   and materialscode='"+tempString1+"'
//                  writer.write("select * from sts_waste_prices where to_char(wfid)='"+temp+"' and to_char(wdid)='"+tempString1+"' "
//                          + "and to_char(start_date,'yyyy/fmmm/dd')='2015/5/1' and wunit='元/块' union all ");
//                  writer.write("select * from sts_wastedetails where to_char(wfid)='"+temp+"' and materialscode='"+tempString1+"' and enable=1 union all ");
                    //writer.write(temp.substring(0, 4)+"/"+temp.substring(4, 6)+"/"+temp.substring(6, 8));
//                  writer.write("select * from sts_waste_prices where to_char(wfid)='"+temp+"' and to_char(wdid)='"+tempString1+"' "
//                          + "and to_char(start_date,'yyyy/fmmm/dd')='"+tempString2+"' union all ");
                    //and to_char(expire_date,'yyyy/fmmm/dd')='"+tempString3+"' 
                    
                    writer.write("\r\n");
                    writer.flush();
                    line++;
                //}
            }
                SortedMap<String, String> sort = new TreeMap<String, String>();
            	sort.putAll(map);
                System.out.println(sort);
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    writer.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
