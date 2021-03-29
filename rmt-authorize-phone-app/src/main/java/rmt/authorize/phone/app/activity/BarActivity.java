package rmt.authorize.phone.app.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import rmt.authorize.phone.app.R;

public abstract class BarActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected FrameLayout frameLayout;
    protected TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        frameLayout = (FrameLayout) findViewById(R.id.toolbar_framelayout);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        tv = (TextView) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//这2行是设置返回按钮的
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    public abstract int Menu();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (Menu() != 0) {
            getMenuInflater().inflate(Menu(), menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
