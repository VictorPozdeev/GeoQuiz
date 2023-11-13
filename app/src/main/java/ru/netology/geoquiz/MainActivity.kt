package ru.netology.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import ru.netology.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question(R.string.question_russia, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        with(binding) {
            bTrue.setOnClickListener { _: View ->
                checkAnswer(true)
            }

            bFalse.setOnClickListener { _: View ->
                checkAnswer(false)
            }

            ibNext.setOnClickListener {
                currentIndex = (currentIndex + 1) % questionBank.size
                val questionTextResId = questionBank[currentIndex].textResId
                tvQuestion.setText(questionTextResId)
            }

            ibPrevious.setOnClickListener {
                currentIndex = if(currentIndex - 1 < 0) questionBank.size - 1 else (currentIndex - 1) % questionBank.size
                val questionTextResId = questionBank[currentIndex].textResId
                tvQuestion.setText(questionTextResId)
            }

            tvQuestion.setOnClickListener {
                currentIndex = (currentIndex + 1) % questionBank.size
                val questionTextResId = questionBank[currentIndex].textResId
                tvQuestion.setText(questionTextResId)
            }

            val questionTextResId = questionBank[currentIndex].textResId
            tvQuestion.setText(questionTextResId)


        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .apply { setGravity(Gravity.TOP, 0, 0); show() }
    }
}