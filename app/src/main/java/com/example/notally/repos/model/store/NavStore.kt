package com.example.notally.repos.model.store

import com.example.notally.R
import com.example.notally.repos.model.Label
import com.example.notally.repos.model.NavModel
import com.example.notally.repos.model.NoteBox

object NavStore {

    private const val DIVIDER_LABELS = "Labels"
    private const val NOTE_ID = 1L
    private const val CALENDAR_ID = 2L
    private const val REMINDER_ID = 3L
    private const val TRASH_ID = 4L
    private const val ARCHIVE_ID = 5L

    private const val LABEL_DESIGN_ID = 1L
    private const val LABEL_WORK_ID = 2L
    private const val LABEL_OUTSIDE_ID = 3L
    private const val LABEL_HOME_ID = 4L

    val navMenuItems = mutableListOf(
        NavModel.NavMenuItem(
            NOTE_ID,
            R.drawable.ic_baseline_notes_24,
            R.string.title_note_nav,
            false,
            NoteBox.NOTE
        ),
        NavModel.NavMenuItem(
            CALENDAR_ID,
            R.drawable.ic_baseline_calendar_today_24,
            R.string.title_calendar_nav,
            false,
            NoteBox.CALENDAR
        ),
        NavModel.NavMenuItem(
            REMINDER_ID,
            R.drawable.ic_reminder,
            R.string.title_reminder_nav,
            false,
            NoteBox.REMINDER
        ),
        NavModel.NavMenuItem(
            TRASH_ID,
            R.drawable.ic_baseline_delete_24,
            R.string.title_delete_nav,
            false,
            NoteBox.TRASH
        ),
        NavModel.NavMenuItem(
            ARCHIVE_ID,
            R.drawable.ic_baseline_archive_24,
            R.string.title_archive_nav,
            false,
            NoteBox.ARCHIVED
        ),
    )

    val labelsDivider = NavModel.NavDivider(DIVIDER_LABELS)

    private val defaultLabels = listOf(
        Label(LABEL_DESIGN_ID, "Design"),
        Label(LABEL_WORK_ID, "Work"),
        Label(LABEL_OUTSIDE_ID, "Outside"),
        Label(LABEL_HOME_ID, "Home")
    )

    val navLabelItems = defaultLabels.map {
        NavModel.NavLabelItem(it.id, it.name)
    }

    fun setNavigationMenuItemChecked(id: Long): Boolean {
        var updated = false
        navMenuItems.forEachIndexed { index, item ->
            val shouldCheck = item.id == id
            if (item.checked != shouldCheck) {
                navMenuItems[index] = item.copy(checked = shouldCheck)
                updated = true
            }
        }
        return updated
    }
}
