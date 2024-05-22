package com.advantal.utils;
//package com.advantal.userService.util;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import org.json.JSONObject;
//
//public class PushNotificationUtil {
//	
//	static String androidFirebaseKey="AAAACrAaBZk:APA91bGOjRYdqjnoNA5iIrwTucYf2VCghUIw8IxUCMCrE3ZKdXvJhulVB6U8xJ2yBbClVmGTTA1fpCS3_ViW7VWiG8EKeeoFatOX6DZqJbXYksu2LzeeZHZALBM0iUMrN2Eour7FPFwW";
//
//	
//	public static void main(String[] args) {
//		String deviceToken="fDAAGRLBQSaNDXuzXiZ2U6:APA91bG9O_bTTV3clpLwkuJqjTtM2sn2OXujTzrYtBDmlEaznTIpjvsMT-fOpv9HYKlO2OG9PRNSBKYKoTeVy8AMgMhu3TVVIqD5Uy3HS1zOyHuVXGHf0cn2jmb9TLwsO8rk5Bur6L_b";
//		String title="hello";
//		String Message ="demo push....";
//		String image="hhhhhhhhhhelll";
//		String notificationType="1";
//		sendPushNotificationToAndroid(deviceToken, title, Message, image, notificationType);
//	}
//	
//	public static String sendPushNotificationToAndroid(String deviceToken, String title, String Message, String image ,String notificationType) {
//
//		String result = "";
//
//		try {
//
//		URL url = new URL("https://fcm.googleapis.com/fcm/send");
//		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//		conn.setUseCaches(false);
//		conn.setDoInput(true);
//		conn.setDoOutput(true);
//
//		conn.setRequestMethod("POST");
//		conn.setRequestProperty("Authorization", "key=" +androidFirebaseKey);
//		conn.setRequestProperty("Content-Type", "application/json");
//
//		JSONObject json = new JSONObject();
//		json.put("to", deviceToken.trim());
//
//		JSONObject data = new JSONObject();
//		data.put("message", Message);
//		json.put("data", data);
//
//		JSONObject info = new JSONObject();
//		info.put("title", title); // Notification title
//		if (image != null) {
//		if (image.startsWith("http"))
//		info.put("image", image);
//		}
//
//		info.put("body", Message);
//		info.put("type", notificationType);
//		info.put( "sound" , "default");
//		json.put("data", info);
//
//		System.out.println("Notificatino " + json.toString());
//		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
//		wr.write(json.toString());
//		wr.flush();
//
//		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//		String output;
//		// System.out.println("Output from Server .... \n");
//		while ((output = br.readLine()) != null) {
//		// System.out.println(output);
//		}
//		result = "succcess";
//		} catch (Exception e) {
//		e.printStackTrace();
//		result = "failure";
//		}
//		// System.out.println("Notification is sent successfully");
//
//		return result;
//		}
//
//
//}
