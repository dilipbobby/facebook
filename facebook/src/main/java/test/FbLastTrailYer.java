package test;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
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

public class FbLastTrailYer {

	public static void main(String[] args) throws IOException {

		Properties props = new Properties();

		props.put("metadata.broker.list", "localhost:9092");

		props.put("serializer.class", "kafka.serializer.StringEncoder");

		props.put("partitioner.class", "test.SimplePartitioner");

		/* props.put("message.max.bytes", "" + 1024 * 1024 * 40); */
		props.put("message.max.bytes", "1037626");

		props.put("request.required.acks", "1");

		props.put("retry.backoff.ms", "150");

		props.put("message.send.max.retries", "10");

		props.put("topic.metadata.refresh.interval.ms", "0");

		ProducerConfig config = new ProducerConfig(props);

		final Producer<String, String> producer = new Producer<String, String>(config);
		
		String output = null;
		//KeyedMessage<String, String> fbdata = new KeyedMessage<String, String>("fbyear123", output);
		// KeyedMessage<String, String> fbdata = new KeyedMessage<String,
		// String>("facebook",output);

		// Generate facebook instance.
		Facebook facebook = new FacebookFactory().getInstance();
		// Use default values for oauth app id.
		facebook.setOAuthAppId("1238270156199618", "177cef157d0c8c006d0067b49b4bde32");
        
		  AccessToken accessTokenString;
		try {
			accessTokenString = facebook.getOAuthAppAccessToken();
			facebook.setOAuthAccessToken(accessTokenString);
			/// BrandBazaarr,rakulpreetsinghs
			// AnushkaShetty//SachinTendulkar//narendramodi
			int cou=0;
		//while (true) {
			
				 File text = new File("/home/storm/Documents/Storm-Psql-facebook/test/input.csv");
			     
			        //Creating Scanner instnace to read File in Java
			        Scanner scnr = new Scanner(text);
			     
			        //Reading each line of file using Scanner class
			      try{
			        while(scnr.hasNextLine()){
			           
			        	String line = scnr.nextLine();
			            System.out.println( "Member"+line);
			            Runtime runtime = Runtime.getRuntime();
			            
			            //Print Maximum available memory
			            System.out.println("Max Memory:" + runtime.maxMemory());
			            
			        String fbquery =line+"?fields=id,name,birthday,hometown,website,about,phone,location,picture,category,category_list,talking_about_count,posts.limit(1){created_time,updated_time,id,name,message,description,type,picture,full_picture,link,icon,caption,application,source,object_id,status_type,story,place,parent_id,shares,story_tags,message_tags,privacy,comments.limit(500).summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,user_likes,from},likes.limit(1000).summary(true){id,name}}";
					RawAPIResponse rawresponse = facebook.callGetAPI(fbquery);
					JSONObject jsonobjmain = rawresponse.asJSONObject();

					int link = 0;
					int count = 1;
		     		int postlikecount = 1;
			    	int commentscount = 1;
					int Putlike=0;
			    	JSONArray commetsarry;
					JSONArray likesdata;
					//String postlike;
				//	String commentnext = null;

					JSONObject posts = jsonobjmain.getJSONObject("posts");

					JSONArray postdata = posts.getJSONArray("data");

					JSONObject number = postdata.getJSONObject(0);
					
				//	String message= number.getString("message");
					
					//System.out.println("FIRST POST MEASSAGE "+message);
					
					
				//**********************year getting***************				
					String postct=number.getString("created_time");
					SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
					Date postDate = sd.parse(postct);
					SimpleDateFormat df = new SimpleDateFormat("yyyy");
					String postyear=df.format(postDate);
					System.out.println(postyear);
					//Getting system date
					Date sysdate = new Date();
					SimpleDateFormat ddf = new SimpleDateFormat("yyyy");
					String sysyear=ddf.format(sysdate);
					System.out.println(sysyear);
				if(sysyear.equals(postyear)){
						System.out.println("EQUAL YEARS");
	//***********************************************************************	

					JSONObject likes0 = number.getJSONObject("likes");
					likesdata = likes0.getJSONArray("data");
					try {

						JSONObject paging0 = likes0.getJSONObject("paging");

						String postlike0 = paging0.getString("next");

						while (postlike0 != null) {
							System.out.println("************** Post One Likes *********************");
							postlikecount++;
						//	System.out.println("FIRST POST MEASSAGE "+message);
							System.out.println("POST LIKES LINK OF FIRST POST COUNT " + postlikecount);
							URL oraclepostlike = new URL(postlike0);
							URLConnection oraclepostlikeyc = oraclepostlike.openConnection();
							BufferedReader oraclepostlikeycin = new BufferedReader(
							new InputStreamReader(oraclepostlikeyc.getInputStream()));
							String postlikeinputLine0;
							JSONObject postlikeadd0 = new JSONObject();
							while ((postlikeinputLine0 = oraclepostlikeycin.readLine()) != null) {

								postlikeadd0 = new JSONObject(postlikeinputLine0);
								//System.out.println(postlikeadd0);
								JSONArray postaddlikes0 = postlikeadd0.getJSONArray("data");
							
								for (int like1 = 0; like1 < postaddlikes0.length(); like1++) {
									JSONObject addslikobj0 = postaddlikes0.getJSONObject(like1);
									likesdata.put(addslikobj0);
									Putlike++;
									System.out.println("THE count PUT liKES "+Putlike);
//System.out.println("No of likes "+likesdata);
								} // for close
								
								}//1st post-likes-while close
								
try {
								JSONObject likesnullmake0 = postlikeadd0.getJSONObject("paging");
								postlike0 = likesnullmake0.getString("next");
								System.out.println();
							} catch (Exception e) {
								postlike0 = null;
								System.out.println("there is no next in likes paging");
							}

							oraclepostlikeycin.close();
						}

					} // try close
					catch (Exception e) {

						System.out.println("there is no next in likespg0");
					}

					// comments
					JSONObject comments0 = number.getJSONObject("comments");
					commetsarry = comments0.getJSONArray("data");
				//	System.out.println("COMMENTS ARRAY DATA"+commetsarry);
					try {

						JSONObject commentspg0 = comments0.getJSONObject("paging");
						String commentnext0 = commentspg0.getString("next");
						while (commentnext0 != null)

						{
							System.out.println("*******************Post One Comments**********************");
						//	System.out.println("FIRST POST MEASSAGE "+message);
							URL oraclecomments = new URL(commentnext0);
							URLConnection commentsyc = oraclecomments.openConnection();
							BufferedReader commentsin = new BufferedReader(new InputStreamReader(commentsyc.getInputStream()));
							String commentsinputLine;
							JSONObject commentsobj = new JSONObject();
							while ((commentsinputLine = commentsin.readLine()) != null) {

								commentsobj = new JSONObject(commentsinputLine);

								JSONArray commentsadd = commentsobj.getJSONArray("data");
								for (int comentsinc = 0; comentsinc < commentsadd.length(); comentsinc++) {

									JSONObject commentsaddobj = commentsadd.getJSONObject(comentsinc);
									commetsarry.put(commentsaddobj);
									//System.out.println(""+commetsarry);
								}

							} // comments readline while close

							try {
								JSONObject commentssnullmake = commentsobj.getJSONObject("paging");
								commentnext0 = commentssnullmake.getString("next");
							} catch (Exception e) {
								commentnext0 = null;
								System.out.println("there is no comments next");
							}

							commentsin.close();
						}

					} // comments try close
					catch (Exception e) {
						//commentnext0 = null;
						System.out.println("there is no comments next");
					} // comments catch close
						// System.out.println(ouput);
					output = jsonobjmain.toString();
					if (postdata.length() == 1) {
						try {
							KeyedMessage<String, String> fbdata = new KeyedMessage<String, String>("fbyear1234",output);
							producer.send(fbdata);
							//System.out.println(output);
							System.out.println("*******  SENT THE FIRST POST ****** " + count);

						} catch (Exception e) {
						}
					}
					// ***************************************************************
                 System.out.println("####### Check for paging #########");
				 
                 JSONObject postpaging = posts.getJSONObject("paging");

					String postnext = postpaging.getString("next");

					while (postnext != null) {
					
						count++;

						URL post_oracle = new URL(postnext);

						URLConnection yc = post_oracle.openConnection();

						BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
						String post_inputLine;

						JSONObject post_obj = new JSONObject();

						while ((post_inputLine = in.readLine()) != null) {
							post_obj = new JSONObject(post_inputLine);

							JSONArray addposts = post_obj.getJSONArray("data");

							for (int i = 0; i < addposts.length(); i++) {
								
							//	System.out.println("before ADDING " + postdata);
							//	System.out.println(postdata.length());

								JSONObject addspostobj = addposts.getJSONObject(i);
								postdata.remove(postdata.length() - 1);
							//	String message2= addspostobj.getString("message");
								String postctn=addspostobj.getString("created_time");
								System.out.println(postctn+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
								
								
								SimpleDateFormat sdn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
								Date postDaten = sdn.parse(postctn);
								SimpleDateFormat dfn = new SimpleDateFormat("yyyy");
								String postyearn=dfn.format(postDaten);
								System.out.println(postyearn);
								//Getting system date
								Date sysdaten = new Date();
								SimpleDateFormat ddfn = new SimpleDateFormat("yyyy");
								String sysyearn=ddfn.format(sysdaten);
								System.out.println(sysyearn);
						if(sysyear.equals(postyearn)){
									System.out.println("EQUAL YEARS Rest posts");
									
							//	System.out.println(postdata.length());
								postdata.put(addspostobj);

								// *************************************************************
								// likes
								// ****************************************************************
								JSONObject likes = addspostobj.getJSONObject("likes");

								likesdata = likes.getJSONArray("data"); // likes of the
																		// a post object

								try {

									JSONObject paging = likes.getJSONObject("paging");

									String postlike = paging.getString("next");

									while (postlike != null) {
									System.out.println("*****************LIKES DATA AFTER FIRST POST******************");
									postlikecount++;
							//		System.out.println(" POST MEASSAGE "+message2);
										URL oraclepostlike = new URL(postlike);
										URLConnection oraclepostlikeyc = oraclepostlike.openConnection();
										BufferedReader oraclepostlikeycin = new BufferedReader(
												new InputStreamReader(oraclepostlikeyc.getInputStream()));
										String postlikeinputLine;
										JSONObject postlikeadd = new JSONObject();
										while ((postlikeinputLine = oraclepostlikeycin.readLine()) != null) {

											postlikeadd = new JSONObject(postlikeinputLine);
										//	System.out.println(postlikeadd);
											JSONArray postaddlikes = postlikeadd.getJSONArray("data");
											for (int like = 0; like < postaddlikes.length(); like++) {
												JSONObject addslikobj = postaddlikes.getJSONObject(like);
												likesdata.put(addslikobj);
												Putlike++;
												System.out.println("ADDED LIKES CHECK IN LIKESDATA COUNT"+Putlike);
												
											} // for close
										}

										try {
											JSONObject likesnullmake = postlikeadd.getJSONObject("paging");
											postlike = likesnullmake.getString("next");
											postlikecount++;
											
										} catch (Exception e) {
											postlike = null;
											System.out.println("there is no next in likes paging");
										}

										oraclepostlikeycin.close();
									}

									System.out.println("POST LIKES LINK COUNT " + postlikecount);

								} // try close
								catch (Exception e) {

									System.out.println("there is no next in likespg");
								}

								// **********************************************************
								// likes end
								// *******************************************************************

								// **********************************************************
								// comments
								// *******************************************************************

								// comments
								JSONObject comments = addspostobj.getJSONObject("comments");
								commetsarry = comments.getJSONArray("data");
								//System.out.println("THE COMMENTS ARRAY"+commetsarry);
								try {

									JSONObject commentspg = comments.getJSONObject("paging");
									String commentnext = commentspg.getString("next");
									while (commentnext != null)

									{
										System.out.println("*****************COMMENTS DATA AFTER FIRST POST************************");
										//System.out.println(" POST MEASSAGE "+message2);
										URL oraclecomments = new URL(commentnext);
										URLConnection commentsyc = oraclecomments.openConnection();
										BufferedReader commentsin = new BufferedReader(
												new InputStreamReader(commentsyc.getInputStream()));
										String commentsinputLine;
										JSONObject commentsobj = new JSONObject();
										while ((commentsinputLine = commentsin.readLine()) != null) {

											commentsobj = new JSONObject(commentsinputLine);

											JSONArray commentsadd = commentsobj.getJSONArray("data");
											for (int comentsinc = 0; comentsinc < commentsadd.length(); comentsinc++) {

												JSONObject commentsaddobj = commentsadd.getJSONObject(comentsinc);
												commetsarry.put(commentsaddobj);
												//System.out.println("need to work " + commetsarry);
											}

										} // comments readline while close

										try {
											JSONObject commentssnullmake = commentsobj.getJSONObject("paging");
											commentnext = commentssnullmake.getString("next");
											commentscount++;
											
										} catch (Exception e) {
											commentnext = null;
											System.out.println("there is no comments next");
										}
System.out.println(" #### Comments Next Link Count ######"+commentscount);
										commentsin.close();
									}

								} // comments try close
								catch (Exception e) {
								//	commentnext = null;
									System.out.println("there is no commentspg next");
								} // comments catch close

								// System.out.println("with appendeds" + jsonobjmain);
								output = jsonobjmain.toString();
							//	System.out.println("AFTER ADDING OUPUT" + output);
								
								if (postdata.length() == 1) {
									try {
										KeyedMessage<String, String> fbdata = new KeyedMessage<String, String>("fbyear1234",output);
										producer.send(fbdata);
										System.out.println("*******  SENT THE POST ****** " + count);

									} catch (Exception e) {
									}
								}
								
								}else{System.out.println("THIS IS A LAST YEAR POST NOT SEnding 2nd if");
								break;
								}

								
							} // for loop end

					}
						try {
							JSONObject jo = post_obj.getJSONObject("paging");

							postnext = jo.getString("next");
							System.out.println("try " + postnext);

							link++;
							System.out.println("THE link " + link);
						} catch (Exception e) {
							postnext = null;
							System.out.println("***** NO POST NEXT ****");
						}

						in.close();
					} // while close

				
					}//if date comparing close
					else
					{
						System.out.println("THIS IS A LAST YEAR POST NOT SENDING");
						
						 break;
						//System.exit(0);
				            }
				
					} // read line while close

			//} // read file while close

		} catch (Exception e) {

			System.out.println("This error is from second try   " + e);
			
		} // starting catch-close
			        cou++;
			        System.out.println("the count "+cou);
	//}//while close
			
			
			
		} catch (Exception e) {

			System.out.println("This error is from first try   " + e);
		} // starting catch-close

			
	}// main-close
}// class-close
