package br.com.fiap.mapview

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        //Informações que aparecerão no mapa
        val unidades = arrayOf(
            arrayOf("FIAP Campus Vila Olimpia", "Rua Olimpíadas,186\nSão Paulo - SP\nCEP:04551-000"),
            arrayOf("FIAP Campus Paulista", "Av. Paulista,1106\nSão Paulo - SP\nCEP: 01311-000"),
            arrayOf("FIAP Campus Vila Mariana", "Av. Lins de Vasconcelos, 1264\nSão Paulo - SP\nCEP: 01538-001")
        )

        // Coordenadas dos campus

        val fiapVilaOlimpia = LatLng(-23.5955843,-46.6851937)
        val fiapPaulista = LatLng(-23.5643721, -46.652857)
        val fiapVilaMariana = LatLng(-23.5746685, -46.6232043)

        //Inserir os marcadores no mapa da unidade 1
        googleMap.addMarker(
            MarkerOptions()
                .position(fiapVilaOlimpia)
                .title(unidades[0][0])
                .snippet(unidades[0][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))

        )

        //Inserir os marcadores no mapa da unidade 2
        googleMap.addMarker(
            MarkerOptions()
                .position(fiapPaulista)
                .title(unidades[1][0])
                .snippet(unidades[1][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))

        )

        //Inserir os marcadores no mapa da unidade 3
        googleMap.addMarker(
            MarkerOptions()
                .position(fiapVilaMariana)
                .title(unidades[2][0])
                .snippet(unidades[2][1])
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))

        )

        //Movimentar a câmera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fiapPaulista,12.5f))

        //Configurar a exibição dos detalhes
        googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoContents(marker: Marker): View? {
                val info = LinearLayout(applicationContext)
                info.orientation = LinearLayout.VERTICAL

                val title = TextView(applicationContext)
                title.setTextColor(Color.BLACK)
                title.gravity = Gravity.LEFT
                title.setTypeface(null,Typeface.BOLD)
                title.text = marker.title

                //--Complemento
                val snippet = TextView(applicationContext)
                snippet.setTextColor(Color.GRAY)
                snippet.text = marker.snippet

                //--Adiciona o título e o complemento na marca
                info.addView(title)
                info.addView(snippet)

                return info
            }

            override fun getInfoWindow(marker: Marker): View? {
                return null
            }



        })
    }
}