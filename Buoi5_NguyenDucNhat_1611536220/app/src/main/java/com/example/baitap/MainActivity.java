package com.example.baitap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.baitap.Adapter.MyAdapter;
import com.example.baitap.activity.WebViewActivity;
import com.example.baitap.model.Channel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    ArrayList<Channel> channels = new ArrayList<>();
    public static HashMap<String, Bitmap> map = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetData().execute();
    }
    private static String IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s); String tmp = matcher.replaceAll("");
            return tmp;
        } catch (RuntimeException e) { return e.toString(); } }
    private class GetData extends AsyncTask<String,Void,Void>
    {


        @Override
        protected Void doInBackground(String... params) {
            try {
                URL url = new URL("https://vnexpress.net/"+"rss/thoi-su.rss");
                DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = fac.newDocumentBuilder();
                Document doc = builder.parse(url.openConnection().getInputStream());
                Element root = doc.getDocumentElement();
                NodeList list = root.getElementsByTagName("item");
                NodeList listDescription = root.getElementsByTagName("description");
                String img,des = "";
                for (int i = 0; i < list.getLength(); i++) {
                    Node node = list.item(i);
                    if (node instanceof Element) {
                        Channel channel = new Channel();
                        Element emloyee = (Element) node;
                        NodeList listTitle = emloyee.getElementsByTagName("title");
                        channel.setTitle(listTitle.item(0).getTextContent());
                        NodeList listLink = emloyee.getElementsByTagName("link");
                        channel.setLink(listLink.item(0).getTextContent());
                        NodeList listDes = emloyee.getElementsByTagName("description");
                        channel.setDescription(listDes.item(0).getTextContent());
                        String cdata = (listDescription.item(i+1).getTextContent());
                        String regexDes ="<([^\\s]+)(\\s[^>]*?)?(?<!/)>";
                        des = IsMatch(cdata,regexDes);
                        Log.d("des",des+"......."+i);
                        channel.setDescription(des);
                        Pattern p = Pattern.compile("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)");
                        Matcher matcher = p.matcher(cdata);
                        if(matcher.find())
                        {
                            img = matcher.group();
                            Log.d("hinhanh",img+"......."+i);
                            channel.setImage(img);
                        }
                        channels.add(channel);
                    }
                }
            }catch (MalformedURLException e) {e.printStackTrace();
            }catch (IOException e){e.printStackTrace();
            }catch (ParserConfigurationException e){
                e.printStackTrace();
            }catch (SAXException e) {e.printStackTrace();}
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            ListView listView = (ListView)findViewById(R.id.listview);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new MyAdapter(channels,MainActivity.this));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("link",channels.get(position).getLink());
                    startActivity(intent);
                }
            });
            ((ProgressBar)findViewById(R.id.progressbar)).setVisibility(View.GONE);
            super.onPostExecute(result);
        }
    }
}
