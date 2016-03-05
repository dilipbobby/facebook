package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListExample {
	
	public static void main (String args[]){
List<String> list1 = new ArrayList<String>();  
List<String> list2 = new ArrayList<String>();  
List<String> list3 = new ArrayList<String>();  

        
        String splitstring="man,animal,jackel";
        String splitid="1,2,3";
        String splitscreenname="bobby,lion,dilip";
        
    List<String> l = new ArrayList<String>();  
    
    List<String> animals = new ArrayList<String>();  
    List<String> ids = new ArrayList<String>();  
    List<String> names = new ArrayList<String>();  
   
    List<String> animalslist = new ArrayList<String>();  
    List<String> idslist = new ArrayList<String>();  
    List<String> nameslist = new ArrayList<String>();  
           
           List<String> l1 = new ArrayList<String>();          
           l.add("man,animal,jackel");
           l.add("1,2,3");
           l.add("bobby,lion,dilip");
         
           System.out.println(l);
          /* Iterator<String> splitl= l.iterator();
           while(splitl.hasNext())
           {
                 for (String retval: splitl.next().split(",")){
                	 animalslist.add(retval);
                 }//foreach close
           
           }//while closejps
           
           System.out.println("THIS IS SPLITTED ONE "+animalslist);*/
        
   		Iterator<String> l1Iterator = l.iterator();
   		while (l1Iterator.hasNext()) {
   			
   			animals.add(l1Iterator.next());
   			ids.add(l1Iterator.next());
   			names.add(l1Iterator.next());
   			
   			System.out.println("this is animals "+animals);
   			System.out.println("this is ids     "+ids);
   			System.out.println("this is names   "+names);
   	}
          // System.out.println(animals.get(0));
          Iterator<String> animalsi= animals.iterator();
           while(animalsi.hasNext())
           {
                 for (String retval: animalsi.next().split(",")){
                	 animalslist.add(retval);
                 }//foreach close
           
           }//while close
           System.out.println(animalslist);
   	//	System.out.println(ids.get(0));
           Iterator<String> idsi= ids.iterator();
           while(idsi.hasNext())
           {
                 for (String retval: idsi.next().split(",")){
                	 idslist.add(retval);
                 }//foreach close
            
           }//while close
        //   System.out.println(idslist);
           
  		//System.out.println(names.get(0));
         Iterator<String> namesi= names.iterator();
         while(namesi.hasNext())          {
                for (String retval: namesi.next().split(",")){
                	nameslist.add(retval);
                }//foreach close
           
           }//while close
          System.out.println(nameslist);
        
        String[] arrsplitstring=splitstring.split(",");
        String[] arrssplitid=splitid.split(",");
    	String[] arrsplitedscreenname=splitscreenname.split(",");
    	
    	List<String> wordList = Arrays.asList(arrsplitstring);
    	List<String> wordid = Arrays.asList(arrssplitid);
    	List<String> wordscreenlist = Arrays.asList(arrsplitedscreenname);

/*for (int ii=0;ii<wordList.size();ii++){
    	
    		list1.add(wordList.get(ii));
    		
    	}
   // System.out.println("Getting the list1 objects  " +list1 );
    	
    	for(int j=0;j<wordid.size();j++){
    		list2.add(wordid.get(j));
    	}

    	//System.out.println("Getting the list2 objects  " +list2);
    	for (int k=0;k<wordscreenlist.size();k++){
        	
    		list3.add(wordscreenlist.get(k));
    		
    	}*/
    	//System.out.println("Getting the list3 objects  " +list3);
    	
  	// initializing lists
   List<String> list4 = new ArrayList<String>();

    	// Check the three lists have the same number of elements.
    	// If not, return (you have to show and error)    
    	if (! (
    	  (wordList.size() == wordid.size() ) 
    	  && 
    	  (wordid.size() == wordscreenlist.size() )
    	) ) return;

    	Iterator<String> wordListIt = wordList.iterator();
    	Iterator<String> wordidIt = wordid.iterator();
    	Iterator<String> wordscreenlistIt = wordscreenlist.iterator();
    	while (wordListIt.hasNext()) {
    	        list4.add(wordListIt.next());
    	        list4.add(wordidIt.next());
    	        list4.add(wordscreenlistIt.next());
    	        
    	      break; // exit the while, because you only want the first element.
    	      
    	}
           List<String> list41 = new ArrayList<String>();

       	// Check the three lists have the same number of elements.
       	// If not, return (you have to show and error)    //animalslist //idslist //nameslist
       	if (! (
       	  (animalslist.size() == idslist.size() ) 
       	  && 
       	  (idslist.size() == nameslist.size() )
       	) ) return;

       	Iterator<String> wordListItt = animalslist.iterator();
       	Iterator<String> wordidItt = idslist.iterator();
       	Iterator<String> wordscreenlistItt= nameslist.iterator();
       	while (wordListIt.hasNext()) {
       		System.out.println("Enter the loops");
       	        list41.add(wordListItt.next());
       	        System.out.println(list41);
       	        list41.add(wordidItt.next());
       	     System.out.println(list41);
       	        list41.add(wordscreenlistItt.next());
       	     System.out.println(list41);
       	           	     break; // exit the while, because you only want the first element.
       	      
       	}
    	System.out.println("  "+41);
    	List<String> list5 = new ArrayList<String>();

    	// Check the three lists have the same number of elements.
    	// If not, return (you have to show and error)   
    	/*List<String> list5 = new ArrayList<String>();
    	if (! (
    	  (wordList.size() == wordid.size() ) 
    	  && 
    	  (wordid.size() == wordscreenlist.size() )
    	) ) return;

    	Iterator<String> wordListIt = wordList.iterator();
    	Iterator<String> wordidIt = wordid.iterator();
    	Iterator<String> wordscreenlistIt = wordscreenlist.iterator();
    	while (wordListIt.hasNext()) {
    	        list5.add(wordListIt.next());
    	        list5.add(wordidIt.next());
    	        list5.add(wordscreenlistIt.next());
    	}
    	System.out.println(list5);
/*
     List<String> l = new ArrayList<String>();  
        
        List<String> l1 = new ArrayList<String>();          
        l.add("1,2,3");
        l.add("a,b,c");
        System.out.println(l);
        Iterator<String> i=l.iterator();
        
        while(i.hasNext())
        {
              for (String retval: i.next().split(",")){
                  
                  l1.add(retval);
                  System.out.println(l1);
              }
        
        }*/
		
/*        String splitstring="man,animal,jackel";
	String[] arrsplitedstrings=splitstring.split(",");
	for (String splitted :arrsplitedstrings){
		
		System.out.println("splitted string  "+splitted);
		
	}
*/	
	String test="lan,man,wan";
	
	//System.out.println(test);
	}

 }
