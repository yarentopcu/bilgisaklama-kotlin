package com.yt.bilgisaklama

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.yt.bilgisaklama.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var  sharedPreferences: SharedPreferences
    var alinankulaniciadi:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sharedPreferences=this.getSharedPreferences("com.yt.bilgisaklama", Context.MODE_PRIVATE)

        alinankulaniciadi=sharedPreferences.getString("isim","")
        if(alinankulaniciadi==""){
            binding.textView.text="kaaydedieln isim:"
        }else{
            binding.textView.text="kaydedilenisim:${alinankulaniciadi}"
        }
    }

    fun kaydet(view:View){
        val kullanici_ismi=binding.editTextText.text.toString()
        if(kullanici_ismi==""){
            Toast.makeText(this@MainActivity,"isminizi bos birakamazsiniz",Toast.LENGTH_LONG).show()
        }else{
            sharedPreferences.edit().putString("isim",kullanici_ismi).apply()
            binding.textView.text="kaydedilen isim:${kullanici_ismi}"

        }

    }
    fun sil(view: View){
        alinankulaniciadi=sharedPreferences.getString("isim","")
        if(alinankulaniciadi!=""){
            sharedPreferences.edit().remove("isim").apply()
        }
        binding.textView.text="kaydedilen isim"

    }
}
