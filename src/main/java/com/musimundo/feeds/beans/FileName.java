package com.musimundo.feeds.beans;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FILE_NAME")
public class FileName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "NAME", nullable = false)
    String name;

    @Column(name = "DATE", nullable = false)
    Date date;

    public FileName(String name){
        this.name = name;
        this.date = new Date();
    }

    public FileName(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
