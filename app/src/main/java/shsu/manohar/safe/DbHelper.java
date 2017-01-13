package shsu.manohar.safe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.io.IOException;

/**
 * Created by vemul on 11/08/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "SAFE.db";
    private static final String DB_PATH = "/data/data/shsu.manohar.safe/databases/";
    public static final String table_1 = "item";
    public static final String table_2 = "brand";
    public static final String table_3 = "store";
    private final Context context;
    private SQLiteDatabase db;

    public DbHelper(Context context) {


        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    // Creates a empty database on the system and rewrites it with your own database.
    public void Create() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
        } else {
            // By calling this method and empty database will be created into the default system path
            // of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // Check if the database exist to avoid re-copy the data
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            // database don't exist yet.
            e.printStackTrace();
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null ? true : false;
    }

    // copy your assets db
    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    //Open the database
    public boolean open() {
        try {
            String myPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            return true;
        } catch (SQLException sqle) {
            db = null;
            return false;
        }
    }

    @Override
    public synchronized void close() {
        if (db != null)
            db.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getItemData(String type, String level, String state) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (level.equalsIgnoreCase("Strict")) {
            String selectquery_strict = "SELECT i.itemName, i.sulfites, s.storeName, s.storeType FROM item i JOIN brand b ON i.brandID = b.brandID JOIN store s ON b.storeID = s.storeID WHERE  s.region = '"+state+"' and i.category = '"+type+"' and i.sulfites = 'S'";
            Cursor res = db.rawQuery(selectquery_strict, null);
            return res;
        } else {
            String selectquery = "SELECT i.itemName, i.sulfites, s.storeName, s.storeType FROM item i JOIN brand b ON i.brandID = b.brandID JOIN store s ON b.storeID = s.storeID WHERE  s.region = '"+state+"' and i.category = '"+type+"' and i.sulfites = 'S' or 'X' ";
            Cursor res = db.rawQuery(selectquery, null);
            return res;
        }
    }

    public Cursor getNameData(String name,String level, String state){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectquery_strict = "SELECT i.itemName, i.sulfites, s.storeName, s.storeType FROM item i JOIN brand b ON i.brandID = b.brandID JOIN store s ON b.storeID = s.storeID WHERE  i.itemName = '"+name+"' and s.region = '"+state+"'";
        Cursor res = db.rawQuery(selectquery_strict, null);
        return res;
    }
}


