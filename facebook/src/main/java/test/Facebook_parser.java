package test;

//3 - bigints; 3 - timestamps; 1 - date; 1 - text; share,like,comment counts are not null and comment_like_count should not be null
//bigint as long ; timestamp over; text as character varying ; date over


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.auth.AccessToken;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONObject;


public class Facebook_parser{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//category
	




public static void main(String args[]) throws FacebookException {
	
	String cat_id1;long cat_id;String cat_list_name;

	String id;String name;String birthday1;long birthday;String hometown;String website;String about;int talking_about_count;String category;int page_totoal_likes;

//location
String country = null;String city;String latitude;String longitude;String state;String street;String zipcode;

//posts_data_objs
String postcreatedat1;Timestamp postcreatedat;String updated_time1;Timestamp updated_time;String postid;String post_name;String postmessage;String postdescription;String type;String postpicture;String full_picture;String link;String icon;String caption;String postsource;
String object_id;String statustype;String poststory;String postparentid;

//application
String application_link;String application_name;String application_id;
//share
int post_share_count;

//story_tags
int storyoffset;String storyname;int storylength;String storyid1;long storyid;String storytype;

//message_tags
int msg_tag_offset;String msg_tag_name;int msg_tag_length;String msg_tag_id1;long msg_tag_id;String msg_tag_type;

//privacy
String allow;String deny;String privacydescription;String value;String friends;

//COMMENTS
//summary, data, paging
String can_comment;int total_count;String order;int comment_count;boolean can_remove;int like_count;boolean can_like;boolean user_likes;String dataobj_id;String message;String fb_comment_name;String fb_comment_id;
String before;String after;String created_time1;Timestamp created_time;

//LIKES - summary, data, paging
String likhaslike;String likcanlike;String liktotalcount;String likedid;String likedname;
String paging_next;String cursors_before;String cursors_after;

//picture in mainobject
String picture_url;

//converting into timestamp
DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

//converting into data
	SimpleDateFormat date_formatter = new SimpleDateFormat("MM/dd/yyyy");

	 Facebook facebook = new FacebookFactory().getInstance();
	    // Use default values for oauth app id.
	    facebook.setOAuthAppId("1238270156199618", "177cef157d0c8c006d0067b49b4bde32");
	   
	    AccessToken accessTokenString = facebook.getOAuthAppAccessToken();
	    
	     facebook.setOAuthAccessToken(accessTokenString);
	  ///BrandBazaarr,rakulpreetsinghs
	     //AnushkaShetty
	     String fbquey ="brandbazaarr/?fields=id,name,birthday,hometown,website,about,location,picture,category,category_list,talking_about_count,posts.limit(1){created_time,updated_time,id,name,message,description,type,picture,full_picture,link,icon,caption,application,source,object_id,status_type,story,place,parent_id,shares,story_tags,message_tags,privacy,comments.summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,user_likes,from},likes.summary(true){id,name}}";
	            
	     		RawAPIResponse rawresponse = facebook.callGetAPI(fbquey);
	     		JSONObject mainobject= rawresponse.asJSONObject();
	     		System.out.println("JSON RESPONSE  "+mainobject);
	         
	try{
		

   //page details with in the mainjson
	
		id = mainobject.getString("id");
		
		try{
		name = mainobject.getString("name");
		}catch(Exception e){name = null;}
		

		try {
			birthday1 = mainobject.getString("birthday");
			
			//birthday = date_formatter.parse(birthday1);
			
			birthday = Long.parseLong(birthday1);
			
			//birthday = date_formatter.format(date);

		} catch (Exception e) {
			//birthday = date_formatter.parse(null);
		}

	  
		try {
			hometown = mainobject.getString("hometown");
			}catch(Exception e){hometown="";}

	try {
       website = mainobject.getString("website");

   }catch(Exception e){website="";}

    try{
    	about = mainobject.getString("about");

	}catch(Exception e){about="";}
    
    try{
    
    talking_about_count = mainobject.getInt("talking_about_count");
    
    }catch(Exception e){}
    
    
    try{
		 category = mainobject.getString("category");

	}catch(Exception e){category="";}     
   

    try {

   	 page_totoal_likes = mainobject.getInt("likes");

    }catch(Exception e){
   	 //page_totoal_likes="";
    }


	try{
	   	JSONArray category_list = mainobject.getJSONArray("category_list");
	
	   	for(int cat_list = 0;cat_list<category_list.length();cat_list++)
	   	{
	
	   		JSONObject cat_obj = category_list.getJSONObject(cat_list);
	
	   	 try{ 
			 	cat_id1 = cat_obj.getString("id");
		   	 
		   	 	cat_id = Long.parseLong(cat_id1);
	
		   	 	//}catch(Exception e){/*cat_id=-00;*/}
	
		try{
			cat_list_name = cat_obj.getString("name");
	
		}catch(Exception e){cat_list_name="";}
	
	   	}catch(Exception e){}
	   	 
	   	}
	   	
	}catch(Exception e){}
	   	 

    
   //location details of the page
	try{
   JSONObject location = mainobject.getJSONObject("location");
   try {
    country = location.getString("country");
    
   }catch(Exception e){country="";}
   
   try {
    city = location.getString("city");
   // System.out.println("Location "+city);
   }catch(Exception e){city="";}
   
   try {
    latitude=location.getString("latitude");
   // System.out.println("Location "+latitude);
   }catch(Exception e){latitude="";}
   
   try {
    longitude=location.getString("longitude");
    //System.out.println("Location "+longitude);
   }catch(Exception e){longitude="";}
   
   try {
    state=location.getString("state");
    //System.out.println("Location "+state);
   }catch(Exception e){state="";}
   
   try {
    street=location.getString("street");
    //System.out.println("Location "+street);
   }catch(Exception e){street="";}
   
   try {
    zipcode=location.getString("zipcode");
    //System.out.println("Location "+zipcode);
   }catch(Exception e){zipcode="";}
   
	}catch(Exception e){}
   
   
   //picture in mainobject
  try{ 
 	 JSONObject picture = mainobject.getJSONObject("picture");

 	 JSONObject picture_data = picture.getJSONObject("data");

 	 picture_url = picture_data.getString("url");
 	 
  }catch(Exception e){}
   
	
   try{
	       JSONObject posts = mainobject.getJSONObject("posts");
	      
	       JSONArray data = posts.getJSONArray("data");
	       
	       for(int i=0;i<data.length();i++){
	       
	       JSONObject dataobjs = data.getJSONObject(i);
	       
	       postid=dataobjs.getString("id");
	       
	       try{
	      	 postcreatedat1=dataobjs.getString("created_time");
	      	 			
			 java.util.Date d = formatter.parse(postcreatedat1);
					
			 long time = d.getTime();
			
			 postcreatedat = new Timestamp(time);
			 
	       }catch(Exception e){}
	       
	      	try{	
			updated_time1 = dataobjs.getString("updated_time");
			
			java.util.Date d = formatter.parse(updated_time1);
			
			 long time = d.getTime();
			
			 updated_time = new Timestamp(time);
			
	      	}catch(Exception e){}
	
	
	       try {
	           post_name=dataobjs.getString("name");
	       }catch(Exception e){post_name="";}
	       
	       try {
	       postmessage=dataobjs.getString("message");
	       }catch(Exception e){postmessage="";}

	       try {
	           postdescription=dataobjs.getString("description");
	
	       }catch(Exception e){postdescription="";}
	
	       try{
	       type=dataobjs.getString("type");
	       }catch(Exception e){type="";}
	
	       try {
	       postpicture=dataobjs.getString("picture");
	       }catch(Exception e){postpicture="";}
	
	       try {
	      	 full_picture = dataobjs.getString("full_picture");
	       }catch(Exception e){full_picture="";}
	       
	       try {
	      	 link = dataobjs.getString("link");
	       }catch(Exception e){link="";}
	
	       try {
	      	 icon = dataobjs.getString("icon");
	       }catch(Exception e){icon="";}
	       
	       try {
	           caption = dataobjs.getString("caption");
	
	       }catch(Exception e){caption="";}
	       
	     
	      try {
	          postsource=dataobjs.getString("source");
	      }catch(Exception e){postsource="";}
	
	      try {
	      	object_id = dataobjs.getString("object_id");
	      }catch(Exception e){object_id="";}
	      
	      try{
	       statustype=dataobjs.getString("status_type");                
	      }catch(Exception e){statustype="";}

	       try{
	      	 poststory=dataobjs.getString("story");
	       }catch(Exception e){poststory="";}
	             
	       try {
	           postparentid=dataobjs.getString("parent_id");
	       }catch(Exception e){postparentid="";}
	       
	       
		   	//application in dataobjs
		    try{
		     	JSONObject application = dataobjs.getJSONObject("application");
		   	try {
		   		application_link=application.getString("link");
		   	}catch(Exception e){application_link="";}
		   	
		   	try {
		   		application_name=application.getString("name");
		   	}catch(Exception e){application_name="";}
		   	
		   	try {
		   		application_id=application.getString("id");
		     	}catch(Exception e){application_id="";}
		    
		    } catch(Exception e){}
		    
	     
	       // shares object in dataobjs
	    	try {
	    	JSONObject post_shares = dataobjs.getJSONObject("shares");
	    	
	    	post_share_count = post_shares.getInt("count");
	    	
	    	}catch(Exception e){}
	     
	      //story_tags
	      
	      try{
	      	
	      JSONArray storytags=dataobjs.getJSONArray("story_tags");
	
	      for(int story=0;story<storytags.length();story++)
	      {
	      	JSONObject storydata=storytags.getJSONObject(story);
	      	
	      	 
  		 storyid1 = storydata.getString("id");
  		 
  		 storyid = Long.parseLong(storyid1);
	
	      	try{
	      		
	      	 storyoffset=storydata.getInt("offset");
	      	 
	      	}catch(Exception e ){}
	      	          	
	       	try{
	
	       	 storyname=storydata.getString("name");
	       	
	       	}catch(Exception e ){storyname="";}    
	
	       	try {
	       		
	      	 storylength=storydata.getInt("length");
	      	 
	       	}catch(Exception e){}
	
	      	 	
	      	 try {
	      	 storytype=storydata.getString("type");
	      	 	}catch(Exception e){storytype="";}
	      	        	            	
	      } //story for close
	
	      }
			catch(Exception e ){} //storytags try/catch close
	      
	
			//Message_tags in dataobjs
	
	      try{
			JSONArray message_tags = dataobjs.getJSONArray("message_tags");
	
			
			for(int msg_obj = 0;msg_obj < message_tags.length();msg_obj++)
			{
	
				JSONObject msg_tags_data = message_tags.getJSONObject(msg_obj);
				
				msg_tag_id1 = msg_tags_data.getString("id");
			
				msg_tag_id = Long.parseLong(msg_tag_id1);
		
				try {
				
					msg_tag_offset = msg_tags_data.getInt("offset");
				
					}catch(Exception e){}
					
					try {
				
						msg_tag_name = msg_tags_data.getString("name");
				
					}catch(Exception e){msg_tag_name="";}
					
					try {
						
						msg_tag_length = msg_tags_data.getInt("length");
						
					}catch(Exception e){}
									
					try {
				msg_tag_type = msg_tags_data.getString("page");
					}catch(Exception e){msg_tag_type="";}
			}
			
	      }catch(Exception e){}
	
	
	      //privacy object in dataobjs
	      
			try {
	      JSONObject privacy=dataobjs.getJSONObject("privacy");
	      	try{
	      	 allow=privacy.getString("allow");
	      	}catch(Exception e){allow="";}
	      	
	      	try {
	      	 deny=privacy.getString("deny");
	      	}catch(Exception e){deny="";}
	      	
	      	try {
	      	 privacydescription=privacy.getString("description");
	      	}catch(Exception e){privacydescription="";}
	      	
	      	try {
	      	 value=privacy.getString("value");
	      	}catch(Exception e){value="";}
	      	
	      	try {
	      	 friends=privacy.getString("friends");
	      	}catch(Exception e){friends="";}
	      	
	        
			}catch(Exception e){}
	      
	            
	       
	       //comments object
			try{
		       JSONObject comments=dataobjs.getJSONObject("comments");
		       
		       //summary field in comments
		       try {
		       JSONObject comments_summary=comments.getJSONObject("summary");
		      
		        can_comment=comments_summary.getString("can_comment");
		                   
		        total_count=comments_summary.getInt("total_count");
		                   
		        order=comments_summary.getString("order");
		       
		       }catch(Exception e){}
		       
		       
		       //data field in comments
		       JSONArray comments_data=comments.getJSONArray("data");
		       
		       for(int comm_data=0;comm_data<comments_data.length();comm_data++)
		       {
		       	JSONObject data_obj=comments_data.getJSONObject(comm_data);
		       	
		       	try{
			       	 dataobj_id=data_obj.getString("id");
			       	 
			       	JSONObject from = data_obj.getJSONObject("from");
					
					try {
						
						fb_comment_id = from.getString("id");
						
						fb_comment_name = from.getString("name");
					
					}catch(Exception e){
					
						fb_comment_name="";
					}	
					
					try{
			       	 comment_count=data_obj.getInt("comment_count");
					}catch(Exception e){}
			                  	
			       	try{	
				       	 created_time1=data_obj.getString("created_time");
						
						java.util.Date d = formatter.parse(created_time1);
						
						 long time = d.getTime();
						
						 created_time = new Timestamp(time);
						
				      	}catch(Exception e){}
			       	
			        try{           	
			       	 can_remove=data_obj.getBoolean("can_remove");
			       	 System.out.println("boolean comment like "+can_remove);
			      	}catch(Exception e){}

			       	try{
			       	 like_count=data_obj.getInt("like_count");
			      	}catch(Exception e){}

			       	 
			       	 try {
			       		 
			       		 user_likes = data_obj.getBoolean("user_likes");
			       		 
			       	 }catch(Exception e){user_likes=false;}
			       	 
			       	 try{
			       	 can_like=data_obj.getBoolean("can_like");  
				      	}catch(Exception e){}

			       	try{		                   	
			       	 message=data_obj.getString("message");
			      	}catch(Exception e){}

			  
			       }catch(Exception e){} // if comment have no id
		       	
		       } // for loop
		       
			       //paging field in comments object
		       try{
			           JSONObject comm_paging=comments.getJSONObject("paging");
			
			           JSONObject paging_cursors=comm_paging.getJSONObject("cursors");
			           
			            before=paging_cursors.getString("before");
			           	
			            after=paging_cursors.getString("after");
			            
		       }catch(Exception e){} // paging try-catch
		       
			}catch(Exception e){} // if no comment try-catch


			
	        //like objects of main dataobjs
			
	       try{
	       JSONObject likes = dataobjs.getJSONObject("likes");
	
	       	JSONObject likesummary=likes.getJSONObject("summary");
	       	
	       	try{
	       	 likhaslike=likesummary.getString("has_liked");
	
	       	 likcanlike=likesummary.getString("can_like");
	
	       	 liktotalcount=likesummary.getString("total_count"); 
	       	 
	       	}catch(Exception e){} 
	       	
	       
	       JSONArray likedata=likes.getJSONArray("data");
	
	       for(int dat=0;dat<likedata.length();dat++){         
	      	 
	       	JSONObject likesdata=likedata.getJSONObject(dat);
	
	       	 likedid=likesdata.getString("id");
	
	       	 likedname=likesdata.getString("name");
	       	
	       }//data for close
	          
	       //paging object in likes field
	       try{
	       JSONObject paging = likes.getJSONObject("paging");
	       
	       try{
	        paging_next = paging.getString("next");
	      	}catch(Exception e){}

	       try{
	       JSONObject cursors = paging.getJSONObject("cursors");
	
	        cursors_before = cursors.getString("before");
	
	        cursors_after = cursors.getString("after");
	      	}catch(Exception e){}
	       
	      }catch(Exception e){}// if no paging

	       
	    }catch(Exception e){} // if no likes
	       
	  }	 // post for loop 	             
	   	
 }catch(Exception e){} // if no post
		
}catch(Exception e){} //UTF try/catch close
	System.out.println("Location "+country);


		} // getOutputFields() method close



}//class_close



