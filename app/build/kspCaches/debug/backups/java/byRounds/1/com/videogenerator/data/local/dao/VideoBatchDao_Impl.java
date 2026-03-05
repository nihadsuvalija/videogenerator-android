package com.videogenerator.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.videogenerator.data.local.entity.VideoBatchEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class VideoBatchDao_Impl implements VideoBatchDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<VideoBatchEntity> __insertionAdapterOfVideoBatchEntity;

  private final EntityDeletionOrUpdateAdapter<VideoBatchEntity> __updateAdapterOfVideoBatchEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public VideoBatchDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVideoBatchEntity = new EntityInsertionAdapter<VideoBatchEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `video_batches` (`id`,`batchName`,`folderPath`,`videoCount`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VideoBatchEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBatchName());
        statement.bindString(3, entity.getFolderPath());
        statement.bindLong(4, entity.getVideoCount());
        statement.bindLong(5, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfVideoBatchEntity = new EntityDeletionOrUpdateAdapter<VideoBatchEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `video_batches` SET `id` = ?,`batchName` = ?,`folderPath` = ?,`videoCount` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final VideoBatchEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getBatchName());
        statement.bindString(3, entity.getFolderPath());
        statement.bindLong(4, entity.getVideoCount());
        statement.bindLong(5, entity.getCreatedAt());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM video_batches WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final VideoBatchEntity entity,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfVideoBatchEntity.insertAndReturnId(entity);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final VideoBatchEntity entity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfVideoBatchEntity.handle(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<VideoBatchEntity>> getAll() {
    final String _sql = "SELECT * FROM video_batches ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"video_batches"}, new Callable<List<VideoBatchEntity>>() {
      @Override
      @NonNull
      public List<VideoBatchEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBatchName = CursorUtil.getColumnIndexOrThrow(_cursor, "batchName");
          final int _cursorIndexOfFolderPath = CursorUtil.getColumnIndexOrThrow(_cursor, "folderPath");
          final int _cursorIndexOfVideoCount = CursorUtil.getColumnIndexOrThrow(_cursor, "videoCount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<VideoBatchEntity> _result = new ArrayList<VideoBatchEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final VideoBatchEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBatchName;
            _tmpBatchName = _cursor.getString(_cursorIndexOfBatchName);
            final String _tmpFolderPath;
            _tmpFolderPath = _cursor.getString(_cursorIndexOfFolderPath);
            final int _tmpVideoCount;
            _tmpVideoCount = _cursor.getInt(_cursorIndexOfVideoCount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new VideoBatchEntity(_tmpId,_tmpBatchName,_tmpFolderPath,_tmpVideoCount,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super VideoBatchEntity> $completion) {
    final String _sql = "SELECT * FROM video_batches WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<VideoBatchEntity>() {
      @Override
      @Nullable
      public VideoBatchEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBatchName = CursorUtil.getColumnIndexOrThrow(_cursor, "batchName");
          final int _cursorIndexOfFolderPath = CursorUtil.getColumnIndexOrThrow(_cursor, "folderPath");
          final int _cursorIndexOfVideoCount = CursorUtil.getColumnIndexOrThrow(_cursor, "videoCount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final VideoBatchEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBatchName;
            _tmpBatchName = _cursor.getString(_cursorIndexOfBatchName);
            final String _tmpFolderPath;
            _tmpFolderPath = _cursor.getString(_cursorIndexOfFolderPath);
            final int _tmpVideoCount;
            _tmpVideoCount = _cursor.getInt(_cursorIndexOfVideoCount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new VideoBatchEntity(_tmpId,_tmpBatchName,_tmpFolderPath,_tmpVideoCount,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
