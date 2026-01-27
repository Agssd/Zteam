import android.util.Log
import com.example.zteam.data.Game
import com.example.zteam.data.Genre
import com.example.zteam.data.local.GenreDao
import com.example.zteam.data.local.GenreEntity
import com.example.zteam.data.remote.NetworkDataSource
import com.example.zteam.database.HotNewGameDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GameRepository(
    private val gameDao: GameDao,
    private val genreDao: GenreDao,
    private val hotNewGameDao: HotNewGameDao,
    private val networkDataSource: NetworkDataSource
) {
    fun getPopularGames(): Flow<List<Game>> {
        return gameDao.getAllGamesFlow()
            .map { entities ->
                entities.map { it.toGame() }
            }
            .catch { emit(emptyList()) }
    }

    suspend fun refreshPopularGames() {
        try {
            val response = networkDataSource.getPopularGames()
            val games = response.results
            gameDao.clearGames()
            gameDao.insertGames(games.map { GameEntity.fromGame(it) })
        } catch (e: Exception) {
            Log.e("ROOM", "Error refresh")
        }
    }

    suspend fun refreshGenres() {
        try {
            val response = networkDataSource.getGenres()
            val entities = response.results.map { GenreEntity.fromGenre(it) }
            genreDao.insertGenres(entities)
        } catch (e: Exception) {
            Log.e("GameRepository", "Failed to refresh genres: ${e.message}")
        }
    }

    fun getGenres(): Flow<List<Genre>> {
        return genreDao.getAllGenres().map { entities ->
            entities.map { it.toGenre() }
        }
    }

    fun getHotNewGames(): Flow<List<Game>> = hotNewGameDao.getAll().map { list ->
        list.map { it.toGame() }
    }

    suspend fun refreshHotNewGames() {
        val today = LocalDate.now()
        val lastYear = today.minusYears(1)
        val dateRange = "${lastYear},${today}"

        val response = networkDataSource.getHotNewGames()
        val entities = response.results.map { HotNewGameEntity.fromGame(it) }
        hotNewGameDao.insertAll(entities)
    }
}
