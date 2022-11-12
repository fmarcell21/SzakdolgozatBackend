package com.szakdolgozat.moviedb.Repository;

import com.szakdolgozat.moviedb.Entities.Tvprogress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvRepository extends JpaRepository<Tvprogress, Integer> {
    List<Tvprogress> findAllByUserid_Id(Integer id);
    List<Tvprogress> findAllByUserid_IdAndFlag(Integer id, Character flag);
    List<Tvprogress> findAllByUserid_IdAndFavflag(Integer id, Boolean favflag);

    List<Tvprogress> findAllByUserid_IdAndTvid(Integer id, Integer tvid);
    Boolean existsByTvid(Integer id);
    Boolean existsByUserid_IdAndTvid(Integer id, Integer tvid);
    Tvprogress getByUserid_IdAndTvid(Integer id, Integer tvid);
}
