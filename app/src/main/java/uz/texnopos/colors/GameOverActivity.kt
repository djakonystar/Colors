package uz.texnopos.colors

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        linearLayout.gravity = Gravity.CENTER_VERTICAL
        linearLayout.orientation = LinearLayout.VERTICAL
        setContentView(linearLayout)

        val textView = TextView(this)
        textView.text = "Oyin tamam!\nSabag'in'di oqi!"
        textView.setTextColor(Color.GREEN)
        textView.textAlignment = TEXT_ALIGNMENT_CENTER
        textView.textSize = dp(24)
        linearLayout.addView(textView)

        val restartButton = Button(this)
        restartButton.text = "Qayttan oynaw"
        linearLayout.addView(restartButton)
        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val quitButton = Button(this)
        quitButton.text = "Oyinnan shig'iw"
        linearLayout.addView(quitButton)
        quitButton.setOnClickListener {
            finish()
        }
    }

    fun dp (param: Int) : Float {
        return param * Resources.getSystem().displayMetrics.density + 0.5f
    }
}