package com.yusril.asrama.activity.hal_hasil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.yusril.asrama.Database.DataHelper;
import com.yusril.asrama.Database.DatabaseContract;
import com.yusril.asrama.Database.DatabaseHelper;
import com.yusril.asrama.R;
import com.yusril.asrama.activity.ModelSPK;
import com.yusril.asrama.activity.hal_tambah_mhs.TambahMahasiswa;

import org.w3c.dom.Text;

import static com.yusril.asrama.Database.DatabaseContract.tabel_value.KOLOM_VALUE;
import static com.yusril.asrama.Database.DatabaseContract.tabel_value.TABLE_NAME_VALUE;

public class PerhitunganActivity extends AppCompatActivity {
    private ModelSPK modelSPK;
    private static SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper databaseHelper;
    private static DataHelper dataHelper;
    private Cursor cursor;
    private TableLayout tabelNormalisasi, tabelPembobotan,tabelJarak,tabelValue;
    private TableRow row,rowp,rowjarak,rowValue;
    TextView xrutin,xrutinnon,xpelanggaran,xcatatan,txt
            ,nnama,nrutin,nnonrutin,npelanggaran,ncatatan
            ,tvNama,tvAbsenruitn,tvabsennonrutin,tvpelanggaran,tvcatatan
            ,tvNamap,tvAbsenruitnp,tvabsennonrutinp,tvpelanggaranp,tvcatatanp
            ,tvNamaMax,tvAbsenruitnMax,tvabsennonrutinMax,tvpelanggaranMax,tvcatatanMax
            ,tvNamaMin,tvAbsenruitnMin,tvabsennonrutinMin,tvpelanggaranMin,tvcatatanMin
            ,tvNamaJarak,tvJarakPositive,tvJarakNegativ
            ,tvNamaValue,nilaivalue,ranking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perhitungan);
        getSupportActionBar().setTitle("Perhitungan Model");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        databaseHelper = new DatabaseHelper(this);
        dataHelper = new DataHelper(PerhitunganActivity.this);
        sqLiteDatabase = databaseHelper.getWritableDatabase();

        modelSPK = new ModelSPK();
        modelSPK.clearData();
        modelSPK.cleardatanormalisai();
        modelSPK.clearPembobotan();
        modelSPK.clearDatajarak();
        modelSPK.clearValue();
        modelSPK.addList(this);
        modelSPK.DataX();
        modelSPK.addNormalisasi();
        modelSPK.addkriteria(this);
        modelSPK.addPembobotan();
        modelSPK.addNilaiIdeal();
        modelSPK.addJNilaiJarak();
        modelSPK.addValue(this);
        insertDataValue();
        modelSPK.addValueList(this);

        xrutin = findViewById(R.id.xrutin);
        xrutinnon = findViewById(R.id.xrutinnon);
        xpelanggaran = findViewById(R.id.pelanggaran);
        xcatatan = findViewById(R.id.catatan);
        txt = findViewById(R.id.Normalisasi);
        tvAbsenruitnMax=findViewById(R.id.MAXrutin);
        tvabsennonrutinMax=findViewById(R.id.MAXrutinnon);
        tvpelanggaranMax=findViewById(R.id.MAXpelanggaran);
        tvcatatanMax=findViewById(R.id.MAXcatatan);
        tvAbsenruitnMin=findViewById(R.id.Minrutin);
        tvabsennonrutinMin=findViewById(R.id.Minrutinnon);
        tvpelanggaranMin=findViewById(R.id.Minpelanggaran);
        tvcatatanMin=findViewById(R.id.Mincatatan);
