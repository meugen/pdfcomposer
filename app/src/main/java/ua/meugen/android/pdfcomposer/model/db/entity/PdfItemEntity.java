package ua.meugen.android.pdfcomposer.model.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "pdfitems")
public class PdfItemEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String path;
    public long size;
    public Date date;
}
