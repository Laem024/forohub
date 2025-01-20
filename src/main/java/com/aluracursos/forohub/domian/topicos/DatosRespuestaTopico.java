package com.aluracursos.forohub.domian.topicos;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(

        String titulo,

        String mensaje,

        LocalDateTime fechaCreacion,

        Long usuarioId,

        String curso

) {
}
