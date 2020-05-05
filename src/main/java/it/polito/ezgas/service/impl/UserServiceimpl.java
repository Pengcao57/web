package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		User user = userRepository.findByUserNameAndPassword(credentials.getUser(), credentials.getPw());
		if(user != null) {
			LoginDto login = new LoginDto();
			login.setUserId(user.getUserId());
			login.setUserName(user.getUserName());
			login.setEmail(user.getEmail());
			login.setReputation(user.getReputation());
			login.setAdmin(user.getAdmin());
			
			return login;
			
		} else 	
			throw new InvalidLoginDataException("Invalid login");
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		User user = userRepository.findByUserId(userId);
		if(user != null) {
			if(user.getReputation() < 5) {
				user.setReputation(user.getReputation()+1);
			}
			return user.getReputation();
		} else 
			throw new InvalidUserException("User with Id: "+userId+" doesn't exist!");
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		User user = userRepository.findByUserId(userId);
		if(user != null) {
			if(user.getReputation() > -5) {
				user.setReputation(user.getReputation()-1);
			}
			return user.getReputation();
		} else
			throw new InvalidUserException("User with Id: "+userId+" doesn't exist!");
	}
	
}
