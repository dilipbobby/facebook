package aail.facebook;

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

public class FaceBookLastTrail {

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
			while (true) {
				// File text = new File("/home/storm/Desktop/test/input.csv");
				// Creating Scanner instnace to read File in Java
				// Scanner scnr = new Scanner(text);
				Scanner scnr = new Scanner(new File("/home/storm/Videos/1.json"));
				// Reading each line of file using Scanner class
				int lineNumber = 1;
				// scnr.useDelimiter(",");
				while (scnr.hasNext()) {
					// String line = scnr.nextLine();
					String line = scnr.next();
					//System.out.println("line " + lineNumber + " :" + line);
					
					String fbquery = "brandbazaarr/?fields=id,name,birthday,hometown,website,about,phone,location,picture,category,category_list,talking_about_count,posts.limit(1){created_time,updated_time,id,name,message,description,type,picture,full_picture,link,icon,caption,application,source,object_id,status_type,story,place,parent_id,shares,story_tags,message_tags,privacy,comments.summary(true){comment_count,message,can_remove,id,created_time,can_like,like_count,user_likes,from},likes.summary(true){id,name}}";
					try {
						RawAPIResponse rawresponse = facebook.callGetAPI(fbquery);
						JSONObject jsonobjmain = rawresponse.asJSONObject();

						output = jsonobjmain.toString();

						String postlike;
						String commentnext;
						JSONObject posts = jsonobjmain.getJSONObject("posts");

						JSONArray postdata = posts.getJSONArray("data");

						JSONObject postpaging = posts.getJSONObject("paging");

						String postnext = postpaging.getString("next");

						int count = 1;
						// int postlikecount=1;
						// int commentscount =1;
						JSONArray commetsarry;
						JSONArray likesdata;

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
									JSONObject addspostobj = addposts.getJSONObject(i);

									
									System.out.println("before ADDING " + postdata);
									System.out.println(postdata.length());
									postdata.remove(postdata.length() - 1);
									System.out.println(postdata.length());
									postdata.put(addspostobj);
									
								/*	postdata.put(addspostobj);
									output = jsonobjmain.toString();*/
									// adding up the postlikes
									System.out.println("ADDED POSTS CHANGED TO-STRING");

									// *************************************************************
									// likes
									// ****************************************************************
									JSONObject likes = addspostobj.getJSONObject("likes");

									likesdata = likes.getJSONArray("data"); // likes
																			// of
																			// the
																			// a
																			// post
																			// object

									try {

										JSONObject paging = likes.getJSONObject("paging");

										postlike = paging.getString("next");
										int postlikecount = 1;
										while (postlike != null) {

											// System.out.println("*********************************8");
											postlikecount++;
											URL oraclepostlike = new URL(postlike);
											URLConnection oraclepostlikeyc = oraclepostlike.openConnection();
											BufferedReader oraclepostlikeycin = new BufferedReader(
													new InputStreamReader(oraclepostlikeyc.getInputStream()));
											String postlikeinputLine;
											JSONObject postlikeadd = new JSONObject();
											while ((postlikeinputLine = oraclepostlikeycin.readLine()) != null) {

												postlikeadd = new JSONObject(postlikeinputLine);
												// System.out.println(postlikeadd);
												JSONArray postaddlikes = postlikeadd.getJSONArray("data");
												for (int like = 0; like < postaddlikes.length(); like++) {
													JSONObject addslikobj = postaddlikes.getJSONObject(like);
													likesdata.put(addslikobj);
													output = jsonobjmain.toString();
													System.out.println("ADDED LIKES CHANGED TO STRING");
													// System.out.println(likesdata);
												} // for close
											}

											try {
												JSONObject likesnullmake = postlikeadd.getJSONObject("paging");
												postlike = likesnullmake.getString("next");
												System.out.println("POST LIKES ENTERED LOOP COUNT" + postlikecount);

											} catch (Exception e) {
												postlike = null;
												System.out.println("there is no next in likes paging");
											}

											oraclepostlikeycin.close();
										}

										// System.out.println("POST LIKEZS
										// INCREMENT " +postlikecount);

									} // try close
									catch (Exception e) {

										System.out.println("there is no next in likespg");
									}

									// **********************************************************
									// likes end
									// ********************************************************************

									// **********************************************************
									// comments
									// *******************************************************************

									// comments
									JSONObject comments = addspostobj.getJSONObject("comments");
									commetsarry = comments.getJSONArray("data");
									System.out.println("COMMENTS DATA "+commetsarry);
									try {

										JSONObject commentspg = comments.getJSONObject("paging");
										commentnext = commentspg.getString("next");
										int commentscount = 1;
										while (commentnext != null)

										{

											System.out.println("************************************");
											commentscount++;
											URL oraclecomments = new URL(commentnext);
											URLConnection commentsyc = oraclecomments.openConnection();
											BufferedReader commentsin = new BufferedReader(
													new InputStreamReader(commentsyc.getInputStream()));
											String commentsinputLine;
											JSONObject commentsobj = new JSONObject();
											while ((commentsinputLine = commentsin.readLine()) != null) {

												commentsobj = new JSONObject(commentsinputLine);

												JSONArray commentsadd = commentsobj.getJSONArray("data");
												for (int comentsinc = 0; comentsinc < commentsadd
														.length(); comentsinc++) {

													JSONObject commentsaddobj = commentsadd.getJSONObject(i);
													commetsarry.put(commentsaddobj);
													output = jsonobjmain.toString();
													System.out.println("ADDED COMMENTS CHANGESD TO STRING");
												}
												System.out.println("COMMENTS ENTERED LOOP COUNT" + commentscount);
											} // comments readline while close

											try {
												JSONObject commentssnullmake = commentsobj.getJSONObject("paging");
												commentnext = commentssnullmake.getString("next");
											} catch (Exception e) {
												commentnext = null;
												System.out.println("there is no comments next");
											}

											commentsin.close();
										}

									} // comments try close
									catch (Exception e) {
										commentnext = null;
										System.out.println("there is no comments next");
									} // comments catch close
								} // for loop end

								// String output=jsonobjmain.toString();

								
							/*BufferedWriter writer = null;
								try {
									writer = new BufferedWriter(new FileWriter("/home/storm/Videos/posts.json"));
									writer.write(jsonobjmain.toString());
									System.out.println("Done writing");

								} catch (IOException e) {
									System.out.println("vanaja");
								} finally {
									try {
										if (writer != null)
											writer.close();
									} catch (IOException e) {
										System.out.println("dilip");

									}
								} */
							}
						
							try {
								JSONObject jo = post_obj.getJSONObject("paging");
								postnext = jo.getString("next");
								 System.out.println("NEXT POST LINK IS  "+postnext);
							} catch (Exception e) {
								postnext = null;
								System.out.println("There is no post next");
							}

							in.close();
							System.out.println("LOOP COUNT STARTS WITH ONE  " + count);
						}

					} catch (FacebookException e) {

						e.printStackTrace();
						System.out.println("The great  " + e);
					} // raw response try close

				} // read line while close

			} // read file while close

		} catch (Exception e) {

			System.out.println("This error is from first try   " + e);
		} // starting catch-close

	}// main-close
}// class-close
