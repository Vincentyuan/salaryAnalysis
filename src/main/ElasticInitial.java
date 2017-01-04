package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.elasticsearch.client.transport.TransportClient;

public class ElasticInitial {
	public static void main(String[] argv) throws IOException {
		
		// on startup

		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
		        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

		BufferedReader reader = new BufferedReader(new FileReader("/data/Salaries.csv"));
		Map<String, Object> json = new HashMap<String, Object>();
		reader.readLine();
		String line = null;
		
		 while((line=reader.readLine())!=null){ 
             String item[] = line.split(";");//CSV split by ;
             
             int id = Integer.parseInt(item[0]);
             String EmployeeName = item[1];
             String JobTitle = item[2];
             double BasePay = !item[3].isEmpty()?Double.parseDouble(item[3]):0;
             double OvertimePay = Double.parseDouble(item[4]);
             double OtherPay = !item[5].isEmpty()?Double.parseDouble(item[5]):0;            
             double Benefits = !item[6].isEmpty()?Double.parseDouble(item[6]):0;
             double TotalPay = !item[7].isEmpty()?Double.parseDouble(item[7]):0;
             double TotalPayBenefits = !item[8].isEmpty()?Double.parseDouble(item[8]):0;
             int Year = Integer.parseInt(item[9]);
             String Notes = item[10];
             String Agency = item[11];
             String Status;
             if(item.length>12) {
            	 Status = item[12];
             }
             else {
            	 Status = "";
             }
             
             json.put("Id",id);
             json.put("EmployeeName",EmployeeName);
             json.put("JobTitle",JobTitle);
             json.put("BasePay",BasePay);
             json.put("OvertimePay",OvertimePay);
             json.put("OtherPay",OtherPay);
             json.put("Benefits",Benefits);
             json.put("TotalPay",TotalPay);
             json.put("TotalPayBenefits",TotalPayBenefits);
             json.put("Year",Year);
             json.put("Notes",Notes);
             json.put("Agency",Agency);
             json.put("Status",Status);
             
             IndexResponse response = client.prepareIndex("sf", "salary")
     		        .setSource(json)
     		        .get();
             System.out.println(response + "  id:" + id);
             
             json.clear();
         } 
		
		// on shutdown
		reader.close();
		client.close();
	}}
