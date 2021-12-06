package umn.ac.id.week11_36748;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.week11_36748.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    List<PostModel> data;
    RecyclerView rvPosts;
    PostDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPosts = (RecyclerView) findViewById(R.id.rvPosts);

        PostServices postServices = DataRepository.create();
        postServices.getPosts().enqueue(new Callback<List<PostModel>>(){

            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                if(response.isSuccessful()){
                    data = response.body();
                    adapter = new PostDataAdapter(getApplicationContext(), data);
                    rvPosts.setAdapter(adapter);
                    rvPosts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } else{
                    Toast.makeText(getApplicationContext(), "Failed to get posts. Unknown API error.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to get posts. Check internet connection.", Toast.LENGTH_LONG).show();
            }
        });
    }
}