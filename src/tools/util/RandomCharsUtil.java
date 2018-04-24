package tools.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import org.apache.log4j.Logger;



public class RandomCharsUtil {
	
	public  static  Logger logger = Logger.getLogger( RandomCharsUtil.class);


	/**
	 * 
	 * @param num
	 * @return
	 */
	public static String 获取指定数量纯汉字(int num){
		StringBuffer sb=new StringBuffer();
		if(num<=0){
			return "输入数量异常是几个意思？";
		}
		for (int i = 0; i < num; i++) {
			sb.append(getRandomChineseChar());
			
		}
		logger.info("获取"+num+"汉字！内容为："+sb.toString());
		return sb.toString();
	}
	
	public static String 获取指定数量纯字母(int num){
		StringBuffer sb=new StringBuffer();
		if(num<=0){
			return "输入数量异常是几个意思？";
		}
		for (int i = 0; i < num; i++) {
			sb.append(getARandomCharacter());
			
		}
		logger.info("获取"+num+"纯字母！内容为："+sb.toString());
		return sb.toString();
	}
	public static String 获取指定数量纯数字(int num){
		StringBuffer sb=new StringBuffer();
		if(num<=0){
			return "输入数量异常是几个意思？";
		}
		for (int i = 0; i < num; i++) {
			sb.append(getARandomNums());
			
		}
		logger.info("获取"+num+"个纯数字！内容为："+sb.toString());
		return sb.toString();
	}
	
	public static String 获取指定数量特殊字符集(int num){
		StringBuffer sb=new StringBuffer();
		if(num<=0){
			return "输入数量异常是几个意思？";
		}
		for (int i = 0; i < num; i++) {
			sb.append(getARandomStrangeCharacter());
		}
		logger.info("获取"+num+"个特殊字符！内容为："+sb.toString());
		return sb.toString();
	}
	
	public static String 获取指定数量混合字符集(int num){
		StringBuffer sb=new StringBuffer();
		if(num<=0){
			return "输入数量异常是几个意思？";
		}
		for (int i = 0; i < num; i++) {
			switch ((int)(Math.random() * 4)) {
			case 0:
				sb.append(getARandomStrangeCharacter());
				
				break;
			case 1:
				sb.append(getARandomCharacter());
				break;
			case 2:
				sb.append(getARandomNums());
				break;
			case 3:
				sb.append(getRandomChineseChar());
				break;

			default:
				sb.append(getRandomChineseChar());
				//System.out.println(getRandomChineseChar());
				break;
			}
		}
		logger.info("获取"+num+"随机字符串！内容为："+sb.toString());
		return sb.toString();
	}
	
	/**
	 * 获取一个随机中文字符；
	 * @return
	 */
    private static char getRandomChineseChar() {
        String str = "";
        int hightPos; //
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);
    }
    private final static String sourceUpCase ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String sourceLowCase="abcdefghijklmnopqrstuvwxyz";
    private final static String numbers="0123456789";
    private final static String mix="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static String strange="<>?:/'\"{}|+_)(*&^%$#@!~)";
    private final static String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static String getARandomCharacter(){
    	char[] b =new char[1];
    	b[0]=characters.charAt((int)(Math.random() * 52));  
    	return new  String(b);
    }
    private static String getARandomUpcaseCharacter(){
    	char[] b =new char[1];
    	b[0]=sourceUpCase.charAt((int)(Math.random() * 26));  
    	return new  String(b);
    }
    
    private static String getARandomLowcaseCharacter(){
    	char[] b =new char[1];
    	b[0]=sourceLowCase.charAt((int)(Math.random() * 26));  
    	return new  String(b);
    }
    
    private static String getARandomStrangeCharacter(){
    	char[] b =new char[1];
    	b[0]=strange.charAt((int)(Math.random() * 23));  
    	return new  String(b);
    }
    
    
    private static String getARandomNums(){
    	char[] b =new char[1];
    	b[0]=numbers.charAt((int)(Math.random() * 10));  
    	return new  String(b);
    }
    
    private static String getARandomMIX(){
    	char[] b =new char[1];
    	b[0]=mix.charAt((int)(Math.random() * 62));  
    	return new  String(b);
    }
    
	public static void main(String[] args) {
		获取指定数量纯汉字(150);
		获取指定数量纯字母(150);
		获取指定数量纯数字(150);
		获取指定数量特殊字符集(150);
		获取指定数量混合字符集(150);
	}
}
