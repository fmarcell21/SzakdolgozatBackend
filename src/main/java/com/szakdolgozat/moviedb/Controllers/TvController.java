package com.szakdolgozat.moviedb.Controllers;

import com.szakdolgozat.moviedb.DTO.IdDTO;
import com.szakdolgozat.moviedb.DTO.MovieprogressDto;
import com.szakdolgozat.moviedb.DTO.TvprogressDto;
import com.szakdolgozat.moviedb.Entities.Movieprogress;
import com.szakdolgozat.moviedb.Entities.Tvprogress;
import com.szakdolgozat.moviedb.Repository.MovieprogressRepository;
import com.szakdolgozat.moviedb.Repository.TvRepository;
import com.szakdolgozat.moviedb.Service.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("api/tv")
public class TvController {

    @Autowired
    private TvService tvService;
    @Autowired
    private TvRepository tvRepository;

    public TvController( TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @GetMapping("/find/{userid}")
    public List<Map<String,String>> findByUserid(@PathVariable Integer userid){
        List<Tvprogress> tvprogressList = tvRepository.findAllByUserid_Id(userid);
        // System.out.print(movieprogressList);
        List<Map<String,String>> out = new ArrayList<>();

        for(Tvprogress tvprogress : tvprogressList){
            String id = tvprogress.getId().toString();
            //String userId = movieprogress.getUserid().toString();
            Boolean favflag = tvprogress.getFavflag();
            Character flag = tvprogress.getFlag();
            Integer movieid = tvprogress.getTvid();
            Integer seasoncount = tvprogress.getSeasoncount();
            Integer epcount = tvprogress.getEpisodecount();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            //map.put("userId",userId);

            if(favflag != null){
                map.put("favflag",favflag.toString());
            }
            map.put("flag",flag.toString());
            map.put("movieid",movieid.toString());
            map.put("season_count",seasoncount.toString());
            map.put("episode_count",epcount.toString());
            out.add(map);
        }

        return out;
    }
    @GetMapping("/find/{userid}/flag/{flag}")
    public List<Map<String, String>> getProgressByFlag(@PathVariable Integer userid, @PathVariable Character flag){
        Character[] FlagVal = new Character[] {'P','W','D','F','H'};
        List<Map<String,String>> out = new ArrayList<>();
        if(flag == 'O'){
            List<Tvprogress> tvprogressList = tvRepository.findAllByUserid_Id(userid);


            for(Tvprogress tvprogress : tvprogressList){
                String id = tvprogress.getId().toString();

                Boolean favflag = tvprogress.getFavflag();

                Integer tvid = tvprogress.getTvid();

                Integer episodecount = tvprogress.getEpisodecount();
                Integer seasoncount = tvprogress.getSeasoncount();

                Map<String, String> map = new LinkedHashMap<>();
                map.put("id",id);
                if(favflag !=null){
                    map.put("favflag",favflag.toString());
                }

                map.put("flag",(tvprogress.getFlag()).toString());
                map.put("tvid",tvid.toString());
                map.put("season_count",seasoncount.toString());
                map.put("episode_count",episodecount.toString());

                out.add(map);
            }

        } else if(Arrays.asList(FlagVal).contains(flag)){
            List<Tvprogress> tvprogressList = tvRepository.findAllByUserid_IdAndFlag(userid, flag);



            for(Tvprogress tvprogress : tvprogressList){
                String id = tvprogress.getId().toString();

                Boolean favflag = tvprogress.getFavflag();
                Integer episodecount = tvprogress.getEpisodecount();
                Integer seasoncount = tvprogress.getSeasoncount();
                Integer movieid = tvprogress.getTvid();

                Map<String, String> map = new LinkedHashMap<>();
                map.put("id",id);
                if(favflag !=null){
                    map.put("favflag",favflag.toString());
                }
                map.put("flag",flag.toString());
                map.put("movieid",movieid.toString());
                map.put("season_count",seasoncount.toString());
                map.put("episode_count",episodecount.toString());

                out.add(map);
            }
        }
        return out;
    }

    @GetMapping("/find/{userid}/fav")
    public  List<Map<String,String>> getAllUserFav(@PathVariable Integer userid){
        List<Tvprogress> tvprogressList = tvRepository.findAllByUserid_IdAndFavflag(userid, true);
        List<Map<String,String>> out = new ArrayList<>();

        for(Tvprogress tvprogress : tvprogressList){
            String id = tvprogress.getId().toString();

            Boolean favflag = tvprogress.getFavflag();
            Character flag = tvprogress.getFlag();
            Integer tvid = tvprogress.getTvid();
            Integer episodecount = tvprogress.getEpisodecount();
            Integer seasoncount = tvprogress.getSeasoncount();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            map.put("favflag",favflag.toString());
            map.put("flag",flag.toString());
            map.put("tvid",tvid.toString());
            map.put("season_count",seasoncount.toString());
            map.put("episode_count",episodecount.toString());
            out.add(map);
        }

        return out;
    }

    @GetMapping("/find/{userid}/{tvid}")
    public List<Map<String, String>> getUserTvStatus(@PathVariable Integer userid, @PathVariable Integer tvid){

        List<Tvprogress> tvprogressList = tvRepository.findAllByUserid_IdAndTvid(userid, tvid);

        List<Map<String,String>> out = new ArrayList<>();

        for(Tvprogress tvprogress : tvprogressList){
            String id = tvprogress.getId().toString();

            Boolean favflag = tvprogress.getFavflag();
            Character flag = tvprogress.getFlag();

            Integer episodecount = tvprogress.getEpisodecount();
            Integer seasoncount = tvprogress.getSeasoncount();

            Map<String, String> map = new LinkedHashMap<>();
            map.put("id",id);
            if(favflag != null) {
                map.put("favflag",favflag.toString());
            }

            map.put("flag",flag.toString());
            map.put("tvid",tvid.toString());
            map.put("season_count",seasoncount.toString());
            map.put("episode_count",episodecount.toString());

            out.add(map);
        }

        return out;
    }

    @PostMapping("/{userid}/create")
    public ResponseEntity<?> createMovieprogress(@RequestBody TvprogressDto tvprogressDto, @PathVariable Integer userid){
        tvService.createTvprogress(tvprogressDto, userid);

        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMovieprogress(@RequestBody IdDTO idDTO){
        //System.out.println(idDTO);
        tvService.deleteTvprogress(idDTO);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateFlag/{userid}")
    public ResponseEntity<?> updateFlag(@RequestBody TvprogressDto tvprogressDto,@PathVariable Integer userid){
        tvService.updateTvprogressFlag(tvprogressDto, userid);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateFav/{userid}")
    public ResponseEntity<?> updateFavFlag(@RequestBody TvprogressDto tvprogressDto, @PathVariable Integer userid){
        tvService.updateTvprogressFavFlag(tvprogressDto, userid);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateSeason/{userid}")
    public ResponseEntity<?> updateSeasonCount(@RequestBody TvprogressDto tvprogressDto, @PathVariable Integer userid){
        tvService.updateTvseason(tvprogressDto, userid);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateEpisode/{userid}")
    public ResponseEntity<?> updateEpisodeCount(@RequestBody TvprogressDto tvprogressDto, @PathVariable Integer userid){
        tvService.updateTvepisode(tvprogressDto, userid);
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
