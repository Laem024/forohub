package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domian.auth.UsuarioRepository;
import com.aluracursos.forohub.domian.topicos.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    TopicoRepository topicoRepository;
    UsuarioRepository usuarioRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> getTopicos(@PageableDefault(page = 0, size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> getTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getUsuarioId(),
                topico.getCurso()
        ));
    }

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> addTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        LocalDateTime fechaCreacion = LocalDateTime.now();
        Topico topico = topicoRepository.save(new Topico(datos, fechaCreacion));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                datos.titulo(),
                datos.mensaje(),
                fechaCreacion,
                datos.usuarioId(),
                datos.curso()
        );

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualziar(@RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarDatos(datos);
        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getUsuarioId(),
                topico.getCurso()
        ));
    }
}
