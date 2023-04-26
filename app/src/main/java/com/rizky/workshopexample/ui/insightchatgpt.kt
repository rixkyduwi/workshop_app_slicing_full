// kie kodingan udin
// kodingan untuk mencari huruf didalam kata didalam kalimat
package com.rizky.workshopexample.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.rizky.workshopexample.R
import com.rizky.workshopexample.databinding.ActivityDoaTidurDoneBinding

class insightchatgpt : AppCompatActivity() {

    private lateinit var binding: ActivityDoaTidurDoneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoaTidurDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var hasil = "Salah"
        var soal = "باسمك اللهم احيا وباسمك اموت"
        var jwb = "باسم اللهم"
        var soalarr = soal.split(" ")
        var jwbarr = jwb.split(" ")
        var jmlbnr = 0
        var hasil_hafalan = ""

        var jmlstr = soal?.length ?: 0
        var jmljwb = jwb?.length ?: 0
        println(jmlstr)

        var word = ""
        var letter =""

        // cek kata
        soalarr.forEachIndexed { indexkata, kata ->
            if(indexkata<=jwbarr.size-1){
                if (jwbarr[indexkata] == soalarr[indexkata]) {
                    println("1 kata jawaban $kata index $indexkata ketemu di kata soal $kata index $indexkata ")
                    word = "<font color=#2aad46> $kata </font>"
                    hasil_hafalan += "$word "
                    for(bnr in kata)jmlbnr++

                }
                else{
                    println("2 kata jawaban "+jwbarr[indexkata] +" index $indexkata tidak ketemu di kata soal $kata index $indexkata ")

                    val jwbhrf = jwbarr[indexkata] .toList()
                    val soalhrf = soalarr[indexkata].toList()
                    // cek huruf
                    soalhrf.forEachIndexed { indexhrf, hrf ->
                        if(indexhrf<=jwbhrf.size-1){
                            if (jwbhrf[indexhrf] == soalhrf[indexhrf]) {
                                println("1 huruf jawaban $hrf index $indexhrf ketemu di kata soal $hrf index $indexhrf ")
                                letter =
                                    "<font color=#2aad46>" + hrf + "</font>"
                                word += "$letter"
                                jmlbnr++

                            }
                            else{
                                println("2 huruf jawaban "+jwbhrf[indexhrf] +" index $indexhrf tidak ketemu di kata soal $hrf index $indexhrf ")
                                letter = "<font color=#FF641A>" + hrf + "</font>"
                                word +="$letter"
                            }
                        }
                        else{
                            println("3 huruf jawaban tidak ada index tidak ada maka tidak ketemu di kata soal $hrf index $indexhrf ")
                            letter = "<font color=#FF641A>" + hrf + "</font>"
                            word +="$letter"
                        }

                    }
                    // end cek huruf
                    hasil_hafalan += "$word "
                }
            }
            else{
                println("3 huruf jawaban tidak ada index tidak ada maka tidak ketemu di kata soal $kata index $indexkata ")
                word = "<font color=#FF641A> $kata </font>"
                hasil_hafalan += "$word "
            }

        }
        // end cek kata
        println(hasil_hafalan)

        var score = 0
        val jmlslh = jmlstr - jmlbnr

        hasil = when {
            jmlbnr == 0 -> "Salah"
            jmlbnr == jmlstr -> "Benar"
            else -> "Salah"
        }

        if (hasil == "Salah") {
            var persentase = (jmlbnr.toDouble() / jmlstr.toDouble()) * 100.0
            score = persentase.toInt()
            binding.hasil.setTextColor(Color.parseColor("#FF0000"))
        } else {
            binding.hasil.setTextColor(Color.parseColor("#2aad46"))
            binding.jawaban.setTextColor(Color.parseColor("#2aad46"))
            score = 100
        }

        val scoretext = "Score : <br> ${if (score <= 50) "<font color=#FF0000>$score % </font>" else "<font color=#2aad46>$score % </font>"}"

        binding.hasil.text = hasil
        binding.soal.text = Html.fromHtml(hasil_hafalan)
        binding.jawaban.text = Html.fromHtml(scoretext)
        println(jmlbnr)
        println(hasil)
        println(score)
        binding.btnSelesai.setOnClickListener { finish() }

    }
}