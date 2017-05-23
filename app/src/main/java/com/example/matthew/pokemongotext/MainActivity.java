package com.example.matthew.pokemongotext;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    String ip = "0.0.0.0";
    String sendIp = "192.168.0.120";
    int id = 0;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch s = (Switch) findViewById(R.id.switch1);
        populate();
        location(null);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.setText("Internet");
                    sendIp = "174.1.96.247";
                } else{
                    buttonView.setText("Local");
                    sendIp = "192.168.0.120";
                }

            }
        });
    }

    public void populate() {
        final String[] index = {
                "Bulbasaur",
                "Ivysaur",
                "Venusaur",
                "Charmander",
                "Charmeleon",
                "Charizard",
                "Squirtle",
                "Wartortle",
                "Blastoise",
                "Caterpie",
                "Metapod",
                "Butterfree",
                "Weedle",
                "Kakuna",
                "Beedrill",
                "Pidgey",
                "Pidgeotto",
                "Pidgeot",
                "Rattata",
                "Raticate",
                "Spearow",
                "Fearow",
                "Ekans",
                "Arbok",
                "Pikachu",
                "Raichu",
                "Sandshrew",
                "Sandslash",
                "Nidoran♀",
                "Nidorina",
                "Nidoqueen",
                "Nidoran♂",
                "Nidorino",
                "Nidoking",
                "Clefairy",
                "Clefable",
                "Vulpix",
                "Ninetales",
                "Jigglypuff",
                "Wigglytuff",
                "Zubat",
                "Golbat",
                "Oddish",
                "Gloom",
                "Vileplume",
                "Paras",
                "Parasect",
                "Venonat",
                "Venomoth",
                "Diglett",
                "Dugtrio",
                "Meowth",
                "Persian",
                "Psyduck",
                "Golduck",
                "Mankey",
                "Primeape",
                "Growlithe",
                "Arcanine",
                "Poliwag",
                "Poliwhirl",
                "Poliwrath",
                "Abra",
                "Kadabra",
                "Alakazam",
                "Machop",
                "Machoke",
                "Machamp",
                "Bellsprout",
                "Weepinbell",
                "Victreebel",
                "Tentacool",
                "Tentacruel",
                "Geodude",
                "Graveler",
                "Golem",
                "Ponyta",
                "Rapidash",
                "Slowpoke",
                "Slowbro",
                "Magnemite",
                "Magneton",
                "Farfetch’d",
                "Doduo",
                "Dodrio",
                "Seel",
                "Dewgong",
                "Grimer",
                "Muk",
                "Shellder",
                "Cloyster",
                "Gastly",
                "Haunter",
                "Gengar",
                "Onix",
                "Drowzee",
                "Hypno",
                "Krabby",
                "Kingler",
                "Voltorb",
                "Electrode",
                "Exeggcute",
                "Exeggutor",
                "Cubone",
                "Marowak",
                "Hitmonlee",
                "Hitmonchan",
                "Lickitung",
                "Koffing",
                "Weezing",
                "Rhyhorn",
                "Rhydon",
                "Chansey",
                "Tangela",
                "Kangaskhan",
                "Horsea",
                "Seadra",
                "Goldeen",
                "Seaking",
                "Staryu",
                "Starmie",
                "Mr. Mime",
                "Scyther",
                "Jynx",
                "Electabuzz",
                "Magmar",
                "Pinsir",
                "Tauros",
                "Magikarp",
                "Gyarados",
                "Lapras",
                "Ditto",
                "Eevee",
                "Vaporeon",
                "Jolteon",
                "Flareon",
                "Porygon",
                "Omanyte",
                "Omastar",
                "Kabuto",
                "Kabutops",
                "Aerodactyl",
                "Snorlax",
                "Articuno",
                "Zapdos",
                "Moltres",
                "Dratini",
                "Dragonair",
                "Dragonite",
                "Mewtwo",
                "Mew"
        };
        final String[] pokemon = {
                "Bulbasaur",
                "Ivysaur",
                "Venusaur",
                "Charmander",
                "Charmeleon",
                "Charizard",
                "Squirtle",
                "Wartortle",
                "Blastoise",
                "Caterpie",
                "Metapod",
                "Butterfree",
                "Weedle",
                "Kakuna",
                "Beedrill",
                "Pidgey",
                "Pidgeotto",
                "Pidgeot",
                "Rattata",
                "Raticate",
                "Spearow",
                "Fearow",
                "Ekans",
                "Arbok",
                "Pikachu",
                "Raichu",
                "Sandshrew",
                "Sandslash",
                "Nidoran♀",
                "Nidorina",
                "Nidoqueen",
                "Nidoran♂",
                "Nidorino",
                "Nidoking",
                "Clefairy",
                "Clefable",
                "Vulpix",
                "Ninetales",
                "Jigglypuff",
                "Wigglytuff",
                "Zubat",
                "Golbat",
                "Oddish",
                "Gloom",
                "Vileplume",
                "Paras",
                "Parasect",
                "Venonat",
                "Venomoth",
                "Diglett",
                "Dugtrio",
                "Meowth",
                "Persian",
                "Psyduck",
                "Golduck",
                "Mankey",
                "Primeape",
                "Growlithe",
                "Arcanine",
                "Poliwag",
                "Poliwhirl",
                "Poliwrath",
                "Abra",
                "Kadabra",
                "Alakazam",
                "Machop",
                "Machoke",
                "Machamp",
                "Bellsprout",
                "Weepinbell",
                "Victreebel",
                "Tentacool",
                "Tentacruel",
                "Geodude",
                "Graveler",
                "Golem",
                "Ponyta",
                "Rapidash",
                "Slowpoke",
                "Slowbro",
                "Magnemite",
                "Magneton",
                "Farfetch’d",
                "Doduo",
                "Dodrio",
                "Seel",
                "Dewgong",
                "Grimer",
                "Muk",
                "Shellder",
                "Cloyster",
                "Gastly",
                "Haunter",
                "Gengar",
                "Onix",
                "Drowzee",
                "Hypno",
                "Krabby",
                "Kingler",
                "Voltorb",
                "Electrode",
                "Exeggcute",
                "Exeggutor",
                "Cubone",
                "Marowak",
                "Hitmonlee",
                "Hitmonchan",
                "Lickitung",
                "Koffing",
                "Weezing",
                "Rhyhorn",
                "Rhydon",
                "Chansey",
                "Tangela",
                "Kangaskhan",
                "Horsea",
                "Seadra",
                "Goldeen",
                "Seaking",
                "Staryu",
                "Starmie",
                "Mr. Mime",
                "Scyther",
                "Jynx",
                "Electabuzz",
                "Magmar",
                "Pinsir",
                "Tauros",
                "Magikarp",
                "Gyarados",
                "Lapras",
                "Ditto",
                "Eevee",
                "Vaporeon",
                "Jolteon",
                "Flareon",
                "Porygon",
                "Omanyte",
                "Omastar",
                "Kabuto",
                "Kabutops",
                "Aerodactyl",
                "Snorlax",
                "Articuno",
                "Zapdos",
                "Moltres",
                "Dratini",
                "Dragonair",
                "Dragonite",
                "Mewtwo",
                "Mew"
        };
        Arrays.sort(pokemon);
        ListView list = (ListView)findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long x) {
                for(int i = 0; i < index.length; i++) {
                    if(pokemon[position].equals(index[i])) {
                        id = i+1;
                        break;
                    }
                }
                TextView nameText = (TextView)findViewById(R.id.name);
                nameText.setText(index[id-1]);
            }});
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, (pokemon));
        list.setAdapter(adapter);
    }

    public void send(View v) {
        final Handler handler = new Handler();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    InetAddress local = InetAddress.getByName(ip);
                    InetAddress local2 = InetAddress.getByName(sendIp);
                    Log.d("H: ", ip);
                    DatagramSocket socket = new DatagramSocket(4444, local);
                    byte send = (byte)id;
                    if(id > 127)
                        send = (byte)-(id-127);
                    byte[] buff = {send};
                    byte[] rec = new byte[20];
                    DatagramPacket packet = new DatagramPacket(buff, 1, local2, 4444);
                    DatagramPacket recv = new DatagramPacket(rec, rec.length);
                    socket.send(packet);
                    socket.receive(recv);
                    ByteBuffer buffer = ByteBuffer.wrap(recv.getData());
                    buffer.order(ByteOrder.LITTLE_ENDIAN);
                    final int time = buffer.getInt();
                    final float lat = buffer.getFloat();
                    final float lon = buffer.getFloat();
                    handler.post(new Runnable() {
                        public void run() {
                            TextView timeBox = (TextView)findViewById(R.id.time);
                            TextView latBox = (TextView)findViewById(R.id.lat);
                            TextView lonBox = (TextView)findViewById(R.id.lon);
                            timeBox.setText(time/60 + "m" + time%60 + "s");
                            latBox.setText(Float.toString(lat));
                            lonBox.setText(Float.toString(lon));
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText(" ", lat + "," + lon);
                            clipboard.setPrimaryClip(clip);
                        }
                    });
                    socket.close();

                } catch(Exception e) {
                    Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                }
            }
        };
        t.start();
        Toast.makeText(this, "The message has been sent", Toast.LENGTH_SHORT).show();
    }

    public void nearby(View v) {
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    InetAddress local = InetAddress.getByName(ip);
                    InetAddress local2 = InetAddress.getByName(sendIp);
                    DatagramSocket socket = new DatagramSocket(4444, local);
                    byte[] buff = {'a'};
                    DatagramPacket packet = new DatagramPacket(buff, buff.length, local2, 4444);
                    socket.send(packet);
                    socket.close();
                } catch(Exception e) {
                    Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                }
            }
        };
        t.start();
        Toast.makeText(this, "The message has been sent", Toast.LENGTH_SHORT).show();
    }

    public void location(View v) {
        Button button = (Button)findViewById(R.id.locate);
        final LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        try {
                            InetAddress local = InetAddress.getByName(ip);
                            InetAddress local2 = InetAddress.getByName(sendIp);
                            DatagramSocket socket = new DatagramSocket(4444, local);
                            float lat = (float)location.getLatitude();
                            float lon = (float)location.getLongitude();
                            Log.d("Lat: ", Float.toString(lon));
                            ByteBuffer buffer = ByteBuffer.allocate(10);
                            buffer.order(ByteOrder.LITTLE_ENDIAN);
                            buffer.putFloat(lat);
                            buffer.putChar(':');
                            buffer.putFloat(lon);
                            byte[] combined = buffer.array();
                            DatagramPacket packet = new DatagramPacket(combined, combined.length, local2, 4444);
                            socket.send(packet);
                            socket.close();

                        } catch(Exception e) {
                            Log.e("YOUR_APP_LOG_TAG", "I got an error", e);
                        }
                    }
                };
                t.start();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d("Status Changed", String.valueOf(status));
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d("Provider Enabled", provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d("Provider Disabled", provider);
            }
        };

        final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        final Looper looper = null;

        final Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);

        // Now whenever the button is clicked fetch the location one time
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestSingleUpdate(criteria, locationListener, looper);
            }
        });
    }
}

