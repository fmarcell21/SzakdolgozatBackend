package com.szakdolgozat.moviedb.Service;

import com.szakdolgozat.moviedb.DTO.PersonDto;
import com.szakdolgozat.moviedb.Entities.User;
import com.szakdolgozat.moviedb.Repository.PersonRepository;
import com.szakdolgozat.moviedb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.szakdolgozat.moviedb.Entities.Person;

@Component
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    public void updatePersonFavFlag(PersonDto personDto, Integer userid){
        if(personRepository.existsByUserid_IdAndPerid(userid,personDto.getPerid())){
            Person existingPerson = personRepository.getByUserid_IdAndPerid(userid,personDto.getPerid());
            if(existingPerson.getFavflag() == null){
                existingPerson.setFavflag(true);
            } else if(existingPerson.getFavflag() == false) {
                existingPerson.setFavflag(true);
            } else if(existingPerson.getFavflag() == true){
                existingPerson.setFavflag(false);
            }
            personRepository.save(existingPerson);
        } else {
            if(!personRepository.existsByUserid_IdAndPerid(userid,personDto.getPerid())){
                User user = userRepository.getById(userid);
                Person newPerson = new Person();
                newPerson.setFavflag(true);
                newPerson.setUserid(user);
                newPerson.setPerid(personDto.getPerid());

                personRepository.save(newPerson);
            }
        }
    }
}
