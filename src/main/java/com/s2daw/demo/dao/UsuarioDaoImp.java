package com.s2daw.demo.dao;

import com.s2daw.demo.models.Usuario;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional

public class UsuarioDaoImp implements UsuarioDao{
 private EntityManager entityManager;
    @Override
    public List<Usuario> getUsuarios() {
        return null;
    }
}
