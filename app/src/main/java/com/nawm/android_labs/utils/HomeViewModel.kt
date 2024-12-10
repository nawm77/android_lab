//package com.nawm.android_labs.utils
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.nawm.android_labs.database.CharacterDatabase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class HomeViewModel(private val repository: Chara) : ViewModel() {
//
//    suspend fun getCharacters(): List<GoTCharacter> {
//        return withContext(Dispatchers.IO) {
//            repository.getCharactersFromDatabase()
//        }
//    }
//
//    class Factory(private val context: Context) : ViewModelProvider.Factory {
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            val api = ApiService.createApi()
//            val db = CharacterDatabase.getDatabase(context)
//            val repository = CharacterRepository(api, db.characterDao())
//            @Suppress("UNCHECKED_CAST")
//            return HomeViewModel(repository) as T
//        }
//    }
//}
