package com.example.volleyrest.api

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import java.net.URLEncoder

private const val TAG = "Item"

class ItemRepository {

    companion object {

        fun getItem(ctx: Context, id: String, success: (Item) -> Int, fail: (VolleyError) -> Int)
        {

            val encodedNumber = URLEncoder.encode(id, "UTF-8")
            val url = "https://api.osrsbox.com/items/${encodedNumber}"
            Log.d(TAG, "Initiating request to $url")
            val req = GsonRequest(
                url,
                Item::class.java,
                mutableMapOf(Pair("Authorization", "token xoxb-blah")),
                {
                    success(it)
                },
                {
                    fail(it)
                }
            )
            Log.d(TAG, "Item: ${req}")

            val queue = Volley.newRequestQueue(ctx)

            queue.add(req)
        }
    }
}

