package com.lixa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lixa.util.ProgressDialogUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;

/**
 * 显示天气的Activity
 *
 *
 * @author xiaanming
 *
 */
public class WeatherActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
		init();
	}

	private void init() {
		final TextView mTextWeather = (TextView) findViewById(R.id.weather);
		ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
		HashMap<String, String> properties =  new HashMap<String, String>();
		//properties.put("theCityName", getIntent().getStringExtra("city"));
		properties.put("arg0", getIntent().getStringExtra("city"));

		//WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getWeather", properties, new WebServiceCallBack() {
		//String url="http://192.168.1.103:8080/WeatherMgrService/WeatherInfoPort?wsdl";

		String wsUrl = "http://192.168.1.101:8080/WeatherMgrService/WeatherInfoPort?wsdl";
		String methodName = "getWeatherInfo";
		String namespace = "http://order.servlet.lixa.com/";
		String soapAction = namespace + methodName;

		SoapObject request = new SoapObject(namespace, methodName);
		request.addProperty("arg0", getIntent().getStringExtra("city"));

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.dotNet = false;
		envelope.setOutputSoapObject(request);
		HttpTransportSE ht = new HttpTransportSE(wsUrl);
		ht.debug = true;
		String response = "";
		try {
			// 第5步：调用WebService
			ht.call(soapAction, envelope);
			Log.w(getClass().getName(), ht.requestDump);
			//  System.out.print(envelope.getResponse());
			SoapObject rpcObject = (SoapObject) envelope.bodyIn;
			response = rpcObject.getProperty(0).toString();
			mTextWeather.setText(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ProgressDialogUtils.dismissProgressDialog();
	}
}
