package com.mightysana.utspmo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class ManagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manager_layout)
        setupToolbar()
        findViewById<SwitchCompat>(R.id.themeSwitch)?.visibility =
            if (this is ManagerActivity) View.GONE else View.VISIBLE
    }
}