//        txt.setText(modelSPK.Value.get(0)+"");
        tabelNormalisasi = findViewById(R.id.tabelNormalisasi);
        tabelPembobotan=findViewById(R.id.tabelPembobotan);
        tabelJarak=findViewById(R.id.tabelJArak);
        tabelValue=findViewById(R.id.tabelValue);
        xrutin.setText(modelSPK.Xabsenrutin + "");
        xrutinnon.setText(modelSPK.Xabsennonrutin + "");
        xpelanggaran.setText(modelSPK.Xpelangaran + "");
        xcatatan.setText(modelSPK.Xcatatan + "");
        for (int i = 0; i < modelSPK.id.size(); i++) {
            String nama = modelSPK.nama.get(i);
            double absenruitn = modelSPK.Normalisasiabsenrutinne.get(i);
            double absennonrutin = modelSPK.Normalisasiabsennonrutin.get(i);
            double pelanggaran = modelSPK.Normalisasipelanggaran.get(i);
            double catatan = modelSPK.Normalisasicatatan.get(i);
            row = new TableRow(this);
            tvNama = new TextView(this);
            tvAbsenruitn = new TextView(this);
            tvabsennonrutin = new TextView(this);
            tvpelanggaran = new TextView(this);
            tvcatatan = new TextView(this);
            tvNama.setTextSize(10);
            tvAbsenruitn.setTextSize(10);
            tvabsennonrutin.setTextSize(10);
            tvpelanggaran.setTextSize(10);
            tvcatatan.setTextSize(10);
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.1f);
            params.setMargins(2,0,0,2);
            params1.setMargins(1,0,0,2);
            tvNama.setLayoutParams(params1);
            tvAbsenruitn.setLayoutParams(params);
            tvabsennonrutin.setLayoutParams(params);
            tvpelanggaran.setLayoutParams(params);
            tvcatatan.setLayoutParams(params);
            tvNama.setPadding(0, 5, 0, 5);
            tvAbsenruitn.setPadding(0, 5, 0, 5);
            tvabsennonrutin.setPadding(0, 5, 0, 5);
            tvpelanggaran.setPadding(0, 5, 0, 5);
            tvcatatan.setPadding(0, 5, 0, 5);
            tvNama.setBackgroundColor(getResources().getColor(R.color.white));
            tvAbsenruitn.setBackgroundColor(getResources().getColor(R.color.white));
            tvabsennonrutin.setBackgroundColor(getResources().getColor(R.color.white));
            tvpelanggaran.setBackgroundColor(getResources().getColor(R.color.white));
            tvcatatan.setBackgroundColor(getResources().getColor(R.color.white));
            tvNama.setText(" " + nama);
            tvAbsenruitn.setText(" " + absenruitn);
            tvabsennonrutin.setText(" " + absennonrutin);
            tvpelanggaran.setText(" " + pelanggaran);
            tvcatatan.setText(" " + catatan);
            row.addView(tvNama);
            row.addView(tvAbsenruitn);
            row.addView(tvabsennonrutin);
            row.addView(tvpelanggaran);
            row.addView(tvcatatan);
            tabelNormalisasi.addView(row);
        }

        for (int i = 0; i < modelSPK.id.size(); i++) {
            String nama = modelSPK.nama.get(i);
            double absenruitp = modelSPK.Pembobotanabsenrutinne.get(i);
            double absennonrutip = modelSPK.Pembobotanabsennonrutin.get(i);
            double pelanggarap = modelSPK.Pembobotanpelanggaran.get(i);
            double catatanp = modelSPK.Pembobotancatatan.get(i);
            rowp = new TableRow(this);
            tvNamap = new TextView(this);
            tvAbsenruitnp = new TextView(this);
            tvabsennonrutinp = new TextView(this);
            tvpelanggaranp = new TextView(this);
            tvcatatanp = new TextView(this);
            tvNamap.setTextSize(10);
            tvAbsenruitnp.setTextSize(10);
            tvabsennonrutinp.setTextSize(10);
            tvpelanggaranp.setTextSize(10);
            tvcatatanp.setTextSize(10);
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.1f);
            params.setMargins(2,0,0,2);
            params1.setMargins(1,0,0,2);
            tvNamap.setLayoutParams(params1);
            tvAbsenruitnp.setLayoutParams(params);
            tvabsennonrutinp.setLayoutParams(params);
            tvpelanggaranp.setLayoutParams(params);
            tvcatatanp.setLayoutParams(params);
            tvNamap.setPadding(0, 5, 0, 5);
            tvAbsenruitnp.setPadding(0, 5, 0, 5);
            tvabsennonrutinp.setPadding(0, 5, 0, 5);
            tvpelanggaranp.setPadding(0, 5, 0, 5);
            tvcatatanp.setPadding(0, 5, 0, 5);
            tvNamap.setBackgroundColor(getResources().getColor(R.color.white));
            tvAbsenruitnp.setBackgroundColor(getResources().getColor(R.color.white));
            tvabsennonrutinp.setBackgroundColor(getResources().getColor(R.color.white));
            tvpelanggaranp.setBackgroundColor(getResources().getColor(R.color.white));
            tvcatatanp.setBackgroundColor(getResources().getColor(R.color.white));
            tvNamap.setText(" " + nama);
            tvAbsenruitnp.setText(" " + absenruitp);
            tvabsennonrutinp.setText(" " + absennonrutip);
            tvpelanggaranp.setText(" " + pelanggarap);
            tvcatatanp.setText(" " + catatanp);
            rowp.addView(tvNamap);
            rowp.addView(tvAbsenruitnp);
            rowp.addView(tvabsennonrutinp);
            rowp.addView(tvpelanggaranp);
            rowp.addView(tvcatatanp);
            tabelPembobotan.addView(rowp);
            tvAbsenruitnMax.setText(modelSPK.Maxrutin+"");
            tvabsennonrutinMax.setText(modelSPK.Maxnonrutin+"");
            tvpelanggaranMax.setText(modelSPK.Maxpel+"");
            tvcatatanMax.setText(modelSPK.Maxcat+"");
            tvAbsenruitnMin.setText(modelSPK.Minrutin+"");
            tvabsennonrutinMin.setText(modelSPK.Minnonrutin+"");
            tvpelanggaranMin.setText(modelSPK.Minpel+"");
            tvcatatanMin.setText(modelSPK.Mincat+"");
        }

        for (int i = 0; i < modelSPK.id.size(); i++) {
            String nama = modelSPK.nama.get(i);
            double Jarak_Positive = modelSPK.JarakPositif.get(i);
            double Jarak_Negative = modelSPK.JarakNegatf.get(i);

            rowjarak = new TableRow(this);
            tvJarakPositive = new TextView(this);
            tvJarakNegativ = new TextView(this);
            tvNamaJarak=new TextView(this);

            tvJarakPositive.setTextSize(10);
            tvJarakNegativ.setTextSize(10);
            tvNamaJarak.setTextSize(10);
            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.1f);
            params.setMargins(2,0,0,2);
            params1.setMargins(1,0,0,2);
            tvNamaJarak.setLayoutParams(params1);
            tvJarakPositive.setLayoutParams(params);
            tvJarakNegativ.setLayoutParams(params);

            tvNamaJarak.setPadding(0, 5, 0, 5);
            tvJarakPositive.setPadding(0, 5, 0, 5);
            tvJarakNegativ.setPadding(0, 5, 0, 5);

            tvNamaJarak.setBackgroundColor(getResources().getColor(R.color.white));
            tvJarakPositive.setBackgroundColor(getResources().getColor(R.color.white));
            tvJarakNegativ.setBackgroundColor(getResources().getColor(R.color.white));

            tvNamaJarak.setText(" " + nama);
            tvJarakPositive.setText(" " + Jarak_Positive);
            tvJarakNegativ.setText(" " + Jarak_Negative);

            rowjarak.addView(tvNamaJarak);
            rowjarak.addView(tvJarakPositive);
            rowjarak.addView(tvJarakNegativ);

            tabelJarak.addView(rowjarak);


        }

        for (int i = 0; i < modelSPK.id.size(); i++) {
            String nama = modelSPK.nama.get(i);

        }
