package com.uts.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.uts.gogreen.databinding.ActivityUpdatePostBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePostActivity extends AppCompatActivity {

    private ActivityUpdatePostBinding binding;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUpdatePostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Edit");

        post = getIntent().getParcelableExtra("EXTRA_DATA");
        String id = post.getId();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namapohon = binding.etNamapohon.getText().toString();
                String alamat = binding.etAlamat.getText().toString();

                boolean bolehUpdatePost = true;

                if (TextUtils.isEmpty(namapohon)) {
                    bolehUpdatePost = false;
                    binding.etNamapohon.setError("Nama tidak boleh kosong");
                }
                if (TextUtils.isEmpty(alamat)) {
                    bolehUpdatePost = false;
                    binding.etAlamat.setError("Alamat tidak boleh kosong");
                }

                if (bolehUpdatePost) {
                    updatePost(id, namapohon, alamat);
                    Intent intent = new Intent(UpdatePostActivity.this, MainActivity.class); //sdh post lngsg pindah
                    finish();
                    startActivity(intent);
                }
            }


        });
    }

    private void updatePost(String id, String namapohon, String alamat) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.updatePost(id, namapohon, alamat);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdatePostActivity.this, "Response : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(UpdatePostActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }
}
