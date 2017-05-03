package com.jitesh.JsoupTest;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrapping {
	public static void main(String[] args) {
		
		try {
			Document doc = Jsoup.connect("http://naveenpatnaik.com/mla15th2014.htm").get();
			Element div = doc.getElementById("pageContentContainer");
			Elements rows = div.getElementsByAttributeValue("onmouseover", "this.style.backgroundColor='#f4f9f4';");
			System.out.println(rows.size());
			
			
			Iterator<Element> allRows = rows.iterator();
			while(allRows.hasNext()){
				Element row = allRows.next();
				
				Element td1st = row.child(1);
				// Link And Name 
				Element linkEl = td1st.getElementsByTag("a").first();
				
				//String MLAname = linkEl.attr("href").split("-of")[0].substring(4).replace("-", " ");
				String MLAname = linkEl.text();
				String link = linkEl.absUrl("href");
				// Constituency and Party Name
				Elements spans = td1st.getElementsByTag("span");
				String constituencyName = spans.first().text();
				String partyName = spans.last().text();
				
				System.out.println("MLA Name: "+MLAname);
				System.out.println("Constituency Name: "+ constituencyName);
				System.out.println("Party: "+ partyName);
				System.out.println("MLA Details URL: "+link);
				
				System.out.println("========================================");
				
				Element td2nd = row.child(3);
				// Link And Name 
				Element linkEl2nd = td2nd.getElementsByTag("a").first();
				
				String MLAname2nd = linkEl2nd.text();
				String link2nd = linkEl2nd.absUrl("href");
				// Constituency and Party Name
				Elements spans2nd = td1st.getElementsByTag("span");
				String constituencyName2nd = spans2nd.first().text();
				String partyName2nd = spans2nd.last().text();
				
				System.out.println("MLA Name: "+MLAname2nd);
				System.out.println("Constituency Name: "+ constituencyName2nd);
				System.out.println("Party: "+ partyName2nd);
				System.out.println("MLA Details URL: "+link2nd);
				
				System.out.println("========================================");
				
				Thread.sleep(1000);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
