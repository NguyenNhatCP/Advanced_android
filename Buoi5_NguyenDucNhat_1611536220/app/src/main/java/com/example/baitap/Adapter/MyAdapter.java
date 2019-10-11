package com.example.baitap.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.baitap.MainActivity;
import com.example.baitap.R;
import com.example.baitap.model.Channel;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by SinhVien on 7/30/2019.
 */
public class MyAdapter extends BaseAdapter {
    private List<Channel> channels;
    private Context mCtx;

    public MyAdapter(List<Channel> channels, Context mCtx) {
        this.channels = channels;
        this.mCtx = mCtx;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mCtx).inflate(R.layout.item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.txtViewTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.txtDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            viewHolder.progressBar = (ProgressBar) convertView.findViewById(R.id.progressbar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtViewTitle.setText(channels.get(position).getTitle() + "");
        viewHolder.txtDescription.setText(channels.get(position).getDescription() + "");
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        viewHolder.imageView.setVisibility(View.GONE);
        if (MainActivity.map.get(channels.get(position).getImage()) == null) {
            new DownloadImage(channels.get(position).getImage()).execute(viewHolder);
        } else {
            viewHolder.progressBar.setVisibility(View.GONE);
            viewHolder.imageView.setVisibility(View.VISIBLE);
            viewHolder.imageView.setImageBitmap(MainActivity.map.get(channels.get(position).getImage()));
        }
        return convertView;

    }

    private class ViewHolder {
        TextView txtViewTitle, txtDescription;
        ImageView imageView;
        ProgressBar progressBar;
    }

    private class DownloadImage extends AsyncTask<ViewHolder, Void, Bitmap> {
        String summaryImg;
        ViewHolder viewHolder;

        public DownloadImage(String summaryImg) {
            this.summaryImg = summaryImg;
        }

        @Override
        protected Bitmap doInBackground(ViewHolder... params) {
            try {
                viewHolder = params[0];
                URLConnection connection = new URL(summaryImg).openConnection();
                connection.connect();
                BufferedInputStream buffInput = new BufferedInputStream(connection.getInputStream());
                ByteArrayOutputStream dataStream = new ByteArrayOutputStream(1024);
                int current = 0;
                while ((current = buffInput.read()) != -1) {
                    dataStream.write((byte) current);
                }
                Bitmap bitmap = Bitmap.createBitmap(BitmapFactory.decodeByteArray(dataStream.toByteArray(),
                        0, dataStream.toByteArray().length));
                MainActivity.map.put(summaryImg, bitmap);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                viewHolder.progressBar.setVisibility(View.GONE);
                viewHolder.imageView.setVisibility(View.VISIBLE);
                viewHolder.imageView.setImageBitmap(result);
            }
            super.onPostExecute(result);
        }
    }
}
