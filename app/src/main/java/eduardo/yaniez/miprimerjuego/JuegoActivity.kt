package eduardo.yaniez.miprimerjuego

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.random.Random


class JuegoActivity : AppCompatActivity() {
    lateinit var musicaFondo:MediaPlayer
    lateinit var respuestaUsuario:EditText
    lateinit var btnRespuesta:Button
    lateinit var sonidoRespuestaCorrecta:MediaPlayer
    lateinit var sonidoRespuestaIncorrecta:MediaPlayer
    var numeroGenerado = 0
    var numeroUsuario = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        initUI()
        musicaFondo = MediaPlayer.create(this, R.raw.fondo)
        sonidoRespuestaCorrecta = MediaPlayer.create(this,R.raw.correcto)
        sonidoRespuestaIncorrecta = MediaPlayer.create(this,R.raw.error)
        reproduceMusica()
        generaNumero()

        btnRespuesta.setOnClickListener {
            val respuesta = respuestaUsuario.text.toString()
            if (respuesta.equals("")){
                Toast.makeText(this, "INSANO, PON TU RESPUESTA", Toast.LENGTH_LONG).show()
                sonidoIncorrecto()
            }else{
                numeroUsuario = respuesta.toInt()
                if(numeroGenerado==numeroUsuario){
                    sonidoCorrecto()
                    Toast.makeText(this, "BUUUUUUENAS",Toast.LENGTH_SHORT).show()
                }else{
                    sonidoIncorrecto()
                    Toast.makeText(this, "FIUUUUUUBA $numeroGenerado ",Toast.LENGTH_SHORT).show()

                }
                generaNumero()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        musicaFondo.release()
        sonidoRespuestaCorrecta.release()
        sonidoRespuestaIncorrecta.release()
    }
    fun reproduceMusica(){
        musicaFondo.isLooping = true
        musicaFondo.setVolume(0.5f,0.5f)
        musicaFondo.start() // no need to call prepare(); create() does that for you
    }
    fun sonidoCorrecto(){
        sonidoRespuestaCorrecta.start()
    }
    fun sonidoIncorrecto(){
        sonidoRespuestaIncorrecta.start()
    }
    fun initUI(){
        btnRespuesta = findViewById(R.id.btnComprobar)
        respuestaUsuario = findViewById(R.id.etEntradaUsuario)
    }
    fun generaNumero(){
        numeroGenerado = Random.nextInt(1,7)

    }
}