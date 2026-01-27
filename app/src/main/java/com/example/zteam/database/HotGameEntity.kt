import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.zteam.data.Game
import com.example.zteam.data.Genre

@Entity(tableName = "hot_new_games")
data class HotNewGameEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val backgroundImage: String?,
    val genres: List<String>
) {
    fun toGame(): Game {
        return Game(
            id = id,
            name = name,
            backgroundImage = backgroundImage,
            genres = genres.map { Genre(0, it, "", null) } // id и slug будут пустыми
        )
    }

    companion object {
        fun fromGame(game: Game): HotNewGameEntity {
            return HotNewGameEntity(
                id = game.id,
                name = game.name,
                backgroundImage = game.backgroundImage,
                genres = game.genres.map { it.name }
            )
        }
    }
}
