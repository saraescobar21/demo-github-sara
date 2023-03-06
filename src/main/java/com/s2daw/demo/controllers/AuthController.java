package com.s2daw.demo.controllers;

import com.s2daw.demo.dao.UsuarioDao;
import com.s2daw.demo.models.Usuario;
import com.s2daw.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController //indica que es un controlador
public class AuthController {

    @Autowired //se almacena en memoria
    private UsuarioDao usuarioDao;

    //inyección de dependencias
    //cargo automatico
    @Autowired
    private JWTUtil jwtutil;


    //El ReqMap indica a la url cuando devuelve el contenido
    @RequestMapping(value="api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogeado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        //si usuarioLogeado es distinto a null, salio todo bien
        if (usuarioLogeado !=null){

            //generar token
            String tokenJwt = jwtutil.create(String.valueOf(usuarioLogeado.getId()), usuarioLogeado.getEmail());

            return tokenJwt;
        }
        //sino salio mal
        return "FAIL";
    }
}
