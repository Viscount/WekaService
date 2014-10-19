package ioc.weka.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.JSONLoader;

public class WekaTrainer {
	
	private String modelName;
	private Classifier model;
	private Instances data;
	
	
	public void setModelName(String name){
		this.modelName = name;
	}
	
	public void build(String dataFilePath){
		try{
			data = new Instances(new BufferedReader(new FileReader(dataFilePath)));
			data.setClassIndex(data.numAttributes()-1);
			model = new LinearRegression();
			model.buildClassifier(data);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void build4Json(String dataFilePath){
		try{
			JSONLoader jsonloader = new JSONLoader();
			jsonloader.setSource(new File(dataFilePath));
			data = jsonloader.getDataSet();
			data.setClassIndex(data.numAttributes()-1);
			model = new LinearRegression();
			model.buildClassifier(data);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void save(){
		try{
			SerializationHelper.write(modelName+".model", model);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
