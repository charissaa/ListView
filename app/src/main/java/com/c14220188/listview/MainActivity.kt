package com.c14220188.listview

import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.c14220188.listview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var data = mutableListOf<String>()
        data.addAll(listOf("1","2","3","4","5"))
        //Utk menampilkan data ke dlm ListView membutuhkan adapter
        val lvAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )
        val _lv1 = findViewById<ListView>(R.id.lv1)
        _lv1.adapter =  lvAdapter


        //Tambah Data
        val _btnTambah = findViewById<Button>(R.id.btnTambah)
        _btnTambah.setOnClickListener {
            var dtAkhir = Integer.parseInt(data.get(data.size-1))+1
            data.add(dtAkhir.toString())
            lvAdapter.notifyDataSetChanged() //untuk refresh data
        }

        //Menampilkan data yg dipilih
        _lv1.setOnItemClickListener { parent, view, position, id->
            Toast.makeText(this, "${data[position]}",
                Toast.LENGTH_LONG).show()
        }

        //Hapus Data Pertama dari Array
        val _btnHapus = findViewById<Button>(R.id.btnHapus)
        _btnHapus.setOnClickListener{
            data.removeFirst()
            lvAdapter.notifyDataSetChanged()
        }

//        binding.lv1.adapter = lvAdapter

        //Search
        binding.searchvw.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean{
                lvAdapter.getFilter().filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean{
                lvAdapter.getFilter().filter(newText)
                return false
            }
        })
    }
}