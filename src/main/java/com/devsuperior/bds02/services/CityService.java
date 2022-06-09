package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.respositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.DbConstraintException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {
	
	@Autowired
	private CityRepository repository;
	
	
	public List<CityDTO> findAll(){
			
		List<City> list = repository.findAll(Sort.by("name"));
		
		return list.stream().map(city -> new CityDTO(city)).toList();
	}


	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		entity.setName(dto.getName());
		repository.save(entity);
		return new CityDTO(entity);
	}


	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DbConstraintException("Integrity DB violation! There is a event associate to entity with id: " + id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found " + id);
		}
		
		
	}
	
}
