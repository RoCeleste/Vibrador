package com.example.vibrador

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var vibrando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var info = findViewById<TextView>(R.id.info)
        var vibrar = findViewById<Button>(R.id.botonVibrador)
        var vibrador: Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrador = (getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
        } else {
            vibrador = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }


        vibrar.setOnClickListener {
            if (vibrando) {
                vibrador.cancel()
                info.visibility = View.INVISIBLE

                vibrando = false
                vibrar.text = "VIBRAR"
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrador.vibrate(VibrationEffect.createOneShot(1000 * 60 * 30, 255))
                    info.text = "Para conservar energia el vibrador se desactiva automaticamente en 30 minutos"
                    info.visibility = View.VISIBLE
                    vibrando = true
                    vibrar.text = "DETENER"

                } else {
                    vibrador.vibrate(1000 * 60 * 30)
                    info.text = "Para conservar energia el vibrador se desactiva automaticamente en 30 minutos"
                    info.visibility = View.VISIBLE
                    vibrando = true
                    vibrar.text = "DETENER"
                }
            }
        }
    }
}





