package com.lixa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class OrderDataBaseUtil extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "order_db";
	private static final int DATABASE_VERSION = 2;

	private static final String DEFAULT_ID = "_id integer primary key autoincrement";

	private String tableName;
	private String columns[];
	private String constraints[];

	public static final String ID = "_id";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String LOGINID = "loginid";
	public static final String REFDATE = "refdate";

	public static final String ORDER_ID = "order_id";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String TYPE = "type";
	public static final String VERSION = "version";
	public static final String PRICE = "price";
	public static final String IMAGE_PATH = "image_path";
	public static final String COLLECT = "collect"; // -1 默认，0为收藏
	public static final String CREATE_AT = "create_at";

	/**
	 */
	/*
	public OrderDataBaseUtil(Context ctx, String tbname, String cols[], String cons[]) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		tableName = tbname;
		columns = cols;
		constraints = cons;
	}
*/
	public OrderDataBaseUtil(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);

	}


	@Override
	public void onCreate(SQLiteDatabase db) {

		tableName="notes";
		columns= new String[]{TITLE, CONTENT, LOGINID, REFDATE, CREATE_AT};
		constraints=new String[]{OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL,OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL};
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE ").append(tableName);
		sql.append("(").append(DEFAULT_ID).append(",");
		for(int i = 0; i < columns.length; i++){
			if(i < columns.length -1)
				sql.append(columns[i]).append(" ").append(constraints[i]).append(",");
			else
				sql.append(columns[i]).append(" ").append(constraints[i]);
		}
		sql.append(")");
		Log.i("CREATE_TB==>>", sql.toString());
		db.execSQL(sql.toString());

		tableName="orders";
		columns= new String[]{ORDER_ID, NAME, DESCRIPTION, TYPE, PRICE, VERSION, IMAGE_PATH, COLLECT, CREATE_AT};
		constraints=new String[]{OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL,
				OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL, OrderStringUtil.TEXT_NOT_NULL,
				OrderStringUtil.TEXT_NOT_NULL};
		sql = new StringBuilder();
		sql.append("CREATE TABLE ").append(tableName);
		sql.append("(").append(DEFAULT_ID).append(",");
		for(int i = 0; i < columns.length; i++){
			if(i < columns.length -1)
				sql.append(columns[i]).append(" ").append(constraints[i]).append(",");
			else
				sql.append(columns[i]).append(" ").append(constraints[i]);
		}
		sql.append(")");
		Log.i("CREATE_TB==>>", sql.toString());
		db.execSQL(sql.toString());



		//OrderDataBaseUtil odb=new OrderDataBaseUtil(this);
		//OrderDataBaseUtil database=new OrderDataBaseUtil(this);
		//db=database.getWritableDatabase();
		//database.onUpgrade(db,1,2);
		//db.close();
		//db=this.getWritableDatabase();
		//this.onUpgrade(db,1,2);
		//db.close();
		/*
		OrderDataBaseUtil database=new OrderDataBaseUtil(this,this,this,this);
		db=database.getWritableDatabase();
		database.onUpgrade(db,1,2);
		db.close();
		*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + tableName;
		Log.i("DROP_TB==>>", sql);
		db.execSQL(sql);
		onCreate(db);
	}

	/**
	 * 得到一个可读的数据库
	 * @return
	 */
	public SQLiteDatabase openReadDB() {
		return this.getReadableDatabase();
	}

	/**
	 * 得到一个可读、可写的数据库
	 * @return
	 */
	public SQLiteDatabase openWriteDB() {
		return getWritableDatabase();
	}

}
