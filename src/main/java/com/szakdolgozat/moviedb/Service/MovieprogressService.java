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

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@Service
public class MovieprogressService {
    @Autowired
    private MovieprogressRepository movieprogressRepository;
    @Autowired UserRepository userRepository;

    @Transactional
    public void deleteMovieprogress(IdDTO idDTO){
        if(movieprogressRepository.existsById(idDTO.getId())){
            movieprogressRepository.deleteById(idDTO.getId());
        }
        //System.out.println(idDTO.getId());
    }

    public void updateMovieprogressFavFlag(MovieprogressDto movieprogressDto){
        if(movieprogressRepository.existsById(movieprogressDto.getId())){
            Movieprogress existingMP = movieprogressRepository.getById(movieprogressDto.getId());


            if(existingMP.getFavflag() == null){
                existingMP.setFavflag(true);
            } else if(existingMP.getFavflag() == false) {
                existingMP.setFavflag(true);
            } else if(existingMP.getFavflag() == true){
                existingMP.setFavflag(false);
            }

            movieprogressRepository.save(existingMP);
        }

    }

    public void updateMovieprogressFlag(MovieprogressDto movieprogressDto){
        Character[] FlagVal = new Character[] {'P','W','D','F'};
        if(movieprogressRepository.existsById(movieprogressDto.getId())){
            if(Arrays.asList(FlagVal).contains(movieprogressDto.getFlag())){

                Movieprogress existingMP = movieprogressRepository.getById(movieprogressDto.getId());
                existingMP.setFlag(movieprogressDto.getFlag());
                movieprogressRepository.save(existingMP);

            } else {System.out.println("Wrong flag");}

        }
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
