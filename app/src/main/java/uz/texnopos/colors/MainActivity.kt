package uz.texnopos.colors

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        setContentView(linearLayout)
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.marginStart = dp(16)
        layoutParams.marginEnd = dp(16)

        val editText = EditText(this)
        editText.layoutParams = layoutParams
        editText.hint = "Buttonlar sanin kiritin'"
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        linearLayout.addView(editText)

        val startButton = Button(this)
        startButton.layoutParams = layoutParams
        startButton.text = "Kettik"
        linearLayout.addView(startButton)



        startButton.setOnClickListener {
            when {
                editText.text.toString().toInt() % 2 != 0 -> {
                    Toast.makeText(this, "Iltimas, jup san kiritin'!", Toast.LENGTH_SHORT).show()
                }
                editText.text.toString().toInt() < 1 -> {
                    Toast.makeText(this, "Iltimas, 0den u'lken san kiritin'!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val intent = Intent(this, GameActivity::class.java)
                    intent.putExtra("buttons", editText.text.toString().toInt())
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