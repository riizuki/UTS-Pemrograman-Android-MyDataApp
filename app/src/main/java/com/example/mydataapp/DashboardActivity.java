package com.example.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private EditText etNim, etNama, etProdi, etKelas, etAlamat, etEmail;
    private Button btnTambah, btnLogout;
    private ListView listViewData;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User");

        tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText("Selamat Datang, " + username);

        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);
        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);
        listViewData = findViewById(R.id.listViewData);

        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listViewData.setAdapter(adapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                String prodi = etProdi.getText().toString();
                String kelas = etKelas.getText().toString();
                String alamat = etAlamat.getText().toString();
                String email = etEmail.getText().toString();

                if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || kelas.isEmpty() || alamat.isEmpty() || email.isEmpty()) {
                    Toast.makeText(DashboardActivity.this, "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                } else {
                    String combinedData = "NIM: " + nim + "\nNama: " + nama + "\nProdi: " + prodi + "\nKelas: " + kelas + "\nAlamat: " + alamat + "\nEmail: " + email;
                    dataList.add(combinedData);
                    adapter.notifyDataSetChanged();
                    
                    // Clear inputs
                    etNim.setText("");
                    etNama.setText("");
                    etProdi.setText("");
                    etKelas.setText("");
                    etAlamat.setText("");
                    etEmail.setText("");
                    
                    Toast.makeText(DashboardActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear all data (isLoggedIn and username)
                editor.apply();

                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
