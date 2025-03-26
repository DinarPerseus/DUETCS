package com.example.duetcs.panels

import kotlinx.coroutines.flow.Flow

class PanelRepository(private val panelItemDao: PanelMemberDao) {
    val allPanelMembers: Flow<List<PanelMember>> = panelItemDao.getAllPanelMembers()

    suspend fun insert(item: PanelMember) {
        panelItemDao.insertPanelMember(item)
    }

    suspend fun delete(item: PanelMember) {
        panelItemDao.deletePanelMember(item)
    }

    suspend fun update(item: PanelMember) {
        panelItemDao.updatePanelMember(item)
    }
}
