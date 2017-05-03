package com.jitesh.JsoupTest;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewMlaDetails {

	public static void main(String[] args) {
		mlaDetails();
	}

	public static void mlaDetails() {
		try {
			Document doc = Jsoup.connect(
					"http://naveenpatnaik.com/mla15th2014.htm").get();
			Element div = doc.getElementById("pageContentContainer");
			Elements rows = div.getElementsByAttributeValue("onmouseover",
					"this.style.backgroundColor='#f4f9f4';");

			Element td1st = rows.first();
			Element linkEl = td1st.getElementsByTag("a").first();
			String name = "MLA: "
					+ linkEl.attr("href").split("-of")[0].substring(4).replace(
							"-", " ");
			Elements spans = td1st.getElementsByTag("span");

			String link = linkEl.absUrl("href");
			String constituencyName = "Constituency: " + spans.first().text();
			String partyName = "Party: " + spans.last().text();

			moreDetails(link, name, constituencyName, partyName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void moreDetails(String link, String name,
			String constituency, String party) throws Exception {
		String output = "";
		try {
			Document doc = Jsoup.connect(link).get();
			Element div = doc.getElementById("pageContentContainer");
			Elements rows = div.getElementsByAttributeValue("valign", "top");
			// System.out.println(rows.size());
			Element detailEl = rows.get(3);
			// String mla = name+":"+constituency+":"+party;
//			System.out.println(name);
//			System.out.println(constituency);
//			System.out.println(party);

			String details = detailEl.html();
			String[] mlaDetailsArr = details.replace("<strong>", "")
					.replace("</strong>", "").replace("<h2>", "")
					.replace("</h2>", "").split("<br>");

			System.out.println(mlaDetailsArr.length);
			System.out.println(mlaDetailsArr[0]);
			for (int i = 0; i < mlaDetailsArr.length; i++) {
				String data = mlaDetailsArr[i];
				output += data;

			}
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}

	//	myCsv(name+constituency+party+output);
	}

	public static void myCsv(String data){
		String[] value = data.split(":");
		System.out.println(value.length);
		FileWriter fw = null;
		StringBuilder sb = null;
		try{
			fw = new FileWriter(new File("mla.csv"));
			sb = new StringBuilder();
			
			sb.append("List");
			sb.append(',');
			sb.append("Details");
			sb.append('\n');
			
			
			fw.write(sb.toString());
			System.out.println("Done");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally{
			try {
				if(null!=fw)
					fw.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
	}
}
