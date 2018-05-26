package com.example.nonem.ex3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nonem.ex3.adapter.ItemRowAdapter;
import com.example.nonem.ex3.model.ItemRow;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    ArrayList<ItemRow> itemList;
    ItemRowAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = new ArrayList<>();
        init();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadData().execute("https://batdongsan.com.vn/Modules/RSS/RssDetail.aspx?catid=52&typeid=1");
            }
        });
    }

    private void init() {
         recyclerView = findViewById(R.id.rvc_list_item);
         recyclerView.setHasFixedSize(true);
    }

    class ReadData extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            XMLDOMParser parser = new XMLDOMParser();
            Document document  = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeListTitle = document.getElementsByTagName("title");
            NodeList nodeListDescrip = document.getElementsByTagName("description");
            NodeList nodeListDate = document.getElementsByTagName("pubDate");
            String title = "";
            String image ="";
            String date = "";
            String link = "";
            String prices ="";
            String info ="";
            String address ="";
            for (int i = 0;  i < nodeList.getLength(); i++){

                String descrip = nodeListDescrip.item(i +1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(descrip);
                if ( matcher.find()){
                    image = matcher.group(1);
                }
                Element element = (Element) nodeList.item(i);
                date = nodeListDate.item(i+1).getTextContent();
                link = parser.getValue(element, "link");
                title = nodeListTitle.item(i+1).getTextContent();
                itemList.add(new ItemRow(title,date,0.0,"",image," ",link));
                //Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();

            }
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter = new ItemRowAdapter(itemList, getApplicationContext());
            recyclerView.setAdapter(adapter);

            super.onPostExecute(s);

        }
    }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
