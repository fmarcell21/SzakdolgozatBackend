package com.szakdolgozat.moviedb.Repository;

import com.szakdolgozat.moviedb.Entities.Movieprogress;
import com.szakdolgozat.moviedb.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieprogressRepository extends JpaRepository<Movieprogress, Integer> {

    List<Movieprogress> findAllByUserid_Id(Integer Id);

    List<Movieprogress> findAllByUserid_IdAndFavflag(Integer Id, Boolean favflag);
   // List<Movieprogress> findMovieprogressByUserid_IdAndFavflagAndMovid(Integer Id, Boolean favlag, Integer movid);
    List<Movieprogress> findAllByUserid_IdAndMovid(Integer Id, Integer movid);

   // List<Movieprogress> findMovieprogressesByMovid(Integer movid);

   // Boolean existsByUserid_Id(Integer id);
    Boolean existsByMovid(Integer id);

    List<Movieprogress> findAllByUserid_IdAndFlag(Integer Id, Character flag);

    Boolean existsByUserid_IdAndMovid(Integer id, Integer movid);

}
