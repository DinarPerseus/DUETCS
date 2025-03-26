package com.example.duetcs.panels
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PanelMember::class], version = 1, exportSchema = false)
abstract class PanelDatabase : RoomDatabase() {
    abstract fun panelMemberDao(): PanelMemberDao

    companion object {
        @Volatile
        private var INSTANCE: PanelDatabase? = null

        fun getDatabase(context: Context): PanelDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PanelDatabase::class.java,
                    "panel_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
