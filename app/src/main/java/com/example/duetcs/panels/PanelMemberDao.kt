package com.example.duetcs.panels
import androidx.room.*

@Dao
interface PanelMemberDao {
    @Insert
    suspend fun insertPanelMember(item: PanelMember)

    @Query("SELECT * FROM panel_members ORDER BY id ASC")
    fun getAllPanelMembers(): kotlinx.coroutines.flow.Flow<List<PanelMember>>

    @Delete
    suspend fun deletePanelMember(item: PanelMember)

    @Update
    suspend fun updatePanelMember(item: PanelMember)
}
