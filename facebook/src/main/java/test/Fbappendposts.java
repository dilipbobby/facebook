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
//import org.json.*;

public class Fbappendposts{


	//static
public static void main(String[] args) throws FacebookException, JSONException, IOException {

    // Generate facebook instance.
    Facebook facebook = new FacebookFactory().getInstance();
    // Use default values for oauth app id.
    facebook.setOAuthAppId("1238270156199618", "177cef157d0c8c006d0067b49b4bde32");
   
    AccessToken accessTokenString = facebook.getOAuthAppAccessToken();
    
     facebook.setOAuthAccessToken(accessTokenString);
  ///BrandBazaarr,rakulpreetsinghs
     //AnushkaShetty
     String m ="rakulpreetsinghs/?fields=posts.limit(1).since(2015).until(now){id,message,name,type,picture,link,caption,description,icon,application,shares,updated_time,source,comments.summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,comments{comment_count,comments{comment_count}}},place,object_id,privacy,status_type,created_time,story,parent_id,story_tags,full_picture,likes.summary(true){id,name,username}},id,hometown,website,about,location,birthday,name,tagged{message_tags},category,category_list,talking_about_count,likes";
            
     		RawAPIResponse res1 = facebook.callGetAPI(m);
            
           
            JSONObject jsonObject55= res1.asJSONObject();
         
            String postlike;
            String commentnext;
            JSONObject posts = jsonObject55.getJSONObject("posts");
            
            JSONArray post_data = posts.getJSONArray("data");
            
    	    JSONObject post_paging = posts.getJSONObject("paging");
            
    	    String post_next = post_paging.getString("next");
    	    
    	    int count = 1;
    	   int postlikecount=1;
    	   int commentscount =1;
    	   JSONArray commetsarry;
    	   JSONArray likesdata;
   	    
    	    while(post_next != null)
    	    {
    	    	count++;

            	URL post_oracle = new URL(post_next);
               
            	URLConnection yc = post_oracle.openConnection();
                
            	BufferedReader in = new BufferedReader(new InputStreamReader(
                                            yc.getInputStream()));
                 String post_inputLine;
                
                JSONObject post_obj = new JSONObject();
               
                while ((post_inputLine = in.readLine()) != null) 
                {
                    post_obj = new JSONObject(post_inputLine); 
                    
                   JSONArray addposts=post_obj.getJSONArray("data");
                   
                for (int i = 0; i < addposts.length(); i++)
                  {
              	    JSONObject addspostobj = addposts.getJSONObject(i);
              	       
              	    post_data.put(addspostobj);
              	    //adding up the postlikes
              	  System.out.println("addpost"+addspostobj);
              	
//************************************************************* likes ****************************************************************
              	  JSONObject likes = addspostobj.getJSONObject("likes");
              	  
              	   likesdata=likes.getJSONArray("data");  //likes of the a post object
                  
              	  try{
              	  
              		  JSONObject paging = likes.getJSONObject("paging");
                  
              		  postlike = paging.getString("next");
                  
              		  while(postlike != null)
                  {
              			 // System.out.println("*********************************8");
                	      postlikecount++;
                	  URL oraclepostlike = new URL(postlike);
                      URLConnection oraclepostlikeyc = oraclepostlike.openConnection();
                      BufferedReader oraclepostlikeycin = new BufferedReader(new InputStreamReader(oraclepostlikeyc.getInputStream()));
                       String postlikeinputLine;
                       JSONObject postlikeadd = new JSONObject();
                       while ((postlikeinputLine = oraclepostlikeycin.readLine()) != null){
                    	   
                    	   postlikeadd = new JSONObject(postlikeinputLine);
                    	   System.out.println(postlikeadd);
                    	   JSONArray postaddlikes=postlikeadd.getJSONArray("data");
                    	   for (int like=0; like < postaddlikes.length(); like++)
                    	   {
                    		   JSONObject addslikobj = postaddlikes.getJSONObject(like);
                               likesdata.put(addslikobj);
                              // System.out.println("ADDED LIKES CHECK IN LIKESDATA");
                              // System.out.println(likesdata);
                    	   }//for close
                       }
                                              
                       try
                       {
                           JSONObject likesnullmake= postlikeadd.getJSONObject("paging");
                    	   postlike = likesnullmake.getString("next");
                    	   System.out.println();
                       }
                       catch(Exception e)
                       {
                    	   postlike=null;
                    	   System.out.println("there is no next in likes paging");
                       }
                        
                       oraclepostlikeycin.close();
                       }
                  
                 
                  System.out.println("POST LIKEZS INCREMENT " +postlikecount);
                 
  }//try  close
                   catch(Exception e){
                               	   
                	   System.out.println("there is no next in likespg");
                  }
              	  
//********************************************************** likes end ********************************************************************

              	  
//********************************************************** comments *******************************************************************              	  
                	
              	  //comments 
              /*JSONObject comments = addspostobj.getJSONObject("comments");
              commetsarry=comments.getJSONArray("data");
              try{
              
            	  JSONObject commentspg=comments.getJSONObject("paging");
                  commentnext = commentspg.getString("next");
                  while(commentnext != null)
                  
                  {
                	System.out.println("*********************************8");
                  	commentscount++;
                  	URL oraclecomments = new URL(commentnext);
                  	URLConnection commentsyc = oraclecomments.openConnection();
                  	BufferedReader commentsin = new BufferedReader(new InputStreamReader(commentsyc.getInputStream()));
                  	String commentsinputLine;
                  	 JSONObject commentsobj = new JSONObject();
                  	while ((commentsinputLine = commentsin.readLine()) != null) {
                  		
                  		commentsobj = new JSONObject(commentsinputLine); 
                        
                        JSONArray commentsadd=commentsobj.getJSONArray("data");
                        for (int comentsinc = 0; comentsinc < commentsadd.length(); comentsinc++)
                        {
                    	    
                        	JSONObject commentsaddobj = commentsadd.getJSONObject(i);
                        	commetsarry.put(commentsaddobj);
                        	System.out.println(commetsarry);
                    	}
                  		
                  	}//comments readline while close
                  	
                  	 try
                     {
                         JSONObject commentssnullmake= commentsobj.getJSONObject("paging");
                         commentnext = commentssnullmake.getString("next");
                     }
                     catch(Exception e)
                     {
                  	   postlike=null;
                  	   System.out.println("there is no next in likes paging");
                     }
                      
                  	commentsin.close();
                     }
                  
                  
                  
              
              }//comments try close
              catch(Exception e){
            	  
            	  commentnext=null;
           	   System.out.println("there is no comments next");
              }//comments catch close
*/              	 	}
                  
                  System.out.println("with appendeds" +jsonObject55);
              	
                  BufferedWriter writer = null;
                       try
                       {
                           writer = new BufferedWriter( new FileWriter("/home/storm/Videos/posts.json"));
                           writer.write( jsonObject55.toString());
                           System.out.println("Done writing");

                       }
                       catch ( IOException e)
                       {
                    	   System.out.println("vanaja");
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
                        	   System.out.println("dilip");

                           }
                       }
           
    	    }
  try
               {
            	   JSONObject jo = post_obj.getJSONObject("paging");
            	   post_next = jo.getString("next");
               }
               catch(Exception e)
               {
            	   post_next=null;
            	   System.out.println("there is no post next");
               }
                
                in.close();
               }
        System.out.println(count);
        System.out.println(postlikecount);
        System.out.println(commentscount);
   
    }
}