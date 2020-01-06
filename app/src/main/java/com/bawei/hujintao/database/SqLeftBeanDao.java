package com.bawei.hujintao.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.bawei.hujintao.model.bean.SqLeftBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SQ_LEFT_BEAN".
*/
public class SqLeftBeanDao extends AbstractDao<SqLeftBean, Void> {

    public static final String TABLENAME = "SQ_LEFT_BEAN";

    /**
     * Properties of entity SqLeftBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Json = new Property(0, String.class, "json", false, "JSON");
    }


    public SqLeftBeanDao(DaoConfig config) {
        super(config);
    }
    
    public SqLeftBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SQ_LEFT_BEAN\" (" + //
                "\"JSON\" TEXT);"); // 0: json
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SQ_LEFT_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SqLeftBean entity) {
        stmt.clearBindings();
 
        String json = entity.getJson();
        if (json != null) {
            stmt.bindString(1, json);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SqLeftBean entity) {
        stmt.clearBindings();
 
        String json = entity.getJson();
        if (json != null) {
            stmt.bindString(1, json);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public SqLeftBean readEntity(Cursor cursor, int offset) {
        SqLeftBean entity = new SqLeftBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // json
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SqLeftBean entity, int offset) {
        entity.setJson(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(SqLeftBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(SqLeftBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(SqLeftBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
