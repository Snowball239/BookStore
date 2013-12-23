/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.ejb;

import edu.data.Author;
import edu.data.Author_;
import edu.data.Credentials_;
import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexander
 */
@Stateless
@LocalBean
public class AuthorEjb {
    @PersistenceContext
    private EntityManager em;
    
    public List<Author> findAll() {
        final TypedQuery<Author> query = em.createNamedQuery(Author.FIND_ALL, Author.class);
        return query.getResultList();
    }
    
    public Author findById(@NotNull final Long id) {
        return em.find(Author.class, id);
    }
    
    public List<Author> findByCredentials(@NotNull final String name, 
            @NotNull final String lastName) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Author> query = cb.createQuery(Author.class);
        final Root<Author> root = query.from(Author.class);
        
        final Expression<String> n = root.get(Author_.credentials).get(Credentials_.name);
        final Expression<String> l = root.get(Author_.credentials).get(Credentials_.lastName);
        
        if (!name.isEmpty() && !lastName.isEmpty()) {
            query.select(root).where(cb.and(cb.like(n, name), cb.like(l, lastName)));
        } else if (name.isEmpty()) {
            query.select(root).where(cb.like(l, lastName));
        } else if (lastName.isEmpty()) {
            query.select(root).where(cb.like(n, name));
        } else {
            return Collections.emptyList();
        }
        
        return em.createQuery(query).getResultList();
    }
    
    public @NotNull Author create(@NotNull final Author author) {
        em.persist(author);
        return author;
    }
    
    public @NotNull Author update(@NotNull final Author author) {
        return em.merge(author);
    }
    
    public void delete(@NotNull final Author author) {
        em.remove(em.merge(author));
    }
}
