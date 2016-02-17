package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ReadingFromFile {

  public static void main(String[] args) throws IOException, FacebookException {
    
	Scanner sc = new Scanner(new File("/home/storm/Desktop/test/input.csv"));
	 
    sc.useDelimiter(",");
   
    System.out.println(sc);
    while (sc.hasNext()) {
      String s = sc.next();
      System.out.println(s);
   // Generate facebook instance.
 Facebook facebook = new FacebookFactory().getInstance();
 // Use default values for oauth app id.
 facebook.setOAuthAppId("1238270156199618", "177cef157d0c8c006d0067b49b4bde32");

 AccessToken accessTokenString = facebook.getOAuthAppAccessToken();
 
  facebook.setOAuthAccessToken(accessTokenString);
///BrandBazaarr,rakulpreetsinghs
  //AnushkaShetty
  String fbquery =s+"/?fields=posts.limit(1).since(2015).until(now){id,message,name,type,picture,link,caption,description,icon,application,shares,updated_time,source,comments.summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,comments{comment_count,comments{comment_count}}},place,object_id,privacy,status_type,created_time,story,parent_id,story_tags,full_picture,likes.summary(true){id,name,username}},id,hometown,website,about,location,birthday,name,tagged{message_tags},category,category_list,talking_about_count,likes";
         try{
  		RawAPIResponse rawresponse = facebook.callGetAPI(fbquery);
  		JSONObject jsonobjmain= rawresponse.asJSONObject();
  		
      
         String postlike;
         String commentnext;
         JSONObject posts = jsonobjmain.getJSONObject("posts");
         
         JSONArray postdata = posts.getJSONArray("data");
         
 	    JSONObject postpaging = posts.getJSONObject("paging");
         
 	    String postnext = postpaging.getString("next");
 	    String output = null;
 	    output =jsonobjmain.toString();
			KeyedMessage<String, String> fbdata = new KeyedMessage<String, String>("facebook",output);
 	    
 	   int count = 1;
 	   int postlikecount=1;
 	   int commentscount =1;
 	   JSONArray commetsarry;
 	   JSONArray likesdata;
 	   
	    
 	    while(postnext != null)
 	    {
 	    	count++;

         	URL post_oracle = new URL(postnext);
            
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
           	       
           	    postdata.put(addspostobj);
           	  output =jsonobjmain.toString();
           	    //producer.send(fbdata);
           	    //adding up the postlikes
           	 // System.out.println("addpost"+addspostobj);
           	
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
                            output =jsonobjmain.toString();
                           // producer.send(fbdata);
                            System.out.println("Chnged to to string");
                           // System.out.println(likesdata);
                 	
                 	   }//for close
                 	   System.out.println("THE LIKE LOOP COUNT "+postlikecount);
                    }
                    
                    
                                           
                    try
                    {
                        JSONObject likesnullmake= postlikeadd.getJSONObject("paging");
                 	   postlike = likesnullmake.getString("next");
                 	
                 	   //System.out.println("sent likes");
                    }
                    catch(Exception e)
                    {
                 	   postlike=null;
                 	   System.out.println("there is no next in likes paging");
                    }
                     
                    oraclepostlikeycin.close();
                    }
               
              
               
              
}//try  close
                catch(Exception e){
                            	   
             	   System.out.println("there is no next in likespg");
               }
           	  
//********************************************************** likes end ********************************************************************

           	  
//********************************************************** comments *******************************************************************              	  
             	
           	  //comments 
         JSONObject comments = addspostobj.getJSONObject("comments");
           commetsarry=comments.getJSONArray("data");
           try{
           
         	  JSONObject commentspg=comments.getJSONObject("paging");
               commentnext = commentspg.getString("next");
               while(commentnext != null)
               
               {
             	System.out.println("************************************");
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
                     	output =jsonobjmain.toString();
                     	//producer.send(fbdata);
                     	System.out.println("changed to string data");
                     	//System.out.println(commetsarry);
                 	}
                     System.out.println("THE COMMENTS COUNT  "+commentscount);
               	}//comments readline while close
               	
               	 try
                  {
                      JSONObject commentssnullmake= commentsobj.getJSONObject("paging");
                      commentnext = commentssnullmake.getString("next");
                      //System.out.print("sending comments");
                      //producer.send(fbdata);
                  }
                  catch(Exception e)
                  {
                 	 commentnext=null;
               	   System.out.println("there is no comments next");
                  }
                   
               	commentsin.close();
                  }
               

           }//comments try close
           catch(Exception e){
         	  commentnext=null;
        	   System.out.println("there is no comments next");
           }//comments catch close
        	 	}//for loop end
               
              // System.out.println("with appendeds" +jsonobjmain);
              
           	
              
	try{
               
              // producer.send(fbdata);
               System.out.println("sent done");
              }catch(Exception e )
              {
             	 PrintStream out = new PrintStream(new FileOutputStream("/home/storm/Videos/erroroutput.txt"));
             	 System.setErr(out);
             	 System.out.println( "system exception returned to a file");
              }
               
               BufferedWriter writer = null;
                    try
                    {
                        writer = new BufferedWriter( new FileWriter("/home/storm/Videos/posts.json"));
                        writer.write( jsonobjmain.toString());
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
                    System.out.println("THE POST COUNT  "+count);
 	    }
try
            {
         	   JSONObject jo = post_obj.getJSONObject("paging");
         	   postnext = jo.getString("next");
         	   System.out.println("try "+postnext);
            }
            catch(Exception e)
            {
         	   postnext=null;
         	   System.out.println("there is no post next");
            }
             
             in.close();
            }
     
  
     

         }catch(Exception e){
         
         	System.out.println("THE EXCEPTION I CAUGHT  "+e);
         }    
      
      if (s.trim().isEmpty()) {
    	  System.out.println("Done the print ");
    	  continue;
        }
      System.out.println(s);
    }
    sc.close();
  }

}
