package jti.rofika.a04androidrecyclerview;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import jti.rofika.a04androidrecyclerview.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    
    private ArrayList<RecipeData> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        
        setRecipeList();
        
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.addLast("+ Recipe " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);

        RecipeListAdapter recipeListAdapter = new RecipeListAdapter(MainActivity.this, recipeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(recipeListAdapter);
        recipeListAdapter.setOnItemClickListener(onItemClickListener);
    }

    private void setRecipeList() {
        recipeList = new ArrayList<>();
        RecipeData data;
        data = new RecipeData(getString(R.string.moo_shu_name), getString(R.string.moo_shu_description), R.drawable.moo_shu_img, getString(R.string.moo_shu_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.grilled_shrimp_name), getString(R.string.grilled_shrimp_description), R.drawable.grilled_shrimp_img, getString(R.string.grilled_shrimp_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.sirloin_tips_name), getString(R.string.sirloin_tips_description), R.drawable.sirloin_tips_img, getString(R.string.sirloin_tips_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.squash_casserole_name), getString(R.string.squash_casserole_description), R.drawable.squash_casserole_img, getString(R.string.squash_casserole_details));
        recipeList.add(data);
        data = new RecipeData(getString(R.string.slow_casserole_name), getString(R.string.slow_casserole_description), R.drawable.slow_casserole_img, getString(R.string.slow_casserole_details));
        recipeList.add(data);
    }
    public void openDetailActivity(int imageId, String details){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("image", imageId);
        intent.putExtra("details", details);
        startActivity(intent);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();
            RecipeData thisRecipe = recipeList.get(position);
            openDetailActivity(thisRecipe.getImage(), thisRecipe.getDetails());
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // This comment suppresses the Android Studio warning about simplifying
        // the return statements.
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            mWordList.clear();
            // add default items again
            for (int i = 0; i < 20; i++) {
                mWordList.addLast("Word " + i);
            }
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}