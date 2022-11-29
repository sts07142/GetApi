import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ApiExplorer {
	public static void main(String[] args) throws IOException, ParseException {
		
		String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
		//API key
		String serviceKey = "P1Y090Bo1Jizqg%2FoJVCcMyFWbczurAVQNJVVYc0Vceqwaim2vBzb%2BkLg5uSJSh4WQqx%2FBAww%2FVCjHsgadOXq2Q%3D%3D";
		String nx = "62";	//lat
		String ny = "127";	//lng
		String base_Date = "20221126"; //date yesterday or today
		String base_time = "2000";	//time to find
		String type = "JSON";	//type xml or json
		String numOfRows = "153";	//lines in page
				
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_Date, "UTF-8")); 
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); 
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));	
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		String result= sb.toString();
		System.out.println(result);
		
		
		// Json parser
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(result); 
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 		 
		JSONObject parse_items = (JSONObject) parse_body.get("items");
		JSONArray parse_item = (JSONArray) parse_items.get("item");
		String category;
		JSONObject weather;
		String day="";
		String time="";
		for(int i = 0 ; i < parse_item.size(); i++) {
			weather = (JSONObject) parse_item.get(i);
			
			Object baseDate = weather.get("baseDate");
			Object baseTime = weather.get("baseTime");
			category = (String)weather.get("category"); 
			Object obsrValue = weather.get("obsrValue");
			
			if(!day.equals(baseDate.toString())) {
				day=baseDate.toString();
			}
			if(!time.equals(baseTime.toString())) {
				time=baseTime.toString();
				System.out.println(day+"  "+time);
			}
			System.out.print("\tcategory : "+ category);
			System.out.println(", obsrValue : "+ obsrValue);
			
		}

		/*
		 * POP	Rain %	 			%
		 * PTY	Rain type			int
		 * R06	6hours rain			(1 mm)
		 * REH	water %	 			%
		 * S06	6hours snow			(1 cm)
		 * SKY	sky type			int
		 * T3H	3hours ℃			℃
		 * TMN	morning min℃		℃
		 * TMX	day max℃ 			℃
		 * UUU	wind(East/West)		m/s
		 * VVV	wind(South/North)	m/s
		 * WAV	wave height			M
		 * VEC	wind direction		m/s
		 * WSD	wind speed			1
		 */
			
			
	}
}
