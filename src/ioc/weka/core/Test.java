package ioc.weka.core;

public class Test {
	
	private static String TRAINING_DATA = "test.json";
	private static String PREDICT_DATA = "predict.json";
	
	public static void main(String[] args){
		WekaTrainer trainer = new WekaTrainer();
		WekaScorer scorer = new WekaScorer();
		
		trainer.setModelName("test");
		trainer.build4Json(TRAINING_DATA);
		trainer.save();
		scorer.setModelName("test");
		scorer.load();
		double[] result = scorer.score4Json(PREDICT_DATA); 
		for (int i=0; i<result.length; i++){
			System.out.println(result[i]);
		}
	}

}