//        displayDatabaseInfo();
        insertDataValue();
        for (int i=0;i<modelSPK.id.size();i++){
            tvNamaValue=new TextView(this);
            nilaivalue=new TextView(this);
            ranking=new TextView(this);
            rowValue = new TableRow(this);

            int rank=i+1;
            Double value=modelSPK.nilai_value.get(i);
            String nama=modelSPK.nama_value.get(i);

            tvNamaValue.setTextSize(10);
            nilaivalue.setTextSize(10);
            ranking.setTextSize(10);

            TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams params1 = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.1f);
            params.setMargins(2,0,0,2);
            params1.setMargins(1,0,0,2);
            tvNamaValue.setLayoutParams(params);
            nilaivalue.setLayoutParams(params);
            ranking.setLayoutParams(params);

            tvNamaValue.setPadding(0, 5, 0, 5);
            nilaivalue.setPadding(0, 5, 0, 5);
            ranking.setPadding(0, 5, 0, 5);

            tvNamaValue.setBackgroundColor(getResources().getColor(R.color.white));
            nilaivalue.setBackgroundColor(getResources().getColor(R.color.white));
            ranking.setBackgroundColor(getResources().getColor(R.color.white));

            tvNamaValue.setText(nama);
            nilaivalue.setText(value+"");
            ranking.setText(rank+"");
            rowValue.addView(tvNamaValue);
            rowValue.addView(nilaivalue);
            rowValue.addView(ranking);
            tabelValue.addView(rowValue);
        }

    }
    private void displayDatabaseInfo(){
        databaseHelper=new DatabaseHelper(this);
        sqLiteDatabase=databaseHelper.getReadableDatabase();
        String query=String.format("SELECT * FROM "+ TABLE_NAME_VALUE+" ORDER BY "+KOLOM_VALUE+" ASC");
        cursor=sqLiteDatabase.rawQuery(query,null);
        try {
//            TextView textView= (TextView) findViewById      (R.id.textView);
            txt.setText("Jumlah row di Value databae table "+cursor.getCount());
        }finally {
        }
    }
    private void insertDataValue(){
        dataHelper.open();
        dataHelper.deleteValue();
        for (int i=0;i<modelSPK.id.size();i++){
            dataHelper.insertValue(modelSPK.nama.get(i),modelSPK.Value.get(i));
        }
    }
    private void getDataValue(Context context){



    }

}
