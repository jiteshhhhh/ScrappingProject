package com.jitesh.JsoupTest;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MlaDetailsLink {
	
	public static void main(String[] args) {
		
		try {
			Document doc = Jsoup.connect(
					"http://naveenpatnaik.com/mla15th2014.htm").get();
			Element div = doc.getElementById("pageContentContainer");
			Elements rows = div.getElementsByAttributeValue("onmouseover",
					"this.style.backgroundColor='#f4f9f4';");
			
			System.out.println(rows.size());
			Iterator<Element> allRows = rows.iterator();
			while(allRows.hasNext()){
				
				Element row = allRows.next();
				if("td".equals(row.tagName())) continue;
				
				Element td1st = row.child(1);
				Element linkEl = td1st.getElementsByTag("a").first();				
				String MLAname = linkEl.text();
				String link = linkEl.absUrl("href");
				Elements spans = td1st.getElementsByTag("span");
				String constituencyName = spans.first().text();
				String partyName = spans.last().text();
			//System.out.println(rows.size());

//			Element td1st = rows.first();
//			Element linkEl = td1st.getElementsByTag("a").first();
//			String name = linkEl.attr("href").split("-of")[0].substring(4)
//					.replace("-", " ");
			System.out.println("MLA:  " + MLAname);
//			Elements spans = td1st.getElementsByTag("span");
//
//			String link = linkEl.absUrl("href");
//			String constituencyName = spans.first().text();
//			String partyName = spans.last().text();
			System.out.println("Constituency: " + constituencyName);
			System.out.println("Party: " + partyName);
			System.out.println("===================");
			System.out.println(link);
		
			//moreDetails(link);
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void moreDetails(String link) {
		try {
			Document doc = Jsoup.connect(link).get();	
			Element div = doc.getElementById("pageContentContainer");
			Elements rows = div.getElementsByAttributeValue("valign", "top");
			System.out.println(rows.size());
			Element detailEl = rows.get(3);
			
			String details = detailEl.html();
			String[] mlaDetailsArr = details.replace("<strong>", "")
					.replace("</strong>", "").replace("<h2>", "")
					.replace("</h2>", "").split("<br>");
			for (int i = 0; i < mlaDetailsArr.length ; i++) {
				String data = mlaDetailsArr[i];

				System.out.println(data);

			}
			//System.out.println(mlaDetailsArr);				
				
			
		}	
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
}
