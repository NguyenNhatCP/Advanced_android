package com.example.buoi6;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buoi6.data.CityPreference;
import com.example.buoi6.data.JSONWeatherParser;
import com.example.buoi6.model.Weather;
import com.example.buoi6.utils.Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private TextView cityName, countryName, temp, press, windSpeed, hum;
    private ImageView imagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // String city = "HaNoi,vn";
        CityPreference city = new CityPreference(MainActivity.this);
        cityName = (TextView) findViewById(R.id.tv_City);
        countryName = (TextView) findViewById(R.id.tv_Country);
        temp = (TextView) findViewById(R.id.tv_Temperature);
        press = (TextView) findViewById(R.id.tv_almosphericPressure);
        windSpeed = (TextView) findViewById(R.id.tv_wind);
        hum = (TextView) findViewById(R.id.tv_moisture);
        imagView = (ImageView) findViewById(R.id.imageWeather);
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city.getCity() + "&appid=cb1ec5a57f9ac84b28fa1ac6b7e8988e"});
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Option city");

        final EditText cityInput = new EditText(MainActivity.this);
        cityInput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityInput.setHint("Ex: Hanoi,Vn");
        builder.setView(cityInput);
        builder.setPositiveButton("Find", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    if (cityInput.getText().toString().equals("")) {
                        Toast.makeText(MainActivity.this, "Please input a City name!", Toast.LENGTH_LONG).show();
                        return;
                    }
                    CityPreference cityPreference = new CityPreference(MainActivity.this);
                    cityPreference.setCity(cityInput.getText().toString());
                    String newCity = cityPreference.getCity();
                    JSONWeatherTask task = new JSONWeatherTask();
                    task.execute(new String[]{newCity + "&appid=cb1ec5a57f9ac84b28fa1ac6b7e8988e"});
                }
                catch (Exception e){e.printStackTrace();}
            }
        });
        builder.show();
    }
    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new JSONWeatherParser()).getWeatherData(params[0]));
            try {
                weather = JSONWeatherParser.getWeather(data);
                weather.image = weather.statePresent.getImageName();
                //weather.image = ((new JSONWeatherParser()).getImage(weather.statePresent.getImageName()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new DownLoadImageAsyncTask().execute(weather.image);
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            try {
                // if(weather.image != null && weather.image.length > 0){
                //   Bitmap img = BitmapFactory.decodeByteArray(weather.image,0,weather.image.length);
                // imagView.setImageBitmap(img);
                //}
                cityName.setText(weather.location.getCityName() + ", ");
                countryName.setText(weather.location.getCountryName());
                temp.setText(Math.round((weather.temperature.getTp() - 275.15)) + "°C");
                hum.setText("" + weather.statePresent.getMoisture() + "%");
                press.setText("" + weather.statePresent.getAlmosphericPressure() + "hPa");
                windSpeed.setText("" + weather.wind.getSpeed() + "mps");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class DownLoadImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImage(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imagView.setImageBitmap(bitmap);
        }

        private Bitmap downloadImage(String code) {
            final DefaultHttpClient client = new DefaultHttpClient();
            final HttpGet getRequest = new HttpGet((Utils.IMG_URL + code + ".png"));
            try {
                HttpResponse response = client.execute(getRequest);
                final int status = response.getStatusLine().getStatusCode();
                if (status != HttpStatus.SC_OK) {
                    Log.d("DownloadImage", "Error" + status);
                }
                final HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream inputStream = null;
                    inputStream = entity.getContent();

                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.changeCity) {
            showInputDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}