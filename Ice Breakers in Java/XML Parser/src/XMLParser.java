import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
//import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XMLParser {
	
	 public static String replaceMatching2(String input, String lowerBound, String upperBound, String ticker){
//	      String result = input.replaceAll("(.*?"+lowerBound + ")" + "(.*?)" + "(" + upperBound + ".*)", "$1$3");
	      String result = input.replaceAll("(.*?"+lowerBound + ")" + "(.*?)" + "(" + upperBound + ".*)", "$1"+ticker+"$3");
	      return result;
	 }

	public static void main (String[] args) throws IOException, SAXException, ParserConfigurationException{
		Scanner usrInput = new Scanner (System.in);
		System.out.println("Pleanse enter a ticker symbol");
		String ticker = usrInput.next();
		String website = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22)&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		String result = replaceMatching2(website, "%22", "%22",ticker);
		System.out.println(result);
			
		URL url = new URL(result); 
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

		String inputLine;
		String fileName = "test.xml";
		File file = new File(fileName);

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		while ((inputLine = br.readLine()) != null) {
			bw.write(inputLine);
		}
		bw.close();
		br.close();
		
//		FileReader fr = new FileReader("test.xml");
//		BufferedReader readData = new BufferedReader(fr);
		File fXmlFile = new File("test.xml");		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);		
		((Node) doc.getDocumentElement()).normalize();		 
	//	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	 
		NodeList nList = doc.getElementsByTagName("quote");
 
		for (int temp = 0; temp < nList.getLength(); temp++) {		 
			Node nNode = nList.item(temp);	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());	 
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode; 
				System.out.println("YearLow : " + eElement.getElementsByTagName("YearLow").item(0).getTextContent());
				System.out.println("YearHigh : " + eElement.getElementsByTagName("YearHigh").item(0).getTextContent());
				System.out.println("MarketCapitalization : " + eElement.getElementsByTagName("MarketCapitalization").item(0).getTextContent());
			}
		}
	
	}
}
