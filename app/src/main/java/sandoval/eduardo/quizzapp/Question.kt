package sandoval.eduardo.quizzapp

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)