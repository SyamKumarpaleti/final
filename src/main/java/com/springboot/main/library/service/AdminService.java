package com.springboot.main.library.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.main.library.exception.InvalidIdException;
import com.springboot.main.library.model.Admin;
import com.springboot.main.library.repository.AdminRepository;
@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	
	public Admin postAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminRepository.save(admin);
	}


	public Admin getOne(int id) throws InvalidIdException {
		Optional<Admin> optional =  adminRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("Admin ID Invalid");
		}
		return optional.get();
	}


	public List<Admin> getAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return adminRepository.findAll(pageable).getContent();
	}


	public void deleteAdmin(Admin admin) {
		// TODO Auto-generated method stub
		adminRepository.delete(admin);
		
	}


	public Admin getAdmin(int id) {
		return adminRepository.getAdmin(id);
	}


//	public Admin getOneById(int id) throws InvalidIdException{
//		Optional<Admin> optional =  adminRepository.getAdminById(id);
//		if(!optional.isPresent()){
//			throw new InvalidIdException("Admin ID Invalid");
//		}
//		return optional.get();
//
	}

	
	

	
	
