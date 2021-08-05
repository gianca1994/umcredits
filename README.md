# umcredits
```
Lenguaje: Java
Framework: Spring Boot
Gestion y construccion del proyecto: Maven
Base de datos: Postgres
```

## Lista de patrones utilizados:

1. **MVC**: Permite separar una aplicación en 3 capas, una forma de organizar y de hacer escalable un proyecto.
2. **Observer**: Cuando el estado de un objeto cambia, todos los objetos que dependen de él son notificados y actualizados automáticamente.
3. **Singleton**: Una clase tiene que tener solo una instancia y se proporciona un punto de acceso global a ella.
4. **Factory**: Al momento de crear los logs (duda).
5. **Decorador**: 
    - Service
    - Repository


## Principios SOLID:
> S: Single Responsability Principle (SRP): se basa en que cada clase o método sólo debe hacer una cosa.
>  
> O: Open / Closed Principle: Una clase/método debe estar abierto a extensiones pero cerrado a modificaciones.
>  
> L: Liskov Substitution Principle: Cualquier subclase debería poder ser sustituible por la clase padre.
>  
> I: Interface Segregation Principle: Definir una serie de métodos abstractos a través de una serie de interfaces para que implementen nuestras clases.
>  
> D: Dependency Inversion Principle: Que una clase pueda funcionar por sí sola sin depender de otra.
