package com.azim.sharedprefdemo

import android.app.backup.BackupManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.azim.sharedprefdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // Declare constant / the key where our data will be saved
    private final val SAVED = "saved"
    private var backupManager : BackupManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        backupManager = BackupManager(this)

        binding.readDataBtn.isEnabled = false

        var savedData = loadPreference()
        if (savedData != "") {
            binding.readDataEditText.setText(savedData)
        } else {
            binding.readDataBtn.isEnabled = false
        }

        binding.saveDataBtn.setOnClickListener {
            if (binding.saveDataEditText.text.toString() != ""){
                savePreference(binding.saveDataEditText.text.toString())
            }
            // Enabled the read button
            binding.readDataBtn.isEnabled = true
        }

        binding.readDataBtn.setOnClickListener {
            val savedData = loadPreference()
            binding.readDataEditText.setText(savedData)
        }

    }

    private fun savePreference(value:String) {
        // Retrieve the share with mode private, only accessible in this app
        val sharedPreferences =
            getSharedPreferences(BackupData.PREFS_TEST, MODE_PRIVATE)
        // Enter the edit mode of shared preference
        val editor = sharedPreferences.edit();

        // Write the value "value" in key "key"
        // Nama : wan
        // Hobi : Melancong
        // ALamat : Bandung
        editor.putString(SAVED,value);
        // save the data
        editor.commit()

        Log.d("Debug","Calling backup Manager!!")
        backupManager?.dataChanged()
    }

    private fun loadPreference():String{
        val sharedPreferences =
            getSharedPreferences(BackupData.PREFS_TEST, MODE_PRIVATE)
        val saveData = sharedPreferences.getString(SAVED,"")
        return saveData!!
    }
}