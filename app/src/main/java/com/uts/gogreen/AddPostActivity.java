package com.uts.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.uts.gogreen.databinding.ActivityAddpostBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddPostActivity extends AppCompatActivity {
    private ActivityAddpostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddpostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Add Post");


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namapohon = binding.etNamapohon.getText().toString();
                String alamat = binding.etAlamat.getText().toString();

                boolean bolehpost = true;
                if(TextUtils.isEmpty(namapohon)) {
                    bolehpost = false;
                    binding.etNamapohon.setError("Nama tidak boleh kosong!");
                }
                if(TextUtils.isEmpty(alamat)) {
                    bolehpost = false;
                    binding.etAlamat.setError("Alamat tidak boleh kosong!");
                }
                if (bolehpost) {
                    String userId = Utility.getValue(AddPostActivity.this, "xUserId");
                    addPost( namapohon, alamat,userId);
                }
            }
        });
    }

    private void addPost(String namapohon,String alamat, String userId) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        retrofit2.Call<ValueNoData> call = api.addPost(namapohon, alamat ,userId);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(retrofit2.Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if(response.code()==200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if(success==1){
                        Toast.makeText(AddPostActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(AddPostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(AddPostActivity.this, "Response : "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : "+ t.getMessage());

                Toast.makeText(AddPostActivity.this, "Retrofit Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}