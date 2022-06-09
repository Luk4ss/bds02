package com.devsuperior.bds02.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.respositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	
	
	public EventDTO update(EventDTO dto, Long id) {
		try {
			Event entity = repository.findById(id).get();
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity.setDate(dto.getDate());
			entity.setCity(new City(dto.getCityId(), null));
			
			repository.save(entity);
			
			return new EventDTO(entity);
			
		}
		catch(NoSuchElementException e) {
			throw new ResourceNotFoundException("Resource not found " + id);
		}
		
		
	}
	
}
