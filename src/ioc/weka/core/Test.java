package ioc.weka.core;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.text.AbstractDocument.BranchElement;

public class Test {
	
	private static String TRAINING_DATA = "test.json";
	private static String PREDICT_DATA = "predict.json";
	
	 
	
	public static void main(String[] args){
		WekaTrainer trainer = new WekaTrainer();
		WekaScorer scorer = new WekaScorer();
		String content = "";
		
		try{
			trainer.setModelName("test");
			FileReader fr = new FileReader(TRAINING_DATA);
			BufferedReader br = new BufferedReader(fr);
			String read = br.readLine();
			while ( read!=null ){
				content = content + read.trim();
				read = br.readLine();
			}
			trainer.build4Json(content);
			trainer.save();
			
			scorer.setModelName("test");
			scorer.load();
			
			fr = new FileReader(PREDICT_DATA);
			br = new BufferedReader(fr);
			read = br.readLine();
			content = "";
			while ( read!=null ){
				content = content + read.trim();
				read = br.readLine();
			}

			double[] result = scorer.score4Json(content); 
			for (int i=0; i<result.length; i++){
				System.out.println(result[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
