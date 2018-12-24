package com.cn.xls;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 需要改进：
 * 在数据量大的时候：需批量发送，而不是单挑发送。
 * 另外最好使用框架，毕竟查询的时候使用二级缓存可以减小压力！
 * @author Administrator
 *
 */
public class ReadXls {

    private static POIFSFileSystem fs;//poi文件流
    private static HSSFWorkbook wb;//获得execl
    private static HSSFRow row;//获得行
    private static HSSFSheet sheet;//获得工作簿
 
	 public static void main(String[] args) throws Exception {
		 
		// 读取Excel文件
        //加载文件的位置
        InputStream in= new FileInputStream("D:/360安全浏览器下载/mjxx.xls");
        
        readXlsx(in);
             
	 }
	 
	 /**
	  * 读取xls文件
	  * @param in 文件输入流
	  */
    public static void readXlsx(InputStream in){
         
    	/*
    	 * 创建学生对象
    	 */
        List<Student> list = new ArrayList<>();
         
        try {
        	
             fs = new POIFSFileSystem(in);
             //获得workbook
             wb = new HSSFWorkbook(fs);
             //获得sheet
             sheet=wb.getSheetAt(0);
             //int rowfirst=sheet.getFirstRowNum();
             
             int rowEnd=sheet.getLastRowNum();//获取最后一行
              
             /**
              * 获取每一行
              */
             for (int i = 0; i <=rowEnd; i++) {
                row=sheet.getRow(i); //获取每行
                 
                 
               // int colNum = row.getPhysicalNumberOfCells();//一行总列数
                
                //每行就是一个对象，创建一个对象
                Student s = new Student();
                //一共8列
                for(int j = 0; j<8;j++){
                    if( row.getCell(j) != null){
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        //获取每列的数据 string类型
                        String value = row.getCell(j).getStringCellValue();
                         
                        /**
                         * 根据每次循环获取到的内容不同，设置给student对象的各个属性
                         */
                        switch (j) {
                        case 0:
                            s.setDdbh(value);
                            
                        case 1:
                            s.setMjhym(value);
                        case 2:
                            s.setMjzfb(value);
                        case 3 :
                            s.setPrice(value);
                        case 4 :
                            s.setName(value);
                        case 5 :
                            s.setShdz(value);
                        case 6 :
                            s.setSjh(value);
                        }
                    }
                }
                System.out.println(s);
                 
                /**
                 * 发送到数据库
                 */
                
                send(s);
                System.out.println("发送成功！");
             }    
                //添加到list中方便后续使用
                //list.add(s);
            }catch (Exception e) {
             e.printStackTrace();
         }
    }
     
    /**
     * 发送数据库的实现方法
     * @param s
     */
    public static void send(Student s) {
         
        String sql = "insert into mto (ddbh,mjhym,mjzfb,price,name,shdz,sjh) values (?,?,?,?,?,?,?)";
        Connection conn =null;
        PreparedStatement pstmt = null;
        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver") ;
             String url = "jdbc:mysql://localhost:3306/mjxx" ;    
             String username = "root" ;   
             String password = "root" ;   
             
            //连接数据库
            conn = DriverManager.getConnection(url , username , password ) ;
            
            System.out.println("链接成功！");
            pstmt = conn.prepareStatement(sql);
            
            //设置参数
            
            pstmt.setString(1, s.getDdbh());
            pstmt.setString(2, s.getMjhym());
            pstmt.setString(3, s.getMjzfb());
            pstmt.setString(4, s.getPrice());
            pstmt.setString(5, s.getName());
            pstmt.setString(6, s.getShdz());
            pstmt.setString(7, s.getSjh());
             
            //执行sql被影响的条数
            pstmt.executeUpdate();
             
            //System.out.println(i);
             
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //关闭一些东西
            if(pstmt != null){
                try {
                    pstmt.close();
                    if(conn != null){
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	 
 
	
}
