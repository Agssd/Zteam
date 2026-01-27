import androidx.compose.foundation.interaction.PressInteraction
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.zteam.data.Game

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val backgroundImage: String?,
    val rating: Float,
    val release: String?
) {
    fun toGame(): Game {
        return Game(
            id = id,
            name = name,
            backgroundImage = backgroundImage,
            rating = rating,
            released = release
        )
    }

    companion object {
        fun fromGame(game: Game): GameEntity {
            return GameEntity(
                id = game.id,
                name = game.name,
                backgroundImage = game.backgroundImage,
                rating = game.rating,
                release = game.released
            )
        }
    }
}
