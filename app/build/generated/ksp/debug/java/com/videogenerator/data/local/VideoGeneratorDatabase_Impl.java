package com.videogenerator.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.videogenerator.data.local.dao.GeneratedVideoDao;
import com.videogenerator.data.local.dao.GeneratedVideoDao_Impl;
import com.videogenerator.data.local.dao.ImageBatchDao;
import com.videogenerator.data.local.dao.ImageBatchDao_Impl;
import com.videogenerator.data.local.dao.VideoBatchDao;
import com.videogenerator.data.local.dao.VideoBatchDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VideoGeneratorDatabase_Impl extends VideoGeneratorDatabase {
  private volatile VideoBatchDao _videoBatchDao;

  private volatile ImageBatchDao _imageBatchDao;

  private volatile GeneratedVideoDao _generatedVideoDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `video_batches` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `batchName` TEXT NOT NULL, `folderPath` TEXT NOT NULL, `videoCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `image_batches` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `batchName` TEXT NOT NULL, `folderPath` TEXT NOT NULL, `imageCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `generated_videos` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `outputPath` TEXT NOT NULL, `duration` REAL NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, `type` TEXT NOT NULL, `batchName` TEXT NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9ad4fe298a545e21a1645ee4db8c12b8')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `video_batches`");
        db.execSQL("DROP TABLE IF EXISTS `image_batches`");
        db.execSQL("DROP TABLE IF EXISTS `generated_videos`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsVideoBatches = new HashMap<String, TableInfo.Column>(5);
        _columnsVideoBatches.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideoBatches.put("batchName", new TableInfo.Column("batchName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideoBatches.put("folderPath", new TableInfo.Column("folderPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideoBatches.put("videoCount", new TableInfo.Column("videoCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVideoBatches.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVideoBatches = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVideoBatches = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVideoBatches = new TableInfo("video_batches", _columnsVideoBatches, _foreignKeysVideoBatches, _indicesVideoBatches);
        final TableInfo _existingVideoBatches = TableInfo.read(db, "video_batches");
        if (!_infoVideoBatches.equals(_existingVideoBatches)) {
          return new RoomOpenHelper.ValidationResult(false, "video_batches(com.videogenerator.data.local.entity.VideoBatchEntity).\n"
                  + " Expected:\n" + _infoVideoBatches + "\n"
                  + " Found:\n" + _existingVideoBatches);
        }
        final HashMap<String, TableInfo.Column> _columnsImageBatches = new HashMap<String, TableInfo.Column>(5);
        _columnsImageBatches.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImageBatches.put("batchName", new TableInfo.Column("batchName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImageBatches.put("folderPath", new TableInfo.Column("folderPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImageBatches.put("imageCount", new TableInfo.Column("imageCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsImageBatches.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysImageBatches = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesImageBatches = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoImageBatches = new TableInfo("image_batches", _columnsImageBatches, _foreignKeysImageBatches, _indicesImageBatches);
        final TableInfo _existingImageBatches = TableInfo.read(db, "image_batches");
        if (!_infoImageBatches.equals(_existingImageBatches)) {
          return new RoomOpenHelper.ValidationResult(false, "image_batches(com.videogenerator.data.local.entity.ImageBatchEntity).\n"
                  + " Expected:\n" + _infoImageBatches + "\n"
                  + " Found:\n" + _existingImageBatches);
        }
        final HashMap<String, TableInfo.Column> _columnsGeneratedVideos = new HashMap<String, TableInfo.Column>(8);
        _columnsGeneratedVideos.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("outputPath", new TableInfo.Column("outputPath", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("duration", new TableInfo.Column("duration", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("width", new TableInfo.Column("width", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("height", new TableInfo.Column("height", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("batchName", new TableInfo.Column("batchName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGeneratedVideos.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGeneratedVideos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGeneratedVideos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGeneratedVideos = new TableInfo("generated_videos", _columnsGeneratedVideos, _foreignKeysGeneratedVideos, _indicesGeneratedVideos);
        final TableInfo _existingGeneratedVideos = TableInfo.read(db, "generated_videos");
        if (!_infoGeneratedVideos.equals(_existingGeneratedVideos)) {
          return new RoomOpenHelper.ValidationResult(false, "generated_videos(com.videogenerator.data.local.entity.GeneratedVideoEntity).\n"
                  + " Expected:\n" + _infoGeneratedVideos + "\n"
                  + " Found:\n" + _existingGeneratedVideos);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "9ad4fe298a545e21a1645ee4db8c12b8", "9f4e172481869d2cc35b635104b3c2af");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "video_batches","image_batches","generated_videos");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `video_batches`");
      _db.execSQL("DELETE FROM `image_batches`");
      _db.execSQL("DELETE FROM `generated_videos`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(VideoBatchDao.class, VideoBatchDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ImageBatchDao.class, ImageBatchDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GeneratedVideoDao.class, GeneratedVideoDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public VideoBatchDao videoBatchDao() {
    if (_videoBatchDao != null) {
      return _videoBatchDao;
    } else {
      synchronized(this) {
        if(_videoBatchDao == null) {
          _videoBatchDao = new VideoBatchDao_Impl(this);
        }
        return _videoBatchDao;
      }
    }
  }

  @Override
  public ImageBatchDao imageBatchDao() {
    if (_imageBatchDao != null) {
      return _imageBatchDao;
    } else {
      synchronized(this) {
        if(_imageBatchDao == null) {
          _imageBatchDao = new ImageBatchDao_Impl(this);
        }
        return _imageBatchDao;
      }
    }
  }

  @Override
  public GeneratedVideoDao generatedVideoDao() {
    if (_generatedVideoDao != null) {
      return _generatedVideoDao;
    } else {
      synchronized(this) {
        if(_generatedVideoDao == null) {
          _generatedVideoDao = new GeneratedVideoDao_Impl(this);
        }
        return _generatedVideoDao;
      }
    }
  }
}
