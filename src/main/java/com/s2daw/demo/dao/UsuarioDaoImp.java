package com.s2daw.demo.dao;

import com.s2daw.demo.models.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class UsuarioDaoImp implements UsuarioDao{
    @PersistenceContext
 EntityManager entityManager;
    @Override
    public List<Usuario> getUsuarios() {
       String query = "FROM Usuario";
      return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);

    }
}
