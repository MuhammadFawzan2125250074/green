package com.uts.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
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
                String namapohon = binding.etNamapohon.getText().toString();
                String alamat = binding.etAlamat.getText().toString();

                boolean bolehpost = true;
                if(TextUtils.isEmpty(namapohon)) {
                    bolehpost = false;
                    binding.etNamapohon.setError("Nama tidak boleh kosong!");
                }
                if(TextUtils.isEmpty(alamat)) {
                    bolehpost = false;
                    binding.etNamapohon.setError("Alamat tidak boleh kosong!");
                }
                if (bolehpost) {
                    String username = Utility.getValue(AddPostActivity.this, "xUsername");
                    addPost(username,namapohon, alamat);
                }
            }
        });
    }

    private void addPost(String username, String namapohon, String alamat) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.addPost("", username,namapohon,alamat);
        call.enque

    }
}