package com.lixa.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lixa.bean.City;
import com.lixa.bean.Province;
import com.lixa.bean.Weather;
import com.lixa.dao.CityDao;
import com.lixa.dao.ProvinceDao;
import com.lixa.dao.WeatherDao;
import com.lixa.dao.impl.CityDaoImpl;
import com.lixa.dao.impl.ProvinceDaoImpl;
import com.lixa.dao.impl.WeatherDaoImpl;

//import com.sun.java.util.jar.pack.Attribute.Layout.Element;

/***
 * @author xuechong src http://eric-619.iteye.com/blog/692838 6/11/2010 16:58
 *         DomXMLString.java 概述：纯java方式访问远程WebService接口返回的xml格式的数据保存在本地
 */
public class DomXMLString {
	private static String SERVICES_HOST = "www.webxml.com.cn";
	// 远程WebService接口url
	private static String NETDATA_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getSupportProvince";
	private static String NETDATA_URL2 = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getSupportCity?byProvinceName=all";
	//private static String NETDATA_URL3 = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName?theCityName=";
	private static String NETDATA_URL3 = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityNamePro?theUserID=a75fc8da47b44b8d973c0f9173c999f5&theCityName=";
	// 访问远程WebService接口返回的xml格式的数据保存在本地的绝对路径
	private static String LOCAL_PC_SAVEFILE_URL = "E:/dataTest/netDataToLocalFile.xml";
	private static String LOCAL_PC_SAVEFILE_URL2 = "E:/dataTest/netDataToLocalFile2.xml";
	private static String LOCAL_PC_SAVEFILE_URL3 = "E:/dataTest/netDataToLocalFile3.xml";

	private DomXMLString() {
	}

	public static void main(String[] args) throws Exception {
		// Document document = getProvinceCode(NETDATA_URL);
		// Document document2 = getCityCode(NETDATA_URL2);
		
		// helloOK(document, LOCAL_PC_SAVEFILE_URL);
		// helloOK(document2, LOCAL_PC_SAVEFILE_URL2);
		
		// DomXMLString.getParserAuthor();
		// DomXMLString.getParserAuthor2();
        
         CityDao cityDao=new CityDaoImpl();
		
		for(int i=0;i<cityDao.getAllCity().size();i++){
			//System.out.println(cityDao.getAllCity().get(i).getName().substring(0, cityDao.getAllCity().get(i).getName().indexOf("(")));
			Document document3 = getWeatherCode(NETDATA_URL3+cityDao.getAllCity().get(i).getName().substring(0, cityDao.getAllCity().get(i).getName().indexOf("(")));
			helloOK(document3, LOCAL_PC_SAVEFILE_URL3);
			DomXMLString.getParserAuthor3();
		}

	}

