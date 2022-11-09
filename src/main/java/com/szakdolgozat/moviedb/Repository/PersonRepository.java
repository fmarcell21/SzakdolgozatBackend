package com.szakdolgozat.moviedb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.szakdolgozat.moviedb.Entities.Person;

import java.util.List;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Integer> {

    List<Person>  findAllByUserid_IdAndPerid(Integer id, Integer perid);
    Boolean existsByUserid_IdAndPerid(Integer id, Integer perid);
    Person getByUserid_IdAndPerid(Integer id, Integer perid);

    List<Person> findAllByUserid_Id(Integer id);
    List<Person> findAllByUserid_IdAndFavflag(Integer id, Boolean favflag);

}
