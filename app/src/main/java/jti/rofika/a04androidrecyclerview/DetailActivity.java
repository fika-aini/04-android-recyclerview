package jti.rofika.a04androidrecyclerview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView imageView = findViewById(R.id.recipe_image);
        TextView textView = findViewById(R.id.recipe_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            textView.setText(getIntent().getStringExtra("details"));
            Glide.with(this).load(getIntent().getIntExtra("image", 0)).into(imageView);
        }
    }
}
