package com.Gutierrez.infraccionservice.soap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.gutierrez.infraccionservice.AddInfraccionRequest;
import com.gutierrez.infraccionservice.AddInfraccionResponse;
import com.gutierrez.infraccionservice.InfraccionDetalle;
import com.gutierrez.infraccionservice.DeleteInfraccionRequest;
import com.gutierrez.infraccionservice.DeleteInfraccionResponse;
import com.gutierrez.infraccionservice.GetAllInfraccionesRequest;
import com.gutierrez.infraccionservice.GetAllInfraccionesResponse;
import com.gutierrez.infraccionservice.GetInfraccionRequest;
import com.gutierrez.infraccionservice.GetInfraccionResponse;
import com.gutierrez.infraccionservice.ServiceStatus;
import com.gutierrez.infraccionservice.UpdateInfraccionRequest;
import com.gutierrez.infraccionservice.UpdateInfraccionResponse;
import com.gutierrez.infraccionservice.entity.Infraccion;
import com.gutierrez.infraccionservice.service.InfraccionService;

@Endpoint
public class InfraccionEndPoint {
    @Autowired
    private InfraccionService service;
    
    @PayloadRoot(namespace = "http://www.Gutierrez.com/infraccionservice", localPart = "GetAllInfraccionesRequest")
    @ResponsePayload
    public GetAllInfraccionResponse findAll(@RequestPayload GetAllInfraccionRequest request) {
        GetAllInfraccionResponse response = new GetAllInfraccionResponse();
        
        Pageable page = PageRequest.of(request.getOffset(), request.getLimit());
        List<Infraccion> infracciones;
        if (request.getTexto() == null) {
            infracciones = service.findAll(page);
        } else {
            infracciones = service.findByNombre(request.getTexto(), page);
        }
        
        List<InfraccionDetalle> infraccionesResponse = new ArrayList<>();
        for (Infraccion infraccion : infracciones) {
            InfraccionDetalle detalle = new InfraccionDetalle();
            BeanUtils.copyProperties(infraccion, detalle);
            infraccionesResponse.add(detalle);
        }
        response.getInfraccionDetalle().addAll(infraccionesResponse);
        return response;
    }
    
    @PayloadRoot(namespace = "http://www.uss.com/infraccion-soap", localPart = "GetInfraccionRequest")
    @ResponsePayload
    public GetInfraccionResponse findById(@RequestPayload GetInfraccionRequest request) {
        GetInfraccionResponse response = new GetInfraccionResponse();
        Infraccion infraccion = service.findById(request.getId());
        if (infraccion != null) {
            InfraccionDetalle detalle = new InfraccionDetalle();
            BeanUtils.copyProperties(infraccion, detalle);
            response.setInfraccionDetalle(detalle);
        }
        return response;
    }
    
    @PayloadRoot(namespace = "http://www.uss.com/infraccion-soap", localPart = "AddInfraccionRequest")
    @ResponsePayload
    public AddInfraccionResponse create(@RequestPayload AddInfraccionRequest request) {
        AddInfraccionResponse response = new AddInfraccionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        
        Infraccion infraccion = new Infraccion();
        infraccion.setNombre(request.getNombre());
        infraccion.setPrecio(request.getPrecio());
        
        Infraccion savedInfraccion = service.save(infraccion);
        
        if (savedInfraccion != null) {
            InfraccionDetalle detalle = new InfraccionDetalle();
            BeanUtils.copyProperties(savedInfraccion, detalle);
            response.setInfraccionDetalle(detalle);
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Infraccion added successfully.");
        } else {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Infraccion already exists or could not be added.");
        }
        
        response.setServiceStatus(serviceStatus);
        return response;
    }
    
    @PayloadRoot(namespace = "http://www.uss.com/infraccion-soap", localPart = "UpdateInfraccionRequest")
    @ResponsePayload
    public UpdateInfraccionResponse update(@RequestPayload UpdateInfraccionRequest request) {
        UpdateInfraccionResponse response = new UpdateInfraccionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        
        Infraccion infraccion = service.findById(request.getId());
        if (infraccion != null) {
            infraccion.setNombre(request.getNombre());
            infraccion.setPrecio(request.getPrecio());
            
            Infraccion updatedInfraccion = service.update(infraccion);
            if (updatedInfraccion != null) {
                InfraccionDetalle detalle = new InfraccionDetalle();
                BeanUtils.copyProperties(updatedInfraccion, detalle);
                response.setInfraccionDetalle(detalle);
                serviceStatus.setStatusCode("SUCCESS");
                serviceStatus.setMessage("Infraccion updated successfully.");
            } else {
                serviceStatus.setStatusCode("CONFLICT");
                serviceStatus.setMessage("Infraccion could not be updated.");
            }
        } else {
            serviceStatus.setStatusCode("NOT_FOUND");
            serviceStatus.setMessage("Infraccion not found.");
        }
        
        response.setServiceStatus(serviceStatus);
        return response;
    }
    
    @PayloadRoot(namespace = "http://www.Gutierrez.com/infraccion-soap", localPart = "DeleteInfraccionRequest")
    @ResponsePayload
    public DeleteInfraccionResponse delete(@RequestPayload DeleteInfraccionRequest request) {
        DeleteInfraccionResponse response = new DeleteInfraccionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        
        boolean deleted = service.delete(request.getId());
        if (deleted) {
            serviceStatus.setStatusCode("SUCCESS");
            serviceStatus.setMessage("Infraccion deleted successfully.");
        } else {
            serviceStatus.setStatusCode("CONFLICT");
            serviceStatus.setMessage("Infraccion could not be deleted.");
        }
        
        response.setServiceStatus(serviceStatus);
        return response;
    }
}
