package com.example.guessme

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.guessme.data.Quiz
import com.example.guessme.util.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_read_quiz.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL




class ReadQuizActivity : AppCompatActivity() {

    var readQuizList: Array<Quiz> = arrayOf() // 퀴즈 리스트 담을 배열 생성
    val my_answer_list: Array<Int> = arrayOf(-1, -1, -1, -1, -1)
    var nickname: String = ""

    // url 추가!!
    val urlString: String = ""

    //

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_quiz)

        nickname = intent.getStringExtra("nickname")
        tv_cq_title.setText(String.format("%s님의 퀴즈를 풀어보세요!", nickname))
        readQuizList = GET_quiz(urlString, nickname)

    }


    lateinit var recyclerView: RecyclerView
    lateinit var viewAdapter: RecyclerView.Adapter<*>
    lateinit var viewManager: RecyclerView.LayoutManager
    val context: Context = this


    fun GET_quiz(urlString: String, nickname: String): Array<Quiz> {
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        var buffer = ""

        val read_quiz_List:ArrayList<Quiz> = arrayListOf()

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "url!!!!!!!!!") //header 작성

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
        }
        val data = Gson().fromJson(buffer, Array<Quiz>::class.java)
        return data
    }
    // view 보기 위한 초기화
//        recyclerView = findViewById<RecyclerView>(R.id.read_quiz)
//        for (i in 0 until 5){
//            readQuizList.add(Quiz(1,"퀴즈가 나온다리",1))
//        }
//        val adapter = ReadQuizAdapter(context, readQuizList)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this@ReadQuizActivity)


}
