package com.example.muhyiddin.kamusbatakindonesia;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.muhyiddin.kamusbatakindonesia.Adapter.RecycleAdapter;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity{
    @BindView(R.id.idsearch)SearchView idsearch;

    LayoutInflater inflater;
    ListView lv;
    View dialogView;
    SearchView searchView;
    TextView textview1;
    TextView textview2;
    ArrayAdapter<String> adapter;
    List<BahasaBatakTerbaru> listBatak = new ArrayList<>();
    RecycleAdapter adapterRV;
    RecyclerView rv;
    ImageButton tombolswitch;
    SearchView search;





    String [] kosakatabatak= {"ampunima","anjuon","aha laning","arsaki","andiganma",
            "batu pinangka","bingkasna","ditangianghi","dituhilhon",
            "diparhanalom","diaena","ebaebana","elekan","etengetengma",
            "etengma","gugui","gabusta","gorengma",
            "hupaias","hupahatop","hutuhilhon","hudabuhon","ihurmu",
            "ihurhu","inumonta","jaithon","jonggol","jamitai",
            "jolma i","kalekalemu","kaletekta","kaletekmu",
            "lam tu naekna","lae muna","langulangu","lumanguan",
            "marmarga","malumma","marnangon","narade","ndang marsalaoar","nagkohan","nagkir","nanggo sada dang adong",
            "otoni on","otoni","ontakna","ontakanna","ongkosta","panangkasion","podangta","podangmu","podanghu","podangnya","pinangka","ruarna","rohaon",
            "rayar","raja na lasang","robeani","sasudena i","sude i","sahatop","sahitni","salaoar jempekna","tapaias",
            "tapahatop","tartuhil","tardabu","tudia nama ahu","ulaonmi","ulaonhi","utus","uhumma","uhumanta"};
    String [] artibatak = {"ampunilah","yang dibujuk","apa gerangan","susahi","kapanlah",
            "batu nangka","sebabnya","didoaku","dipahatkan",
            "dirasa senang","dideritanya","sisa-sisanya","rayuan","kecil-kecillah",
            "kecillah","tanggungjawab bersama","dusta kita","gorenglah",
            "kubersihkan","kupercepat","kupahatkan","kujatuhkan","pantatmu",
            "pantatku","minuman kita","jahitkan","bingkah","ceritain",
            "orang itu","teman-temanmu","sandal kita","sandalmu",
            "semakin bertambah","ipar kalian","hambar","lebih tawar",
            "bermarga","sembuhlah","kuat","yang siap/yang sedia","tak bercelana/tak memakai celana",
            "lereng","akal/cerdik","satupun tidak ada","bodohnya ini","bodohnya","denyutnya","denyutannya","ongkos kita",
            "Wawancara","pedang kita","pedang kamu","pedang aku","pedangnya","biji buah nangka","luarnya",
            "hati ini","layar","raja yang mulia","kaki bukit itu","semuanya itu","semua itu","secepat",
            "sakitnya","celana pendeknya","kita bersihkan","kita percepat","terpahat","terjatuh",
            "kemanalah saya","pekerjaanmu","pekerjaanku","utus","hukumlah","hukuman kita"};

    private String switcher = "BATAK_TO_INDONESIA";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor()).build();


        AppDataBase db= Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"BahasaBatakTerbaru").allowMainThreadQueries().build();
        for (int i = 0; i < kosakatabatak.length; i++) {
            BahasaBatakTerbaru batak= new BahasaBatakTerbaru();
            batak.setArti (artibatak[i]);
            batak.setBatak(kosakatabatak[i]);
            listBatak.add(batak);
        }

        //db.userDao().insertALL(listBatak);
        //db.userDao().deleteKamusBatak(listBatak);
        //kosakatabatak= db.userDao().viewbatak();
        listBatak= db.userDao().getAll();
        //Log.e("halo",String.valueOf(listBatak.size()));

        search=(SearchView) findViewById(R.id.idsearch);
        tombolswitch=(ImageButton) findViewById(R.id.tombolswitch);
        rv=(RecyclerView) findViewById(R.id.idlistview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        searchView=(SearchView) findViewById(R.id.idsearch);
        adapterRV=new RecycleAdapter(listBatak,  switcher);
        rv.setAdapter(adapterRV);
        textview1=(TextView) findViewById(R.id.kiri);
        textview2=(TextView) findViewById(R.id.kanan);



        tombolswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp=textview1.getText().toString();
                String temp2=textview2.getText().toString();
                textview1.setText(temp2);
                textview2.setText(temp);

                if(switcher=="BATAK_TO_INDONESIA"){
                    switcher = "INDONESIA_TO_BATAK";
                    adapterRV = new RecycleAdapter(listBatak, switcher);
                    rv.setAdapter(adapterRV);
                    adapterRV.notifyDataSetChanged();
                    search.setQueryHint("Cari Kata Indonesia Disini...");
                }else{
                    switcher= "BATAK_TO_INDONESIA";
                    adapterRV=new RecycleAdapter(listBatak,switcher);
                    rv.setAdapter(adapterRV);
                    adapterRV.notifyDataSetChanged();
                    search.setQueryHint("Cari Kata Batak Disini...");
                }

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapterRV.getFilter().filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterRV.getFilter().filter(s);
                return false;
            }
        });



        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.loopicon);

                if(switcher=="BATAK_TO_INDONESIA"){
                    dialog.setMessage(adapterRV.getmDataset().get(position).getArti());
                    dialog.setTitle("Bahasa Indonesia:");
                }else{
                    dialog.setMessage(adapterRV.getmDataset().get(position).getBatak());
                    dialog.setTitle("Bahasa Batak:");
                }
                dialog.setNegativeButton("TUTUP ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });



    }







}

