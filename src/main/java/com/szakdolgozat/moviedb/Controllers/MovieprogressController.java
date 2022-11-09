package com.szakdolgozat.moviedb.Controllers;

import com.szakdolgozat.moviedb.DTO.IdDTO;
import com.szakdolgozat.moviedb.DTO.MovieprogressDto;
import com.szakdolgozat.moviedb.DTO.UserDto;
import com.szakdolgozat.moviedb.Entities.Movieprogress;

import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Repository.MovieprogressRepository;
import com.szakdolgozat.moviedb.Service.MovieprogressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/movie")
public class MovieprogressController {

    @Autowired
    private MovieprogressService movieprogressService;
    @Autowired
    private MovieprogressRepository movieprogressRepository;

    public MovieprogressController( MovieprogressRepository movieprogressRepository) {
        this.movieprogressRepository = movieprogressRepository;
    }


    @GetMapping("/find/{userid}")
    public  List<Map<String,String>> findByUserid(@PathVariable Integer userid){
        List<Movieprogress> movieprogressList = movieprogressRepository.findAllByUserid_Id(userid);
       // System.out.print(movieprogressList);
        List<Map<String,String>> out = new ArrayList<>();

        for(Movieprogress movieprogress : movieprogressList){
            String id = movieprogress.getId().toString();
            //String userId = movieprogress.getUserid().toString();
            Boolean favflag = movieprogress.getFavflag();
            Character flag = movieprogress.getFlag();
            Integer movieid = movieprogress.getMovid();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            //map.put("userId",userId);

            if(favflag != null){
                map.put("favflag",favflag.toString());
            }
            map.put("flag",flag.toString());
            map.put("movieid",movieid.toString());

            out.add(map);
        }

        return out;
    }

    @GetMapping("/find/{userid}/fav")
    public  List<Map<String,String>> getAllUserFav(@PathVariable Integer userid){
        List<Movieprogress> movieprogressList = movieprogressRepository.findAllByUserid_IdAndFavflag(userid, true);
        List<Map<String,String>> out = new ArrayList<>();

        for(Movieprogress movieprogress : movieprogressList){
            String id = movieprogress.getId().toString();

            Boolean favflag = movieprogress.getFavflag();
            Character flag = movieprogress.getFlag();
            Integer movieid = movieprogress.getMovid();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            map.put("favflag",favflag.toString());
            map.put("flag",flag.toString());
            map.put("movieid",movieid.toString());

            out.add(map);
        }

        return out;
    }
    @GetMapping("/find/{userid}/{movid}")
    public List<Map<String, String>> getUserMovieStatus(@PathVariable Integer userid, @PathVariable Integer movid){
        //Map<String, String> out = new HashMap<String, String>();
       // Movieprogress movieprogress = movieprogressRepository.findMovieprogressByUserid_IdAndFavflagAndMovid(userid, true, movid);
        //System.out.print(movieprogress);
       // Map<String, String> map = new LinkedHashMap<>();
        List<Movieprogress> movieprogressList = movieprogressRepository.findAllByUserid_IdAndMovid(userid, movid);
        //List<Movieprogress> movieprogressList = movieprogressRepository.findMovieprogressesByMovid(movid);
        List<Map<String,String>> out = new ArrayList<>();

        for(Movieprogress movieprogress : movieprogressList){
            String id = movieprogress.getId().toString();

            Boolean favflag = movieprogress.getFavflag();
            Character flag = movieprogress.getFlag();
            Integer movieid = movieprogress.getMovid();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            if(favflag != null) {
                map.put("favflag",favflag.toString());
            }

            map.put("flag",flag.toString());
            map.put("movieid",movieid.toString());

            out.add(map);
        }

        return out;
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMovieprogress(@RequestBody IdDTO idDTO){
        //System.out.println(idDTO);
        movieprogressService.deleteMovieprogress(idDTO);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateFav")
    public ResponseEntity<?> updateFavFlag(@RequestBody MovieprogressDto movieprogressDto){
        movieprogressService.updateMovieprogressFavFlag(movieprogressDto);

        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

}