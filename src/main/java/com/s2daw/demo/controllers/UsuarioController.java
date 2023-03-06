package com.s2daw.demo.controllers;

import com.s2daw.demo.dao.UsuarioDao;
import com.s2daw.demo.models.Usuario;
import com.s2daw.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //indica que es un controlados

public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtutil;

    //prueba de comentario
    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@hotmail.com");
        usuario.setTelefono("632147895");
        return usuario;
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.GET)

        //GUARDAMOS EN TOKEN LO QUE NOS VIENE EN CABECERA
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization")String token){
        if(!validarToken(token)){return null;}
        return usuarioDao.getUsuarios();

    }

    private boolean validarToken(String token){
        //VERIFICAR LA SESION CORRECTA
        String usuarioId = jwtutil.getKey(token);
       return usuarioId != null;
    }

    @RequestMapping(value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        //LIBRERIA
        //Argon2 es una función de hash de contraseñas que resume el estado del arte
        // en el diseño de funciones de memoria dura
        // y puede usarse para hash de contraseñas para almacenamiento
        // de credenciales, derivación de claves u otras aplicaciones.
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);

    }

    @RequestMapping(value="usuario56")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("Moy");
        usuario.setEmail("lucasmoy@hotmail.com");
        usuario.setTelefono("632147895");
        return usuario;
    }

    @RequestMapping(value="api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization")String token,
                         @PathVariable Long id){
        if(!validarToken(token)){return;}
        usuarioDao.eliminar(id);
    }

}
