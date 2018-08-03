package com.lixa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lixa.util.ProgressDialogUtils;
import com.lixa.util.WebServiceUtils;
import com.lixa.util.WebServiceUtils.WebServiceCallBack;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;

/**
 * 显示天气的Activity
 *
 *
 * @author xiaanming
 *
 */
public class WeatherActivitynet extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
		init();
	}

	private void init() {
		final TextView mTextWeather = (TextView) findViewById(R.id.weather);
		ProgressDialogUtils.showProgressDialog(this, "数据加载中...");
		HashMap<String, String> properties = new HashMap<String, String>();
		//properties.put("theCityName", getIntent().getStringExtra("city"));
		properties.put("arg0", getIntent().getStringExtra("city"));

		//WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getWeather", properties, new WebServiceCallBack() {
            String url="http://192.168.1.104:8080/WeatherMgrService/WeatherInfoPort?wsdl";
			WebServiceUtils.callWebService1(url, "getWeatherInfo", properties, new WebServiceCallBack() {
			@Override
			public void callBack(SoapObject result) {
				//getWeatherbyCityName getWeatherInfo
				//WebServiceUtils.WEB_SERVER_URL
				ProgressDialogUtils.dismissProgressDialog();
				if(result != null){
					//SoapObject detail = (SoapObject) result.getProperty("getWeatherResult");
					SoapObject detail = (SoapObject) result.getProperty("getWeatherInfoResponse");
					//getWeatherResult
					//getWeatherbyCityNameResult
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<detail.getPropertyCount(); i++){
						sb.append(detail.getProperty(i)).append("\r\n");
					}
					mTextWeather.setText(sb.toString());
				}else{
					//mTextWeather.setText("天气实况：气温：1℃；东北风 4级；湿度：48%");
					Toast.makeText(WeatherActivitynet.this, "获取WebService数据错误!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
