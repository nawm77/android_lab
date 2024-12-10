//import android.util.Log
//import com.nawm.android_labs.dao.CharacterDao
//import com.nawm.android_labs.domain.Character
//import com.nawm.android_labs.utils.RetrofitNetworkApi
//import com.nawm.android_labs.utils.toDomain
//import com.nawm.android_labs.utils.toEntity
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//class CharacterRepository(
//    private val characterDao: CharacterDao,
//    private val networkApi: RetrofitNetworkApi
//) {
//    val allCharacters: Flow<List<Character>> = characterDao.getAllCharacters()
//        .map { entities -> entities.map { it.toDomain() } }
//
//    suspend fun getCharactersInRange(startId: Int, endId: Int): List<Character> {
//        val charactersFromDb = characterDao.getCharactersInRange(startId, endId)
//            .map { it.toDomain() }
//
//        if (charactersFromDb.isNotEmpty()) {
//            Log.d("app", "return from db")
//            return charactersFromDb
//        }
//
//        val charactersFromApi = networkApi.getCharactersInRange(startId, endId)
//        Log.d("app", "return from API")
//        val entities = charactersFromApi.mapIndexed { index, character ->
//            character.toEntity(startId + index)
//        }
//        characterDao.insertCharacters(entities)
//        return charactersFromApi
//    }
//
//    suspend fun getCharacterById(id: Int): Character? {
//        val characterEntity = characterDao.getCharacterById(id)
//        if (characterEntity != null) {
//            return characterEntity.toDomain()
//        }
//
//        val characterFromApi = networkApi.getCharacterById(id)
//        if (characterFromApi != null) {
//            characterDao.insertCharacter(characterFromApi.toEntity(id))
//        }
//        return characterFromApi
//    }
//
//    suspend fun fetchAndSaveCharacters(startId: Int, endId: Int) {
//        val charactersFromApi = networkApi.getCharactersInRange(startId, endId)
//        val entities = charactersFromApi.mapIndexed { index, character -> character.toEntity(startId + index) }
//        characterDao.insertCharacters(entities)
//    }
//}