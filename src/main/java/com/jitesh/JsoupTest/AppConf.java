package com.jitesh.JsoupTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class AppConf {

	static Properties prop = null;
	static boolean initialized = false;

	// static {
	// try {
	// prop = new Properties();
	// prop.load(new FileReader("conf/config.properties"));
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e){
	// e.printStackTrace();
	// }
	// }

	public static String getConfig(String key) {
		if (initialized) {
			return prop.getProperty(key);
		} else {
			init();
			return prop.getProperty(key);
		}

	}

	private static void init() {
		try {
			prop = new Properties();
			prop.load(new FileReader("src/main/java/mla.properties"));
			initialized = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
