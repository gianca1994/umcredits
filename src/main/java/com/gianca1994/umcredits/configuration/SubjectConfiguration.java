package com.gianca1994.umcredits.configuration;

import com.gianca1994.umcredits.model.Subject;
import com.gianca1994.umcredits.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubjectConfiguration {
    @Bean
    public CommandLineRunner autoSaveSubjects(SubjectRepository subjectRepository) {
        return args -> {
            subjectRepository.save(new Subject(1L,"TECNOLOGIA CIENCIA Y SOCIEDAD", 3, 1));
            subjectRepository.save(new Subject(2L, "QUIMICA GENERAL", 4, 1));
            subjectRepository.save(new Subject(3L, "CALCULO I", 3, 1));
            subjectRepository.save(new Subject(4L, "CALCULO II", 3, 1));
            subjectRepository.save(new Subject(5L, "SISTEMAS DE REPRESENTACION", 3, 1));
            subjectRepository.save(new Subject(2025L, "ANALISIS DE SISTEMAS I", 6, 1));
            subjectRepository.save(new Subject(7L, "ALGEBRA Y GEOMETRIA ANALITICA", 5, 1));
            subjectRepository.save(new Subject(8L, "INGLES TECNICO", 4, 1));
            subjectRepository.save(new Subject(9L, "INFORMATICA", 7, 1));
            subjectRepository.save(new Subject(2031L, "ANALISIS DE SISTEMAS II", 4, 2));
            subjectRepository.save(new Subject(2023L, "ARQUITECTURA DE COMPUTADORAS", 3, 2));
            subjectRepository.save(new Subject(2040L, "DISEÑO DE BASES DE DATOS I (*)", 5, 2));
            subjectRepository.save(new Subject(2022L, "MATEMATICA DISCRETA Y DISEÑO LOGICO (*)", 3, 2));
            subjectRepository.save(new Subject(2032L, "REDES DE DATOS", 6, 2));
            subjectRepository.save(new Subject(2027L, "ALGEBRA LINEAL", 3, 2));
            subjectRepository.save(new Subject(2024L, "ESTADISTICA APLICADA I (*)", 4, 2));
            subjectRepository.save(new Subject(2020L, "FISICA", 5, 2));
            subjectRepository.save(new Subject(2026L, "SISTEMAS OPERATIVOS", 4, 2));
            subjectRepository.save(new Subject(2028L, "COMPUTACION (*)", 6, 2));
            subjectRepository.save(new Subject(2030L, "ANALISIS NUMERICO", 5, 3));
            subjectRepository.save(new Subject(2035L, "AUTOMATAS Y GRAMATICAS", 3, 3));
            subjectRepository.save(new Subject(2033L, "ESTADISTICA APLICADA II", 3, 3));
            subjectRepository.save(new Subject(2043L, "ORGANIZACION", 3, 3));
            subjectRepository.save(new Subject(2054L, "DISEÑO DE BASES DE DATOS II", 5, 3));
            subjectRepository.save(new Subject(2046L, "ECONOMIA", 3, 3));
            subjectRepository.save(new Subject(2036L, "DISEÑO DE SISTEMAS", 5, 3));
            subjectRepository.save(new Subject(2037L, "COMUNICACION DE DATOS", 3, 3));
            subjectRepository.save(new Subject(2038L, "PROGRAMACION", 4, 3));
            subjectRepository.save(new Subject(2034L, "COMPUTACION II", 6, 3));
            subjectRepository.save(new Subject(2041L, "INGENIERIA DE SOFTWARE", 5, 4));
            subjectRepository.save(new Subject(2044L, "SEGURIDAD INFORMATICA I", 5, 4));
            subjectRepository.save(new Subject(2042L, "TELEINFORMATICA", 5, 4));
            subjectRepository.save(new Subject(2404L, "TRABAJO INTEGRADOR FINAL 1", 3, 4));
            subjectRepository.save(new Subject(2048L, "FORMULACION Y EVALUACION DE PROYECTOS", 4, 4));
            subjectRepository.save(new Subject(2080L, "INGENIERIA DE SOFTWARE APLICADA", 4, 4));
            subjectRepository.save(new Subject(2055L, "INTELIGENCIA ARTIFICIAL", 4, 4));
            subjectRepository.save(new Subject(2047L, "INVESTIGACION OPERATIVA", 4, 4));
            subjectRepository.save(new Subject(2409L, "TRABAJO INTEGRADOR FINAL 2", 3, 4));
            subjectRepository.save(new Subject(2049L, "PROGRAMACION II", 6, 4));
            subjectRepository.save(new Subject(2501L, "ASEGURAMIENTO DE CALIDAD DEL SOFTWARE", 4, 5));
            subjectRepository.save(new Subject(2502L, "GESTION DE CALIDAD Y MEDIO AMBIENTE", 4, 5));
            subjectRepository.save(new Subject(2503L, "HIGIENE Y SEGURIDAD DEL TRABAJO", 3, 5));
            subjectRepository.save(new Subject(2504L, "MODELOS Y SIMULACION", 3, 5));
            subjectRepository.save(new Subject(2505L, "TRABAJO INTEGRADOR FINAL 3", 3, 5));
            subjectRepository.save(new Subject(2506L, "SEGURIDAD INFORMATICA II", 4, 5));
            subjectRepository.save(new Subject(2507L, "INGENIERIA DERECHO Y ETICA PROFESIONAL", 3, 5));
            subjectRepository.save(new Subject(2508L, "AUDITORIA DE SISTEMAS", 4, 5));
            subjectRepository.save(new Subject(2509L, "PLANEAMIENTO Y GESTION DE EMPRESAS (*)", 5, 5));
            subjectRepository.save(new Subject(2510L, "TRABAJO INTEGRADOR FINAL 4", 3, 5));
            subjectRepository.save(new Subject(2500L, "PRACTICA PROFESIONAL", 0, 5));
        };
    }
}