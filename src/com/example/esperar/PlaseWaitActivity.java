package com.example.esperar;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
public class PlaseWaitActivity extends Activity {
	private ProgressDialog pd;
	private Button boton1;
	List<String> noticias;
	GridView gridView;
	private Context context;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.main);
        boton1=(Button)findViewById(R.id.button1);
        List<String> Noticias;
        //Se establece listener para nuestro boton
        boton1.setOnClickListener(listener);
      
    }
    private OnClickListener listener = new OnClickListener() 
    {
	public void onClick(View arg0) {
		     //	Usamos un AsyncTask, para poder mostrar una ventana de por favor espere, mientras se consulta el servicio web
			 new DownloadTask2().execute("");
			 pd = ProgressDialog.show(context, "Por favor espere","Consultando", true, false);
			
		}
	};
    //Tarea en Background
	private class DownloadTask2 extends AsyncTask<String, Void, Object> {
		/*protected Integer doInBackground(String... args) 
		{
			CargaDatosWS ws=new CargaDatosWS();
			//Se invoca nuestro metodo
			String resultado=ws.getService(ciudad.getText().toString(), pais.getText().toString());
			List<String> rs =new ArrayList<String>();
			try {
				rs = readXML(resultado);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res= resultado.replace("\n", "");
			return 1;
		}*/
		protected Integer doInBackground(String... args) 
		{
			CargaDatosWS ws=new CargaDatosWS();
			//Se invoca nuestro metodo
			String resultado=ws.getNotices();
			noticias =new ArrayList<String>();
			try 
			{
				noticias = readXML(resultado);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
		
		private List<String> readXML(String xmlfile) throws ParserConfigurationException
		{
			 Document doc = convertStringToDocument(xmlfile);
			 doc.getAttributes();
			 NodeList errNodes = doc.getElementsByTagName("Location");
			 List<String> ls= new ArrayList<String>();
			 ls.add(errNodes.toString());
			return null;
		}

		

		 
		    private Document convertStringToDocument(String xmlStr) {
		    	Document doc=null;
				try {
					doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					        .parse(new InputSource(new StringReader(xmlStr)));
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		        return doc;
		        
		    }
		    
		    
		    public View getView(int position, View convertView, ViewGroup parent) 
		     {
		             View row = convertView;
		             RecordHolder holder = null;
		             holder = new RecordHolder(); 
		             holder.txtTitle = (TextView) row.findViewById(R.id.noticias);
		             row.findViewById(R.id.noticias); 
		             row.setTag(holder);
		      return row;
		      
		     }

		protected void onPostExecute(Object result) {
			//Se elimina la pantalla de por favor espere.
			pd.dismiss();
			//Se muestra mensaje con la respuesta del servicio web
			//Toast.makeText(context,"Clima: "+res,Toast.LENGTH_LONG).show();
			super.onPostExecute(result);
			 gridView = (GridView) findViewById(R.id.noticias);
		}
		
	}
    static class RecordHolder { TextView txtTitle;}
}