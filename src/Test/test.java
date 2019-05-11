package Test;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import Client.Client;
import Entity.ItemBill;
import Entity.MatHang_ChiNhanh;
import Entity.TaiKhoan;
import Request.MyRequest;
import Request.MyRequest.METHOD;
import Service.DonHangService;
import Service.SearchService;

public class test {

	public static int Tim(int[] a,int[] b, int size) {
		if(a[size] != Tim(b, a, size))
		{	
			System.out.println(size +Tim(a,b,size-1));
		}
		return -1;
	}
	public static void main(String[] args) {
//		int[] a= {1,2,3,4,5};
//		int[] b= {1,2,6,4,5};
//		System.out.println(Tim(a,b,a.length-1));
		DonHangService donHangService= new DonHangService();
		List<ItemBill> lsBill= Stream.of(new ItemBill("1",2),new ItemBill("4",2)).collect(Collectors.toList());
//		try {
//			donHangService.SaveBill(lsBill, "1", 1, "abc",1);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
