import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ApiExplorer {
   String answer="";
   LocalTime now2;
   public ApiExplorer() throws IOException, ParseException {
        
   }
   String getApi() throws IOException, ParseException {
	   //get weather api
	   
	   answer="";
	   now2=LocalTime.now();
	   //format to use in api
       Calendar calendar = new GregorianCalendar();
       SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
       String chkDate = SDF.format(calendar.getTime());
       
       //get current time info
        int hh=now2.getHour();//0-23
        int mm=now2.getMinute();//0-59
        int chk=0;
        //format to print time info
        String nowdate=new SimpleDateFormat("yyyy/MM/dd").format(calendar.getTime());
        String nowtime="";
        //format to print time info
        if(hh<10)
       	 if(mm<10)
       		 nowtime+="0"+hh+"시0"+mm+"분";
       	 else
       		 nowtime+="0"+hh+"시"+mm+"분";
        else
       	 if(mm<10)
       		 nowtime+=hh+"시0"+mm+"분";
       	 else
       		 nowtime+=hh+"시"+mm+"분";
        
        //format to use in api
        if(mm>=40)
        {
           //can use now hh
        }else {
           //can't use now hh
        	//becuase this api only can use 40~59minutes
           if(hh==0) {
        	   //if now hour is 0, use 23hour(yesterday)
              hh=23;
              //use yesterday info
              calendar.add(Calendar.DATE, -1);      
              chkDate = SDF.format(calendar.getTime());
           }
           else
        	   //use 1hour past time
              hh-=1;
        }
        //check minute is 0~9 or not
        if(hh<10)
           chk=1;
        //set format to use in api
       String formatedNow2;
       if(chk==0)
       {
          formatedNow2=""+hh+"00";
       }else {
          formatedNow2="0"+hh+"00";
       }
        
        //informations for use api
       String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String serviceKey = "P1Y090Bo1Jizqg%2FoJVCcMyFWbczurAVQNJVVYc0Vceqwaim2vBzb%2BkLg5uSJSh4WQqx%2FBAww%2FVCjHsgadOXq2Q%3D%3D";
        String nx = "62";   //lat gachon univ. = 62
        String ny = "124";   //lng gachon univ. = 124
        String base_Date = chkDate; //date today
        String base_time = formatedNow2;   //time 0000(0000,0100,0200,...)
        String type = "JSON";   //type xml or json
        String numOfRows = "153";   //lines in page
                 
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "="+serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(base_Date, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));   
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());
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
        //System.out.println(result);
        
        
        JSONParser parser = new JSONParser(); 
        JSONObject obj = (JSONObject) parser.parse(result); 
        JSONObject parse_response = (JSONObject) obj.get("response"); 
        JSONObject parse_body = (JSONObject) parse_response.get("body"); 
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        String category;
        JSONObject weather;
        //get category, key
        String day="";
        String time="";
        for(int i = 0 ; i < parse_item.size(); i++) {
           weather = (JSONObject) parse_item.get(i);
           
           Object baseDate = weather.get("baseDate");
           Object baseTime = weather.get("baseTime");
           //double obsrValue = Double.parseDouble(weather.get("obsrValue").toString());
           category = (String)weather.get("category"); 
           Object obsrValue = weather.get("obsrValue");
           
           if(!day.equals(baseDate.toString())) {
              day=baseDate.toString();
           }
           if(!time.equals(baseTime.toString())) {
              time=baseTime.toString();
           }
           if(i!=0)
              answer+=",";
           answer+=category;
           answer+=",";
           answer+=obsrValue;
           //we save informations in String answer with ','           
        }
        answer+=","+nowdate+","+nowtime;
        /* NONE(0), RAIN(1), RAIN+SNOW(2), SNOW(3), RAIN DROP(5), RAIN DROP+BLOWING SNOW(6), BLOWING SNOW(7)
         * PTY   Code      rain type
         * REH   %         humidity
         * RN1   1mm         rain drop
         * T1H   ℃         temperature
         * UUU   m/s         East/West wind speed
         * VEC   deg         wind direction
         * VVV   m/s         South/North wind speed
         * WSD   m/s         wind speed
         * 
         */
      return answer;
   }

}
