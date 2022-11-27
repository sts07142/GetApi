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
		// 홈페이지에서 받은 키
		String serviceKey = "P1Y090Bo1Jizqg%2FoJVCcMyFWbczurAVQNJVVYc0Vceqwaim2vBzb%2BkLg5uSJSh4WQqx%2FBAww%2FVCjHsgadOXq2Q%3D%3D";
		String nx = "62";	//위도
		String ny = "127";	//경도
		String base_Date = "20221126";
		String base_time = "2000";	//API 제공 시간을 입력하면 됨
		String type = "JSON";	//타입 xml, json 등등 ..
		String numOfRows = "153";	//한 페이지 결과 수 
		
		//전날 23시 부터 153개의 데이터를 조회하면 오늘과 내일의 날씨를 알 수 있음
		
		
		StringBuilder urlBuilder = new StringBuilder(apiUrl);
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
		urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
		urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_Date, "UTF-8")); /* 조회하고싶은 날짜*/
		urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
		urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));	/* 타입 */
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));	/* 한 페이지 결과 수 */
		
		/*
		 * GET방식으로 전송해서 파라미터 받아오기
		 */
		URL url = new URL(urlBuilder.toString());
		//어떻게 넘어가는지 확인하고 싶으면 아래 출력분 주석 해제
		//System.out.println(url);
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
		
		
		// Json parser를 만들어 만들어진 문자열 데이터를 객체화 
		JSONParser parser = new JSONParser(); 
		JSONObject obj = (JSONObject) parser.parse(result); 
		// response 키를 가지고 데이터를 파싱 
		JSONObject parse_response = (JSONObject) obj.get("response"); 
		// response 로 부터 body 찾기
		JSONObject parse_body = (JSONObject) parse_response.get("body"); 
		// body 로 부터 items 찾기 
		JSONObject parse_items = (JSONObject) parse_body.get("items");

		// items로 부터 itemlist 를 받기 
		JSONArray parse_item = (JSONArray) parse_items.get("item");
		String category;
		JSONObject weather; // parse_item은 배열형태이기 때문에 하나씩 데이터를 하나씩 가져올때 사용
		// 카테고리와 값만 받아오기
		String day="";
		String time="";
		for(int i = 0 ; i < parse_item.size(); i++) {
			weather = (JSONObject) parse_item.get(i);
			
			Object baseDate = weather.get("baseDate");
			Object baseTime = weather.get("baseTime");
			//double형으로 받고싶으면 아래내용 주석 해제
			//double obsrValue = Double.parseDouble(weather.get("obsrValue").toString());
			category = (String)weather.get("category"); 
			Object obsrValue = weather.get("obsrValue");
			// 출력
			if(!day.equals(baseDate.toString())) {
				day=baseDate.toString();
			}
			if(!time.equals(baseTime.toString())) {
				time=baseTime.toString();
				System.out.println(day+"  "+time);
			}
			System.out.print("/t baseDate : "+ baseDate);
			System.out.print(", baseTime : "+ baseTime);
			System.out.print(",category : "+ category);
			System.out.println(", obsrValue : "+ obsrValue);
			
		}

		/*
		 * 항목값	항목명		단위
		 * POP	강수확률	 	%
		 * PTY	강수형태		코드값
		 * R06	6시간 강수량	범주 (1 mm)
		 * REH	습도	 		%
		 * S06	6시간 신적설	범주(1 cm)
		 * SKY	하늘상태		코드값
		 * T3H	3시간 기온	 	℃
		 * TMN	아침 최저기온	℃
		 * TMX	낮 최고기온	 	℃
		 * UUU	풍속(동서성분)	m/s
		 * VVV	풍속(남북성분)	m/s
		 * WAV	파고	 		M
		 * VEC	풍향	 		m/s
		 * WSD	풍속			1

		 */
			
			
	}
}
