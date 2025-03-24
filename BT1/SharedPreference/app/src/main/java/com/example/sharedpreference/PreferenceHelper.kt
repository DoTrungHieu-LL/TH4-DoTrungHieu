package com.example.sharedpreference
import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {

    // Khởi tạo SharedPreferences với tên file là "MyPrefs" và chế độ MODE_PRIVATE
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    // Phương thức để lưu dữ liệu vào SharedPreferences
    fun saveData(username: String, password: String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("username", username) // Lưu tên người dùng
        editor.putString("password", password) // Lưu mật khẩu
        editor.apply() // Áp dụng thay đổi
    }

    // Phương thức để lấy dữ liệu từ SharedPreferences dưới dạng Pair
    fun getData(): Pair<String?, String?> {
        val username = sharedPreferences.getString("username", null) // Lấy tên người dùng
        val password = sharedPreferences.getString("password", null) // Lấy mật khẩu
        return Pair(username, password) // Trả về một cặp giá trị (username, password)
    }

    // Phương thức mới: Trả về dữ liệu dưới dạng chuỗi để hiển thị
    fun getDataAsString(): String {
        val username = sharedPreferences.getString("username", null)
        val password = sharedPreferences.getString("password", null)
        return if (username != null && password != null) {
            "Tên người dùng: $username\nMật khẩu: $password"
        } else {
            "Không có dữ liệu để hiển thị!"
        }
    }

    // Phương thức để xóa toàn bộ dữ liệu trong SharedPreferences
    fun clearData() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear() // Xóa tất cả dữ liệu
        editor.apply() // Áp dụng thay đổi
    }
}

