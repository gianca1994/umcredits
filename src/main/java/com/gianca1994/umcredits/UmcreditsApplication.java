package com.gianca1994.umcredits;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class UmcreditsApplication {
    public static void main(String[] args) {

        Log log = LogFactory.getLog(UmcreditsApplication.class);

        SpringApplication.run(UmcreditsApplication.class, args);

        log.info("UM-CREDITS API INICIALIZADA!!!");



    }
}


/*

        SubjectRepository subjectRepository;

        subjectRepository.save(new Subject(1L, "0001", "TECNOLOGIA CIENCIA Y SOCIEDAD", 3, 1));
        subjectRepository.save(new Subject(2L, "0002", "QUIMICA GENERAL", 4, 1));
        subjectRepository.save(new Subject(3L, "0003", "CALCULO I", 3, 1));
        subjectRepository.save(new Subject(4L, "0004", "CALCULO II", 3, 1));
        subjectRepository.save(new Subject(5L, "0005", "SISTEMAS DE REPRESENTACION", 3, 1));
        subjectRepository.save(new Subject(6L, "2025", "ANALISIS DE SISTEMAS I", 6, 1));
        subjectRepository.save(new Subject(7L, "0007", "ALGEBRA Y GEOMETRIA ANALITICA", 5, 1));
        subjectRepository.save(new Subject(8L, "0008", "INGLES TECNICO", 4, 1));
        subjectRepository.save(new Subject(9L, "0009", "INFORMATICA", 7, 1));
        subjectRepository.save(new Subject(10L, "2031", "ANALISIS DE SISTEMAS II", 4, 2));
        subjectRepository.save(new Subject(11L, "2023", "ARQUITECTURA DE COMPUTADORAS", 3, 2));
        subjectRepository.save(new Subject(12L, "2040", "DISEÑO DE BASES DE DATOS I (*)", 5, 2));
        subjectRepository.save(new Subject(13L, "2022", "MATEMATICA DISCRETA Y DISEÑO LOGICO (*)", 3, 2));
        subjectRepository.save(new Subject(14L, "2032", "REDES DE DATOS", 6, 2));
        subjectRepository.save(new Subject(15L, "2027", "ALGEBRA LINEAL", 3, 2));
        subjectRepository.save(new Subject(16L, "2024", "ESTADISTICA APLICADA I (*)", 4, 2));
        subjectRepository.save(new Subject(17L, "2020", "FISICA", 5, 2));
        subjectRepository.save(new Subject(18L, "2026", "SISTEMAS OPERATIVOS", 4, 2));
        subjectRepository.save(new Subject(19L, "2028", "COMPUTACION (*)", 6, 2));
        subjectRepository.save(new Subject(20L, "2030", "ANALISIS NUMERICO", 5, 3));
        subjectRepository.save(new Subject(21L, "2035", "AUTOMATAS Y GRAMATICAS", 3, 3));
        subjectRepository.save(new Subject(22L, "2033", "ESTADISTICA APLICADA II", 3, 3));
        subjectRepository.save(new Subject(23L, "2043", "ORGANIZACION", 3, 3));
        subjectRepository.save(new Subject(24L, "2054", "DISEÑO DE BASES DE DATOS II", 5, 3));
        subjectRepository.save(new Subject(25L, "2046", "ECONOMIA", 3, 3));
        subjectRepository.save(new Subject(26L, "2036", "DISEÑO DE SISTEMAS", 5, 3));
        subjectRepository.save(new Subject(27L, "2037", "COMUNICACION DE DATOS", 3, 3));
        subjectRepository.save(new Subject(28L, "2038", "PROGRAMACION", 4, 3));
        subjectRepository.save(new Subject(29L, "2034", "COMPUTACION II", 6, 3));
        subjectRepository.save(new Subject(30L, "2041", "INGENIERIA DE SOFTWARE", 5, 4));
        subjectRepository.save(new Subject(31L, "2044", "SEGURIDAD INFORMATICA I", 5, 4));
        subjectRepository.save(new Subject(32L, "2042", "TELEINFORMATICA", 5, 4));
        subjectRepository.save(new Subject(33L, "2404", "TRABAJO INTEGRADOR FINAL 1", 3, 4));
        subjectRepository.save(new Subject(34L, "2048", "FORMULACION Y EVALUACION DE PROYECTOS", 4, 4));
        subjectRepository.save(new Subject(35L, "2080", "INGENIERIA DE SOFTWARE APLICADA", 4, 4));
        subjectRepository.save(new Subject(36L, "2055", "INTELIGENCIA ARTIFICIAL", 4, 4));
        subjectRepository.save(new Subject(37L, "2047", "INVESTIGACION OPERATIVA", 4, 4));
        subjectRepository.save(new Subject(38L, "2409", "TRABAJO INTEGRADOR FINAL 2", 3, 4));
        subjectRepository.save(new Subject(39L, "2049", "PROGRAMACION II", 6, 4));
        subjectRepository.save(new Subject(40L, "2501", "ASEGURAMIENTO DE CALIDAD DEL SOFTWARE", 4, 5));
        subjectRepository.save(new Subject(41L, "2502", "GESTION DE CALIDAD Y MEDIO AMBIENTE", 4, 5));
        subjectRepository.save(new Subject(42L, "2503", "HIGIENE Y SEGURIDAD DEL TRABAJO", 3, 5));
        subjectRepository.save(new Subject(43L, "2504", "MODELOS Y SIMULACION", 3, 5));
        subjectRepository.save(new Subject(44L, "2505", "TRABAJO INTEGRADOR FINAL 3", 3, 5));
        subjectRepository.save(new Subject(45L, "2506", "SEGURIDAD INFORMATICA II", 4, 5));
        subjectRepository.save(new Subject(46L, "2507", "INGENIERIA DERECHO Y ETICA PROFESIONAL", 3, 5));
        subjectRepository.save(new Subject(47L, "2508", "AUDITORIA DE SISTEMAS", 4, 5));
        subjectRepository.save(new Subject(48L, "2509", "PLANEAMIENTO Y GESTION DE EMPRESAS (*)", 5, 5));
        subjectRepository.save(new Subject(49L, "2510", "TRABAJO INTEGRADOR FINAL 4", 3, 5));
        subjectRepository.save(new Subject(50L, "2500", "PRACTICA PROFESIONAL", 0, 5));


 */