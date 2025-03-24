package com.example.databasedemo

import DatabaseHelper
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnShow: Button
    private lateinit var tvDisplay: TextView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
        btnShow = findViewById(R.id.btnShow)
        tvDisplay = findViewById(R.id.tvDisplay)

        databaseHelper = DatabaseHelper(this)

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                databaseHelper.addUser(name, phone)
                Toast.makeText(this, "Đã thêm người dùng", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        btnUpdate.setOnClickListener {
            val id = 1
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                databaseHelper.updateUser(id, name, phone)
                Toast.makeText(this, "Đã cập nhật người dùng", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener {
            val id = 1
            databaseHelper.deleteUser(id)
            Toast.makeText(this, "Đã xóa người dùng", Toast.LENGTH_SHORT).show()
        }

        btnShow.setOnClickListener {
            val cursor: Cursor = databaseHelper.getAllUsers()
            val buffer = StringBuffer()
            while (cursor.moveToNext()) {
                buffer.append("ID: ${cursor.getInt(0)}\n")
                buffer.append("Tên: ${cursor.getString(1)}\n")
                buffer.append("SĐT: ${cursor.getString(2)}\n\n")
            }
            tvDisplay.text = buffer.toString()
        }

    }
}