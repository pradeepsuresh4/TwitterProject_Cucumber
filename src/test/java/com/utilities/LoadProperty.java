package com.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LoadProperty {

	public static String filePath = "src\\main\\resources\\X.properties";

	public static void LoadProperties() {
		// to read property from the property file

		Properties property = new Properties();

		try {
			FileReader file = new FileReader(filePath);
			try {
				property.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Constants.browser = property.getProperty("browser");
		Constants.APP_Url= property.getProperty("APP_URL");
		Constants.username = property.getProperty("username");
		Constants.password = property.getProperty("password");
		Constants.chrome_driver_location = property.getProperty("chrome_driver_location");
		Constants.edge_driver_location = property.getProperty("edge_driver_location");
		Constants.testData_path = property.getProperty("testdata_path");
		

	}

	public static void main(String[] args) {
		LoadProperties();
	}

}
