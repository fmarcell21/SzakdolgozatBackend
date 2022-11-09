package com.szakdolgozat.moviedb.Service;

import com.szakdolgozat.moviedb.DTO.IdDTO;
import com.szakdolgozat.moviedb.DTO.MovieprogressDto;
import com.szakdolgozat.moviedb.Entities.Movieprogress;
import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Repository.MovieprogressRepository;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@Service
public class MovieprogressService {
    @Autowired
    private MovieprogressRepository movieprogressRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void deleteMovieprogress(IdDTO idDTO){
        if(movieprogressRepository.existsById(idDTO.getId())){
            movieprogressRepository.deleteById(idDTO.getId());
        }
        //System.out.println(idDTO.getId());
    }

    public void updateMovieprogressFavFlag(MovieprogressDto movieprogressDto, Integer userid){
        if(movieprogressRepository.existsByMovid(movieprogressDto.getMovid())){
            Movieprogress existingMP = movieprogressRepository.getByUserid_IdAndMovid(userid,movieprogressDto.getMovid());


            if(existingMP.getFavflag() == null){
                existingMP.setFavflag(true);
            } else if(existingMP.getFavflag() == false) {
                existingMP.setFavflag(true);
            } else if(existingMP.getFavflag() == true){
                existingMP.setFavflag(false);
            }

            movieprogressRepository.save(existingMP);
        } else {
                if(!movieprogressRepository.existsByUserid_IdAndMovid(userid,movieprogressDto.getMovid())){
                    //System.out.println("Ez a movid szabad");
                    User user = userRepository.getById(userid);
                    Movieprogress newProgress = new Movieprogress();

                    newProgress.setMovid(movieprogressDto.getMovid());
                    newProgress.setFlag('O');
                    newProgress.setUserid(user);
                    newProgress.setFavflag(true);
                    newProgress.setId(movieprogressDto.getId());

                    movieprogressRepository.save(newProgress);


                } else { System.out.println("Ez a movid már szerepel ennek a usernek a listájában"); }


        }

    }

    public void updateMovieprogressFlag(MovieprogressDto movieprogressDto, Integer userid){
        Character[] FlagVal = new Character[] {'P','W','D','F'};
        if(movieprogressRepository.existsByUserid_IdAndMovid(userid, movieprogressDto.getMovid())){
            if(Arrays.asList(FlagVal).contains(movieprogressDto.getFlag())){

                Movieprogress existingMP = movieprogressRepository.getByUserid_IdAndMovid(userid, movieprogressDto.getMovid());
                existingMP.setFlag(movieprogressDto.getFlag());
                movieprogressRepository.save(existingMP);

            } else {System.out.println("Wrong flag");}

        } else {System.out.println("Not found");}

    }

    public void createMovieprogress(MovieprogressDto movieprogressDto, Integer userid){

        if(userRepository.existsById(userid))    {
            if(!movieprogressRepository.existsByUserid_IdAndMovid(userid,movieprogressDto.getMovid())){
                //System.out.println("Ez a movid szabad");
                User user = userRepository.getById(userid);
                Movieprogress newProgress = new Movieprogress();

                newProgress.setMovid(movieprogressDto.getMovid());
                newProgress.setFlag(movieprogressDto.getFlag());
                newProgress.setUserid(user);

                movieprogressRepository.save(newProgress);


            } else { System.out.println("Ez a movid már szerepel ennek a usernek a listájában"); }
        } else {System.out.println("Ez a user nem létezik");}

    }


}
