package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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

public class fbComments{


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
            
     		RawAPIResponse res1 = facebook.callGetAPI(m);
          
     		JSONObject jsonObject55= res1.asJSONObject();
           
     		JSONObject posts = jsonObject55.getJSONObject("posts");
            
     		JSONArray data = posts.getJSONArray("data");
            
            JSONObject number = data.getJSONObject(0);
            
            JSONObject comments = number.getJSONObject("comments");
            
            JSONArray commetsarry=comments.getJSONArray("data");
            
            JSONObject commentspg=comments.getJSONObject("paging");
            
            String commentnext = commentspg.getString("next");
            
            int count =1;
                                    
            while(commentnext != null)
            {
            	count++;
            	
            	//System.out.println("go to this link");
            	
            	URL oracle = new URL(commentnext);
                URLConnection yc = oracle.openConnection();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                            yc.getInputStream()));
                 String commentsinputLine;
                
                JSONObject commentsobj = new JSONObject();
               
                while ((commentsinputLine = in.readLine()) != null) 
                {
                	commentsobj = new JSONObject(commentsinputLine); 
                  
                    JSONArray commentsadd=commentsobj.getJSONArray("data");
                 
                    for (int i = 0; i < commentsadd.length(); i++)
                  {
              	    
                    	JSONObject commentsaddobj = commentsadd.getJSONObject(i);
                    	commetsarry.put(commentsaddobj);
              	
              	}
                
         System.out.println("with appendeds" +jsonObject55);
                	
                BufferedWriter writer = null;
                     try
                     {
                         writer = new BufferedWriter( new FileWriter("/home/storm/Videos/test.json"));
                         writer.write( jsonObject55.toString());

                     }
                     catch ( IOException e)
                     {
                     }
                     finally
                     {
                         try
                         {
                             if ( writer != null)
                             writer.close( );
                         }
                         catch ( IOException e)
                         {
                         }
                     }
                	
                }
             
                JSONObject jo = commentsobj.getJSONObject("paging");
                
               try
               {
            	   commentnext = jo.getString("next");
               }
               catch(Exception e)
               {
            	   commentnext=null;
            	   System.out.println("there is no comment next");
               }
                
                in.close();
               }
            
           
           System.out.println(count);
   
    }
}