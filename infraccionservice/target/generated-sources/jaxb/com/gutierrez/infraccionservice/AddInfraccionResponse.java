//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.07.09 a las 08:12:03 PM PET 
//


package com.gutierrez.infraccionservice;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ServiceStatus" type="{http://www.Gutierrez.com/infraccionservice}ServiceStatus"/&gt;
 *         &lt;element name="InfraccionDetalle" type="{http://www.Gutierrez.com/infraccionservice}InfraccionDetalle"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceStatus",
    "infraccionDetalle"
})
@XmlRootElement(name = "AddInfraccionResponse")
public class AddInfraccionResponse {

    @XmlElement(name = "ServiceStatus", required = true)
    protected ServiceStatus serviceStatus;
    @XmlElement(name = "InfraccionDetalle", required = true)
    protected InfraccionDetalle infraccionDetalle;

    /**
     * Obtiene el valor de la propiedad serviceStatus.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    /**
     * Define el valor de la propiedad serviceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setServiceStatus(ServiceStatus value) {
        this.serviceStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad infraccionDetalle.
     * 
     * @return
     *     possible object is
     *     {@link InfraccionDetalle }
     *     
     */
    public InfraccionDetalle getInfraccionDetalle() {
        return infraccionDetalle;
    }

    /**
     * Define el valor de la propiedad infraccionDetalle.
     * 
     * @param value
     *     allowed object is
     *     {@link InfraccionDetalle }
     *     
     */
    public void setInfraccionDetalle(InfraccionDetalle value) {
        this.infraccionDetalle = value;
    }

}