	/* 返回一个Document对象 */
	public static Document getProvinceCode(String netXMLDataURL) {
		Document document = null;
		DocumentBuilderFactory documentBF = DocumentBuilderFactory
				.newInstance();
		documentBF.setNamespaceAware(true);
		try {
			DocumentBuilder documentB = documentBF.newDocumentBuilder();
			InputStream inputStream = getSoapInputStream(netXMLDataURL); // 具体webService相关
			document = documentB.parse(inputStream);
			inputStream.close();
		} catch (DOMException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return document;
	}

	/* 返回一个Document对象 */
	public static Document getCityCode(String netXMLDataURL) {
		Document document = null;
		DocumentBuilderFactory documentBF = DocumentBuilderFactory
				.newInstance();
		documentBF.setNamespaceAware(true);
		try {
			DocumentBuilder documentB = documentBF.newDocumentBuilder();
			InputStream inputStream = getSoapInputStream(netXMLDataURL); // 具体webService相关
			document = documentB.parse(inputStream);
			inputStream.close();
		} catch (DOMException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return document;
	}

	public static Document getWeatherCode(String netXMLDataURL) {
		
		Document document = null;
		DocumentBuilderFactory documentBF = DocumentBuilderFactory
				.newInstance();
		documentBF.setNamespaceAware(true);
		try {
			
			DocumentBuilder documentB = documentBF.newDocumentBuilder();
			InputStream inputStream = getSoapInputStream(netXMLDataURL); // 具体webService相关
			document = documentB.parse(inputStream);
			inputStream.close();
			
			
		} catch (DOMException e) {
			e.printStackTrace();
			return null;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return document;
	}

	/* 返回InputStream对象 */
	public static InputStream getSoapInputStream(String url) {
		InputStream inputStream = null;
		try {
			URL urlObj = new URL(url);
			URLConnection urlConn = urlObj.openConnection();
			urlConn.setRequestProperty("Host", SERVICES_HOST); // 具体webService相关
			urlConn.connect();
			inputStream = urlConn.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	/* 访问远程(WebService)xml数据后返回的xml格式字符串并生成为本地文件 */
	public static void helloOK(Document document, String savaFileURL) {
		TransformerFactory transF = TransformerFactory.newInstance();
		try {
			Transformer transformer = transF.newTransformer();
			DOMSource source = new DOMSource(document);
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "YES");
			PrintWriter pw = new PrintWriter(new FileOutputStream(savaFileURL));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("生成xml文件成功!");
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		}
	}

	private static ArrayList<Province> getParserAuthor() {
		ArrayList<Province> list = new ArrayList<Province>();
		// 获取DOM解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File(LOCAL_PC_SAVEFILE_URL));
			// 得到一个element根元素,获得根节点
			Element root = doc.getDocumentElement();
			System.out.println("根元素：" + root.getNodeName());

			// 子节点
			NodeList personNodes = root.getElementsByTagName("string");
			for (int i = 0; i < personNodes.getLength(); i++) {
				Element personElement = (Element) personNodes.item(i);
				Province publication = new Province();
				// NodeList childNodes = personElement.getChildNodes();
				// System.out.println("*****"+childNodes.getLength());

				// for (int j = 0; j < childNodes.getLength(); j++) {
				// if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
				// if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){

				/*
				 * if("authors".equals(childNodes.item(j).getNodeName())){
				 * publication
				 * .setAuthors(childNodes.item(j).getFirstChild().getNodeValue
				 * ()); }else if("id".equals(childNodes.item(j).getNodeName())){
				 * publication
				 * .setId(childNodes.item(j).getFirstChild().getNodeValue()); }
				 */
				publication.setName(personElement.getChildNodes().item(0)
						.getNodeValue());
				// publication.setName(childNodes.item(j).getFirstChild().getNodeValue());
				// }
				// }
				list.add(publication);
			}
			ProvinceDao dao = new ProvinceDaoImpl();
			for (Province publication2 : list) { // 为了查看数据是否正确，进行打印结果
				// System.out.println(publication2.getName());
				dao.addProvince(publication2);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return list;

	}

	private static ArrayList<City> getParserAuthor2() {
		ArrayList<City> list = new ArrayList<City>();
		// 获取DOM解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File(LOCAL_PC_SAVEFILE_URL2));
			// 得到一个element根元素,获得根节点
			Element root = doc.getDocumentElement();
			System.out.println("根元素：" + root.getNodeName());

			// 子节点
			NodeList personNodes = root.getElementsByTagName("string");
			for (int i = 0; i < personNodes.getLength(); i++) {
				Element personElement = (Element) personNodes.item(i);
				City publication = new City();
				// NodeList childNodes = personElement.getChildNodes();
				// System.out.println("*****"+childNodes.getLength());

				// for (int j = 0; j < childNodes.getLength(); j++) {
				// if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){
				// if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE){

				/*
				 * if("authors".equals(childNodes.item(j).getNodeName())){
				 * publication
				 * .setAuthors(childNodes.item(j).getFirstChild().getNodeValue
				 * ()); }else if("id".equals(childNodes.item(j).getNodeName())){
				 * publication
				 * .setId(childNodes.item(j).getFirstChild().getNodeValue()); }
				 */
				publication.setName(personElement.getChildNodes().item(0)
						.getNodeValue());
				// publication.setName(childNodes.item(j).getFirstChild().getNodeValue());
				// }
				// }
				list.add(publication);
			}
			CityDao dao = new CityDaoImpl();
			for (City publication2 : list) { // 为了查看数据是否正确，进行打印结果
				// System.out.println(publication2.getName());
				dao.addCity(publication2);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return list;

	}

	private static ArrayList<Weather> getParserAuthor3() {
		ArrayList<Weather> list = new ArrayList<Weather>();
		// 获取DOM解析器
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File(LOCAL_PC_SAVEFILE_URL3));
			// 得到一个element根元素,获得根节点
			Element root = doc.getDocumentElement();
			System.out.println("根元素：" + root.getNodeName());
			Weather publication = new Weather();
			// 子节点
			NodeList personNodes = root.getElementsByTagName("string");
			String name = ((Element) personNodes.item(1)).getChildNodes().item(0).getNodeValue();
			String descp = ((Element) personNodes.item(10)).getChildNodes().item(0).getNodeValue()+ ((Element) personNodes.item(11)).getChildNodes().item(0).getNodeValue();
			for (int i = 0; i < personNodes.getLength(); i++) {
				Element personElement = (Element) personNodes.item(i);
			}
			publication.setName(name);
			publication.setDescp(descp);
			WeatherDao dao = new WeatherDaoImpl();
			dao.addWeather(publication);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return list;

	}
}
