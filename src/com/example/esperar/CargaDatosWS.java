package com.example.esperar;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CargaDatosWS {
	public String getService(String ciudad,String pais){
		String res=null;
		//Se crea un objeto de tipo SoapObjecto. Permite hacer el llamado al WS
		SoapObject rpc;
		//rpc = new SoapObject("http://www.webserviceX.NET", "GetWeather");
		rpc = new SoapObject("http://www.webserviceX.NET", "GetWeather");
		//De acuerdo a la documentacion del ws, hay 2 parametros que debemos pasar nombre de la ciuda y del pais
		//Para obtener informacion del WS , se puede consultar http://www.webservicex.net/globalweather.asmx?WSDL
		rpc.addProperty("CityName", ciudad);
		rpc.addProperty("CountryName", pais);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		//Se establece que el servicio web esta hacho en .net
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		//Para acceder al WS se crea un objeto de tipo HttpTransportSE , esto es propio de la libreia KSoap
		HttpTransportSE androidHttpTransport= null;
		try {
			String conexion = "http://www.webservicex.net/globalweather.asmx";
			androidHttpTransport = new HttpTransportSE(conexion);
			androidHttpTransport.debug = true;
			//Llamado al servicio web . Son el nombre del SoapAction, que se encuentra en la documentacion del servicio web y el objeto envelope
			androidHttpTransport.call("http://www.webserviceX.NET/GetWeather", envelope);
			//Respuesta del Servicio web
			res = envelope.getResponse().toString();
		}catch (Exception e){
			System.out.println(e.getMessage());
			res=e.getMessage();
		}
		return res;
		
	}

	public String getNotices()
	{
		String res=null;
		//Se crea un objeto de tipo SoapObjecto. Permite hacer el llamado al WS
		SoapObject rpc;
		//rpc = new SoapObject("http://www.webserviceX.NET", "GetWeather");
		rpc = new SoapObject("http://localhost:8080", "Getnews");
		//De acuerdo a la documentacion del ws, hay 2 parametros que debemos pasar nombre de la ciuda y del pais
		//Para obtener informacion del WS , se puede consultar http://www.webservicex.net/globalweather.asmx?WSDL
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		//Se establece que el servicio web esta hacho en .net
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		//Para acceder al WS se crea un objeto de tipo HttpTransportSE , esto es propio de la libreia KSoap
		HttpTransportSE androidHttpTransport= null;
		try {
			String conexion = "http://localhost:8080/RESTfull/news/all";
			androidHttpTransport = new HttpTransportSE(conexion);
			androidHttpTransport.debug = true;
			//Llamado al servicio web . Son el nombre del SoapAction, que se encuentra en la documentacion del servicio web y el objeto envelope
			androidHttpTransport.call("http://localhost:8080/RESTfull/news/all", envelope);
			//Respuesta del Servicio web
			res = envelope.getResponse().toString();
		}catch (Exception e){
			System.out.println(e.getMessage());
			res=e.getMessage();
		}
		return res;
		
	}

}