package tools.Interface.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageRandom {
	
	
	
	public static String RandomImages(){
	    List<String> list=new ArrayList<String>();
		list.add("http://image.tairanmall.com/FvXAB3NN4zUaQBgzSP93_4UVCsdO");
		list.add("http://image.tairanmall.com/FpLSUS7De3JooKN2h80Q0Trb7sbF");
		list.add("http://image.tairanmall.com/FtxzrO1vQSeqgY9NyeU6BHggMXp3");
		list.add("http://image.tairanmall.com/Fn4oFyfZ-nGb0BTGQowuWqJpRi2k");
		list.add("http://image.tairanmall.com/FkMahTAHAZRwJ5TO7c6ZIUhnqU0i");
		list.add("http://image.tairanmall.com/Fml25KNJByqrBvZl_nSp2Ak6rUuw");
		list.add("http://image.tairanmall.com/FoUGvskV39KX143FxcGmXo7sEOij");
		list.add("http://image.tairanmall.com/FtZN59sz_syyzvsPRCHD4jPSK0RW");
		list.add("http://image.tairanmall.com/FrRMY8Mo4ewAOaY8DNNCWdA4IQZw");
		list.add("http://image.tairanmall.com/Fiuq_bVIFFvamcXgOJHFQU4961zs");
		Random r= new Random();
		int n=r.nextInt(10);
		return list.get(n);
	}

}
