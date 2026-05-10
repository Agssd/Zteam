import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zteam.data.local.GenreDao
import com.example.zteam.data.local.GenreEntity
import com.example.zteam.database.GenreListConverter
import com.example.zteam.database.HotNewGameDao

@Database(
    entities = [GameEntity::class, GenreEntity::class, HotNewGameEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(GenreListConverter::class)
abstract class  GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun genreDao(): GenreDao
    abstract fun hotNewGameDao(): HotNewGameDao

    companion object {
        @Volatile
        private var Instance: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GameDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration() 
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
