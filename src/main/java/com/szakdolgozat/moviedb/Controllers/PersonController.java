package com.szakdolgozat.moviedb.Controllers;


import com.szakdolgozat.moviedb.DTO.PersonDto;
import com.szakdolgozat.moviedb.Entities.Movieprogress;
import com.szakdolgozat.moviedb.Entities.Person;
import com.szakdolgozat.moviedb.Repository.PersonRepository;
import com.szakdolgozat.moviedb.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/find/{userid}")
    public List<Map<String,String>> findByUserid(@PathVariable Integer userid){
        List<Person> personList = personRepository.findAllByUserid_Id(userid);

        List<Map<String,String>> out = new ArrayList<>();

        for(Person person : personList){
            String id = person.getId().toString();
            //String userId = movieprogress.getUserid().toString();
            Boolean favflag = person.getFavflag();

            Integer perid = person.getPerid();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            //map.put("userId",userId);

            if(favflag != null){
                map.put("favflag",favflag.toString());
            }

            map.put("movieid",perid.toString());

            out.add(map);
        }

        return out;
    }

    @GetMapping("/find/{userid}/fav")
    public  List<Map<String,String>> getAllUserFav(@PathVariable Integer userid){
        List<Person> personList = personRepository.findAllByUserid_IdAndFavflag(userid, true);
        List<Map<String,String>> out = new ArrayList<>();

        for(Person person : personList){
            String id = person.getId().toString();

            Boolean favflag = person.getFavflag();

            Integer perid = person.getPerid();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            map.put("favflag",favflag.toString());

            map.put("perid",perid.toString());

            out.add(map);
        }

        return out;
    }

    @GetMapping("/find/{userid}/{perid}")
    public List<Map<String, String>> getUserMovieStatus(@PathVariable Integer userid, @PathVariable Integer perid){

        List<Person> personList = personRepository.findAllByUserid_IdAndPerid(userid, perid);

        List<Map<String,String>> out = new ArrayList<>();

        for(Person person : personList){
            String id = person.getId().toString();
            Boolean favflag = person.getFavflag();



            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            if(person.getFavflag() != null){

                map.put("favflag",favflag.toString());
            }
            map.put("perid",perid.toString());

            out.add(map);
        }

        return out;
    }
    @PutMapping("/updateFav/{userid}")
    public ResponseEntity<?> updateFavFlag(@RequestBody PersonDto personDto, @PathVariable Integer userid){
        personService.updatePersonFavFlag(personDto, userid);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}
