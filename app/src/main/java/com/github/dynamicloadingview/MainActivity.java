package com.github.dynamicloadingview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.library.LoadinDialog;

public class MainActivity extends AppCompatActivity {
    int[] color = {android.R.color.holo_red_light, android.R.color.holo_blue_bright, android.R.color.holo_green_dark,android.R.color.background_dark};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button jiazai= (Button) findViewById(R.id.jiazai);
        jiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadinDialog dialog=new LoadinDialog(MainActivity.this,R.style.PassDialog);
                dialog.setColor(color);
                dialog.show();
            }
        });
    }
}
