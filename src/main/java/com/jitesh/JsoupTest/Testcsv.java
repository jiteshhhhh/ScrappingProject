package com.jitesh.JsoupTest;

import java.io.File;
import java.io.FileWriter;

public class Testcsv {
	public static void main(String[] args) {
		FileWriter fw = null;
		StringBuilder sb = null;
		
		try {
			fw = new FileWriter(new File("test.csv"));
			sb = new StringBuilder();
			
			sb.append("List");
			sb.append(',');
			sb.append("Details");
			sb.append('\n');
			
			sb.append("Name");
			sb.append(',');
			sb.append("Mayadhar");
			sb.append('\n');
			
			fw.write(sb.toString());
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(null != fw)
					fw.close();
				//	fw.flush();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		
	}
}
