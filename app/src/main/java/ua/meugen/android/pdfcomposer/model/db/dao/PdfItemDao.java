package ua.meugen.android.pdfcomposer.model.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;
import ua.meugen.android.pdfcomposer.model.db.entity.PdfItemEntity;

@Dao
public interface PdfItemDao {

    @Insert
    void insert(PdfItemEntity... entity);

    @Query("SELECT * FROM pdfitems ORDER BY date DESC")
    Single<List<PdfItemEntity>> getAllPdfItems();
}
