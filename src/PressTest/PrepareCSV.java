package PressTest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

import tools.Interface.user.UserBasic;
import tools.util.HttpUtil;
public class PrepareCSV {
    public void WriteStringToFile(String filePath) {
        try {
            File file = new File(filePath);
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println("http://www.jb51.net");// 往文件里写入字符串
            ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void WriteStringToFile2(String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("在已有的基础上添加字符串");
            bw.write("abc\r\n ");// 往已有的文件上添加字符串
            bw.write("def\r\n ");
            bw.write("hijk ");
            bw.close();
            fw.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void WriteStringToFile3(String filePath) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
            pw.println("abc ");
            pw.println("def ");
            pw.println("hef ");
            pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void WriteStringToFile4(String filePath) {
        try {
            RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
            rf.writeBytes("op\r\n");
            rf.writeBytes("app\r\n");
            rf.writeBytes("hijklllll");
            rf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void WriteStringToFile5(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            String s = "http://www.jb51.netl";
            fos.write(s.getBytes());
            fos.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void TestCSV(String filePath,String head,int start,int nums) throws Exception{
    	  
			FileOutputStream fos = new FileOutputStream(filePath);
			for (int i = 0; i < nums; i++) {
				String ss=head+(start+i)+","+head+(start+i)+"@qq.com"+"\r\n";
				fos.write(ss.getBytes());
			}
            fos.close();
			
 
    }
    
    
    
    public void TestAAACSV(String filePath,String head,int start,int nums) throws Exception{
  	  
			FileOutputStream fos = new FileOutputStream(filePath);
			for (int i = 0; i < 100; i++) {
				
				int a=60000;
				String ss1="131574";
				
				String ss=ss1+(a+i)+","+UserBasic.登录获取cookie111(ss1+(a+i))+"\r\n";
				//String ss=head+(start+i)+","+head+(start+i)+"@qq.com"+"\r\n";
				fos.write(ss.getBytes());
			}
            fos.close();
			
 
    }
    
    
    public static void main(String[] args) {
        String filePath = "E:\\link.txt";
        // new WriteStringToTxt().WriteStringToFile(filePath);
        // new WriteStringToTxt().WriteStringToFile2(filePath);
        // new WriteStringToTxt().WriteStringToFile3(filePath);
        // new WriteStringToTxt().WriteStringToFile4(filePath);
       // new PrepareCSV().WriteStringToFile5(filePath);
        try {
			new PrepareCSV().TestAAACSV(filePath,"378899",10000,9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}