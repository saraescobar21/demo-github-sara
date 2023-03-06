package com.s2daw.demo.models;

import lombok.*;

import javax.persistence.*;

@Entity //entidad que va a hacer referencia a la BBDD
@Table(name = "usuarios")
@ToString @EqualsAndHashCode //Genera los métodos equals() y hashCode()
public class Usuario {



    @Id //para indicar que va a ser la clave primaria

    //Para generar los valores de una clave primaria,
    // es decir, que los genere JPA automáticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "nombre")
    private String nombre;
    @Getter @Setter @Column(name = "apellido")
    private String apellido;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "telefono")
    private String telefono;
    @Getter @Setter @Column(name = "password")
    private String password;


}