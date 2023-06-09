package com.uts.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.uts.gogreen.databinding.ActivityAddpostBinding;


public class AddPostActivity extends AppCompatActivity {
    private ActivityAddpostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddpostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.etNamapohon.getText().toString();

                boolean bolehpost = true ;
                if(TextUtils.isEmpty(content)){
                    bolehpost=false;
                    binding.etNamapohon.setError("Conten Tidak Boleh Kosong");
                }
                if(bolehpost){
                    String username = Utility.getValue(AddPostActivity.this, "xUsername");
                    addPost(username,content);
                }
            }


        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddpostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.etTempat.getText().toString();

                boolean bolehpost = true ;
                if(TextUtils.isEmpty(content)){
                    bolehpost=false;
                    binding.etTempat.setError("Conten Tidak Boleh Kosong");
                }
                if(bolehpost){
                    String username = Utility.getValue(AddPostActivity.this, "xUsername");
                    addPost(username,content);
                }
            }


        });

    }
    private void addPost(String username, String content) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.addPost("dirumahaja",username,content);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
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

                Toast.makeText(AddPostActivity.this, "Retrofit Erro : "+t.getMessage(), Toast.LENGTH_SHORT).show();

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