package com.Gutierrez.infraccionservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import com.Gutierrez.infraccionservice.entity.Infraccion;
import com.Gutierrez.infraccionservice.repository.InfraccionRepository;

@Service
@Slf4j
public class InfraccionService {
    @Autowired
    private InfraccionRepository repository;
    
    @Transactional(readOnly=true)
    public List<Infraccion> findAll(Pageable page) {
        try {
            return repository.findAll(page).toList();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional(readOnly=true)
    public List<Infraccion> findByDni(String dni, Pageable page) {
        try {
            return repository.findByDniContaining(dni, page);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional(readOnly=true)
    public Infraccion findById(int id) {
        try {
        	Infraccion registro = repository.findById(id).orElseThrow();
            return registro;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional
    public Infraccion save(Infraccion infraccion) {
        try {
            if(repository.findByDni(infraccion.getDni()) != null) {
                return null;
            }
            infraccion.setCreatedAt(new Date());
            infraccion.setUpdatedAt(new Date());
            Infraccion nuevo = repository.save(infraccion);
            return nuevo;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Infraccion update(Infraccion infraccion) {
        try {
        	Infraccion registro = repository.findById(infraccion.getId()).orElseThrow();
        	Infraccion registroD = repository.findByDni(infraccion.getDni());
            
            if(registroD != null && infraccion.getId() != registroD.getId()) {
                return null;
            }
            
            registro.setDni(infraccion.getDni());
            registro.setFecha(infraccion.getFecha());
            registro.setTipoInfraccion(infraccion.getTipoInfraccion());
            registro.setUbicacion(infraccion.getUbicacion());
            registro.setDescripcion(infraccion.getDescripcion());
            registro.setMontoMulta(infraccion.getMontoMulta());
            registro.setEstado(infraccion.getEstado());
            registro.setUpdatedAt(new Date());
            repository.save(registro);
            return registro;
        } catch (Exception e) {
            return null;
        }
    }
    
    @Transactional
    public boolean delete(int id) {
        try {
            Infraccion infraccion = repository.findById(id).orElseThrow();
            repository.delete(infraccion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
