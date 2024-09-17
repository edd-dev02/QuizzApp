package sandoval.eduardo.quizzapp

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val banco_preguntas = listOf(
        Question(R.string.pregunta_pais, false),
        Question(R.string.pregunta_color, true),
        Question(R.string.pregunta_comida, true)
    )

    private var currentIndex:Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY)?:0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = banco_preguntas[currentIndex].answer
    val currentQuestionText: Int
        get() = banco_preguntas[currentIndex].textResId
    fun moveToNext(){
        currentIndex = (currentIndex + 1) % banco_preguntas.size

    }

    fun moveToPrev(){
        if(currentIndex == 0){
            currentIndex = banco_preguntas.size - 1
        }else{
            currentIndex = (currentIndex - 1) % banco_preguntas.size
        }
    }

}