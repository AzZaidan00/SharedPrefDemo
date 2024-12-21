package com.azim.sharedprefdemo

import android.app.backup.BackupAgentHelper
import android.app.backup.SharedPreferencesBackupHelper

// it extends BackUpAgentHelper
class BackupData : BackupAgentHelper() {
    companion object{
        val PREFS_TEST = "testPref"
        val MY_PRES_BACKUP_KEY = "myprefs"
    }

    // ehen the BackupData is created
    override fun onCreate() {
        super.onCreate()
        // Initializa the backup services
        // Create a SharedPreferencesBackupHelper
        // load the backup helper
        var helper = SharedPreferencesBackupHelper(
            this, PREFS_TEST
        )
        // add the helper to the backup agent
        addHelper(MY_PRES_BACKUP_KEY,helper)
    }
}