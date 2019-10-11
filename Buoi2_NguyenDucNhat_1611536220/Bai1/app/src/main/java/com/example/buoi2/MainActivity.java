package com.example.buoi2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    TextView tvTime;
    EditText editTime;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTime = (TextView) findViewById(R.id.tvTime);
        editTime = (EditText) findViewById(R.id.editNhapso);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        new Timer().execute();
    }

    private class Timer extends AsyncTask<Void, Integer, Void> {
        int num;

        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                try {
                    if (num == 0) {
                        return null;
                    }
                    Thread.sleep(10);
                    num = num - 1;
                    publishProgress(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                num = Integer.parseInt(editTime.getText().toString()) * 100;
                btnStart.setEnabled(false);
                editTime.setEnabled(false);
            } catch (Exception ex) {

            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            btnStart.setEnabled(true);
            editTime.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int millis = values[0] % 100;
            tvTime.setText(values[0] / 100 + ":" + ((millis + "").length() != 2 ? "0" + millis : millis));

        }
    }
}

