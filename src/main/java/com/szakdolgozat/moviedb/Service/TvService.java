package com.szakdolgozat.moviedb.Service;

import com.szakdolgozat.moviedb.DTO.IdDTO;
import com.szakdolgozat.moviedb.DTO.MovieprogressDto;
import com.szakdolgozat.moviedb.DTO.TvprogressDto;
import com.szakdolgozat.moviedb.Entities.Movieprogress;
import com.szakdolgozat.moviedb.Entities.Tvprogress;
import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Repository.TvRepository;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@Service
public class TvService {
    @Autowired
    private TvRepository tvRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void deleteTvprogress(IdDTO idDTO){
        if(tvRepository.existsById(idDTO.getId())){
            tvRepository.deleteById(idDTO.getId());
        }
        //System.out.println(idDTO.getId());
    }

    public void createTvprogress(TvprogressDto tvprogressDto, Integer userid){

        if(userRepository.existsById(userid))    {
            if(!tvRepository.existsByUserid_IdAndTvid(userid,tvprogressDto.getTvid())){
                //System.out.println("Ez a movid szabad");
                User user = userRepository.getById(userid);
                Tvprogress newProgress = new Tvprogress();

                newProgress.setTvid(tvprogressDto.getTvid());
                newProgress.setFlag(tvprogressDto.getFlag());
                newProgress.setUserid(user);
                newProgress.setSeasoncount(tvprogressDto.getSeasoncount());
                newProgress.setEpisodecount(tvprogressDto.getEpisodecount());

                tvRepository.save(newProgress);


            } else { System.out.println("Ez a tvid már szerepel ennek a usernek a listájában"); }
        } else {System.out.println("Ez a user nem létezik");}

    }
    public void updateTvprogressFavFlag(TvprogressDto tvprogressDto, Integer userid){
        if(tvRepository.existsByTvid(tvprogressDto.getTvid())){
            Tvprogress existingMP = tvRepository.getByUserid_IdAndTvid(userid,tvprogressDto.getTvid());


            if(existingMP.getFavflag() == null){
                existingMP.setFavflag(true);
            } else if(existingMP.getFavflag() == false) {
                existingMP.setFavflag(true);
            } else if(existingMP.getFavflag() == true){
                existingMP.setFavflag(false);
            }

            tvRepository.save(existingMP);
        } else {
            if(!tvRepository.existsByUserid_IdAndTvid(userid,tvprogressDto.getTvid())){
                //System.out.println("Ez at szabad");
                User user = userRepository.getById(userid);
                Tvprogress newProgress = new Tvprogress();

                newProgress.setTvid(tvprogressDto.getTvid());
                newProgress.setFlag('O');
                newProgress.setUserid(user);
                newProgress.setEpisodecount(1);
                newProgress.setSeasoncount(1);
                newProgress.setFavflag(true);
                newProgress.setId(tvprogressDto.getId());

                tvRepository.save(newProgress);


            } else { System.out.println("Ez a tvid már szerepel ennek a usernek a listájában"); }


        }

    }
    public void updateTvprogressFlag(TvprogressDto tvprogressDto, Integer userid){
        Character[] FlagVal = new Character[] {'P','W','D','F', 'H'};
        if(tvRepository.existsByUserid_IdAndTvid(userid, tvprogressDto.getTvid())){
            if(Arrays.asList(FlagVal).contains(tvprogressDto.getFlag())){

                Tvprogress existingTP = tvRepository.getByUserid_IdAndTvid(userid, tvprogressDto.getTvid());
                existingTP.setFlag(tvprogressDto.getFlag());
                tvRepository.save(existingTP);

            } else {System.out.println("Wrong flag");}

        } else {System.out.println("Not found");}

    }

    public void updateTvseason(TvprogressDto tvprogressDto, Integer userid){
        if(tvRepository.existsByUserid_IdAndTvid(userid, tvprogressDto.getTvid())){


                Tvprogress existingTP = tvRepository.getByUserid_IdAndTvid(userid, tvprogressDto.getTvid());
                existingTP.setSeasoncount(tvprogressDto.getSeasoncount());
                tvRepository.save(existingTP);



        } else {System.out.println("Not found");}

    }
    public void updateTvepisode(TvprogressDto tvprogressDto, Integer userid){
        if(tvRepository.existsByUserid_IdAndTvid(userid, tvprogressDto.getTvid())){


            Tvprogress existingTP = tvRepository.getByUserid_IdAndTvid(userid, tvprogressDto.getTvid());
            existingTP.setEpisodecount(tvprogressDto.getEpisodecount());
            tvRepository.save(existingTP);



        } else {
            System.out.println("Not found");
            System.out.println( tvprogressDto.getTvid());
            System.out.println(userid);
        }

    }

}
