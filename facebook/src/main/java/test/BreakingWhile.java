package test;

import java.util.Random;

public class BreakingWhile {

	public static void main(String args[]){
		boolean a=true;
		while(a){
			  
			Random rand = new Random(); 
			 int i=5;
			for (int j=0;j < 5;j++)
			 {
				if(i<j){
			   System.out.printf("%12d ",rand.nextInt());
			   System.out.print(rand.nextLong());
			   System.out.println();
				}else{System.out.println("Test Loop");
				a=false;
				break;
				}
			 } 
			}
			 
		}
}

