package ioc.weka.core;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.SerializationHelper;

public class WekaScorer {
	
	private String modelName;
	private Classifier model;
	private Instance data;
	
	public void setModelName(String name){
		modelName = name;
	}
	
	public void load(){
		try {
			model = (Classifier) SerializationHelper.read(modelName+".model");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double score(){
		try {
			return model.classifyInstance(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Double) null;
		}
	}
}
