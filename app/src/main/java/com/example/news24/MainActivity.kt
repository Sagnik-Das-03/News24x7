package com.example.news24

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.blogspot.atifsoftwares.animatoolib.Animatoo

lateinit var rvNews : RecyclerView
private lateinit var rvAdapter : NewsListAdapter
class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvNews = findViewById(R.id.rvNews)
        rvNews.layoutManager = LinearLayoutManager(this)
        fetchData()
        rvAdapter = NewsListAdapter( this)
        rvNews.adapter = rvAdapter

    }

    private fun fetchData() {
        //volley library
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=${API_KEY}"
        //making a request
        val jsonObjectRequest = object: JsonObjectRequest(
            Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for(i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getJSONObject("source").getString("name"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("publishedAt"),
                        newsJsonObject.getString("content")
                    )
                    newsArray.add(news)
                }

                rvAdapter.updateNews(newsArray)
            },
            Response.ErrorListener {
            }

        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClick(item: News) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
        Animatoo.animateSlideUp(this)
    }

    private fun countryList(): MutableMap<String, String>{
        val cMap = mutableMapOf<String, String>()

        return cMap
    }

}