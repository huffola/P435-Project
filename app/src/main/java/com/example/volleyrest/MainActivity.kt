package com.example.volleyrest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.volleyrest.api.Item
import com.example.volleyrest.api.ItemRepository
import com.example.volleyrest.api.ItemRepository.Companion.getItem

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<RecyclerView>(R.id.list)
        val ctx = this
        val searchButton = findViewById<Button>(R.id.showInput)
        val editText = findViewById<EditText>(R.id.editText)
        searchButton.setOnClickListener {
            var inputtext = editText.text //set value
            Toast.makeText(this, inputtext, Toast.LENGTH_SHORT).show() //testing
        }

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)

                }
                ItemRepository.getItem(ctx, "3", {
                    adapter = MyItemRecyclerViewAdapter(it)
                    Log.d(TAG, "$it")
                }, {
                    Toast.makeText(ctx, "$it", Toast.LENGTH_LONG).show()
                    Log.e(TAG, "$it")
                })
            }
        }

    }

    private val columnCount = 1

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}