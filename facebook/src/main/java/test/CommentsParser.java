package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.JSONArray;
import org.json.JSONObject;

public class CommentsParser {
	
	public static void main(String [] args) {

        // The name of the file to open.
        String fileName = "/home/storm/Videos/post.json";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
             //***************************************comment parser *****************   

String postid=null; String facebookid=null; String pagename=""; String commentmessg=""; boolean can_remove=false; Integer commentlikecount=0; boolean userlikes=false; String commentmessid=null; long created_time=0;
            	try{
            	JSONObject mobj=new JSONObject(line);
                facebookid=mobj.getString("id");
               System.out.println(facebookid);
               
               JSONObject post=mobj.getJSONObject("posts");
                
               JSONArray data=post.getJSONArray("data");
              
                JSONObject dataobj=data.getJSONObject(0);
                postid=dataobj.getString("id");
                pagename=mobj.getString("name");
                System.out.println(pagename);
                System.out.println(postid);
                JSONObject comments=dataobj.getJSONObject("comments");
                //System.out.println(comments);
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
              try{  
              JSONObject commentsummary=comments.getJSONObject("summary");
              try{
              Integer totalcommentcount=commentsummary.getInt("total_count");
              }catch(Exception r){System.out.println("totalcomment not found" );}
              try{
              boolean cancomment=commentsummary.getBoolean("can_comment");
              }catch(Exception r ){System.out.println( "can comment not found" +r);}
              try{
              String commentorder=commentsummary.getString("order");}catch(Exception e){
            	  System.out.println("comment order not found"+e);
              }
              //System.out.println(commentorder);
              }catch(Exception r ){  System.out.println("commentsummary obj not found "+r);}
           try{  
           JSONArray commentsdata=comments.getJSONArray("data");
           for(int d=0;d<commentsdata.length();d++){
        	   
       
      JSONObject commentdataobj=commentsdata.getJSONObject(d);
       try{
       Integer comment_count=commentdataobj.getInt("comment_count");
       System.out.println(comment_count);
       //created_time  	 ,bo:can_remove,int like_count,bo:user_likes,can_like
       }catch(Exception e ){ System.out.println("comment_count string not found "+e);}
       try{
       String created_time1=commentdataobj.getString("created_time");
       java.util.Date date= formatter.parse(created_time1);
       
       long time = date.getTime();

     created_time = time;
       
       System.out.println(created_time);
       }catch(Exception e ){ System.out.println("created time not found");}
       
       try{
       can_remove=commentdataobj.getBoolean("can_remove");
       System.out.println(can_remove);
       }catch(Exception e){System.out.println("can remove boolen not found "+e);}
       try{
      commentlikecount=commentdataobj.getInt("like_count");
       }catch(Exception e){System.out.println("comment like count not found"+e);}
      userlikes=commentdataobj.getBoolean("user_likes");
       System.out.println(userlikes);
       try{
       boolean canlike=commentdataobj.getBoolean("can_like");
       System.out.println(canlike);
       }catch(Exception e){System.out.println("can like String nort found"+e); }
       
      try{ 
    	  commentmessg=commentdataobj.getString("message");
    	  System.out.println(commentmessg);
      }catch(Exception e){ System.out.println("comment message string not found"+e);}
      try{
    	 commentmessid=commentdataobj.getString("id");
    	  System.out.println(commentmessid);
    	  
      }catch(Exception e){
    	  System.out.println("comment mess id string not found "+e);
      }
        	  
       try{
       JSONObject commentfromobj=commentdataobj.getJSONObject("from");
       System.out.println(commentfromobj);
       try{
    	   String commenteduserid=commentfromobj.getString("id");
    	   System.out.println(commenteduserid);
       }catch(Exception e){System.out.println("commenteduser is string not found"+e);}
       try{
    	   String commenteduserfbname=commentfromobj.getString("name");
    	   System.out.println(commenteduserfbname);
       }catch(Exception e){System.out.println("commented fbuserid string not found"+e);}
      
       }catch(Exception e){
    	   System.out.println("comments from obj not found");
       }
          
//	System.out.println(commentmessg);
           }//for close
           
           }catch(Exception e){
        	   System.out.println("comments data array not found"+e);
           }	}catch(Exception e){
            		
            		System.out.println("no value obj found"+e);
            	}//try-catch of main valueobje
            
            
            
            
            
            
            
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
}


