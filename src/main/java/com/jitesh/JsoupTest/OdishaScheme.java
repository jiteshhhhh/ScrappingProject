package com.jitesh.JsoupTest;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OdishaScheme {
	public static void main(String[] args) {

		allScheme();

	}

	private static void allScheme() {
		try {
			Document doc = Jsoup.connect(
					"http://www.naveenpatnaik.com/initiatives.php").get();
			Element div = doc.getElementById("pageContentContainer");
			Elements rows = div.getElementsByAttributeValue("onmouseover",
					"this.style.backgroundColor='#f4f9f4';");
			System.out.println("No. of scheme: " + rows.size());

			Iterator<Element> allRows = rows.iterator();
			while (allRows.hasNext()) {
				Element row = allRows.next();

				Element td = row.child(0);
				Element linkEl = td.getElementsByTag("a").first();
				String scheme = linkEl.text();
				System.out.println("Scheme: " + scheme);
				Thread.sleep(500);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
