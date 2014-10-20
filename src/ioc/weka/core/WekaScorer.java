package ioc.weka.core;

import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.JSONLoader;

public class WekaScorer {
	
	private static String defaultJsonFileName = "Scoring_Set.json";
	
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
	
	public double[] score4Json(String jsondata){
		try{
			FileWriter fw = new FileWriter(defaultJsonFileName);
			fw.write(jsondata);
			fw.close();
			JSONLoader jsonloader = new JSONLoader();
			jsonloader.setSource(new File(defaultJsonFileName));
			Instances instances = jsonloader.getDataSet();
			instances.setClassIndex(instances.numAttributes()-1);
			double[] result = new double[instances.numInstances()];
			for (int i=0; i<instances.numInstances();i++){
				Instance instance = instances.get(i);
				result[i] = model.classifyInstance(instance);
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
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
