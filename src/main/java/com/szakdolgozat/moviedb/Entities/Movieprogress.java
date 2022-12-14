package com.szakdolgozat.moviedb.Entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "movieprogress")
public class Movieprogress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "movid", nullable = false)
    private Integer movid;

    @Column(name = "favflag")
    private Boolean favflag;

    @Column(name = "flag", nullable = false)
    private Character flag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    public Movieprogress() {
        super();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovid() {
        return movid;
    }

    public void setMovid(Integer movid) {
        this.movid = movid;
    }

    public Boolean getFavflag() {
        return favflag;
    }

    public void setFavflag(Boolean favflag) {
        this.favflag = favflag;
    }

    public Character getFlag() {
        return flag;
    }

    public void setFlag(Character flag) {
        this.flag = flag;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Movieprogress( Integer movid, Boolean favlag, User userid){
        this.movid = movid;
        this.favflag = favlag;
        this.userid = userid;
    }

   /* public Movieprogress( Integer movid, Character flag ,Boolean favlag, User userid){
        this.movid = movid;
        this.flag = flag;
        this.favflag = favlag;
        this.userid = userid;
    } */

}