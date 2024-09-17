package sandoval.eduardo.quizzapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Paso 2: Importar Clase para el funcionamiento de los botones
import android.widget.Button
import android.widget.Toast
import sandoval.eduardo.quizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private val quizViewModel: QuizViewModel by viewModels()

    //private lateinit var trueButton: Button;
    //private lateinit var falseButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Código aqui abajo

        //Paso 1: Obtener ID's de los botones Verdadero o Falso
        //trueButton = findViewById(R.id.true_button);
        //falseButton = findViewById(R.id.false_button);

        //Paso 3: Agregar Listener a los botones
        //trueButton.setOnClickListener { view: View ->
        binding.trueButton.setOnClickListener { view: View ->
            // Esto sucedera cuando se de click en el botón de Verdadero
            checkAnswer(true)
        }

        //falseButton.setOnClickListener { view: View ->
        binding.falseButton.setOnClickListener { view: View ->
            // Esto sucedera cuando se de click en el botón de Falso
            checkAnswer(false)
        }

        binding.prevButton.setOnClickListener{
            quizViewModel.moveToPrev()
            updateQuestion()
            Log.d(TAG, "Pase por el método onCreate")
            Log.d(TAG, "Tengo un QuizViewModel: $quizViewModel")
        }

        binding.nextButton.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()

        }

        //val questionTextResId = banco_preguntas[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)
        updateQuestion()
    }

    private fun updateQuestion(){
        val questionTexResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTexResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if(userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast

        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()

    }
}