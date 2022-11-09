package com.szakdolgozat.moviedb.Service;

import com.szakdolgozat.moviedb.DTO.IdDTO;
import com.szakdolgozat.moviedb.DTO.MovieprogressDto;
import com.szakdolgozat.moviedb.Entities.Movieprogress;
import com.szakdolgozat.moviedb.Repository.MovieprogressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Component
@Service
public class MovieprogressService {
    @Autowired
    private MovieprogressRepository movieprogressRepository;


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
}
