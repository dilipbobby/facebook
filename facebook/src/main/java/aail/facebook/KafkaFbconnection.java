package aail.facebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;


public class KafkaFbconnection{

/**
 * A simple Facebook4J client which
 * illustrates how to access group feeds / posts / comments.
 * 
 * @param args
 * @throws FacebookException 
 * @throws JSONException 
 * @throws IOException 
 */
public static void main(String[] args) throws FacebookException, JSONException, IOException {

    // Generate facebook instance.
    Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
    facebook.setOAuthAppId("1238270156199618", "177cef157d0c8c006d0067b49b4bde32");
    //facebook.setOAth
    // Get an access token from: 
    // https://developers.facebook.com/tools/explorer
    // Copy and paste it below.
    
    AccessToken accessTokenString = facebook.getOAuthAppAccessToken();
    
    //String accessTokenString = "CAACEdEose0cBANP13XHEu768lu27DSngjRGmmGs6IhSRxa0OkZC13reFcusuYWugMWRWeZAsZC33saowICIIKADXscwaHcdM1ZBcu0xAYAUuRZBEZAz5eZBZAqbhq4wOjAZC5UwzbVciSslwXqkhFNiyK9S6AfACFhMUXdZApky6AmWC5LcfgVWf0xx9SO8qZCnU7ZBLUBLEJ29PWwZDZD";
    //AccessToken at = new AccessToken(accessTokenString);
    // Set access token.
     facebook.setOAuthAccessToken(accessTokenString);
     Properties props = new Properties();

	     props.put("metadata.broker.list", "localhost:9092");

	    // props.put("serializer.class", "kafka.serializer.StringEncoder");

	     //props.put("partitioner.class", "SimplePartitioner");
	     props.put("serializer.class", "kafka.serializer.StringEncoder");
	     props.put("partitioner.class", "example.producer.SimplePartitioner");

	     props.put("request.required.acks", "1");

	     props.put("retry.backoff.ms", "150");

	     props.put("message.send.max.retries","10");

	     props.put("topic.metadata.refresh.interval.ms","0");
	     
	     

	     ProducerConfig config = new ProducerConfig(props); 
	     

	    final Producer<String, String> producer = new Producer<String, String>(config);

 
/*
     StringBuilder sb = new StringBuilder();
     
     String z="";*/
   //String n="609425942504811/?fields=posts{id,message,type,name,picture,link,caption,description,icon,application,shares,updated_time,source,comments{comment_count},place,object_id,privacy,status_type,created_time,story,parent_id,story_tags,full_picture},id,hometown,website,about,location,birthday,gender,locale";
     		String m ="AnushkaShetty/?fields=posts.limit(1).since(2015).until(now){id,message,name,type,picture,link,caption,description,icon,application,shares,updated_time,source,comments.limit(500).summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,comments{comment_count,comments{comment_count}}},place,object_id,privacy,status_type,created_time,story,parent_id,story_tags,full_picture,likes.limit(9999).summary(true){id,name,username}},id,hometown,website,about,location,birthday,name,tagged{message_tags},category,category_list,talking_about_count,likes";
     		//RawAPIResponse res = facebook.callGetAPI(n);//"me/friends"
            RawAPIResponse res1 = facebook.callGetAPI(m);//"me/friends"
            
            //System.out.println(res);
           // JSONObject jsonObject = res.asJSONObject();
            JSONObject jsonObject55= res1.asJSONObject();
            String output=jsonObject55.toString();
     
            //System.out.println(jsonObject);
            
            System.out.println(jsonObject55); //609425942504811
            
            //z=sb.append(jsonObject55);
            
            JSONObject posts = jsonObject55.getJSONObject("posts");
            
            JSONArray data = posts.getJSONArray("data");
            
            JSONObject number = data.getJSONObject(0);
            
            JSONObject likes = number.getJSONObject("likes");
            
            JSONObject paging = likes.getJSONObject("paging");
            
            String s = paging.getString("next");
            
            int count =1;
                                    
            while(s != null)
            {
            	count++;
            	System.out.println("go to this link");
            	
            	URL oracle = new URL(s);
                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                            yc.getInputStream()));
                String inputLine;
              /*  String oldfile="";
                String newfile="";*/
                
                JSONObject obj = new JSONObject();
                
                while ((inputLine = in.readLine()) != null) 
                {
                    obj = new JSONObject(inputLine);                  
                }
               
              //  z+=sb.append(obj);
                //System.out.println(obj);
                
                //System.out.println(count);
                
                JSONObject jo = obj.getJSONObject("paging");
                
               try
               {
            	   s = jo.getString("next");
               }
               catch(Exception e)
               {
            	   s=null;
            	   System.out.println("there is no next");
               }
                
                in.close();

                
            }
            System.out.println(count);
            KeyedMessage<String, String> fbdata = new KeyedMessage<String, String>("facebook",output);
            //System.out.println(z);
            //System.out.println(accessTokenString);
     
    }
}