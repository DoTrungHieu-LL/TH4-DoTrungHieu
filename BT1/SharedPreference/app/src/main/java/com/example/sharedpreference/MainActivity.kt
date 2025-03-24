package com.example.sharedpreference

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var btnShow: Button
    private lateinit var tvDisplay: TextView // TextView để hiển thị dữ liệu
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view từ layout
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        btnShow = findViewById(R.id.btnShow)
        tvDisplay = findViewById(R.id.DisplayShow) // Ánh xạ TextView

        // Khởi tạo PreferenceHelper
        preferenceHelper = PreferenceHelper(this)

        // Xử lý sự kiện khi nhấn nút "Lưu"
        btnSave.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            preferenceHelper.saveData(username, password)
            tvDisplay.text = Editable.Factory.getInstance().newEditable("Dữ liệu đã được lưu")
        }

        // Xử lý sự kiện khi nhấn nút "Xóa"
        btnDelete.setOnClickListener {
            preferenceHelper.clearData()
            tvDisplay.text = Editable.Factory.getInstance().newEditable("Dữ liệu đã được xóa")
        }

        // Xử lý sự kiện khi nhấn nút "Hiển thị"
        btnShow.setOnClickListener {
            val data = preferenceHelper.getDataAsString() ?: "Không có dữ liệu"
            tvDisplay.text = data
        }
    }
}