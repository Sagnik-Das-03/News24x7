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
const val API_KEY = ApiKeyCredential.API_KEY
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
        cMap["UNITED ARAB EMIRATES"] = "ae"
        cMap["ARGENTINA"] = "ar"
        cMap["AUSTRIA"] = "at"
        cMap["AUSTRALIA"] = "au"
        cMap["BELGIUM"] = "be"
        cMap["BULGARIA"] = "bg"
        cMap["BRAZIL"] = "br"
        cMap["CANADA"] = "ca"
        cMap["SWITZERLAND"] = "ch"
        cMap["CHINA"] = "cn"
        cMap["COLOMBIA"] = "co"
        cMap["CUBA"] = "cu"
        cMap["CZECH REPUBLIC"] = "cz"
        cMap["GERMANY"] = "de"
        cMap["EGYPT"] = "eg"
        cMap["FRANCE"] = "fr"
        cMap["UNITED KINGDOM"] = "gb"
        cMap["GREECE"] = "gr"
        cMap["HONG KONG"] = "hk"
        cMap["HUNGARY"] = "hu"
        cMap["INDONESIA"] = "id"
        cMap["IRELAND"] = "ie"
        cMap["ISRAEL"] = "il"
        cMap["INDIA"] = "in"
        cMap["ITALY"] = "it"
        cMap["JAPAN"] = "jp"
        cMap["SOUTH KOREA"] = "kr"
        cMap["LITHUANIA"] = "lt"
        cMap["LATVIA"] = "lv"
        cMap["MOROCCO"] = "ma"
        cMap["MEXICO"] = "mx"
        cMap["MALAYSIA"] = "my"
        cMap["NIGERIA"] = "ng"
        cMap["NETHERLANDS"] = "nl"
        cMap["NORWAY"] = "no"
        cMap["NEW ZEALAND"] = "nz"
        cMap["PHILIPPINES"] = "ph"
        cMap["POLAND"] = "pl"
        cMap["PORTUGAL"] = "pt"
        cMap["ROMANIA"] = "ro"
        cMap["SERBIA"] = "rs"
        cMap["RUSSIA"] = "ru"
        cMap["SAUDI ARABIA"] = "sa"
        cMap["SWEDEN"] = "se"
        cMap["SINGAPORE"] = "sg"
        cMap["SLOVENIA"] = "si"
        cMap["SLOVAKIA"] = "sk"
        cMap["THAILAND"] = "th"
        cMap["TURKEY"] = "tr"
        cMap["TAIWAN"] = "tw"
        cMap["UKRAINE"] = "ua"
        cMap["UNITED STATES OF AMERICA"] = "us"
        cMap["VENEZUELA"] = "ve"
        cMap["SOUTH AFRICA"] = "za"
        return cMap
    }
    private fun categoryList() :MutableList<String>{
        val catList = mutableListOf<String>()
        catList.add("business")
        catList.add("entertainment")
        catList.add("general")
        catList.add("health")
        catList.add("science")
        catList.add("sports")
        catList.add("technology")
        return catList
    }
}