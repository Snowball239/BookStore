/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author alexander
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Publisher.FIND_ALL, query = "SELECT p FROM Publisher p"),
})
public class Publisher implements Serializable {
    public static final String FIND_ALL = "Publisher.findAll";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    private String name;
    
    protected Publisher() { }
    
    public Publisher(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
}
