package mmalla.android.com.jokeandroidlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        Intent intent = getIntent();
        String joke = intent.getStringExtra("JOKE_TEXT");
        TextView tv = (TextView) findViewById(R.id.joke_content);
        if(joke != null && joke.length() != 0){
            tv.setText(joke);
        }
    }

}
