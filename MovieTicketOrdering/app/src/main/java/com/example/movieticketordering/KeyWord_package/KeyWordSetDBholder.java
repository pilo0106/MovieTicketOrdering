package com.example.movieticketordering.KeyWord_package;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;




public class KeyWordSetDBholder extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME  = "keyword.db";
    public static final String KEYWORDSET_TABLE = "KEYWORDSET_TABLE";
    public static final String COLUMN_KEYWORDSET_ID = "ID";
    public static final String COLUMN_KEYWORDSET_NAME = "KEYWORDSET_NAME";
    public static final String COLUMN_kEYWORDSET_KEYWORD = "KEYWORDSET_KEYWORD";

    public final static String TableName = "KEYWORDSET_TABLE";
    public KeyWordSetDBholder(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    /*public KeyWordSetDBholder(@Nullable Context context){
        super(context, "keyword.db", null, 1);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 產生資料表的 SQL 寫在這 onCreate
        // 如果 Android 載入時找不到生成的資料庫檔案，就會觸發 onCreate
        String SQLTable = "CREATE TABLE IF NOT EXISTS "+ TableName +"( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Name TEXT, " +
                "Keyword TEXT" +
                ");";
        db.execSQL(SQLTable);

        insertInitialData(db);
    }

    // 插入初始数据的方法
    private void insertInitialData(SQLiteDatabase db) {

        String[][] initialData = {
                {"功夫熊貓4 Panda4", "阿波離開和平谷前往大城市尋找「神龍大俠」稱號的繼任者期間，遇上全新反派變色龍，她能夠召喚阿波過去的敵人。"},
                {"排球少年 Haikyu", "烏野高中排球隊朝著全國大賽冠軍邁進，對上全縣最強球隊白鳥澤學園高中。"},
                {"The Full Guy", "Colt Seavers, a Hollywood stunt performer, works as the stunt double for famous action star Tom Ryder. However, he gets severely injured during a stunt gone wrong and he abandons his career and his girlfriend Jody Moreno, a camerawoman.\\n\\n18 months later, Colt, now a valet for a small Mexican restaurant, is contacted by Gail Meyer, Tom's film producer. She tells him that Moreno is directing her first film, a science fiction epic called Metalstorm, and wants Colt to join the production in Sydney, Australia."},
                {"Godzilla x Kong: The New Empire", "Three years after defeating Mechagodzilla, Kong has established his new territory in the Hollow Earth and searches for more of his kind. On Earth's surface, Godzilla continues to maintain order between humanity and giant monsters, known as 'Titans' — killing Scylla in Rome and resting in the Colosseum afterwards.  A Monarch observation outpost stationed in Hollow Earth picks up an unidentified signal. On the surface, the signal causes Jia, the last known survivor of the Iwi tribe from Skull Island, to experience hallucinations and visions, causing her adoptive mother, Dr. Ilene Andrews, to worry."}
        };
        // 循环插入每条初始数据
        for (String[] data : initialData) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("Name", data[0]);
            contentValues.put("Keyword", data[1]);

            long newRowId = db.insert(TableName, null, contentValues);

            // 检查插入是否成功
            if (newRowId == -1) {
                Log.e("Database", "Failed to insert row: " + data[0]);
            } else {
                Log.i("Database", "Row inserted with id: " + newRowId);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade 則是如果資料庫結構有改變了就會觸發 onUpgrade

        final String SQL = "DROP TABLE "+ TableName;
        db.execSQL(SQL);
    }

    public void addData(String name, String keyword){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Keyword", keyword);
        db.insert(TableName, null, values);
    }
    public boolean addOne(KeyWordSet keywordSet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_KEYWORDSET_ID, keywordSet.getId());
        cv.put(COLUMN_KEYWORDSET_NAME, keywordSet.getName());
        cv.put(COLUMN_kEYWORDSET_KEYWORD, keywordSet.getKeyword());

        long insert = db.insert(KEYWORDSET_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }
    public void modify(String id, String name, String keyword){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Keyword", keyword);
        db.update(TableName, values, "_id = "+id, null);

    }
    public boolean deleteOne(KeyWordSet keywordSet){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TableName + " WHERE " + COLUMN_KEYWORDSET_ID + " = " + keywordSet.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){       //find the object
            return true;
        }
        else{
            return false;
        }
    }

    public int checkDBTable(){
        int result = 0; // 默认值为0，表示表不存在

        // 调试信息，打印TableName
        System.out.println("TableName: " + TableName);
        Log.d("checkTable", "TableName: " + TableName);

        Cursor cursor = getWritableDatabase().rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + TableName + "'", null);

        if(cursor != null){
            System.out.println("Cursor count: " + cursor.getCount()); // 打印查询结果的行数
            if(cursor.getCount() > 0){ // 如果表存在
                result = 1; // 设置结果为1
            } else { // 如果表不存在
                // 创建表
                getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + TableName + "( " +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Name TEXT, " +
                        "Keyword TEXT" +
                        ");");
            }
            cursor.close();
        }

        return result; // 返回结果，1表示表存在，0表示表不存在且已创建
    }



    public ArrayList<KeyWordSet> searchData(Cursor cursor){
        ArrayList<KeyWordSet> dbDataArrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String keyword = cursor.getString(2);

            KeyWordSet dbData = new KeyWordSet();
            dbData.setId(id);
            dbData.setName(name);
            dbData.setKeyword(keyword);
            dbDataArrayList.add(dbData);
        }
        cursor.close();
        return dbDataArrayList;
    }

    /*public List<KeyWordSet> getEveryone(){
        List<KeyWordSet> returnList = new ArrayList<>();
        //get dat from database
        String queryString = "SELECT * FROM " + KEYWORDSET_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);   //cursor: result set
        if(cursor.moveToFirst()){
            do{
                String keyWordSetId = cursor.getString(0);
                String keyWordSetName = cursor.getString(1);
                String keyWordSetKeyWord = cursor.getString(2);

                KeyWordSet newCustomer = new KeyWordSet(keyWordSetId, keyWordSetName, keyWordSetKeyWord);
                returnList.add(newCustomer);
            }while(cursor.moveToNext());
        }
        else{
            //failed. don't add anything
        }
        cursor.close();
        db.close();
        return returnList;
    }*/
    public List<KeyWordSet> getEveryone() {
        List<KeyWordSet> returnList = new ArrayList<>();
        // 從資料庫獲取數據
        String queryString = "SELECT * FROM " + TableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null); // cursor: 結果集

        if (cursor.moveToFirst()) {
            // 獲取列的索引
            int idIndex = cursor.getColumnIndex("_id");
            int nameIndex = cursor.getColumnIndex("Name");
            int keywordIndex = cursor.getColumnIndex("Keyword");

            do {
                // 獲取每一列的數據
                String keyWordSetId = cursor.getString(idIndex);
                String keyWordSetName = cursor.getString(nameIndex);
                String keyWordSetKeyWord = cursor.getString(keywordIndex);

                // 調試：打印每一行的數據
                Log.d("DBHelper", "ID: " + keyWordSetId + ", Name: " + keyWordSetName + ", Keyword: " + keyWordSetKeyWord);

                // 創建新的 KeyWordSet 對象並添加到列表中
                KeyWordSet newKeyWordSet = new KeyWordSet(keyWordSetId, keyWordSetName, keyWordSetKeyWord);

                returnList.add(newKeyWordSet);

            } while (cursor.moveToNext());
        } else {
            Log.d("DBHelper", "No data found in the table.");
        }

        cursor.close(); // 關閉 cursor
        db.close();     // 關閉資料庫
        //Log.d("DBHelper", "check: " + returnList );
        return returnList;
    }



    /*public List<KeyWordSet> getBack(String key) {
        List<KeyWordSet> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // 构建 SQL 查询语句，查找包含关键字的数据名
        String queryString = "SELECT DISTINCT " + COLUMN_KEYWORDSET_NAME + " FROM " + KEYWORDSET_TABLE +
                " WHERE " + COLUMN_KEYWORDSET_NAME + " LIKE ? OR " +
                COLUMN_kEYWORDSET_KEYWORD + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + key + "%", "%" + key + "%"};
        Log.d("Key", "key: " + key );
        for (String arg : selectionArgs) {
            Log.d("SelectionArg", "Arg value: " + arg);
        }

        Cursor cursor = db.rawQuery(queryString, selectionArgs);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_KEYWORDSET_NAME);
            do {
                String keywordSetName = cursor.getString(columnIndex);
                if (keywordSetName != null) {
                    Log.d("KeywordCheckInFolder", "keywordName: " + keywordSetName );
                    KeyWordSet newCustomer = new KeyWordSet(keywordSetName);
                    returnList.add(newCustomer);
                    Log.d("KeywordCheckReturnList", "returnList: " + returnList );
                }
                else{
                    Log.d("KeywordCheckInFolder", "no");
                }
            } while (cursor.moveToNext());
        } else {
            // 查询失败或无结果，这里添加相应的错误处理逻辑
            Log.e("DatabaseError", "No matching records found for the given keyword: " + key);
            // 可以抛出异常或者记录错误信息到日志等
            // 例如： throw new RuntimeException("No matching records found for the given keyword: " + key);
        }

        cursor.close();
        db.close();
        return returnList;
    }*/
    /*public List<String> getContent(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> values = new ArrayList<>();
        String query = "SELECT * FROM " + KEYWORDSET_TABLE + " WHERE " + COLUMN_KEYWORDSET_NAME + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});

        if (cursor.moveToFirst()) {
            do {
                String value = cursor.getString(2);
                values.add(value);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return values;
    }*/

    public List<KeyWordSet> searchWord(String search_word, KeyWordSetDBholder key_db) {
        SQLiteDatabase db = key_db.getReadableDatabase(); // 確保在 key_db 上調用
        List<KeyWordSet> returnList = new ArrayList<>();
        Log.d("search_wordCheck", "search_word: " + search_word);

        // 使用單個關鍵字進行搜索
        String[] columns = {"*"};
        String selection = "Name LIKE ?";
        String[] selectionArgs = new String[]{"%" + search_word + "%"};

        Cursor cursor = db.query("KEYWORDSET_TABLE", columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            // 獲取列的索引
            int idIndex = cursor.getColumnIndex("_id");
            int nameIndex = cursor.getColumnIndex("Name");
            int keywordIndex = cursor.getColumnIndex("Keyword");

            do {
                String generalId = cursor.getString(idIndex);
                String generalName = cursor.getString(nameIndex);
                String generalKeyword = cursor.getString(keywordIndex);

                // 這裡不再需要手動檢查 generalName.contains(search_word)，因為已經使用了 LIKE 查詢
                KeyWordSet newKeyWordSet = new KeyWordSet(generalId, generalName, generalKeyword);
                returnList.add(newKeyWordSet);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return returnList;
    }


    /**




    /**
     public boolean deleteOne(KeyWordSet keywordSet) {
     SQLiteDatabase db = this.getWritableDatabase();
     return db.delete(KEYWORDSET_TABLE, COLUMN_KEYWORDSET_ID + " = ?", new String[]{String.valueOf(keywordSet.getId())}) > 0;
     }
     */

}
