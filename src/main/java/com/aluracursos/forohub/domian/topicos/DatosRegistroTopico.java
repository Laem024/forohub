package com.aluracursos.forohub.domian.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotNull
        Long usuarioId,

        @NotBlank
        String mensaje,

        @NotBlank
        String curso,

        @NotBlank
        String titulo

) {
}
