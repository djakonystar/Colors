package uz.texnopos.colors

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private var countOfButtons = 0
    private var colors = arrayListOf<Int>()
    private var buttons = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countOfButtons = intent.getIntExtra("buttons", 0)

        repeat(countOfButtons / 2) {
            val col = (Random.nextInt(-16777216, 0))
            colors.add(col)
            colors.add(col)
        }

//        var size = colors.size
//        while (countOfButtons - colors.size >= size) {
//            colors.plusAssign(colors)
//        }
//        if (countOfButtons - colors.size != 0) {
//            size = colors.size
//            for (i in 0 until countOfButtons - size) {
//                colors.add(colors[i])
//            }
//        }
        colors.shuffle()

        val scrollView = ScrollView(this)
        setContentView(scrollView)

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        scrollView.addView(linearLayout)

        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(dp(16), dp(8), dp(16), dp(0))


        var selectedColor: Int? = null
        var selectedButton: Button? = null
        repeat(countOfButtons) {i ->
            val button = Button(this)
            button.layoutParams = layoutParams
            button.setBackgroundColor(colors[i])
            button.tag = colors[i]
            linearLayout.addView(button)
            buttons.add(button)
            button.setOnClickListener {
                if (selectedColor == null) {
                    selectedColor = button.tag as Int
                    selectedButton = it as Button
                } else if (button.tag == selectedColor && selectedButton != it) {
                    linearLayout.removeView(it)
                    linearLayout.removeView(selectedButton!!)
                    selectedButton = null
                    selectedColor = null
                } else {
                    selectedButton = null
                    selectedColor = null
                }

                if (linearLayout.childCount == 0) {
                    val intent = Intent(this, GameOverActivity::class.java)
                    Toast.makeText(this, "Oyin tamam!", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    fun dp (param : Int) : Int {
        return (param * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }
}