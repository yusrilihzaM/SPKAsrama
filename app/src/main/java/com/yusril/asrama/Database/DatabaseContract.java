package com.yusril.asrama.Database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public DatabaseContract() {
    }
    public static final class kriteria implements BaseColumns{
        public final static  String TABLE_NAME_KRITERIA="kriteria";
        public final static  String ID_KRITERIA=BaseColumns._ID;
        public final static  String KOLOM_KRITERIA_ABSEN_RUTIN="kriteria_absen_rutin";
        public final static  String KOLOM_KRITERIA_ABSEN_NON_RUTIN="kriteria_absen_non_rutin";
        public final static  String KOLOM_KRITERIA_PELANGGARAN="kriteria_pelanggaran";
        public final static  String KOLOM_KRITERIA_CATATAN="kriteria_catatan";
    }

    public static final class mahasiswa  implements BaseColumns{
        public final static  String TABLE_NAME_MAHASISWA="college_student";
        public final static  String ID_MAHASISWA=BaseColumns._ID;
        public final static  String KOLOM_MHS_NIM="nim";
        public final static  String KOLOM_MHS_NAMA="nama_mhs";
        public final static  String KOLOM_MHS_ABSEN_RUTIN="mhs_absen_rutin";
        public final static  String KOLOM_MHS_ABSEN_NON_RUTIN="mhs_absen_non_rutin";
        public final static  String KOLOM_MHS_PELANGGARAN="mhs_pelanggaran";
        public final static  String KOLOM_MHS_CATATAN="mhs_catatan";
    }
    public static final class tabel_normalisasi  implements BaseColumns{
        public final static  String TABLE_NAME_NORMALISASI="tabel_normalisasi";
        public final static  String KOLOM_NORMALISASI_NAMA="nama_normalisasi";
        public final static  String KOLOM_NORMALISASI_ABSEN_RUTIN="normalisasi_absen_rutin";
        public final static  String KOLOM_NORMALISASI_ABSEN_NON_RUTIN="normalisasi_absen_non_rutin";
        public final static  String KOLOM_NORMALISASI_PELANGGARAN="normalisasi_pelanggaran";
        public final static  String KOLOM_NORMALISASI_CATATAN="normalisasi_catatan";
    }
    public static final class tabel_value  implements BaseColumns{
        public final static  String TABLE_NAME_VALUE="tabel_value";
        public final static  String ID_VALUE=BaseColumns._ID;
        public final static  String KOLOM_VALUE_NAMA="nama_value";
        public final static  String KOLOM_VALUE="value";
    }
}
