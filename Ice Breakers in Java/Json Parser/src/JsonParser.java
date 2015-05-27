
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.commons.io.IOUtils;




public class JsonParser {
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	static class Item {
	    String title;
	    String link;
	    String description;
	}

	static class Page {
	    String title;
	    String link;
	    String description;
	    String language;
	    List<Item> items;
	}
	private static final String filePath ="F:\\Workspace\\Lab14\\test.json";
	public static void main (String[] args) throws Exception {

		String website = "http://gdata.youtube.com/feeds/api/standardfeeds/most_popular?v=2&alt=json";

			
		URL url = new URL(website); 
		URLConnection conn = url.openConnection();
		BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));

		String inputLine;
		String fileName = "test.json";
		File file = new File(fileName);

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		while ((inputLine = br.readLine()) != null) {
			bw.write(inputLine);
		}
		bw.close();
		br.close();

		    try {
//	            String genreJson = IOUtils.toString(new URL(url1));
	            JSONObject json = new JSONObject(IOUtils.toString(new URL("http://gdata.youtube.com/feeds/api/standardfeeds/most_popular?v=2&alt=json"), Charset.forName("UTF-8")));
//	            JSONObject genreJsonObject = (JSONObject) JSONValue.parseWithException(json);
	            // get the title
	            JSONObject feed = (JSONObject)json.get("feed");
	            JSONArray entry = (JSONArray)feed.get("entry");
	            for(int i=0;i<10;i++){

	            	System.out.print(((JSONObject) (entry.getJSONObject(i).get("title"))).get("$t"));
	            	System.out.print(" has ");
	            	System.out.print(((JSONObject) (entry.getJSONObject(i).get("yt$statistics"))).get("viewCount"));
	            	System.out.print(" views. ");
	            	System.out.println("");
	            	
//	            	entry.getJSONObject(i).getJSONArray("yt$statistics").getJSONObject("viewCount"));
	            }
//	            System.out.println(entry.getJSONObject(0).get("title"));
	            


		} catch (JSONException e) {
		    e.printStackTrace();
		}
		
		
	}

}
