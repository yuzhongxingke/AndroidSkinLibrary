package com.simon.skin.simple;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.simon.skin.SkinManager;
import com.simon.skin.simple.utils.CrashHandleUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private String mSkinPkgPath = Environment.getExternalStorageDirectory() + File.separator + "skin_plugin.apk";
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("Activity", "Service", "Activity", "Service",
            "Activity", "Service", "Activity", "Service","Activity", "Service", "Activity", "Service",
            "Activity", "Service", "Activity", "Service","Activity", "Service", "Activity", "Service",
            "Activity", "Service", "Activity", "Service","Activity", "Service", "Activity", "Service",
            "Activity", "Service", "Activity", "Service","Activity", "Service", "Activity", "Service",
            "Activity", "Service", "Activity", "Service","Activity", "Service", "Activity", "Service",
            "Activity", "Service", "Activity", "Service"));
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrashHandleUtil.getmInstance().init(this,"sample");
        SkinManager.getmInstance().init(this);
        SkinManager.getmInstance().register(this);
        initViews();
    }

    private void initViews() {
        mListView = (ListView) findViewById(R.id.id_listview);
        mListView.setAdapter(mAdapter = new ArrayAdapter<String>(this, -1, mDatas)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                if (convertView == null)
                {
                    convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent , false);
                }
                SkinManager.getmInstance().injectSkin(convertView);
                TextView tv = (TextView) convertView.findViewById(R.id.id_tv_title);
                tv.setText(getItem(position));
                return convertView;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.day:
                SkinManager.getmInstance().changeSkin("day");
                break;
            case R.id.night:
                SkinManager.getmInstance().changeSkin("night");
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}
