package aail.facebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import org.json.*;

public class Facebookthe{

/**
 * A simple Facebook4J client which
 * illustrates how to access group feeds / posts / comments.
 * 
 * @param args
 * @throws FacebookException 
 * @throws JSONException 
 * @throws IOException 
 */
	//static
public static void main(String[] args) throws FacebookException, JSONException, IOException {

    // Generate facebook instance.
    Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
    facebook.setOAuthAppId("1238270156199618", "177cef157d0c8c006d0067b49b4bde32");
   
    AccessToken accessTokenString = facebook.getOAuthAppAccessToken();
    
     facebook.setOAuthAccessToken(accessTokenString);
  
     StringBuilder sb = new StringBuilder();
     
     String z="";
     		String m ="AnushkaShetty/?fields=posts.limit(1).since(2015).until(now){id,message,name,type,picture,link,caption,description,icon,application,shares,updated_time,source,comments.limit(500).summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,comments{comment_count,comments{comment_count}}},place,object_id,privacy,status_type,created_time,story,parent_id,story_tags,full_picture,likes.limit(9999).summary(true){id,name,username}},id,hometown,website,about,location,birthday,name,tagged{message_tags},category,category_list,talking_about_count,likes";
            
     		RawAPIResponse res1 = facebook.callGetAPI(m);//"me/friends"
            
            //System.out.println(res);
           // JSONObject jsonObject = res.asJSONObject();
            JSONObject jsonObject55= res1.asJSONObject();
           
          //  System.out.println(jsonObject55); //609425942504811
            
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
            	//System.out.println("go to this link");
            	
            	URL oracle = new URL(s);
                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                            yc.getInputStream()));
                
              /*  String oldfile="";
                String newfile="";*/
                String inputLine;
                
                JSONObject obj = new JSONObject();
                JSONArray objarry=new JSONArray();
                JSONObject objl = new  JSONObject();
                while ((inputLine = in.readLine()) != null) 
                {
                    obj = new JSONObject(inputLine); 
                	
                   //objarry=new JSONArray(obj);
                    //objl = new JSONObject(objarry);  
                   // System.out.println(objl);
                }
               System.out.println(jsonObject55);
           //  z+=sb.append(obj);
             //sb.setLength(0);  
            // System.out.println(z);
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
            //System.out.println(z);
            //System.out.println(accessTokenString);
     
    }
}