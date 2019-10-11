package com.example.bai2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStart;
    EditText editTime;
    TextView tvTime;
    int numb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btnStart);
        editTime = (EditText)findViewById(R.id.editNhapso);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnStart.setOnClickListener(this);
    }
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0) {
                btnStart.setEnabled(false);
                editTime.setEnabled(false);
            }
            else if(msg.what == 1)
            {
                int millis = msg.arg1 % 100;
                tvTime.setText(msg.arg1 / 100 + ":" +((millis+ "").length() != 2 ? "0" + millis : millis));
            }
            else
            {
                btnStart.setEnabled(true);
                editTime.setEnabled(true);
            }
        }
    };

    @Override
    public void onClick(View v) {

        numb = Integer.parseInt(editTime.getText().toString())* 100;
        new  Thread(){
            @Override
            public void run() {
                handler.obtainMessage(0).sendToTarget();
                while (true)
                {
                    try {
                        if(numb == 0 )
                        {
                            handler.obtainMessage(2).sendToTarget();
                            return;
                        }
                        Thread.sleep(10);
                        numb = numb -1;
                        handler.obtainMessage(1,numb,0).sendToTarget();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
}